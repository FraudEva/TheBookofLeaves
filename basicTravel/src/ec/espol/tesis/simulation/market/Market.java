/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.market;

/**
 *
 * @author Usuario
 */
public class Market {

    private static final Double COSTO_POR_MI = 1.0;
    public static MarketMechanism mecanismo;
    
    public static Double estimarCostoMI(Long mi){
        return COSTO_POR_MI*mi;
    }
}