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
import reseptivarasto.Ainekset;
import reseptivarasto.Ainesosa;
import reseptivarasto.Resepti;
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
    ArrayList<Ruokalaji> ruokalajit;
    
    public RuokalajienKasittelijaTest() {
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
    public void tiedostonLukeminenOnnistuu() throws IOException {
        
        kasittelija = new RuokalajienKasittelija("test/testikirjasto.txt");
        
        tiedosto = new File("test/testikirjasto.txt");
        kirjoittaja = new FileWriter(tiedosto);
        kirjoittaja.write("test/Pahaa ruokaa\ntest/Hyvaa ruokaa");
        kirjoittaja.close();
        
        ruokalajit = kasittelija.lueRuokalajit();
        Reseptikirjasto testikirjasto = new Reseptikirjasto(ruokalajit);
        
        assertEquals("1 test/Pahaa ruokaa\n2 test/Hyvaa ruokaa\n", testikirjasto.toString());
    
    }
    
    @Test
    public void tiedostonKirjoittaminenJaLukeminen() throws IOException {
        
        kasittelija = new RuokalajienKasittelija("test/testikirjasto.txt");
        
        Reseptikirjasto testikirjasto = new Reseptikirjasto();
        Ruokalaji testiruokalaji = new Ruokalaji("test/testiruokalaji1");
        testikirjasto.lisaaRuokalaji(testiruokalaji);
        
        kasittelija.kirjoita(testikirjasto.getRuokalajit());
        
        kasittelija = new RuokalajienKasittelija("test/testikirjasto.txt");
        ruokalajit = kasittelija.lueRuokalajit();
        Reseptikirjasto testikirjasto1 = new Reseptikirjasto(ruokalajit);

        assertEquals("1 test/testiruokalaji1\n", testikirjasto.toString());
       
    }
}
