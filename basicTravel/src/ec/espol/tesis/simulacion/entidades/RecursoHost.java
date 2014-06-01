/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.entidades;

import java.util.List;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;

/**
 *
 * @author Usuario
 */
public class RecursoHost extends Host {
    
    public static final double PROBABILIDAD_MINIMA = 0;
    public static final double PROBABILIDAD_MEDIA = 0.5;
    public static final double PROBABILIDAD_MAXIMA = 1;
    public static final double LIMITE_MINIMO_PARA_EJECUTARSE = 0.7;
    private Integer capacidadCPU;
    private Integer memoriaDisponible;
    private double probabilidadDeEjecutarse;
    
    public RecursoHost(int id, RamProvisioner ramProvisioner, BwProvisioner bwProvisioner, 
            long storage, List<? extends Pe> peList, VmScheduler vmScheduler) {
        super(id, ramProvisioner, bwProvisioner, storage, peList, vmScheduler);
        probabilidadDeEjecutarse = PROBABILIDAD_MEDIA;
    }

    @Override
    public boolean vmCreate(Vm vm) {
        if(!calcularProbabilidad(probabilidadDeEjecutarse)){
            Log.printLine("Asignación de Maquina Virtual #"+ vm.getId() + "a RecursoHost #" + getId() + "ha fallado por Disponibilidad" );
            //Disminuir reputación
            //((ProveedorDeServicio)this.getDatacenter()).bajarReputacion();
            return false;
        }
        
        if(!getRamProvisioner().allocateRamForVm(vm,vm.getCurrentRequestedRam())){
            Log.printLine("Asignación de Maquina Virtual #"+ vm.getId() + "a RecursoHost #" + getId() + "ha fallado por Memoria - RAM" );
            return false;
        }
        
        if(!getBwProvisioner().allocateBwForVm(vm,vm.getCurrentRequestedBw())){
            Log.printLine("Asignación de Maquina Virtual #"+ vm.getId() + "a RecursoHost #" + getId() + "ha fallado por Ancho de banda - BW" );
            return false;
        }
        
        if(!getVmScheduler().allocatePesForVm(vm, vm.getCurrentRequestedMips())){
            Log.printLine("[VmScheduler].vmCreate Asignación de Maquina Virtual #" + vm.getId() + "a RecursoHost #"+ getId() + "ha fallado por MIPS");
            getRamProvisioner().deallocateRamForVm(vm);
            getBwProvisioner().deallocateBwForVm(vm);
            return false;
        }
        getVmList().add(vm);
        vm.setHost(this);
        //((ProveedorDeServicio)this.getDatacenter()).incrementarReputacion();
        return true;
    }

    public Integer getCapacidadCPU() {
        return capacidadCPU;
    }

    public void setCapacidadCPU(Integer capacidadCPU) {
        this.capacidadCPU = capacidadCPU;
    }

    public Integer getMemoriaDisponible() {
        return memoriaDisponible;
    }

    public void setMemoriaDisponible(Integer memoriaDisponible) {
        this.memoriaDisponible = memoriaDisponible;
    }

    public double getProbabilidadDeEjecutarse() {
        return probabilidadDeEjecutarse;
    }

    public void setProbabilidadDeEjecutarse(double probabilidadDeEjecutarse) {
        this.probabilidadDeEjecutarse = probabilidadDeEjecutarse;
    }
    
    protected boolean calcularProbabilidad( double p){
        return (Math.random() < p);
    }
    
}
