/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

import ec.espol.tesis.simulation.entities.Provider;
import ec.espol.tesis.simulation.entities.SLA;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface Subasta {
    public Provider searchBestProvider(long mips, double precioDispuestoAPagar, Integer reputacionMinimaExijida, SLA sla);
}
