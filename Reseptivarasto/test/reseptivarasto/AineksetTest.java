package reseptivarasto;

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
import reseptivarasto.Ainekset;
import reseptivarasto.Ainesosa;

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
    public void lisaaAineksetToimii() {
      
        aines1 = new Ainesosa("jauho", "5 dl");
        aines2 = new Ainesosa("sokeri", "2 dl");
        
        ainekset.lisaaAines(aines1);
        ainekset.lisaaAines(aines2);
        
        String aineslista = ainekset.toString();
        

        assertEquals("  jauho, 5 dl" + "\n" + "  sokeri, 2 dl" + "\n", aineslista);
  }
}
