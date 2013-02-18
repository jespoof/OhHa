package reseptivarasto.domain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
public class RuokalajiTest {
    
    Ruokalaji ruokalaji;
    Resepti resepti;
    Resepti resepti2;
    Ainekset ainekset1;
    Ainekset ainekset2;
    
    public RuokalajiTest() {
        
        ruokalaji = new Ruokalaji("Pahaa ruokaa");
    }
    
    @Before
    public void setUp() {
        
        Ainesosa osa1 = new Ainesosa("jauho", "5 dl");
        Ainesosa osa2 = new Ainesosa("sokeri", "2 dl");
        
        ainekset1 = new Ainekset();
        
        ainekset1.lisaaAines(osa1);
        ainekset1.lisaaAines(osa2);
        
        Ainesosa osa3 = new Ainesosa("vesi", "5 dl");
        Ainesosa osa4 = new Ainesosa("suola", "1 rkl");
        
        ainekset2 = new Ainekset();
        
        ainekset2.lisaaAines(osa1);
        ainekset2.lisaaAines(osa2);
        
        resepti = new Resepti("Jauhosokeri", ainekset1, "Sekoita jauhot ja sokeri");
        resepti2 = new Resepti("Suolavesi", ainekset2, "Lisää suola veteen");
        
        ruokalaji.lisaaResepti(resepti2);
        ruokalaji.lisaaResepti(resepti);
        
           
    }
    
    @Test
    public void lisaaReseptiToimiiJaAakkosjarjestaa() {
        
        String ruokalajinReseptit = ruokalaji.toString();
        
        assertEquals("1 Jauhosokeri\n2 Suolavesi\n", ruokalajinReseptit);
    
   }
    
    @Test
    public void getReseptitToimiiJaAakkosjarjestaa() {
        
        ArrayList<Resepti> reseptit = ruokalaji.getReseptit();
        
        String s = "";
        
        for (Resepti r : reseptit) {
            s += r.getNimi() + "\n";
        }
        
        assertEquals("Jauhosokeri\nSuolavesi\n", s);
    }
    
    
    @Test
    public void reseptinPoistoToimii() {
        
        ruokalaji.poistaResepti(0);
        
        String ruokalajinReseptit = ruokalaji.toString();
        
        assertEquals("1 Jauhosokeri" + "\n", ruokalajinReseptit);
    }
    
    
    @Test
    public void haeReseptiToimiiOikeallaNumerolla() {
        
        String oikeaHaku = ruokalaji.haeResepti(1);
        
        assertEquals("JAUHOSOKERI\n\n\nAINEKSET:\n  - jauho, 5 dl\n  - sokeri, 2 dl\n\n\nVALMISTUSOHJEET:\nSekoita jauhot ja sokeri", oikeaHaku);
    
   }
    
    @Test
    public void haeReseptiEiToimiVaarallaNumerolla() {
        
        String vaaraHaku = ruokalaji.haeResepti(3);
        
        assertEquals("Ei reseptiä", vaaraHaku);
    
    }
}
