/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class User {
    private Integer     id;
    private Integer     numberOfRequeriments;
    private Service     service;
    private SLA         sla;
    private List<Broker> brokerList;

    public User(Integer id, Integer numberOfRequeriments) {
        this.id = id;
        this.numberOfRequeriments = numberOfRequeriments;
        this.service = new Service();
    }
    
    
    
    public void makeOrder(Service service, SLA sla) throws Exception{
        this.service = service;
        this.sla = sla;
        brokerList = new ArrayList<Broker>();
        for( int i=0; i<numberOfRequeriments ; i++){
            Broker broker = new Broker("AgenteBroker_"+id+"."+i,this.service.getScheduledTime() , sla, this.service.getMaximumProfit());
            broker.submitService(service, sla, id);
            brokerList.add(broker);
        }
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    public SLA getSla() {
        return sla;
    }

    public void setSla(SLA sla) {
        this.sla = sla;
    }

    public List<Broker> getListaAgentesBrokers() {
        return brokerList;
    }

    public void setListaAgentesBrokers(List<Broker> brokerList) {
        this.brokerList = brokerList;
    }
}


