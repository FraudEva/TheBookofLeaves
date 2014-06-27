/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

import ec.espol.tesis.simulation.entities.Broker;
import org.cloudbus.cloudsim.DatacenterBroker;

/**
 *
 * @author Usuario
 */
public class Market {

    private static final Double COSTO_POR_MI = 1.0;
    public MarketMechanism mecanismo;
    public Broker broker;

    public Market() {
        Broker broker = null;
        try {
                broker = new Broker("Broker_A");
                this.broker =  broker;
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    
    public static Double estimarCostoMI(Long mi){
        return COSTO_POR_MI*mi;
    }

    public MarketMechanism getMecanismo() {
        return mecanismo;
    }

    public void setMecanismo(MarketMechanism mecanismo) {
        this.mecanismo = mecanismo;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }
    
    
}