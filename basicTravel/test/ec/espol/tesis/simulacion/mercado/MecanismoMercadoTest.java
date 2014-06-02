/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.mercado;

import ec.espol.tesis.simulacion.entidades.ProveedorDeServicio;
import java.util.ArrayList;
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
public class MecanismoMercadoTest {
    
    public MecanismoMercadoTest() {
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
     * Test of agregarProveedorServicio method, of class MecanismoMercado.
     */
    @Test
    public void testAgregarProveedorServicio() {
        System.out.println("agregarProveedorServicio");
        ProveedorDeServicio proveedor = null;
        MecanismoMercado.agregarProveedorServicio(proveedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inicializarMecanismoSubasta method, of class MecanismoMercado.
     */
    @Test
    public void testInicializarMecanismoSubasta() {
        System.out.println("inicializarMecanismoSubasta");
        Subasta mecanismoSubasta = null;
        MecanismoMercado.inicializarMecanismoSubasta(mecanismoSubasta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaProveedores method, of class MecanismoMercado.
     */
    @Test
    public void testGetListaProveedores() {
        System.out.println("getListaProveedores");
        ArrayList<ProveedorDeServicio> expResult = null;
        ArrayList<ProveedorDeServicio> result = MecanismoMercado.getListaProveedores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setListaProveedores method, of class MecanismoMercado.
     */
    @Test
    public void testSetListaProveedores() {
        System.out.println("setListaProveedores");
        ArrayList<ProveedorDeServicio> listaProveedores = null;
        MecanismoMercado.setListaProveedores(listaProveedores);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMecanismo method, of class MecanismoMercado.
     */
    @Test
    public void testGetMecanismo() {
        System.out.println("getMecanismo");
        Subasta expResult = null;
        Subasta result = MecanismoMercado.getMecanismo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMecanismo method, of class MecanismoMercado.
     */
    @Test
    public void testSetMecanismo() {
        System.out.println("setMecanismo");
        Subasta mecanismo = null;
        MecanismoMercado.setMecanismo(mecanismo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
