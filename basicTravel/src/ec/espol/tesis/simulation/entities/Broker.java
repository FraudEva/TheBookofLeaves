/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.entities;

import ec.espol.tesis.simulation.market.MarketMechanism;
import ec.espol.tesis.simulation.market.Market;
import ec.espol.tesis.simulation.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.lists.VmList;

/**
 *
 * @author Usuario
 */
public class Broker extends DatacenterBroker {

    private Double maximumValueByMip;
    private Double startTime;
    private Integer minimumReputation;
    private SLA sla;
    private long totalMips;
    
    
    public Broker(String name, Double startTime, SLA sla, Double maximumValueByMip) throws Exception {
        super(name);
        this.startTime = startTime;
        this.minimumReputation = 1;
        this.sla = sla; 
        this.totalMips = 0;
        this.maximumValueByMip = maximumValueByMip;
    }

    public Broker submitService(Service service, SLA sla, int id){
         List<Vm> listaMaquinasVirtuales = MarketHelper.createVM(this.getId(),1,service.getShift()+id, service.getMipsPerVm());
         List<Cloudlet> listaCloudlets = MarketHelper.createCloudLet(this.getId(),1,service.getShift()+id,service.getMiPerCloudlet());
         this.submitVmList(listaMaquinasVirtuales);
         this.submitCloudletList(listaCloudlets);
         return this;
    }
    
    @Override
    public void startEntity() {
        //super.startEntity(); //To change body of generated methods, choose Tools | Templates.
        Util.printMessage("Entity: "+ getName() +" starting...");
        schedule(getId(),this.startTime,CloudSimTags.RESOURCE_CHARACTERISTICS_REQUEST);
    }    
    
    @Override
    protected void processResourceCharacteristics(SimEvent ev) {
        //super.processResourceCharacteristics(ev); //To change body of generated methods, choose Tools | Templates.
        DatacenterCharacteristics characteristics = (DatacenterCharacteristics) ev.getData();
        getDatacenterCharacteristicsList().put(characteristics.getId(), characteristics);
        if(getDatacenterCharacteristicsList().size()==getDatacenterIdsList().size()){
            setDatacenterRequestedIdsList(new ArrayList<Integer>());
            //Seleccionar el mejor proveedor
            Double preferredPriceToPay = Market.estimarCostoMI(totalMips)*(1+maximumValueByMip);
            Provider bestProvider = MarketMechanism.getSubasta().searchBestProvider(totalMips, preferredPriceToPay, minimumReputation, sla);
            if(bestProvider!=null)
            {
                createVmsInDatacenter(bestProvider.getId());
            }
        }
    }

    @Override
    protected void processVmCreate(SimEvent ev) {
        //super.processVmCreate(ev); //To change body of generated methods, choose Tools | Templates.
        int[] data = (int[])ev.getData();
        int dataCenterId = data[0];
        int vmId = data[1];
        int result = data[2];
        
        if(result == CloudSimTags.TRUE){
            getVmsToDatacentersMap().put(vmId,dataCenterId);
            getVmsCreatedList().add(VmList.getById(getVmList(), vmId));
            Util.printMessage(CloudSim.clock()+ ": " + getName() + " VM #" + vmId + "has been created in DataCenter #"+ dataCenterId + ", Host # "+VmList.getById(getVmsCreatedList(), vmId).getHost().getId());
        }else{
            Util.printMessage(CloudSim.clock()+ ": " + getName() + " VM #" + vmId + "failed in Datacenter # "+ dataCenterId);
        }       
        incrementVmsAcks();
        if(getVmsCreatedList().size() == getVmList().size() - getVmsDestroyed()){
            submitCloudlets();
        }else{
            if(getVmsRequested() == getVmsAcks()){
                //Seleccionar el mejor proveedor
                Double preferredPriceToPay = Market.estimarCostoMI(totalMips)*(maximumValueByMip);
                Provider bestProvider = MarketMechanism.getSubasta().searchBestProvider(totalMips, preferredPriceToPay, minimumReputation, sla);
                if(bestProvider!=null){
                    createVmsInDatacenter(bestProvider.getId());
                    return;
                }
            }
            if(getVmsCreatedList().size() > 0 ){
                submitCloudlets();
            }else{
                Util.printMessage(CloudSim.clock() + ": " + getName() + ": none of the required VMs could be created.");
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
    public void submitCloudletList(List<? extends Cloudlet> list){
        getCloudletList().addAll(list);
        for ( Cloudlet c : list){
            totalMips += c.getCloudletLength();
        }
    }

    @Override
    public void shutdownEntity() {
        //super.shutdownEntity(); //To change body of generated methods, choose Tools | Templates.
        Util.printMessage(getName() + " is shutting down...");
    }
    
    /**
     *
     * @return
     */
    public Integer getNumberOfTries()
    {
        return getDatacenterRequestedIdsList().size();
    }
    
}
