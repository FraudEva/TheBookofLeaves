/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.entities;

import java.util.List;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;

/**
 *
 * @author Usuario
 */
public class ResourceHost extends Host {
    
    public static final double MINIMUM_PROBABILITY = 0;
    public static final double DEFAULT_PROBABILITY = 0.5;
    public static final double MAXIMUM_PROBABILITY = 1;
    public static final double LOWER_BOUNDARY_TO_SUCCEED = 0.7;
    private Integer CPUCapacity;
    private Integer memoryAvailable;
    private double probabilityToSucceed;
    
    public ResourceHost(int id, RamProvisioner ramProvisioner, BwProvisioner bwProvisioner, 
            long storage, List<? extends Pe> peList, VmScheduler vmScheduler) {
        super(id, ramProvisioner, bwProvisioner, storage, peList, vmScheduler);
        probabilityToSucceed = DEFAULT_PROBABILITY;
    }

    @Override
    public boolean vmCreate(Vm vm) {
        if(!setProbability(probabilityToSucceed)){
            Log.printLine(CloudSim.clock()+": Allocation of VM # "+vm.getId()+" to RecurseHost # "+getId()+" failed by Availability in " + getDatacenter().getName()+"\n");
            //Disminuir reputación
            return false;
        }
        
        if(!getRamProvisioner().allocateRamForVm(vm,vm.getCurrentRequestedRam())){
            Log.printLine("Allocation of VM #"+vm.getId()+"to RecursoHost # "+getId()+"failed by Memory - RAM \n" );
            return false;
        }
        
        if(!getBwProvisioner().allocateBwForVm(vm,vm.getCurrentRequestedBw())){
            Log.printLine("Allocation of VM #"+vm.getId()+"to RecursoHost # " + getId() + "failed by Bandwith - BW \n" );
            return false;
        }
        
        if(!getVmScheduler().allocatePesForVm(vm, vm.getCurrentRequestedMips())){
            Log.printLine("[VmScheduler.vmCreate] Allocation of VM #"+vm.getId()+" to Host #"+ getId() + " failed by MIPS \n");
            getRamProvisioner().deallocateRamForVm(vm);
            getBwProvisioner().deallocateBwForVm(vm);
            return false;
        }
        getVmList().add(vm);
        vm.setHost(this);
        //((ProveedorDeServicio)this.getDatacenter()).incrementarReputacion();
        return true;
    }

    public Integer getCPUCapacity() {
        return CPUCapacity;
    }

    public void setCPUCapacity(Integer CPUCapacity) {
        this.CPUCapacity = CPUCapacity;
    }

    public Integer getMemoryAvailable() {
        return memoryAvailable;
    }

    public void setMemoryAvailable(Integer memoryAvailable) {
        this.memoryAvailable = memoryAvailable;
    }

    public double getProbabilityToSucceed() {
        return probabilityToSucceed;
    }

    public void setProbabilityToSucceed(double probabilityToSucceed) {
        this.probabilityToSucceed = probabilityToSucceed;
    }
   
    protected boolean setProbability( double p){
        return true;
         //return (Math.random() < p);
    }
    
}
