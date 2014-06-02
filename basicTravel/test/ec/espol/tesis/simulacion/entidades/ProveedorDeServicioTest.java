/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.entidades;

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
public class ProveedorDeServicioTest {
    
    public ProveedorDeServicioTest() {
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
     * Test of crearProveedorServicio method, of class ProveedorDeServicio.
     */
    @Test
    public void testCrearProveedorServicio() throws Exception {
        System.out.println("crearProveedorServicio");
        String nombre = "";
        Double gananciaPreferida = null;
        Double ganaciaMinima = null;
        Double probabilidadDeEvento = null;
        Integer mipsPorNucleo = null;
        Integer reputacionInicial = null;
        ProveedorDeServicio expResult = null;
        ProveedorDeServicio result = ProveedorDeServicio.crearProveedorServicio(nombre, gananciaPreferida, ganaciaMinima, probabilidadDeEvento, mipsPorNucleo, reputacionInicial);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of realizarOferta method, of class ProveedorDeServicio.
     */
    @Test
    public void testRealizarOferta() {
        System.out.println("realizarOferta");
        long mi = 0L;
        double precioReferencia = 0.0;
        ProveedorDeServicio instance = null;
        double expResult = 0.0;
        double result = instance.realizarOferta(mi, precioReferencia);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGanaciaPreferida method, of class ProveedorDeServicio.
     */
    @Test
    public void testGetGanaciaPreferida() {
        System.out.println("getGanaciaPreferida");
        ProveedorDeServicio instance = null;
        Double expResult = null;
        Double result = instance.getGanaciaPreferida();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGanaciaPreferida method, of class ProveedorDeServicio.
     */
    @Test
    public void testSetGanaciaPreferida() {
        System.out.println("setGanaciaPreferida");
        Double ganaciaPreferida = null;
        ProveedorDeServicio instance = null;
        instance.setGanaciaPreferida(ganaciaPreferida);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGananciaMinima method, of class ProveedorDeServicio.
     */
    @Test
    public void testGetGananciaMinima() {
        System.out.println("getGananciaMinima");
        ProveedorDeServicio instance = null;
        Double expResult = null;
        Double result = instance.getGananciaMinima();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGananciaMinima method, of class ProveedorDeServicio.
     */
    @Test
    public void testSetGananciaMinima() {
        System.out.println("setGananciaMinima");
        Double gananciaMinima = null;
        ProveedorDeServicio instance = null;
        instance.setGananciaMinima(gananciaMinima);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServiciosExitosos method, of class ProveedorDeServicio.
     */
    @Test
    public void testGetServiciosExitosos() {
        System.out.println("getServiciosExitosos");
        ProveedorDeServicio instance = null;
        Integer expResult = null;
        Integer result = instance.getServiciosExitosos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setServiciosExitosos method, of class ProveedorDeServicio.
     */
    @Test
    public void testSetServiciosExitosos() {
        System.out.println("setServiciosExitosos");
        Integer serviciosExitosos = null;
        ProveedorDeServicio instance = null;
        instance.setServiciosExitosos(serviciosExitosos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServiciosFallidos method, of class ProveedorDeServicio.
     */
    @Test
    public void testGetServiciosFallidos() {
        System.out.println("getServiciosFallidos");
        ProveedorDeServicio instance = null;
        Integer expResult = null;
        Integer result = instance.getServiciosFallidos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setServiciosFallidos method, of class ProveedorDeServicio.
     */
    @Test
    public void testSetServiciosFallidos() {
        System.out.println("setServiciosFallidos");
        Integer serviciosFallidos = null;
        ProveedorDeServicio instance = null;
        instance.setServiciosFallidos(serviciosFallidos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReputacion method, of class ProveedorDeServicio.
     */
    @Test
    public void testGetReputacion() {
        System.out.println("getReputacion");
        ProveedorDeServicio instance = null;
        Integer expResult = null;
        Integer result = instance.getReputacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReputacion method, of class ProveedorDeServicio.
     */
    @Test
    public void testSetReputacion() {
        System.out.println("setReputacion");
        Integer reputacion = null;
        ProveedorDeServicio instance = null;
        instance.setReputacion(reputacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarServiciosAlojados method, of class ProveedorDeServicio.
     */
    @Test
    public void testConsultarServiciosAlojados() {
        System.out.println("consultarServiciosAlojados");
        ProveedorDeServicio instance = null;
        int expResult = 0;
        int result = instance.consultarServiciosAlojados();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarProbabilidadDeEjecutarse method, of class ProveedorDeServicio.
     */
    @Test
    public void testConsultarProbabilidadDeEjecutarse() {
        System.out.println("consultarProbabilidadDeEjecutarse");
        ProveedorDeServicio instance = null;
        Double expResult = null;
        Double result = instance.consultarProbabilidadDeEjecutarse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarMipsPorNucleo method, of class ProveedorDeServicio.
     */
    @Test
    public void testConsultarMipsPorNucleo() {
        System.out.println("consultarMipsPorNucleo");
        ProveedorDeServicio instance = null;
        Integer expResult = null;
        Integer result = instance.consultarMipsPorNucleo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
