/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.main;

import ec.espol.tesis.simulation.entities.*;
import ec.espol.tesis.simulation.market.*;
import ec.espol.tesis.simulation.util.UtilPrint;
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
    
    public static void main(String[] args){
        UtilPrint.printTittle();
        startSimulation();
    }
    
    private static void startSimulation(){
        try 
        {
            // First step: Initialize the CloudSim package. It should be called
            // before creating any entities.
            int num_user = 1; // number of cloud users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false; // mean trace events

            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);
            
            //Set Market Settings
            initMarket();
            
            // Second step: Create Datacenters (Provider)
            // Datacenters are the resource providers in CloudSim. We need at
            // list one of them to run a CloudSim simulation
            createProviders();
                 
            // Third step: Create Broker
            //Fourth step: Create VMs and Cloudlets and send them to broker
            createUsers();
            makeOrders();
            
            // Fifth step: Starts the simulation
            CloudSim.startSimulation();

            // Final step: Print results when simulation is over
            CloudSim.stopSimulation();
            
            printCloudletStatus();
            
          
            
         } catch (Exception ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void createProviders() throws Exception{
        /*  String nombre,
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
                                                            0.98,
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
            UtilPrint.printProviderList(Broker.getProviderList());
    }
    
    public static void init(){
        int userNumber = 4;
        int providerNumber = 4;
    }
    
    private static void createUsers(){
   
        /*Double scheduledTime,
        Integer mipsPerCloudlet,
        Integer mipsPerVm,
        Integer minimumReputation,
        Integer shift,
        Double maximumProfit*/
        
        User user1 = new User(1,2);
        user1.setService(new Service(0.0,4000,250,1,0,0.4));
        user1.setSla(new SLA());

        User user2 = new User(2,2);
        user2.setService(new Service(20.0,4000,250,1,2,0.4));
        user2.setSla(new SLA());
        
        User user3 = new User(3,2);
        user3.setService(new Service(40.0,4000,250,1,4,0.4));
        user3.setSla(new SLA());
         
        User user4 = new User(4,2);
        user4.setService(new Service(60.0,4000,250,1,6,0.4));
        user4.setSla(new SLA());
        
        market.getUserList().add(user1);market.getUserList().add(user2);market.getUserList().add(user3);market.getUserList().add(user4);
        UtilPrint.printUserList(market.getUserList());
        
    }
    
    private static void makeOrders() throws Exception{
        UtilPrint.printMessage("Making order for requeriments...");
        for(User u: market.getUserList()){
            u.makeOrder(u.getService(), u.getSla());
        }
        UtilPrint.printMessage("VMs and Jobs submited\n");
    }
    
    private static void initMarket(){
        UtilPrint.printMessage("Setting Market ...");
        market = new Market();
        
        //MarketMechanism mecanismo = new ReverseAuction();
        MarketMechanism mecanismo = new PostedOffer();
        market.setMecanismo(mecanismo);
        UtilPrint.printMarketMechanismName(mecanismo);
        
        ReputationSystem rs = new ReputationSystem();
        market.setRs(rs);
    }
    
    private static void printCloudletStatus(){
        List<Cloudlet> listaCloudletRecibidos = new ArrayList<Cloudlet>();
        List<Cloudlet> listaCloudlet = new ArrayList<Cloudlet>();
        List<Broker> listaAgenteBroker = new ArrayList<Broker>();
        for(Broker agenteBroker : Simulator.market.getBrokerList()){
            listaCloudletRecibidos.addAll(agenteBroker.getCloudletReceivedList());
            listaCloudlet.addAll(agenteBroker.getCloudletList());
          }
        UtilPrint.printCloudletList(listaCloudletRecibidos,listaAgenteBroker);
        if(listaCloudlet.size()>0)
        {
            UtilPrint.printCloudletList(listaCloudlet, listaAgenteBroker);  
        }
        //Print the debt of each user to each datacenter
        UtilPrint.printSellerAgentInfo(Broker.getProviderList());/*allSellerAgents*/;
    }
}

