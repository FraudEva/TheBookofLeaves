/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

import ec.espol.tesis.simulation.entities.Provider;
import ec.espol.tesis.simulation.entities.SLA;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public abstract class MarketMechanism {
    private ArrayList<Provider> providerList;

    public MarketMechanism() {
        providerList = new ArrayList<Provider>();
    }
    
    
    public abstract Provider searchBestProvider(long mips, double precioDispuestoAPagar, Integer reputacionMinimaExijida, SLA sla);
    
    public ArrayList<Provider> getProviderList() {
        return providerList;
    }

    public void setProviderList(ArrayList<Provider> providerList) {
        this.providerList = providerList;
    }
    
    public void addProviderToList(Provider provider){
        providerList.add(provider);
    }
 }
