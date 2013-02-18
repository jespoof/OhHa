package reseptivarasto.domain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Johanna
 */
public class ReseptiTest {
    
    Resepti resepti;
    Ainekset ainekset;
    
    public ReseptiTest() {
    }
    
    @Before
    public void setUp() {
        Ainesosa osa1 = new Ainesosa("jauho", "5 dl");
        Ainesosa osa2 = new Ainesosa("sokeri", "2 dl");
        
        ainekset = new Ainekset();
        
        ainekset.lisaaAines(osa1);
        ainekset.lisaaAines(osa2);
        
        resepti = new Resepti("Jauhosokeri", ainekset, "Sekoita jauhot ja sokeri");
    
    }
    
    
    @Test
    public void reseptiToStringOikein() {
        
        String resepti1 = resepti.toString();
        
        assertEquals("JAUHOSOKERI\n\n\nAINEKSET:\n  - jauho, 5 dl\n  - sokeri, 2 dl\n\n\nVALMISTUSOHJEET:\nSekoita jauhot ja sokeri", resepti1);
    
    }
    
    @Test
    public void onkoAinestaToimiiKirjaintenKoostaRiippumatta() {
        
        assertTrue(resepti.onkoAinesta("JAuhO"));
    }
    
    @Test
    public void onkoNimiToimiiKirjaintenKoostaRiippumattaJaSananOsalla() {
        
        assertTrue(resepti.onkoNimi("jauhosokeri"));
        assertTrue(resepti.onkoNimi("JAuh"));
    }
    
    @Test
    public void ohjeetTiedostoonToimii() {
        Resepti uusi = new Resepti("Uusi", ainekset, "Rivinvaihtoja\nRivinvaihtojaaa\nLisää rivinvaihtojaaaaa");
        assertEquals("Rivinvaihtoja:&n:Rivinvaihtojaaa:&n:Lisää rivinvaihtojaaaaa:&n:", uusi.ohjeetTiedostoon());
    }
}
