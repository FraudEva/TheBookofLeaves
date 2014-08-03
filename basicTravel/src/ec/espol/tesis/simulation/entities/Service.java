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
public class Service {
    private Double      scheduledTime;
    private Integer     mipsPerCloudlet;
    private Integer     mipsPerVm;
    private Integer     shift;
    private Double      maximumProfit;

    public Service() {
        this.scheduledTime = 0.0;
        this.mipsPerCloudlet= 0;
        this.mipsPerVm= 0;
        this.shift= 0;
        this.maximumProfit= 0.0;
    }

    
    public Service(Double scheduledTime,
        Integer mipsPerCloudlet,
        Integer mipsPerVm,
        Integer minimumReputation,
        Integer shift,
        Double maximumProfit) {
            this.scheduledTime = scheduledTime;
            this.mipsPerCloudlet = mipsPerCloudlet;
            this.mipsPerVm = mipsPerVm;
            this.shift = shift;
            this.maximumProfit = maximumProfit;
    }
    
    public static Service createService(Double scheduledTime,
        Integer mipsPerCloudlet,
        Integer mipsPerVm,
        Integer minimumReputation,
        Integer shift,
        Double maximumProfit){
        Service s = new Service(scheduledTime, mipsPerCloudlet, mipsPerVm, minimumReputation, shift, maximumProfit);
        return s;
    }

    public Double getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Double scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Integer getMiPerCloudlet() {
        return mipsPerCloudlet;
    }

    public void setMiPerCloudlet(Integer mipsPerCloudlet) {
        this.mipsPerCloudlet = mipsPerCloudlet;
    }

    public Integer getMipsPerVm() {
        return mipsPerVm;
    }

    public void setMipsPerVm(Integer mipsPerVm) {
        this.mipsPerVm = mipsPerVm;
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
