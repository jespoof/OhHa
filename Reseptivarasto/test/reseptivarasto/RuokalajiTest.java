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
import reseptivarasto.Ruokalaji;

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
        
        
    }
    
    @Test
    public void lisaaReseptiToimii() {
        
        resepti = new Resepti("Jauhosokeri", ainekset1, "Sekoita jauhot ja sokeri");
        resepti2 = new Resepti("Suolavesi", ainekset2, "Lisää suola veteen");
        
        ruokalaji.lisaaResepti(resepti);
        ruokalaji.lisaaResepti(resepti2);
        
        String ruokalajinReseptit = ruokalaji.toString();
        
        assertEquals("1 Jauhosokeri" + "\n" + "2 Suolavesi" + "\n", ruokalajinReseptit);
    
   }
    
    @Test
    public void haeReseptiToimiiOikeallaNumerolla() {
        
        resepti = new Resepti("Jauhosokeri", ainekset1, "Sekoita jauhot ja sokeri");
        resepti2 = new Resepti("Suolavesi", ainekset2, "Lisää suola veteen");
        
        ruokalaji.lisaaResepti(resepti);
        ruokalaji.lisaaResepti(resepti2);
        
        String oikeaHaku = ruokalaji.haeResepti(1);
        
        assertEquals("Jauhosokeri" + "\n" + "\n" + "Ainekset:" + "\n" + "  jauho, 5 dl" + "\n" + "  sokeri, 2 dl" + "\n" + "\n" + "Valmistusohjeet:" + "\n" + "Sekoita jauhot ja sokeri", oikeaHaku);
    
   }
    
    @Test
    public void haeReseptiEiToimiVaarallaNumerolla() {
        
        resepti = new Resepti("Jauhosokeri", ainekset1, "Sekoita jauhot ja sokeri");
        resepti2 = new Resepti("Suolavesi", ainekset2, "Lisää suola veteen");
        
        ruokalaji.lisaaResepti(resepti);
        ruokalaji.lisaaResepti(resepti2);
        
        String vaaraHaku = ruokalaji.haeResepti(3);
        
        assertEquals("Ei reseptiä", vaaraHaku);
    
    }
}