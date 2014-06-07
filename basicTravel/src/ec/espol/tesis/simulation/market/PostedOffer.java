/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

import ec.espol.tesis.simulation.entities.Provider;
import ec.espol.tesis.simulation.entities.SLA;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PostedOffer implements Subasta {
    public static final String Nombre = "Posted Offer";
    private ArrayList<Provider> providerList;
    
    public PostedOffer(){
        providerList = new ArrayList<Provider>();
    }

    @Override
    public Provider searchBestProvider(long mips, double referencePrice, Integer minimumReputationRequired, SLA sla) {
        Provider bestProvider = null;
        double lowestBid = -1;
        List<Provider> observers = MarketMechanism.getProviderList();
        for(Provider provider : observers)
        {
            if(provider.getReputation()>= minimumReputationRequired)
            {
                double bid = provider.makeOffer(mips,referencePrice);
                double bidPonder = bid*1;//Peso de sistema de reputacion
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
    
}
