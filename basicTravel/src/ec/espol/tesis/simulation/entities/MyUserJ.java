/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.entities;

import ec.espol.tesis.simulation.main.Simulator;
import ec.espol.tesis.simulation.util.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MyUserJ {
    private Integer     id;
    private Integer     numberOfRequeriments;
    private Service     service;
    private SLA         sla;
    private List<Broker> brokerList;
    
    public MyUserJ(Integer id, Integer numberOfRequeriments) {
        this.id = id;
        this.numberOfRequeriments = numberOfRequeriments;
    }
    
    /*Definir un solo broker para uno o mas usuarios con varios requerimientos. El broker es el mismo*
    los requerimientos y los usuarios pueden variar*/
    public void makeOrder(Service service, SLA sla) throws Exception{
        this.service = service;
        this.sla = sla;
        brokerList = new ArrayList<Broker>();
        for( int i=0; i<numberOfRequeriments ; i++){
            Broker broker = new Broker("AgenteBroker_"+id+"."+i,this.service.getScheduledTime() , sla, this.service.getMaximumProfit());
            broker.submitService(service, sla, id);
            brokerList.add(broker);
            Simulator.market.brokerList.add(broker);
            broker.setCountIds(broker.getCountIds()+1);
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

    public Integer getNumberOfRequeriments() {
        return numberOfRequeriments;
    }

    public void setNumberOfRequeriments(Integer numberOfRequeriments) {
        this.numberOfRequeriments = numberOfRequeriments;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
}


