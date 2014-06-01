/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.mercado;

import ec.espol.tesis.simulacion.entidades.ProveedorDeServicio;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class MecanismoMercado {
    public static ArrayList<ProveedorDeServicio> listaProveedores;
    private static Subasta subasta;
    
    public static void agregarProveedorServicio(ProveedorDeServicio proveedor){
        listaProveedores.add(proveedor);
    }
    
    public static void inicializarMecanismoSubasta(Subasta mecanismoSubasta){
        listaProveedores = new ArrayList<ProveedorDeServicio>();
        subasta = mecanismoSubasta;
    }

    public static ArrayList<ProveedorDeServicio> getListaProveedores() {
        return listaProveedores;
    }

    public static void setListaProveedores(ArrayList<ProveedorDeServicio> listaProveedores) {
        MecanismoMercado.listaProveedores = listaProveedores;
    }

    public static Subasta getMecanismo() {
        return subasta;
    }

    public static void setMecanismo(Subasta mecanismo) {
        MecanismoMercado.subasta = mecanismo;
    }
    
}
