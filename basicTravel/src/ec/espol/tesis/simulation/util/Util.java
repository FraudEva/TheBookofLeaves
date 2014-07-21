/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.util;

import ec.espol.tesis.simulation.entities.Broker;
import ec.espol.tesis.simulation.entities.MyUserJ;
import ec.espol.tesis.simulation.entities.Provider;
import ec.espol.tesis.simulation.entities.SLA;
import ec.espol.tesis.simulation.entities.User;
import ec.espol.tesis.simulation.market.MarketMechanism;
import ec.espol.tesis.simulation.market.PostedOffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Log;

/**
 *
 * @author Usuario
 */
public class Util {
    
    public static void printTittle(){
         Util.printMessage("\t\t\t===================================== \n\t\t\t========== TheBookofLeaves ========== \n\t\t\t===================================== \n ");
    }
    public static void printMarketMechanismName(MarketMechanism m){
        /*if(m instanceof PostedOffer){
            printMessage("Using PostedOffer");
        }*/
        printMessage("Using MarketMechanism :" + m.getClass().getName()+"\n");
    }
    
    public static void printMessage(String mensaje){
        Log.printLine(mensaje);
    }
    
    public static void printProviderList(ArrayList<Provider> providerList){
        Log.printLine("Provider list");
        String indent = "\t";
        Log.printLine("ID" + indent +  
                      "Name" + indent + indent +
                      "Preferred Profit" + indent +
                      "Minimun Profit"+ indent + indent +
                      "Mips Per Core(Host-VM)"  );
        for(Provider p: providerList){
            Log.printLine(p.getId() + indent +  
                      p.getName() + indent + indent +
                      p.getPreferredProfit() + indent + indent +
                      p.getMinimunProfit()+ indent + indent + indent +
                      p.getMipsPerCore());
        }
        Log.printLine();
    }
    
    public static void printUserList(ArrayList<MyUserJ> userList){
        Log.printLine("User requeriments list");
        String indent = "\t";
        Log.printLine("User ID" + indent + 
                      "Scheduled Time" + indent +
                      "Mips Per Vm" + indent +
                      "Mips Per Cloudlet" + indent +
                      "Shift" + indent + indent +
                      "Maximum Profit");
        
        for(MyUserJ u: userList){
            for(int i = 0; i < u.getNumberOfRequeriments() ; i++){
                Log.printLine(u.getId() + indent +
                u.getService().getScheduledTime() + indent +  indent +  indent +
                u.getService().getMipsPerVm() + indent + indent +
                u.getService().getMiPerCloudlet() + indent + indent +
                u.getService().getShift() + indent + indent + indent +
                u.getService().getMaximumProfit());
            }
        }
        Log.printLine();
    }
    
    public static void printCloudletList(List<Cloudlet> list, List<Broker> buyerAgents)
    {
        int size = list.size();        
        Cloudlet job;

        String indent = "\t";//"    ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
                        "Data center ID" + indent + "VM ID" + indent +
                        "Time" + indent +
                        "Start Time" + indent + "Finish Time"+ indent +
                        "# of Tries" + indent + "User ID" );
        /*Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
                        "Data center ID" + indent + "VM ID" + indent + indent +
                        "Time" + indent +
                        "Start Time" + indent + "Finish Time"+ indent +
                        "# of Tries" + indent + indent + "User ID" );*/

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++)
        {
            job = list.get(i);
            Log.print(job.getCloudletId() + indent);
            /*Log.print(indent + job.getCloudletId() + indent + indent);*/

            //if (job.getCloudletStatus() == Cloudlet.SUCCESS || job.getCloudletStatus() == Cloudlet.CREATED)
            //{
            Log.print(job.getCloudletStatusString());
            //Log.print("SUCCESS");
            int tries = 0;
            for(Broker buyerAgent : buyerAgents)
            {
                if(buyerAgent.getId() == job.getUserId())
                {
                    tries = buyerAgent.getNumberOfTries();
                    break;
                }
            }

            /*Log.printLine( indent + indent +indent +indent + indent + job.getResourceId() + indent + indent + indent + job.getVmId() +
                            indent + indent + indent + dft.format(job.getActualCPUTime()) +
                            indent + indent + dft.format(job.getExecStartTime())+ indent + indent + indent + dft.format(job.getFinishTime()) +
                            indent + indent + indent + indent + tries +
                            indent + indent + indent + indent + job.getUserId());*/

            Log.printLine( indent + job.getResourceId() + indent +  job.getVmId() +
                            indent + dft.format(job.getActualCPUTime()) +
                            indent +  dft.format(job.getExecStartTime())+ indent +  dft.format(job.getFinishTime()) +
                            indent +  tries +
                            indent +  job.getUserId());
            //}
        }
    }
    
    /**
     * Prints the SellerAgent's information after the simulation
     * @param sellerAgents
     */
    public static void printSellerAgentInfo(List<Provider> sellerAgents)
    {
        for (Provider sellerAgent : sellerAgents)
        {
            Log.printLine(sellerAgent.getName() +" reputation is "+sellerAgent.getReputation());
            sellerAgent.printDebts();
        }
    }
    
    public static void printServiceTittle(){
        Log.printLine("Searching best provider for  service requerid");
        String indent = "\t";
        Log.printLine("Mips" + indent +  
                      "Reference Price" + indent + 
                      "Minimum Reputation Required" + indent +
                      "SLA");
    }
    
    public static void printServiceInfo(long mips, double referencePrice, Integer minimumReputationRequired, SLA sla){
        String indent = "\t";
        Log.printLine(mips + indent +  
                      referencePrice+ indent + indent +
                      minimumReputationRequired + indent + indent + indent + indent +
                      sla.toString());
    }
    
    public static void printBidTittle(){
        Log.printLine("Provider bids");
        String indent = "\t";
        Log.printLine("Provider" + indent +  
                      "Bid" + indent + indent + indent +
                      "Pondered Bid" + indent + indent + indent +
                      "Reputacion");
    }
    
    public static void printBidInfo(Provider p, double bid, double ponderedBid, double reputation){
        String indent = "\t";
        Log.printLine(p.getName() + indent +  
                      bid + indent + indent + indent +
                      ponderedBid +  indent + indent + indent +
                      reputation);
    }
    
    public static void printBestProvider(Provider p){
        Log.printLine("Best Provider :" + p.getName());
    }
}

