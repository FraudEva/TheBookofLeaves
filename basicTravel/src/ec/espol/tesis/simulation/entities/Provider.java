/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase que permite definir el Proveedor en la nube
 */
package ec.espol.tesis.simulation.entities;

import ec.espol.tesis.simulation.market.Market;
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
public class Provider extends Datacenter{
    public static final Integer MAXIMUM_REPUTATION=100;
    public static final Integer MINIMUM_REPUTATION=1;
    public static final Integer MEDIUM_REPUTATION=50;
    private Double preferredProfit; //ganancia preferida
    private Double minimunProfit; //gananciaMinima
    private Integer successfullServices; 
    private Integer failedServices;
    private Integer reputation;
    
    public Provider(String name,
            DatacenterCharacteristics characteristics,
            VmAllocationPolicy vmAllocationPolicy,
            List<Storage> storageList,
            double schedulingInterval,
            Double preferredProfit,
            Double minimunProfit,
            Integer reputation) throws Exception {
            super(name, characteristics, vmAllocationPolicy, storageList, schedulingInterval);
            this.preferredProfit = preferredProfit;
            this.minimunProfit = minimunProfit;
            this.reputation = reputation;
            this.successfullServices = 0;
            this.failedServices = 0;
    }
   
    public static Provider createProvider(String name,
            Double preferredProfit,
            Double minimunProfit,
            Double probabilyToSucceed,
            Integer mipsPerCore,
            Integer initialReputation) throws Exception{ 
    
            // Here are the steps needed to create a PowerDatacenter:
            // 1. We need to create a list to store
            // our machine
            List<ResourceHost> resourceList = new ArrayList<ResourceHost>();

            // 2. A Machine contains one or more PEs or CPUs/Cores.
            // In this example, it will have only one core(4 para este ejemplo).
            List<Pe> listaPe = new ArrayList<Pe>();

            // 3. Create PEs and add these into a list.
            listaPe.add(new Pe(0, new PeProvisionerSimple(mipsPerCore)));
            listaPe.add(new Pe(1, new PeProvisionerSimple(mipsPerCore)));
            listaPe.add(new Pe(2, new PeProvisionerSimple(mipsPerCore)));
            listaPe.add(new Pe(3, new PeProvisionerSimple(mipsPerCore)));

            // 4. Create Host with its id and list of PEs and add them to the list
            // of machines
            int idHost = 0;
            int ram = /*2048*/16384;
            long storage = 1000000;
            int bw = 10000;

            ResourceHost recursoHost = new ResourceHost(idHost,
                                                        new RamProvisionerSimple(ram),
                                                        new BwProvisionerSimple(bw),
                                                        storage,
                                                        listaPe,
                                                        new VmSchedulerTimeShared(listaPe));// This is our machine
            
            recursoHost.setProbability(probabilyToSucceed); //Usado para la disponibilidad
            resourceList.add(recursoHost);

            // 5. Create a DatacenterCharacteristics object that stores the
            // properties of a data center: architecture, OS, list of
            // Machines, allocation policy: time- or space-shared, time zone
            // and its price (G$/Pe time unit).
                String architecture = "x86"; // system architecture
                String oS = "Linux";  // operating system
                String virtualMachine = "Xen";
                double timeZone = 10.0; // time zone this resource located
                double costPerMips = 3.0; // the cost of using processing in this resource
                double costPerMemory = 0.05; // the cost of using memory in this resource
                double costPerStorage= 0.05; // the cost of using storage in this
                double costPerBandWidth = 0.1; // the cost of using bw in this resource

                LinkedList<Storage> storageList = new LinkedList<Storage>();  // we are not adding SAN
                                                                                                                    // devices by now
                DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                        architecture, 
                        oS,
                        virtualMachine,
                        resourceList,
                        timeZone,
                        costPerMips,
                        costPerMemory,
                        costPerStorage,
                        costPerBandWidth);    

            // 6. Finally, we need to create a PowerDatacenter object.
            Provider proveedor = new Provider(name,
                    characteristics,
                    new VmAllocationPolicySimple(resourceList),
                    storageList,
                    0,
                    preferredProfit,
                    minimunProfit,
                    initialReputation);
                    return proveedor;
    }
    
    private double calculatePreferredPrice(long mi){
        return Market.estimarCostoMI(mi)*(1+preferredProfit);
    }
    
    private double calculateMinimumPrice(long mi){
        return Market.estimarCostoMI(mi)*(1+minimunProfit);
    }
    
    public double makeOffer(long mi, double referencePrice){
        double offeredPrice = -1;
        double preferredPrice = calculatePreferredPrice(mi);
        double minimumPrice = calculateMinimumPrice(mi);
        if(preferredPrice < referencePrice)
        {
            offeredPrice = preferredPrice;
        }else if(referencePrice == minimumPrice){
            offeredPrice = minimumPrice;
        }else if (preferredPrice > referencePrice && minimumPrice < referencePrice ){
            double Min = minimumPrice;
            double Max = referencePrice;
            offeredPrice = Min + Math.random()*(Max-Min);
        }
        return offeredPrice;
    }

    public Double getPreferredProfit() {
        return preferredProfit;
    }

    public void setPreferredProfit(Double preferredProfit) {
        this.preferredProfit = preferredProfit;
    }

    public Double getMinimunProfit() {
        return minimunProfit;
    }

    public void setMinimunProfit(Double minimunProfit) {
        this.minimunProfit = minimunProfit;
    }

    public Integer getSuccessfullServices() {
        return successfullServices;
    }

    public void setSuccessfullServices(Integer successfullServices) {
        this.successfullServices = successfullServices;
    }

    public Integer getFailedServices() {
        return failedServices;
    }

    public void setFailedServices(Integer failedServices) {
        this.failedServices = failedServices;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }
    
    public int getNumberOfAllocatedJobs(){
        return getDebts().keySet().size();
    }
    
    public Double getProbabilityToSucceed(){
        Double probabilityToSuceed = 0.0;
        List<ResourceHost> resourcesHost = getCharacteristics().getHostList();
        if(resourcesHost.size() > 0){
            probabilityToSuceed = resourcesHost.get(0).getProbabilityToSucceed();
        }
        return probabilityToSuceed;
    }
    
    public Integer getMipsPerCore(){
        Integer mipspc = 0;
        List<ResourceHost> resourcesHost = getCharacteristics().getHostList();
        if(resourcesHost.size() > 0){
            mipspc = resourcesHost.get(0).getPeList().get(0).getMips();
        }
        return mipspc;
    }
  
}

