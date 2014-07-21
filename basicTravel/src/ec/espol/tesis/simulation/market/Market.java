/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

import ec.espol.tesis.simulation.entities.Broker;
import ec.espol.tesis.simulation.entities.MyUserJ;
import ec.espol.tesis.simulation.entities.ReputationSystem;
import ec.espol.tesis.simulation.entities.User;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Market {

    private static final Double COSTO_POR_MI = 1.0;
    public MarketMechanism mecanismo;
    public ArrayList<MyUserJ> userList;
    public ArrayList<Broker> brokerList;
    public ReputationSystem rs;

    public Market() {
        userList =   new ArrayList<MyUserJ>();
        brokerList = new ArrayList<Broker>();
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

    public ArrayList<MyUserJ> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<MyUserJ> userList) {
        this.userList = userList;
    }

    public ReputationSystem getRs() {
        return rs;
    }

    public void setRs(ReputationSystem rs) {
        this.rs = rs;
    }
    
}