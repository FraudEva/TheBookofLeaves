/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.mercado;

import ec.espol.tesis.simulacion.entidades.ProveedorDeServicio;
import ec.espol.tesis.simulacion.entidades.SLA;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface Subasta {
    public ProveedorDeServicio encontrarMejorProveedor(long mips, double precioDispuestoAPagar, Integer reputacionMinimaExijida, SLA sla);
}
