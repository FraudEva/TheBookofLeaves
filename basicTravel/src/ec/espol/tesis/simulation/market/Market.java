/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

import ec.espol.tesis.simulation.entities.Broker;
import ec.espol.tesis.simulation.entities.User;
import ec.espol.tesis.simulation.entities.ReputationSystem;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Market {

    public static final Double COSTO_POR_MI = 1.0;
    public static final Integer MIN_VALUE_MIPS = 500;
    public static final Integer MAX_VALUE_MIPS = 1000;
    public static final Double MIN_VALUE_PRICE_TO_SELL = 0.30;
    public static final Double MAX_VALUE_PRICE_TO_SELL = 0.50;
    public static final Double MIN_VALUE_PRICE_TO_BUY = 0.20;
    public static final Double MAX_VALUE_PRICE_TO_BUY = 0.40;
    public static final Double MAX_VALUE_SCHEDULED_TIME = 10.00;
    public static final Integer MIN_VALUE_MIPS_PER_VM = 200;
    public static final Integer MAX_VALUE_MIPS_PER_VM = 400;
    public static final Integer MAX_VALUE_NUMBER_USERS = 400;
    public static final Double MIN_VALUE_PROBABILITY_TO_SUCCESS = 0.60;
    public static final Double MAX_VALUE_PROBABILITY_TO_SUCCESS = 0.99;
    
    
    private MarketMechanism mecanismo;
    private ArrayList<User> userList;
    private ArrayList<Broker> brokerList;
    private ReputationSystem rs;
    private int userNumber;
    private int providerNumber;

    public Market() {
        userList =   new ArrayList<User>();
        brokerList = new ArrayList<Broker>();
        userNumber = 0;
        providerNumber = 0;
    }

    public Market(int userNumber, int providerNumber) {
        this();
        this.userNumber = userNumber;
        this.providerNumber = providerNumber;
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

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ReputationSystem getRs() {
        return rs;
    }

    public void setRs(ReputationSystem rs) {
        this.rs = rs;
    }

    public ArrayList<Broker> getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(ArrayList<Broker> brokerList) {
        this.brokerList = brokerList;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(int providerNumber) {
        this.providerNumber = providerNumber;
    }
}