/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase que permite definir el Proveedor de la nube
 */
package ec.espol.tesis.simulacion.entidades;

import java.util.List;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicy;

/**
 *
 * @author Usuario
 */
public class ProveedorDeServicio extends Datacenter{
    public static final Integer REPUTACION_MAXIMA=100;
    public static final Integer REPUTACION_MINIMA=1;
    public static final Integer REPUTACION_MEDIA=50;
    private Integer serviciosExitosos;
    private Integer serviciosFallidos;
    private Integer reputacion;
    
    public ProveedorDeServicio(String name, DatacenterCharacteristics characteristics, VmAllocationPolicy vmAllocationPolicy, List<Storage> storageList, double schedulingInterval) throws Exception {
        super(name, characteristics, vmAllocationPolicy, storageList, schedulingInterval);
    }

    public ProveedorDeServicio(Integer reputacion, String name, DatacenterCharacteristics characteristics, VmAllocationPolicy vmAllocationPolicy, List<Storage> storageList, double schedulingInterval) throws Exception {
        super(name, characteristics, vmAllocationPolicy, storageList, schedulingInterval);
        this.serviciosExitosos = 0;
        this.serviciosFallidos = 0;
        this.reputacion = reputacion;
    }
    
    
}
