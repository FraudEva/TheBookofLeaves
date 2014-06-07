/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.util;

import ec.espol.tesis.simulation.entities.Broker;
import ec.espol.tesis.simulation.entities.Provider;
import java.text.DecimalFormat;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Log;

/**
 *
 * @author Usuario
 */
public class Util {
    public static void printMessage(String mensaje){
        Log.printLine(mensaje);
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
}

