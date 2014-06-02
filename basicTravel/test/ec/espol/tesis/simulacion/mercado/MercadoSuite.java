/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulacion.mercado;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Usuario
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ec.espol.tesis.simulacion.mercado.MecanismoMercadoTest.class, ec.espol.tesis.simulacion.mercado.OfertaPublicadaTest.class, ec.espol.tesis.simulacion.mercado.MercadoTest.class, ec.espol.tesis.simulacion.mercado.SubastaTest.class})
public class MercadoSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
