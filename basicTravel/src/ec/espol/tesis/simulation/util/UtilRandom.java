/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.util;

/**
 *
 * @author José Luis
 */
public class UtilRandom {
    public static double getRandomDoubleNumber(double min, double max, int decimalPlaces){
        double number =  (Math.random()*(max - min))+min;
        //UtilPrint.printMessage(number+"");
        String numberString =  String.format("%1." + decimalPlaces + "f",number).replace(",",".");
        //UtilPrint.printMessage(numberString+"");
        return Double.parseDouble(numberString);
    }
    
    public static int getRandomIntNumber(int min, int max){
        double number =  (Math.random()*(max - min))+min;
        return (int)number;
    }
}
