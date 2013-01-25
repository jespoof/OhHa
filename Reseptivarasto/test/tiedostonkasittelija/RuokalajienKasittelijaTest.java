/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonkasittelija;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reseptivarasto.Reseptikirjasto;
import reseptivarasto.Ruokalaji;

/**
 *
 * @author Johanna
 */
public class RuokalajienKasittelijaTest {
    
    RuokalajienKasittelija kasittelija;
    File tiedosto;
    FileWriter kirjoittaja;
    Reseptikirjasto kirjasto;
    ArrayList<Ruokalaji> ruokalajit;
    
    public RuokalajienKasittelijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void ruokalajitKirjoitetaanJaLuetaanOikein() throws IOException {
        
        tiedosto = new File("testikirjasto.txt");
        kirjoittaja = new FileWriter(tiedosto);
        
        kirjoittaja.write("Pahaa ruokaa\nHyvää ruokaa");
        kirjoittaja.close();
        
        kasittelija = new RuokalajienKasittelija("testikirjasto.txt");
        kirjasto = new Reseptikirjasto(kasittelija.lueRuokalajit());
        
        kirjasto.lisaaRuokalaji(new Ruokalaji("oho"));
        
        String lista = kirjasto.toString();
        
        assertEquals("1 Huonoa ruokaa\n2 Hyvaa ruokaa \n", lista);
    
    }
}
