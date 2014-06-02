/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.entidades;

import org.cloudbus.cloudsim.Vm;
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
public class RecursoHostTest {
    
    public RecursoHostTest() {
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
     * Test of vmCreate method, of class RecursoHost.
     */
    @Test
    public void testVmCreate() {
        System.out.println("vmCreate");
        Vm vm = null;
        RecursoHost instance = null;
        boolean expResult = false;
        boolean result = instance.vmCreate(vm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCapacidadCPU method, of class RecursoHost.
     */
    @Test
    public void testGetCapacidadCPU() {
        System.out.println("getCapacidadCPU");
        RecursoHost instance = null;
        Integer expResult = null;
        Integer result = instance.getCapacidadCPU();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCapacidadCPU method, of class RecursoHost.
     */
    @Test
    public void testSetCapacidadCPU() {
        System.out.println("setCapacidadCPU");
        Integer capacidadCPU = null;
        RecursoHost instance = null;
        instance.setCapacidadCPU(capacidadCPU);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMemoriaDisponible method, of class RecursoHost.
     */
    @Test
    public void testGetMemoriaDisponible() {
        System.out.println("getMemoriaDisponible");
        RecursoHost instance = null;
        Integer expResult = null;
        Integer result = instance.getMemoriaDisponible();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMemoriaDisponible method, of class RecursoHost.
     */
    @Test
    public void testSetMemoriaDisponible() {
        System.out.println("setMemoriaDisponible");
        Integer memoriaDisponible = null;
        RecursoHost instance = null;
        instance.setMemoriaDisponible(memoriaDisponible);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbabilidadDeEjecutarse method, of class RecursoHost.
     */
    @Test
    public void testGetProbabilidadDeEjecutarse() {
        System.out.println("getProbabilidadDeEjecutarse");
        RecursoHost instance = null;
        double expResult = 0.0;
        double result = instance.getProbabilidadDeEjecutarse();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProbabilidadDeEjecutarse method, of class RecursoHost.
     */
    @Test
    public void testSetProbabilidadDeEjecutarse() {
        System.out.println("setProbabilidadDeEjecutarse");
        double probabilidadDeEjecutarse = 0.0;
        RecursoHost instance = null;
        instance.setProbabilidadDeEjecutarse(probabilidadDeEjecutarse);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularProbabilidad method, of class RecursoHost.
     */
    @Test
    public void testCalcularProbabilidad() {
        System.out.println("calcularProbabilidad");
        double p = 0.0;
        RecursoHost instance = null;
        boolean expResult = false;
        boolean result = instance.calcularProbabilidad(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
