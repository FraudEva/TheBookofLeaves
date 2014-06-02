/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.util;

import ec.espol.tesis.simulacion.entidades.AgenteBroker;
import ec.espol.tesis.simulacion.entidades.ProveedorDeServicio;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
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
public class UtilTest {
    
    public UtilTest() {
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
     * Test of imprimirMensaje method, of class Util.
     */
    @Test
    public void testImprimirMensaje() {
        System.out.println("imprimirMensaje");
        String mensaje = "";
        Util.imprimirMensaje(mensaje);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printCloudletList method, of class Util.
     */
    @Test
    public void testPrintCloudletList() {
        System.out.println("printCloudletList");
        List<Cloudlet> list = null;
        List<AgenteBroker> buyerAgents = null;
        Util.printCloudletList(list, buyerAgents);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printSellerAgentInfo method, of class Util.
     */
    @Test
    public void testPrintSellerAgentInfo() {
        System.out.println("printSellerAgentInfo");
        List<ProveedorDeServicio> sellerAgents = null;
        Util.printSellerAgentInfo(sellerAgents);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
