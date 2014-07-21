/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

import ec.espol.tesis.simulation.entities.Broker;
import ec.espol.tesis.simulation.entities.Provider;
import ec.espol.tesis.simulation.entities.SLA;
import ec.espol.tesis.simulation.main.Simulator;
import java.util.List;

/**
 *
 * @author José Luis
 */
public class ReverseAuction extends MarketMechanism {

    @Override
    /**
     * 
     * @param mi
     * @param referencePrice
     * @param minimumReputation
     * @param sla
     * @param region
     * @param datacenterRequestedIdsList
     * @return 
     */
    public Provider searchBestProvider(long mips, double precioDispuestoAPagar, Integer reputacionMinimaExijida, SLA sla) {
        int iteration = 0;        
        Provider bestProvider = null;
        double lowestBid = -1;
        Provider oldProvider  = null;
        Provider newProvider = null;
        double currentReferencePrice = precioDispuestoAPagar;
        do
        {
            iteration++;
            List<Provider> observers = Broker.getProviderList();
            for (Provider provider : observers)
            {
                if(provider.getReputation() >= reputacionMinimaExijida)
                {
                    double bid = provider.makeOffer(mips,currentReferencePrice);
                    double reputation = getReputation(provider);
                    double ponderedBid = bid*ponderPriceWithReputation(bid, reputation);//Peso de sistema de reputacion
               
                    if(bid>0 && (lowestBid == -1 ||  ponderedBid<lowestBid || (ponderedBid==lowestBid /* && Math.random()>0.5 */ )))
                    {
                        lowestBid = ponderedBid;
                        oldProvider = newProvider;
                        newProvider = provider;
                        currentReferencePrice = bid;
                    }                
                }
            }                       
        }while (iteration < 3 && newProvider!=null && newProvider!=oldProvider);
        bestProvider = newProvider;
        return bestProvider;
    }
    
    public double ponderPriceWithReputation(double price, double reputation){
        return price*reputation;
    }
    
    public double getReputation(Provider p){
        return Simulator.market.getRs().getReputation(p);
    }
}
