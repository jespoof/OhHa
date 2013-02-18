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
import reseptivarasto.domain.Reseptikirjasto;
import reseptivarasto.domain.Ruokalaji;

/**
 *
 * @author Johanna
 */
public class RuokalajienKasittelijaTest {
    
    RuokalajienKasittelija kasittelija;
    File tiedosto;
    File tiedosto2;
    FileWriter kirjoittaja;
    ArrayList<Ruokalaji> ruokalajit;
    
    public RuokalajienKasittelijaTest() {
    }
    
    
    @Before
    public void setUp() throws IOException {
        tiedosto = new File("testikirjasto.txt");
        tiedosto2 = new File("testiruokalaji.txt");
    }
    
    @After
    public void tearDown() {
        tiedosto.deleteOnExit();
        tiedosto2.deleteOnExit();
    }
    
    @Test
    public void tiedostonLukeminenOnnistuu() throws IOException {
        
        kasittelija = new RuokalajienKasittelija("testikirjasto.txt");
        
        kirjoittaja = new FileWriter(tiedosto);
        kirjoittaja.write("Pahaa ruokaa\nHyvaa ruokaa");
        kirjoittaja.close();
        
        ruokalajit = kasittelija.lueRuokalajit();
        Reseptikirjasto testikirjasto = new Reseptikirjasto(ruokalajit);
        
        assertEquals("1 Pahaa ruokaa\n2 Hyvaa ruokaa\n", testikirjasto.toString());
    
    }
    
    @Test
    public void tiedostonKirjoittaminenJaLukeminen() throws IOException {
        
        kasittelija = new RuokalajienKasittelija("testikirjasto.txt");
        
        Reseptikirjasto testikirjasto = new Reseptikirjasto();
        Ruokalaji testiruokalaji = new Ruokalaji("testiruokalaji");
        testikirjasto.lisaaRuokalaji(testiruokalaji);
        
        kasittelija.kirjoitaRuokalajit(testikirjasto.getRuokalajit());
        
        kasittelija = new RuokalajienKasittelija("testikirjasto.txt");
        ruokalajit = kasittelija.lueRuokalajit();
        Reseptikirjasto testikirjasto1 = new Reseptikirjasto(ruokalajit);

        assertEquals("1 testiruokalaji\n", testikirjasto.toString());
       
    }
}
