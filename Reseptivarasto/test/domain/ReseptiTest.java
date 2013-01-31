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
import reseptivarasto.Resepti;

/**
 *
 * @author Johanna
 */
public class ReseptiTest {
    
    Resepti resepti;
    Ainekset ainekset;
    
    public ReseptiTest() {
        
        Ainesosa osa1 = new Ainesosa("jauho", "5 dl");
        Ainesosa osa2 = new Ainesosa("sokeri", "2 dl");
        
        ainekset = new Ainekset();
        
        ainekset.lisaaAines(osa1);
        ainekset.lisaaAines(osa2);
        
        resepti = new Resepti("Jauhosokeri", ainekset, "Sekoita jauhot ja sokeri");
    }
    
    @Before
    public void setUp() {
    }
    
    
    @Test
    public void reseptiOikein() {
        
        String resepti1 = resepti.toString();
        
        assertEquals("Jauhosokeri\n\nAinekset:\n  jauho, 5 dl\n  sokeri, 2 dl\n\nValmistusohjeet:\nSekoita jauhot ja sokeri", resepti1);
    
    }
}
