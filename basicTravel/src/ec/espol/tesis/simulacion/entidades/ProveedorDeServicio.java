/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase que permite definir el Proveedor en la nube
 */
package ec.espol.tesis.simulacion.entidades;

import ec.espol.tesis.simulacion.mercado.Mercado;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

/**
 *
 * @author Usuario
 */
public class ProveedorDeServicio extends Datacenter{
    public static final Integer REPUTACION_MAXIMA=100;
    public static final Integer REPUTACION_MINIMA=1;
    public static final Integer REPUTACION_MEDIA=50;
    private Double ganaciaPreferida;
    private Double gananciaMinima;
    private Integer serviciosExitosos;
    private Integer serviciosFallidos;
    private Integer reputacion;
    
    public ProveedorDeServicio(String name,
            DatacenterCharacteristics characteristics,
            VmAllocationPolicy vmAllocationPolicy,
            List<Storage> storageList,
            double schedulingInterval,
            Double ganaciaPreferida,
            Double gananciaMinima,
            Integer reputacion) throws Exception {
            super(name, characteristics, vmAllocationPolicy, storageList, schedulingInterval);
            this.ganaciaPreferida = ganaciaPreferida;
            this.gananciaMinima = gananciaMinima;
            this.reputacion = reputacion;
            this.serviciosExitosos = 0;
            this.serviciosFallidos = 0;
 
    }
    
    public static ProveedorDeServicio crearProveedorServicio(String nombre,
    Double gananciaPreferida,
    Double ganaciaMinima,
    Double probabilidadDeEvento,
    Integer mipsPorNucleo,
    Integer reputacionInicial) throws Exception{ 
    
    // Here are the steps needed to create a PowerDatacenter:
    // 1. We need to create a list to store
    // our machine
    List<RecursoHost> listaRecursos = new ArrayList<RecursoHost>();
    
    // 2. A Machine contains one or more PEs or CPUs/Cores.
    // In this example, it will have only one core(4 para este ejemplo).
    List<Pe> listaPe = new ArrayList<Pe>();
    
    // 3. Create PEs and add these into a list.
    listaPe.add(new Pe(0, new PeProvisionerSimple(mipsPorNucleo)));
    listaPe.add(new Pe(1, new PeProvisionerSimple(mipsPorNucleo)));
    listaPe.add(new Pe(2, new PeProvisionerSimple(mipsPorNucleo)));
    listaPe.add(new Pe(3, new PeProvisionerSimple(mipsPorNucleo)));
    
    // 4. Create Host with its id and list of PEs and add them to the list
    // of machines
    int idHost = 0;
    int memoriaRam = /*2048*/16384;
    long almacenamiento = 1000000;
    int anchoBanda = 10000;

    RecursoHost recursoHost = new RecursoHost(idHost,
                                                new RamProvisionerSimple(memoriaRam),
                                                new BwProvisionerSimple(anchoBanda),
                                                almacenamiento,
                                                listaPe,
                                                new VmSchedulerTimeShared(listaPe)
    );// This is our machine
    recursoHost.setProbabilidadDeEjecutarse(probabilidadDeEvento); //Usado para la disponibilidad
    listaRecursos.add(recursoHost);

    // 5. Create a DatacenterCharacteristics object that stores the
    // properties of a data center: architecture, OS, list of
    // Machines, allocation policy: time- or space-shared, time zone
    // and its price (G$/Pe time unit).
        String arquitectura = "x86"; // system architecture
        String sistemaOperativo = "Linux";  // operating system
        String maquinaVirtual = "Xen";
        double zonaTiempo = 10.0; // time zone this resource located
        double costoMips = 3.0; // the cost of using processing in this resource
        double costoPorMemoria = 0.05; // the cost of using memory in this resource
        double costoPorAlmacenamiento = 0.05; // the cost of using storage in this
        double costoPorAnchoBanda = 0.1; // the cost of using bw in this resource

        LinkedList<Storage> listaAlmacenamiento = new LinkedList<Storage>();  // we are not adding SAN
                                                                                                            // devices by now
        DatacenterCharacteristics caracterizticas = new DatacenterCharacteristics(
                arquitectura, 
                sistemaOperativo,
                maquinaVirtual,
                listaRecursos,
                zonaTiempo,
                costoMips,
                costoPorMemoria,
                costoPorAlmacenamiento,
                costoPorAnchoBanda);    
    
    // 6. Finally, we need to create a PowerDatacenter object.
    ProveedorDeServicio proveedor = null;
    proveedor = new ProveedorDeServicio(nombre,
            caracterizticas,
            new VmAllocationPolicySimple(listaRecursos),
            listaAlmacenamiento,
            0,
            gananciaPreferida,
            ganaciaMinima,
            reputacionInicial);
    return proveedor;
    }
    
    private double calcularPrecioPreferido(long mi){
        return Mercado.estimarCostoMI(mi)*(1+ganaciaPreferida);
    }
    
    private double calcularPrecioMinimo(long mi){
        return Mercado.estimarCostoMI(mi)*(1+gananciaMinima);
    }
    
    public double realizarOferta(long mi, double precioReferencia){
        double precioOfertado = -1;
        double precioPreferido = calcularPrecioPreferido(mi);
        double precioMinimo = calcularPrecioMinimo(mi);
        if(precioPreferido < precioReferencia)
        {
            precioOfertado = precioPreferido;
        }else if(precioReferencia == precioMinimo){
            precioOfertado = precioMinimo;
        }else if (precioPreferido > precioReferencia && precioMinimo < precioReferencia ){
            double Min = precioMinimo;
            double Max = precioReferencia;
            precioOfertado = Min + Math.random()*(Max-Min);
        }
        return precioOfertado;
    }

    public Double getGanaciaPreferida() {
        return ganaciaPreferida;
    }

    public void setGanaciaPreferida(Double ganaciaPreferida) {
        this.ganaciaPreferida = ganaciaPreferida;
    }

    public Double getGananciaMinima() {
        return gananciaMinima;
    }

    public void setGananciaMinima(Double gananciaMinima) {
        this.gananciaMinima = gananciaMinima;
    }

    public Integer getServiciosExitosos() {
        return serviciosExitosos;
    }

    public void setServiciosExitosos(Integer serviciosExitosos) {
        this.serviciosExitosos = serviciosExitosos;
    }

    public Integer getServiciosFallidos() {
        return serviciosFallidos;
    }

    public void setServiciosFallidos(Integer serviciosFallidos) {
        this.serviciosFallidos = serviciosFallidos;
    }

    public Integer getReputacion() {
        return reputacion;
    }

    public void setReputacion(Integer reputacion) {
        this.reputacion = reputacion;
    }
    
    public int consultarServiciosAlojados(){
        return getDebts().keySet().size();
    }
    
    public Double consultarProbabilidadDeEjecutarse(){
        Double probabilidad = 0.0;
        List<RecursoHost> recursosHost = getCharacteristics().getHostList();
        if(recursosHost.size() > 0){
            probabilidad = recursosHost.get(0).getProbabilidadDeEjecutarse();
        }
        return probabilidad;
    }
    
    public Integer consultarMipsPorNucleo(){
        Integer mipspc = 0;
        List<RecursoHost> recursosHost = getCharacteristics().getHostList();
        if(recursosHost.size() > 0){
            mipspc = recursosHost.get(0).getPeList().get(0).getMips();
        }
        return mipspc;
    }
  
}

