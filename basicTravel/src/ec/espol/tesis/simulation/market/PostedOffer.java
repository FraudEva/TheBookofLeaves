/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

import ec.espol.tesis.simulation.entities.Broker;
import ec.espol.tesis.simulation.entities.Provider;
import ec.espol.tesis.simulation.entities.SLA;
import ec.espol.tesis.simulation.entities.SystemReputation;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PostedOffer extends MarketMechanism{
    public static final String Nombre = "Posted Offer";
    public PostedOffer(){
        super();
    }

    @Override
    public Provider searchBestProvider(long mips, double referencePrice, Integer minimumReputationRequired, SLA sla) {
        Provider bestProvider = null;
        double lowestBid = -1;
        List<Provider> observers = Broker.getProviderList();
        for(Provider provider : observers)
        {
            if(provider.getReputation()>= minimumReputationRequired)
            {
                double bid = provider.makeOffer(mips,referencePrice);
                double reputation = getReputation(provider);
                double bidPonder = bid*ponderPriceWithReputation(bid, reputation);//Peso de sistema de reputacion
                if(bid > 0 && lowestBid == -1){
                    lowestBid = bidPonder;
                    bestProvider = provider;
                }else if (bid>0 && (bidPonder <= lowestBid)){
                    lowestBid = bidPonder;
                    bestProvider = provider;
                }
            }
        }
        return bestProvider;
    }
    
    public double ponderPriceWithReputation(double price, double reputation){
        return price*reputation;
    }
    
    public double getReputation(Provider p){
        return SystemReputation.getReputation(p);
    }
}
