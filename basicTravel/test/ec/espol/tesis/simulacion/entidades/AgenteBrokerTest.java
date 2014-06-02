/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.entidades;

import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.core.SimEvent;
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
public class AgenteBrokerTest {
    
    public AgenteBrokerTest() {
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
     * Test of startEntity method, of class AgenteBroker.
     */
    @Test
    public void testStartEntity() {
        System.out.println("startEntity");
        AgenteBroker instance = null;
        instance.startEntity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processResourceCharacteristics method, of class AgenteBroker.
     */
    @Test
    public void testProcessResourceCharacteristics() {
        System.out.println("processResourceCharacteristics");
        SimEvent ev = null;
        AgenteBroker instance = null;
        instance.processResourceCharacteristics(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processVmCreate method, of class AgenteBroker.
     */
    @Test
    public void testProcessVmCreate() {
        System.out.println("processVmCreate");
        SimEvent ev = null;
        AgenteBroker instance = null;
        instance.processVmCreate(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finishExecution method, of class AgenteBroker.
     */
    @Test
    public void testFinishExecution() {
        System.out.println("finishExecution");
        AgenteBroker instance = null;
        instance.finishExecution();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of submitCloudletList method, of class AgenteBroker.
     */
    @Test
    public void testSubmitCloudletList() {
        System.out.println("submitCloudletList");
        List<? extends Cloudlet> lista = null;
        AgenteBroker instance = null;
        instance.submitCloudletList(lista);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shutdownEntity method, of class AgenteBroker.
     */
    @Test
    public void testShutdownEntity() {
        System.out.println("shutdownEntity");
        AgenteBroker instance = null;
        instance.shutdownEntity();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarNumeroDeIntentos method, of class AgenteBroker.
     */
    @Test
    public void testConsultarNumeroDeIntentos() {
        System.out.println("consultarNumeroDeIntentos");
        AgenteBroker instance = null;
        Integer expResult = null;
        Integer result = instance.consultarNumeroDeIntentos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
