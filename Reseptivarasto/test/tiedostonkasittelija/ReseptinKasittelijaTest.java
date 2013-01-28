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
public class ReseptinKasittelijaTest {
    
    ReseptinKasittelija kasittelija;
    File tiedosto;
    FileWriter kirjoittaja;
    Reseptikirjasto kirjasto;
    Ruokalaji ruokalaji;
    ArrayList<Resepti> reseptit;
    
    public ReseptinKasittelijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        
        tiedosto = new File("test/testiruokalaji.txt");
        kirjoittaja = new FileWriter(tiedosto);
        
        kirjoittaja.write("Puuro&!Puurohiutale%@1 pussillinen#/Vesi%@2dl#/&!Keita vesi, lisaa hiutaleet\nKeitto&!Purkkikeitto%@1 purkki#/&!Keita");
        kirjoittaja.close();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void tiedostonLukeminen() throws IOException {
    
        kasittelija = new ReseptinKasittelija("test/testiruokalaji.txt");
        
        reseptit = kasittelija.lueReseptit();
        ruokalaji = new Ruokalaji("testiruokalaji", reseptit);
        
        assertEquals("1 Keitto\n2 Puuro\n", ruokalaji.toString());
    }
    
    @Test
    public void tiedostoonKirjoittaminenJaLukeminen() throws IOException {
    
        kasittelija = new ReseptinKasittelija("test/testiruokalaji.txt");
        
        ruokalaji = new Ruokalaji("testiruokalaji");
        Ainekset ainekset = new Ainekset();
        Ainesosa ainesosa = new Ainesosa ("leipa", "2kpl");
        Ainesosa toinenosa = new Ainesosa("juustoviipale", "2kpl");
        ainekset.lisaaAines(ainesosa);
        ainekset.lisaaAines(toinenosa);
        Resepti resepti = new Resepti("Juustoleivat", ainekset, "Laita juustoviipaleet leipien paalle");
        ruokalaji.lisaaResepti(resepti);
        
        Ainekset ainekset2 = new Ainekset();
        Ainesosa ainesosa2 = new Ainesosa ("suklaapatukka", "1kpl");
        ainekset2.lisaaAines(ainesosa);
        Resepti resepti2 = new Resepti("Suklaapatukka", ainekset2, "Ota kaare pois suklaapatukan paalta, syo");
        ruokalaji.lisaaResepti(resepti2);
        
        kasittelija.kirjoitaReseptit(ruokalaji.getReseptit());
        
        reseptit = kasittelija.lueReseptit();
        ruokalaji = new Ruokalaji("testiruokalaji", reseptit);
        
        assertEquals("1 Juustoleivat\n2 Suklaapatukka\n", ruokalaji.toString());
    }
    
    @Test
    public void reseptienLukeminenToimii() throws IOException {
        
        kasittelija = new ReseptinKasittelija("test/testiruokalaji.txt");
        reseptit = kasittelija.lueReseptit();
        
        String luettuResepti = reseptit.get(0).toString();
        
        assertEquals("Puuro\n\nAinekset:\n  Puurohiutale, 1 pussillinen\n  Vesi, 2dl\n\nValmistusohjeet:\nKeita vesi, lisaa hiutaleet", luettuResepti);
    }
}
