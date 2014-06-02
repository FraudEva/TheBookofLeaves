/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.mercado;

import ec.espol.tesis.simulacion.entidades.ProveedorDeServicio;
import ec.espol.tesis.simulacion.entidades.SLA;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class SubastaTest {
    
    public SubastaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of encontrarMejorProveedor method, of class Subasta.
     */
    @Test
    public void testEncontrarMejorProveedor() {
        System.out.println("encontrarMejorProveedor");
        long mips = 0L;
        double precioDispuestoAPagar = 0.0;
        Integer reputacionMinimaExijida = null;
        SLA sla = null;
        Subasta instance = new SubastaImpl();
        ProveedorDeServicio expResult = null;
        ProveedorDeServicio result = instance.encontrarMejorProveedor(mips, precioDispuestoAPagar, reputacionMinimaExijida, sla);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class SubastaImpl implements Subasta {

        public ProveedorDeServicio encontrarMejorProveedor(long mips, double precioDispuestoAPagar, Integer reputacionMinimaExijida, SLA sla) {
            return null;
        }
    }
    
}
