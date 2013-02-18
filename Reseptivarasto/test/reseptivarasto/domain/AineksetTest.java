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
public class AineksetTest {
    
    Ainekset ainekset;
    Ainesosa aines1;
    Ainesosa aines2;
    
    @Before
    public void setUp() {
        ainekset = new Ainekset();
    }
    
    
    @Test
    public void lisaaAinesJaToStringToimivat() {
      
        aines1 = new Ainesosa("jauho", "5 dl");
        aines2 = new Ainesosa("sokeri", "2 dl");
        
        ainekset.lisaaAines(aines1);
        ainekset.lisaaAines(aines2);
        
        String aineslista = ainekset.toString();
        

        assertEquals("  - jauho, 5 dl\n  - sokeri, 2 dl\n", aineslista);
  }
    
    @Test
    public void tiedostoonToimii() {
        aines1 = new Ainesosa("jauho", "5 dl");
        aines2 = new Ainesosa("sokeri", "2 dl");
        
        ainekset.lisaaAines(aines1);
        ainekset.lisaaAines(aines2);
        
        assertEquals("jauho%@5 dl#/sokeri%@2 dl#/", ainekset.tiedostoon());
    }
}
