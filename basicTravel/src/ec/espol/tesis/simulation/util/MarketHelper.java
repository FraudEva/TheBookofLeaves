/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.util;

import ec.espol.tesis.simulation.entities.Job;
import java.util.LinkedList;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;

/**
 *
 * @author José Luis
 */
public class MarketHelper {

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
        //    private List<Cloudlet> createService(int userId, int cloudlets, int idShift, long length){
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

     public static List<Vm> createVM(int userId, int NumberOfVms, int idShift, int mips)
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
        Vm[] vm = new Vm[NumberOfVms];

        for(int i=0;i<NumberOfVms;i++)
        {
            Util.printMessage("Submmiting VM :\t\t#"+(idShift+i));
            vm[i] = new Vm(idShift + i, userId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
            list.add(vm[i]);
        }

        return list;
    }

    /**
     * Creates the Jobs or Cloudlets for this Iteration
     * @param userId
     * @param numberOfCloudlets
     * @param idShift
     * @param length
     * @return
     */
    public static List<Job> createCloudLet(int userId, int numberOfCloudlets, int idShift, long length)
    {
        // Creates a container to store Cloudlets
        LinkedList<Job> list = new LinkedList<Job>();
        //cloudlet parameters
        //long length = 40000;
        long fileSize = 300;
        long outputSize = 300;
        int pesNumber = 1;
        UtilizationModel utilizationModel = new UtilizationModelFull();
        Job[] job = new Job[numberOfCloudlets];
        for(int i=0;i<numberOfCloudlets;i++)
        {
            Util.printMessage("Submmiting Job :\t#"+(idShift+i));
            job[i] = new Job(idShift + i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            // setting the owner of these Cloudlets
            job[i].setUserId(userId); //job[i].setSla(sla);
            list.add(job[i]);
        }
        return list;
    }
    
    /***********************Ejemplo7-Cloudsim**************/
}

