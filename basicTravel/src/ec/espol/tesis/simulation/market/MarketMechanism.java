/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

import ec.espol.tesis.simulation.entities.Provider;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class MarketMechanism {
    public static ArrayList<Provider> providerList;
    private static Subasta subasta;
    
    public static void agregarProveedorServicio(Provider provider){
        providerList.add(provider);
    }
    
    
    public static void inicializarMecanismoSubasta(Subasta mecanismoSubasta){
        providerList = new ArrayList<Provider>();
        subasta = mecanismoSubasta;
    }

    public static ArrayList<Provider> getProviderList() {
        return providerList;
    }

    public static void setProviderList(ArrayList<Provider> providerList) {
        MarketMechanism.providerList = providerList;
    }

    public static Subasta getSubasta() {
        return subasta;
    }

    public static void setSubasta(Subasta subasta) {
        MarketMechanism.subasta = subasta;
    }
    
    
    
}
