/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.entidades;

import ec.espol.tesis.simulacion.mercado.MecanismoMercado;
import ec.espol.tesis.simulacion.mercado.Mercado;
import ec.espol.tesis.simulacion.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.lists.VmList;

/**
 *
 * @author Usuario
 */
public class AgenteBroker extends DatacenterBroker {

    private Double valorMaximoAPagarPorMip;
    private Double tiempoInicio;
    private Integer reputacionMinima;
    private SLA sla;
    private long totalMips;

    public AgenteBroker(String nombre, Double tiempoInicio, SLA sla, Double valorMaximoAPagarPorMip) throws Exception {
        super(nombre);
        this.tiempoInicio = tiempoInicio;
        this.reputacionMinima = 1;
        this.sla = sla; 
        this.totalMips = 0;
        this.valorMaximoAPagarPorMip = valorMaximoAPagarPorMip;
    }

    @Override
    public void startEntity() {
        //super.startEntity(); //To change body of generated methods, choose Tools | Templates.
        Util.imprimirMensaje("Entidad: "+ getName() +"iniciando...");
        schedule(getId(),this.tiempoInicio,CloudSimTags.RESOURCE_CHARACTERISTICS_REQUEST);
    }    
    
    @Override
    protected void processResourceCharacteristics(SimEvent ev) {
        //super.processResourceCharacteristics(ev); //To change body of generated methods, choose Tools | Templates.
        DatacenterCharacteristics caracterizticas = (DatacenterCharacteristics) ev.getData();
        getDatacenterCharacteristicsList().put(caracterizticas.getId(), caracterizticas);
        if(getDatacenterCharacteristicsList().size()==getDatacenterIdsList().size()){
            setDatacenterRequestedIdsList(new ArrayList<Integer>());
            //Seleccionar el mejor proveedor
            Double precioDispuestoAPagar = Mercado.estimarCostoMI(totalMips)*(1+valorMaximoAPagarPorMip);
            ProveedorDeServicio mejorProveedor = MecanismoMercado.getMecanismo().encontrarMejorProveedor(totalMips, precioDispuestoAPagar, reputacionMinima, sla);
            if(mejorProveedor!=null)
            {
                createVmsInDatacenter(mejorProveedor.getId());
            }
        }
    }

    @Override
    protected void processVmCreate(SimEvent ev) {
        //super.processVmCreate(ev); //To change body of generated methods, choose Tools | Templates.
        int[] datos = (int[])ev.getData();
        int idDataCenter = datos[0];
        int idMaquinaVirtual = datos[1];
        int resultado = datos[2];
        
        if(resultado == CloudSimTags.TRUE){
            getVmsToDatacentersMap().put(idMaquinaVirtual,idDataCenter);
            getVmsCreatedList().add(VmList.getById(getVmList(), idMaquinaVirtual));
            Util.imprimirMensaje(CloudSim.clock()+ ": " + getName() + " VM #" + idMaquinaVirtual + "ha sido creado en el datacenter # "+ idDataCenter + ", Host # "+VmList.getById(getVmsCreatedList(), idMaquinaVirtual).getHost().getId());
        }else{
            Util.imprimirMensaje(CloudSim.clock()+ ": " + getName() + " VM #" + idMaquinaVirtual + "ha fallado en el datacenter # "+ idDataCenter);
        }       
        incrementVmsAcks();
        if(getVmsCreatedList().size() == getVmList().size() - getVmsDestroyed()){
            submitCloudlets();
        }else{
            if(getVmsRequested() == getVmsAcks()){
                //Seleccionar el mejor proveedor
                Double precioDispuestoAPagar = Mercado.estimarCostoMI(totalMips)*(valorMaximoAPagarPorMip);
                ProveedorDeServicio mejorProveedor = MecanismoMercado.getMecanismo().encontrarMejorProveedor(totalMips, precioDispuestoAPagar, reputacionMinima, sla);
                if(mejorProveedor!=null){
                    createVmsInDatacenter(mejorProveedor.getId());
                    Util.imprimirMensaje("Mejor proveedor: "+mejorProveedor.getName());
                    return;
                }
            }
            if(getVmsCreatedList().size() > 0 ){
                submitCloudlets();
            }else{
                Util.imprimirMensaje(CloudSim.clock() + ": " + getName() + "No se pudieron crear las maquinas virtuales solicitdas");
                finishExecution();
            }
        }
    }
    
    @Override
    protected void finishExecution()
    {
        sendNow(getId(), CloudSimTags.END_OF_SIMULATION);
    }
    
    @Override
    public void submitCloudletList(List<? extends Cloudlet> lista){
        getCloudletList().addAll(lista);
        for ( Cloudlet c : lista){
            totalMips += c.getCloudletLength();
        }
    }

    @Override
    public void shutdownEntity() {
        //super.shutdownEntity(); //To change body of generated methods, choose Tools | Templates.
        Util.imprimirMensaje(getName() + "ha sido dado de baja");
    }
    
    /**
     *
     * @return
     */
    public Integer consultarNumeroDeIntentos()
    {
        return getDatacenterRequestedIdsList().size();
    }
    
}
