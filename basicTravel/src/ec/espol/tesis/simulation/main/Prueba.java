/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.main;

import ec.espol.tesis.simulation.entities.Provider;
import ec.espol.tesis.simulation.util.UtilGenerate;
import ec.espol.tesis.simulation.util.UtilPrint;
import ec.espol.tesis.simulation.util.UtilRandom;
import java.util.Calendar;
import org.cloudbus.cloudsim.core.CloudSim;

/**
 *
 * @author José Luis
 */
public class Prueba {
    public static void main(String[] args){
        double num = UtilRandom.getRandomDoubleNumber(0.0,1,2);
        UtilPrint.printMessage(num+"");
        UtilPrint.printMessage(UtilRandom.getRandomDoubleNumber(0.3,0.5,2)+"");
        UtilPrint.printMessage(UtilRandom.getRandomIntNumber(200,400)+"");
        
       // First step: Initialize the CloudSim package. It should be called
       // before creating any entities.
       int num_user = 1; // number of cloud users
       Calendar calendar = Calendar.getInstance();
       boolean trace_flag = false; // mean trace events

       // Initialize the CloudSim library
       CloudSim.init(num_user, calendar, trace_flag);
       Provider p = UtilGenerate.generateProvider(1);
       UtilPrint.printMessage(p.getName());
    }
}
