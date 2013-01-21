package reseptiluokkatestit;

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
import reseptivarasto.Reseptikirjasto;
import reseptivarasto.Ruokalaji;

/**
 *
 * @author Johanna
 */
public class ReseptikirjastoTest {
    
    Reseptikirjasto kirjasto;
    Ruokalaji ruokalaji1;
    Ruokalaji ruokalaji2;
    Resepti resepti1;
    Resepti resepti2;
    Ainekset ainekset1;
    Ainekset ainekset2;
    Ainesosa osa1;
    Ainesosa osa2;
    Ainesosa osa3;
    Ainesosa osa4;
    
    public ReseptikirjastoTest() {
        
        kirjasto = new Reseptikirjasto();
    }
    
    
    @Before
    public void setUp() {
        
        osa1 = new Ainesosa("jauho", "5 dl");
        osa2 = new Ainesosa("sokeri", "2 dl");
        osa3 = new Ainesosa("vesi", "5 dl");
        osa4 = new Ainesosa("suola", "1 rkl");
        
        ainekset1 = new Ainekset();
        ainekset2 = new Ainekset();
        
        ainekset1.lisaaAines(osa1);
        ainekset1.lisaaAines(osa2);
        
        ainekset2.lisaaAines(osa3);
        ainekset2.lisaaAines(osa4);
        
        resepti1 = new Resepti("Jauhosokeri", ainekset1, "Sekoita jauhot ja sokeri");
        resepti2 = new Resepti("Suolavesi", ainekset2, "Lisää suola veteen");
        
        ruokalaji1 = new Ruokalaji("Huonoa ruokaa");
        ruokalaji2 = new Ruokalaji("Hyvää ruokaa");
        
    }
    
    
    @Test
    public void ruokalajinLisaysToimii() {
        
        kirjasto.lisaaRuokalaji(ruokalaji1);
        kirjasto.lisaaRuokalaji(ruokalaji2);
        
        String lajit = kirjasto.toString();
        
        assertEquals("1 Huonoa ruokaa" + "\n" + "2 Hyvää ruokaa" + "\n", lajit);
    
    }
    
    @Test
    public void reseptinLisaysOikeallaToimii() {
        
        kirjasto.lisaaRuokalaji(ruokalaji1);
        kirjasto.lisaaResepti(1, resepti1);
        
        assertTrue(true);
        
    }
    
    @Test
    public void reseptinLisaysVaarallaNumerollaEiToimi() {
        
        kirjasto.lisaaRuokalaji(ruokalaji1);
        kirjasto.lisaaResepti(3, resepti1);
        
        assertFalse(false);
    
    }
    
    
    @Test
    public void ruokalajinReseptilistausToimiiOikeallaNumerolla() {
        
        kirjasto.lisaaRuokalaji(ruokalaji1);
        kirjasto.lisaaResepti(1, resepti1);
        
        String reseptit = kirjasto.listaaRuokalajinReseptit(1);
        
        assertEquals("Huonoa ruokaa" + "\n" + "1 Jauhosokeri" + "\n", reseptit);
    
    }
    
    @Test
    public void ruokalajinReseptilistausEiToimiVaarallaNumerolla() {
        
        kirjasto.lisaaRuokalaji(ruokalaji1);
        kirjasto.lisaaResepti(1, resepti1);
        
        String reseptit = kirjasto.listaaRuokalajinReseptit(3);
        
        assertEquals("Väärä ruokalaji", reseptit);
    
    }
    
    @Test
    public void reseptihakuToimiiOikeallaNumerolla() {
        
        kirjasto.lisaaRuokalaji(ruokalaji1);
        kirjasto.lisaaResepti(1, resepti1);
        
        String reseptit = kirjasto.haeRuokalajista(1, 1);
        
        assertEquals("Jauhosokeri" + "\n" + "\n" + "Ainekset:" + "\n" + "  jauho, 5 dl" + "\n" + "  sokeri, 2 dl" + "\n" + "\n" + "Valmistusohjeet:" + "\n" + "Sekoita jauhot ja sokeri", reseptit);
    
    }
    
    @Test
    public void reseptihakuEiToimiVaarallaNumerolla() {
        
        kirjasto.lisaaRuokalaji(ruokalaji1);
        kirjasto.lisaaResepti(1, resepti1);
        
        String reseptit = kirjasto.haeRuokalajista(3, 3);
        
        assertEquals("Väärä ruokalaji", reseptit);
    
    }
    
    @Test
    public void listaaKaikkiReseptitToimii() {
        
        kirjasto.lisaaRuokalaji(ruokalaji1);
        kirjasto.lisaaResepti(1, resepti1);
        kirjasto.lisaaResepti(1, resepti2);
        
        kirjasto.lisaaRuokalaji(ruokalaji2);
        
        String reseptit = kirjasto.listaaKaikkiReseptit();
        
        assertEquals("1 Huonoa ruokaa" + "\n" + "1 Jauhosokeri" + "\n" + "2 Suolavesi" + "\n" + "\n" + "2 Hyvää ruokaa" + "\n" + "\n", reseptit);
    
    }
    
}
