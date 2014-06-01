/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.mercado;

import ec.espol.tesis.simulacion.entidades.ProveedorDeServicio;
import ec.espol.tesis.simulacion.entidades.SLA;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class OfertaPublicada implements Subasta {
    public static final String Nombre = "Oferta Publicada";
    private ArrayList<ProveedorDeServicio> listaProveedores;
    
    public OfertaPublicada(){
        listaProveedores = new ArrayList<ProveedorDeServicio>();
    }

    @Override
    public ProveedorDeServicio encontrarMejorProveedor(long mips, double precioDispuestoAPagar, Integer reputacionMinimaExijida, SLA sla) {
        ProveedorDeServicio mejorProveedor = null;
        double ofertaMasBaja = -1;
        List<ProveedorDeServicio> ofertantes = MecanismoMercado.getListaProveedores();
        for(ProveedorDeServicio proveedorServicio : ofertantes)
        {
            if(proveedorServicio.getReputacion() >= reputacionMinimaExijida)
            {
                double oferta = proveedorServicio.realizarOferta(mips,precioDispuestoAPagar);
                double ofertaPonderada = oferta*1;//Peso de sistema de reputacion
                if(oferta > 0 && ofertaMasBaja == -1){
                    ofertaMasBaja = ofertaPonderada;
                    mejorProveedor = proveedorServicio;
                }else if (oferta>0 && (ofertaPonderada <= ofertaMasBaja)){
                    ofertaMasBaja = ofertaPonderada;
                    mejorProveedor = proveedorServicio;
                }
            }
        }
        return mejorProveedor;
    }
    
}
