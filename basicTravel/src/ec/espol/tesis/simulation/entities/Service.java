/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.entities;

/**
 *
 * @author José Luis
 */
public class Service{
    private Double      scheduledTime;
    private Integer     miPerCloudlet;
    private Integer     mipsPerVm;
    private Integer     minimumReputation;
    private Integer     shift;
    private Double      maximumProfit;

    public Service() {
    }

    
    public Service(Double scheduledTime,
            Integer miPerCloudlet,
            Integer mipsPerVm,
            Integer minimumReputation,
            Integer shift,
            Double maximumProfit) {
        this.scheduledTime = scheduledTime;
        this.miPerCloudlet = miPerCloudlet;
        this.mipsPerVm = mipsPerVm;
        this.minimumReputation = minimumReputation;
        this.shift = shift;
        this.maximumProfit = maximumProfit;
    }

    public Double getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Double scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Integer getMiPerCloudlet() {
        return miPerCloudlet;
    }

    public void setMiPerCloudlet(Integer miPerCloudlet) {
        this.miPerCloudlet = miPerCloudlet;
    }

    public Integer getMipsPerVm() {
        return mipsPerVm;
    }

    public void setMipsPerVm(Integer mipsPerVm) {
        this.mipsPerVm = mipsPerVm;
    }

    public Integer getMinimumReputation() {
        return minimumReputation;
    }

    public void setMinimumReputation(Integer minimumReputation) {
        this.minimumReputation = minimumReputation;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Double getMaximumProfit() {
        return maximumProfit;
    }

    public void setMaximumProfit(Double maximumProfit) {
        this.maximumProfit = maximumProfit;
    }

    
    
}
