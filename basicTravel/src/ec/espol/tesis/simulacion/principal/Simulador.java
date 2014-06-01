/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.principal;

import ec.espol.tesis.simulacion.entidades.Usuario;
import ec.espol.tesis.simulacion.entidades.AgenteBroker;
import ec.espol.tesis.simulacion.entidades.ProveedorDeServicio;
import ec.espol.tesis.simulacion.entidades.SLA;
import ec.espol.tesis.simulacion.mercado.Subasta;
import ec.espol.tesis.simulacion.mercado.MecanismoMercado;
import ec.espol.tesis.simulacion.mercado.OfertaPublicada;
import ec.espol.tesis.simulacion.util.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;

/**
 *
 * @author Usuario
 */
public class Simulador {
    public static void main(String[] args){
        Util.imprimirMensaje("Empezando la simulación");
        try 
        {
            // First step: Initialize the CloudSim package. It should be called
            // before creating any entities.
            int num_user = 1; // number of cloud users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false; // mean trace events

            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);

            // Second step: Create Datacenters (ProveedorDeServicio)
            // Datacenters are the resource providers in CloudSim. We need at
            // list one of them to run a CloudSim simulation
            ProveedorDeServicio proveedorA = ProveedorDeServicio.createProveedorServicio("Proveedor_A",
                                                                                        0.36, 
                                                                                        0.30,
                                                                                        0.98,
                                                                                        250,
                                                                                        ProveedorDeServicio.REPUTACION_MEDIA);
            ProveedorDeServicio proveedorB = ProveedorDeServicio.createProveedorServicio("Proveedor_B",
                                                                                        0.35, 
                                                                                        0.31,
                                                                                        0.80,
                                                                                        250,
                                                                                        ProveedorDeServicio.REPUTACION_MEDIA);
            ProveedorDeServicio proveedorC = ProveedorDeServicio.createProveedorServicio("Proveedor_C",
                                                                                        0.34, 
                                                                                        0.30,
                                                                                        0.68,
                                                                                        250,
                                                                                        ProveedorDeServicio.REPUTACION_MEDIA);
            ProveedorDeServicio proveedorD = ProveedorDeServicio.createProveedorServicio("Proveedor_D",
                                                                                        0.35, 
                                                                                        0.20,
                                                                                        0.95,
                                                                                        250,
                                                                                        ProveedorDeServicio.REPUTACION_MEDIA);
            Subasta mecanismo = new OfertaPublicada();
            MecanismoMercado.inicializarMecanismoSubasta(mecanismo);
            MecanismoMercado.agregarProveedorServicio(proveedorA);
            MecanismoMercado.agregarProveedorServicio(proveedorB);
            MecanismoMercado.agregarProveedorServicio(proveedorC);
            MecanismoMercado.agregarProveedorServicio(proveedorD);
            
//            Integer id, 
//            Integer numeroDeUsuarios, 
//            Double tiempoProgrado, 
//            Integer miPorCloudlet, 
//            Integer mipsPorMaquinaVirtual, 
//            Integer reputacionMinima, 
//            Integer turno, 
//            Double MaximaGanancia, 
//            SLA sla
                 
            // Third step: Create Broker
            //Fourth step: Create VMs and Cloudlets and send them to broker
            Usuario helper1 = new Usuario(1,2,0.0,4000,250,1,0,0.4,new SLA());
            helper1.inicializarAgentesBrokers();
            
            Usuario helper2 = new Usuario(2,2,20.0,4000,250,1,2,0.4,new SLA());
            helper2.inicializarAgentesBrokers();
            
            Usuario helper3 = new Usuario(3,2,40.0,4000,250,1,4,0.4,new SLA());
            helper3.inicializarAgentesBrokers();
            
            Usuario helper4 = new Usuario(4,2,60.0,4000,250,1,6,0.4,new SLA());
            helper4.inicializarAgentesBrokers();
            
            List<AgenteBroker> listaAgenteBroker = new ArrayList<AgenteBroker>();
            listaAgenteBroker.addAll(helper1.getListaAgentesBrokers());
            listaAgenteBroker.addAll(helper2.getListaAgentesBrokers());
            listaAgenteBroker.addAll(helper3.getListaAgentesBrokers());
            listaAgenteBroker.addAll(helper4.getListaAgentesBrokers());
            
            // Fifth step: Starts the simulation
            CloudSim.startSimulation();

            // Final step: Print results when simulation is over
            List<Cloudlet> listaCloudletRecibidos = new ArrayList<Cloudlet>();
            List<Cloudlet> listaCloudlet = new ArrayList<Cloudlet>();
            
            CloudSim.stopSimulation();
            
            for(AgenteBroker agenteBroker: listaAgenteBroker ){
                listaCloudletRecibidos.addAll(agenteBroker.getCloudletReceivedList());
                listaCloudlet.addAll(agenteBroker.getCloudletList());
            }
            Util.printCloudletList(listaCloudletRecibidos,listaAgenteBroker);
            if(listaCloudlet.size()>0)
            {
                Util.printCloudletList(listaCloudlet, listaAgenteBroker);  
            }
            //Print the debt of each user to each datacenter
            Util.printSellerAgentInfo(MecanismoMercado.getListaProveedores());/*allSellerAgents*/;
         } catch (Exception ex) {
            Logger.getLogger(Simulador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
