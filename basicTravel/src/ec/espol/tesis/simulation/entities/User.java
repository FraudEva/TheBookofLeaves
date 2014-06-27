/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.entities;

import ec.espol.tesis.simulation.main.Simulator;

/**
 *
 * @author User
 */
public class User {
    private Integer     id;
    private Integer     numberOfRequeriments;
    private Service     service;
    private SLA         sla;

    public User(Integer id, Integer numberOfRequeriments) {
        this.id = id;
        this.numberOfRequeriments = numberOfRequeriments;
    }
    
    
    /*Definir un solo broker para uno o mas usuarios con varios requerimientos. El broker es el mismo*
	los requerimientos y los usuarios pueden variar*/
    public void makeOrder(Service service, SLA sla) throws Exception{
        this.service = service;
        this.sla = sla;
        for( int i=0; i<numberOfRequeriments ; i++){
            Simulator.market.getBroker().submitService(this.service, this.sla, id);
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
    
}


