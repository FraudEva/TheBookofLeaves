/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.entidades;

import ec.espol.tesis.simulacion.entidades.AgenteBroker;
import ec.espol.tesis.simulacion.entidades.SLA;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;

/**
 *
 * @author Usuario
 */
public class Usuario {
    private Integer     id;
    private Integer     numeroDeUsuarios;
    private Double      tiempoProgrado;
    private Integer     miPorCloudlet;
    private Integer     mipsPorMaquinaVirtual;
    private Integer     reputacionMinima;
    private Integer     turno;
    private Double      MaximaGanancia;
    private SLA         sla;
    private List<AgenteBroker> listaAgentesBrokers;

    public Usuario(Integer id, 
                    Integer numeroDeUsuarios, 
                    Double tiempoProgrado, 
                    Integer miPorCloudlet, 
                    Integer mipsPorMaquinaVirtual, 
                    Integer reputacionMinima, 
                    Integer turno, 
                    Double MaximaGanancia, 
                    SLA sla) {
        this.id = id;
        this.numeroDeUsuarios = numeroDeUsuarios;
        this.tiempoProgrado = tiempoProgrado;
        this.miPorCloudlet = miPorCloudlet;
        this.mipsPorMaquinaVirtual = mipsPorMaquinaVirtual;
        this.reputacionMinima = reputacionMinima;
        this.turno = turno;
        this.MaximaGanancia = MaximaGanancia;
        this.sla = sla;
    }
    
    public void inicializarAgentesBrokers() throws Exception{
        listaAgentesBrokers = new ArrayList<AgenteBroker>();
        for( int i=0; i<numeroDeUsuarios ; i++){
            AgenteBroker agenteBroker = new AgenteBroker("AgenteBroker_"+id+"."+i, tiempoProgrado, sla, MaximaGanancia);
            List<Vm> listaMaquinasVirtuales = createVM(agenteBroker.getId(),1,turno+i, mipsPorMaquinaVirtual);
            List<Cloudlet> listaCloudlets = createCloudlet(agenteBroker.getId(),1,turno+i,miPorCloudlet);
            agenteBroker.submitVmList(listaMaquinasVirtuales);
            agenteBroker.submitCloudletList(listaCloudlets);
            listaAgentesBrokers.add(agenteBroker);
        }
    }
    
//     /***********************Ejemplo7-Cloudsim**************/
//    private  List<Vm> createVM(int userId, int vms, int idShift,int mips) {
//            //Creates a container to store VMs. This list is passed to the broker later
//            LinkedList<Vm> list = new LinkedList<Vm>();
//
//            //VM Parameters
//            long size = 10000; //image size (MB)
//            int ram = 512; //vm memory (MB)
//            //int mips = mips;
//            long bw = 1000;
//            int pesNumber = 1; //number of cpus
//            String vmm = "Xen"; //VMM name
//
//            //create VMs
//            Vm[] vm = new Vm[vms];
//
//            for(int i=0;i<vms;i++){
//                    vm[i] = new Vm(idShift + i, userId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
//                    list.add(vm[i]);
//            }
//
//            return list;
//    }
//    
//    private List<Cloudlet> createCloudlet(int userId, int cloudlets, int idShift, long length){
//		// Creates a container to store Cloudlets
//		LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();
//
//		//cloudlet parameters
//		//long length = 40000;
//		long fileSize = 300;
//		long outputSize = 300;
//		int pesNumber = 1;
//		UtilizationModel utilizationModel = new UtilizationModelFull();
//
//		Cloudlet[] cloudlet = new Cloudlet[cloudlets];
//
//		for(int i=0;i<cloudlets;i++){
//			cloudlet[i] = new Cloudlet(idShift + i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
//			// setting the owner of these Cloudlets
//			cloudlet[i].setUserId(userId);
//			list.add(cloudlet[i]);
//		}
//
//		return list;
//    }

     private List<Vm> createVM(int userId, int vms, int idShift, int mips)
    {
        //Creates a container to store VMs. This list is passed to the broker later
        LinkedList<Vm> list = new LinkedList<Vm>();

        //VM Parameters
        long size = 10000; //image size (MB)
        int ram = 512; //vm memory (MB)
        //int mips = 250;
        long bw = 1000;
        int pesNumber = 1; //number of cpus
        String vmm = "Xen"; //VMM name

        //create VMs
        Vm[] vm = new Vm[vms];

        for(int i=0;i<vms;i++)
        {
            vm[i] = new Vm(idShift + i, userId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
            list.add(vm[i]);
        }

        return list;
    }

    /**
     * Creates the Jobs or Cloudlets for this Iteration
     * @param userId
     * @param cloudlets
     * @param idShift
     * @param length
     * @return
     */
    private List<Cloudlet> createCloudlet(int userId, int cloudlets, int idShift, long length)
    {
        // Creates a container to store Cloudlets
        LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();
        //cloudlet parameters
        //long length = 40000;
        long fileSize = 300;
        long outputSize = 300;
        int pesNumber = 1;
        UtilizationModel utilizationModel = new UtilizationModelFull();
        Cloudlet[] cloudlet = new Cloudlet[cloudlets];
        for(int i=0;i<cloudlets;i++)
        {
            cloudlet[i] = new Cloudlet(idShift + i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            // setting the owner of these Cloudlets
            cloudlet[i].setUserId(userId); //job[i].setSla(sla);
            list.add(cloudlet[i]);
        }
        return list;
    }
    
    /***********************Ejemplo7-Cloudsim**************/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroDeUsuarios() {
        return numeroDeUsuarios;
    }

    public void setNumeroDeUsuarios(Integer numeroDeUsuarios) {
        this.numeroDeUsuarios = numeroDeUsuarios;
    }

    public Double getTiempoProgrado() {
        return tiempoProgrado;
    }

    public void setTiempoProgrado(Double tiempoProgrado) {
        this.tiempoProgrado = tiempoProgrado;
    }

    public Integer getMiPorCloudlet() {
        return miPorCloudlet;
    }

    public void setMiPorCloudlet(Integer miPorCloudlet) {
        this.miPorCloudlet = miPorCloudlet;
    }

    public Integer getMipsPorMaquinaVirtual() {
        return mipsPorMaquinaVirtual;
    }

    public void setMipsPorMaquinaVirtual(Integer mipsPorMaquinaVirtual) {
        this.mipsPorMaquinaVirtual = mipsPorMaquinaVirtual;
    }

    public Integer getReputacionMinima() {
        return reputacionMinima;
    }

    public void setReputacionMinima(Integer reputacionMinima) {
        this.reputacionMinima = reputacionMinima;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public Double getMaximaGanancia() {
        return MaximaGanancia;
    }

    public void setMaximaGanancia(Double MaximaGanancia) {
        this.MaximaGanancia = MaximaGanancia;
    }

    public SLA getSla() {
        return sla;
    }

    public void setSla(SLA sla) {
        this.sla = sla;
    }

    public List<AgenteBroker> getListaAgentesBrokers() {
        return listaAgentesBrokers;
    }

    public void setListaAgentesBrokers(List<AgenteBroker> listaAgentesBrokers) {
        this.listaAgentesBrokers = listaAgentesBrokers;
    }

    
    
    
}


