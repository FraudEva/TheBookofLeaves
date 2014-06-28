/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.entities;

import ec.espol.tesis.simulation.util.MarketHelper;
import ec.espol.tesis.simulation.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Log;
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
    public static Double startTime = 0.0;
    public static ArrayList<Provider> providerList = new ArrayList<Provider>();
    private int countIds  = 0; 
    
    /*private Double maximumValueByMip;
    private Double startTime;
    private Integer minimumReputation;
    private SLA sla;
    private long totalMips;*/
    
    
    public Broker(String name) throws Exception {
        super(name);
        countIds = 0;
    }
    

    public Broker submitService(Service service, SLA sla, int id){
         List<Vm> vmList = MarketHelper.createVM(this.getId(),1,this.getCountIds(), service.getMipsPerVm());
         List<Job> jobList = MarketHelper.createCloudLet(this.getId(),1,this.getCountIds(),service.getMiPerCloudlet());
         this.submitVmList(vmList);
         this.submitCloudletList(jobList);
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
        Util.printMessage("=========> processingRequest <=========");
        DatacenterCharacteristics characteristics = (DatacenterCharacteristics) ev.getData();
        getDatacenterCharacteristicsList().put(characteristics.getId(), characteristics);
        /*if(getDatacenterCharacteristicsList().size()==getDatacenterIdsList().size()){
            setDatacenterRequestedIdsList(new ArrayList<Integer>());
            //Seleccionar el mejor proveedor
            Double preferredPriceToPay = Market.estimarCostoMI(totalMips)*(1+maximumValueByMip);
            Provider bestProvider = Simulator.market.getMecanismo().searchBestProvider(totalMips, preferredPriceToPay, minimumReputation, sla);
            if(bestProvider!=null)
            {
                createVmsInDatacenter(bestProvider.getId());
            }
        }*/
        if (getDatacenterCharacteristicsList().size() == getDatacenterIdsList().size()) {
            setDatacenterRequestedIdsList(new ArrayList<Integer>());
            createVmsInDatacenter(getDatacenterIdsList().get(0));
        }
    }

    @Override
    protected void processVmCreate(SimEvent ev) {
        //super.processVmCreate(ev); //To change body of generated methods, choose Tools | Templates.
        Util.printMessage("=========> processVmCreate <=========");
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
                /*//Seleccionar el mejor proveedor
                Double preferredPriceToPay = Market.estimarCostoMI(totalMips)*(maximumValueByMip);
                Provider bestProvider = Simulator.market.getMecanismo().searchBestProvider(totalMips, preferredPriceToPay, minimumReputation, sla);
                if(bestProvider!=null){
                    createVmsInDatacenter(bestProvider.getId());
                    return;
                }
                if(getVmsCreatedList().size() > 0 ){
                    submitCloudlets();
                }else{
                    Util.printMessage(CloudSim.clock() + ": " + getName() + ": none of the required VMs could be created.");
                    finishExecution();
                }*/
                // find id of the next datacenter that has not been tried
                for (int nextDatacenterId : getDatacenterIdsList()) {
                        if (!getDatacenterRequestedIdsList().contains(nextDatacenterId)) {
                                createVmsInDatacenter(nextDatacenterId);
                                return;
                        }
                }

                // all datacenters already queried
                if (getVmsCreatedList().size() > 0) { // if some vm were created
                        submitCloudlets();
                } else { // no vms created. abort
                        Log.printLine(CloudSim.clock() + ": " + getName()
                                        + ": none of the required VMs could be created. Aborting");
                        finishExecution();
                }
            }
        }
    }
    
    @Override
    protected void finishExecution()
    {
        sendNow(getId(), CloudSimTags.END_OF_SIMULATION);
    }
    
    /*@Override
    public void submitCloudletList(List<? extends Cloudlet> list){
        getCloudletList().addAll(list);
        for ( Cloudlet c : list){
            totalMips += c.getCloudletLength();
        }
    }*/

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
    
    public static void addProviderToList(Provider provider){
        providerList.add(provider);
    }

    public static ArrayList<Provider> getProviderList() {
        return providerList;
    }

    public static void setProviderList(ArrayList<Provider> providerList) {
        Broker.providerList = providerList;
    }

    public int getCountIds() {
        return countIds;
    }

    public void setCountIds(int countIds) {
        this.countIds = countIds;
    }
    
    
    
    
    
}
