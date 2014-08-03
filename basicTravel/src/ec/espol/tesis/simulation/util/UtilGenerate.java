/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.util;

import ec.espol.tesis.simulation.entities.Provider;
import ec.espol.tesis.simulation.entities.Service;
import ec.espol.tesis.simulation.entities.User;
import ec.espol.tesis.simulation.market.Market;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.PortableInterceptor.USER_EXCEPTION;

/**
 *
 * @author José Luis
 */
public class UtilGenerate {
    public static int getMipsXJob(){
        return UtilRandom.getRandomIntNumber(Market.MIN_VALUE_MIPS,Market.MAX_VALUE_MIPS);
    }
    
    public static double getMinPriceToSell(){
        return UtilRandom.getRandomDoubleNumber(Market.MIN_VALUE_PRICE_TO_SELL,Market.MAX_VALUE_PRICE_TO_SELL,2);
    }
    
    public static double getMaxPriceToSell(double minPriceToSell){
        double maxPriceToSell = 0.0;
        do{
            maxPriceToSell = UtilRandom.getRandomDoubleNumber(Market.MIN_VALUE_PRICE_TO_SELL,Market.MAX_VALUE_PRICE_TO_SELL,2);
        }while(maxPriceToSell<minPriceToSell);
        return maxPriceToSell;
    }
    
    public static double getPreferredPriceToBuy(){
        return UtilRandom.getRandomDoubleNumber(Market.MIN_VALUE_PRICE_TO_BUY,Market.MAX_VALUE_PRICE_TO_BUY,2);
    }
    
    public static double getScheduledTime(){
        return UtilRandom.getRandomDoubleNumber(0,Market.MAX_VALUE_SCHEDULED_TIME,1);
    }
    
    public static int getMipsPerVm(){
        return UtilRandom.getRandomIntNumber(Market.MIN_VALUE_MIPS_PER_VM,Market.MAX_VALUE_MIPS_PER_VM);
    }
    
    public static int getNumberOfUsers(){
        return UtilRandom.getRandomIntNumber(1,Market.MAX_VALUE_NUMBER_USERS);
    }
    
    public static double getProbabilityToSuccess(){
        return UtilRandom.getRandomDoubleNumber(Market.MIN_VALUE_PROBABILITY_TO_SUCCESS,Market.MAX_VALUE_PROBABILITY_TO_SUCCESS,2);
    }
    
    public static Provider generateProvider(int id){
        /*String name,
            Double preferredProfit,
            Double minimunProfit,
            Double probabilyToSucceed,
            Integer mipsPerCore,
            Integer initialReputation*/
        double minPrice = getMinPriceToSell();
        double maxPrice = getMaxPriceToSell(minPrice);
        double probability = getProbabilityToSuccess();
        int mips = getMipsXJob();
        Provider p = null;
        try {
            p =  Provider.createProvider("Provider_"+id,maxPrice,minPrice,probability,mips,Provider.MEDIUM_REPUTATION);
        } catch (Exception ex) {
            Logger.getLogger(UtilGenerate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public static User generateUser(int id){
    /*Integer id, Integer numberOfRequeriments*/
        User u = User.createUser(id,getNumberOfUsers());
        return u;
    }
    
    public static Service generateService(int id){
    /*Double scheduledTime,
        Integer mipsPerCloudlet,
        Integer mipsPerVm,
        Integer minimumReputation,
        Integer shift,
        Double maximumProfit*/
        Service s = Service.createService(getScheduledTime(),getMipsXJob(),getMipsPerVm(),Provider.MEDIUM_REPUTATION,0,getPreferredPriceToBuy());
        return s;
    }
}
