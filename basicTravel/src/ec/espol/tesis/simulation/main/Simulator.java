/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.main;

import ec.espol.tesis.simulation.entities.*;
import ec.espol.tesis.simulation.market.*;
import ec.espol.tesis.simulation.util.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.core.CloudSim;

/**
 *
 * @author User
 */
public class Simulator {
    public static Market market;
    List<Broker> listaAgenteBroker;
    public static void main(String[] args){
        Util.printMessage("Empezando la simulación");
        try 
        { 
            
            // First step: Initialize the CloudSim package. It should be called
            // before creating any entities.
            int num_user = 1; // number of cloud users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false; // mean trace events

            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);
            
            market = new Market();
            MarketMechanism mecanismo = new PostedOffer();
            market.setMecanismo(mecanismo);
            
            // Second step: Create Datacenters (Provider)
            // Datacenters are the resource providers in CloudSim. We need at
            // list one of them to run a CloudSim simulation
            createProviders();
                 
            // Third step: Create Broker
            //Fourth step: Create VMs and Cloudlets and send them to broker
            createUsers();
            
            // Fifth step: Starts the simulation
            CloudSim.startSimulation();

            // Final step: Print results when simulation is over
            List<Cloudlet> listaCloudletRecibidos = new ArrayList<Cloudlet>();
            List<Cloudlet> listaCloudlet = new ArrayList<Cloudlet>();
            
            CloudSim.stopSimulation();
            
           /* for(Broker agenteBroker : listaAgenteBroker){
                listaCloudletRecibidos.addAll(agenteBroker.getCloudletReceivedList());
                listaCloudlet.addAll(agenteBroker.getCloudletList());
            }
            Util.printCloudletList(listaCloudletRecibidos,listaAgenteBroker);
            if(listaCloudlet.size()>0)
            {
                Util.printCloudletList(listaCloudlet, listaAgenteBroker);  
            }*/
            //Print the debt of each user to each datacenter
            Util.printSellerAgentInfo(Broker.getProviderList());/*allSellerAgents*/;
            
         } catch (Exception ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void createProviders() throws Exception{
        /*String nombre,
            Double gananciaPreferida,
            Double ganaciaMinima,
            Double probabilidadDeEvento,
            Integer mipsPorNucleo,
            Integer reputacionInicial*/
            Provider proveedorA = Provider.createProvider("Proveedor_A",
                                                            0.36, 
                                                            0.30,
                                                            0.98,
                                                            250,
                                                            Provider.MEDIUM_REPUTATION);
            Provider proveedorB = Provider.createProvider("Proveedor_B",
                                                            0.35, 
                                                            0.31,
                                                            0.80,
                                                            250,
                                                            Provider.MEDIUM_REPUTATION);
            Provider proveedorC = Provider.createProvider("Proveedor_C",
                                                            0.34, 
                                                            0.30,
                                                            0.68,
                                                            250,
                                                            Provider.MEDIUM_REPUTATION);
            Provider proveedorD = Provider.createProvider("Proveedor_D",
                                                            0.35, 
                                                            0.20,
                                                            0.95,
                                                            250,
                                                            Provider.MEDIUM_REPUTATION);
            Broker.addProviderToList(proveedorA);
            Broker.addProviderToList(proveedorB);
            Broker.addProviderToList(proveedorC);
            Broker.addProviderToList(proveedorD);
    }
    
    private static void createUsers() throws Exception{
   
        /*Double scheduledTime,
        Integer mipsPerCloudlet,
        Integer mipsPerVm,
        Integer minimumReputation,
        Integer shift,
        Double maximumProfit*/
        
        User user1 = new User(1,3);
        user1.makeOrder(new Service(0.0,4000,250,1,0,0.4),new SLA());

        User user2 = new User(2,3);
        user2.makeOrder(new Service(20.0,4000,250,1,2,0.4),new SLA());

        User user3 = new User(3,3);
        user3.makeOrder( new Service(40.0,4000,250,1,4,0.4),new SLA());

        User user4 = new User(4,3);
        user4.makeOrder( new Service(60.0,4000,250,1,6,0.4),new SLA());
        
    }
}

