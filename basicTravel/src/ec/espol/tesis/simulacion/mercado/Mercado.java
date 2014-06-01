/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.mercado;

/**
 *
 * @author Usuario
 */
public class Mercado {

    private static final Double COSTO_POR_MI = 1.0;

    public static Double estimarCostoMI(Long mi){
        return COSTO_POR_MI*mi;
    }
}