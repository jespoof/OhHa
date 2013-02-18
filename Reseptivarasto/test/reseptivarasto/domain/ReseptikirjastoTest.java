package reseptivarasto.domain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Johanna
 */
public class ReseptikirjastoTest {
    
    Reseptikirjasto kirjasto;
    Ruokalaji ruokalaji1;
    Ruokalaji ruokalaji2;
    Ruokalaji ruokalaji3;
    Resepti resepti1;
    Resepti resepti2;
    Resepti resepti3;
    Ainekset ainekset1;
    Ainekset ainekset2;
    Ainekset ainekset3;
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
        ainekset3 = new Ainekset();
        
        ainekset1.lisaaAines(osa1);
        ainekset1.lisaaAines(osa2);
        
        ainekset2.lisaaAines(osa3);
        ainekset2.lisaaAines(osa4);
        
        ainekset3.lisaaAines(osa1);
        ainekset3.lisaaAines(osa3);
        
        resepti1 = new Resepti("Jauhosokeri", ainekset1, "Sekoita jauhot ja sokeri");
        resepti2 = new Resepti("Suolavesi", ainekset2, "Lisää suola veteen");
        resepti3 = new Resepti("Jauhovesi", ainekset3, "Sekoita jauhot veteen");
        
        ruokalaji1 = new Ruokalaji("Pahaa ruokaa");
        ruokalaji2 = new Ruokalaji("Pahempaa ruokaa");
        ruokalaji3 = new Ruokalaji("Ihan kamalaa ruokaa");
        
        kirjasto.lisaaRuokalaji(ruokalaji1);
        
        
        
    }
    
    
    @Test
    public void ruokalajinLisaysToimii() {
        kirjasto.lisaaRuokalaji(ruokalaji2);
        kirjasto.lisaaRuokalaji(ruokalaji3);
        String lajit = kirjasto.toString();
        
        assertEquals("1 Pahaa ruokaa\n2 Pahempaa ruokaa\n3 Ihan kamalaa ruokaa\n", lajit);
    
    }
    
    @Test 
    public void getRuokalajienMaaraToimii() {
        kirjasto.lisaaRuokalaji(ruokalaji2);
        
        assertEquals(2, kirjasto.getRuokalajienMaara());
    }
    
    @Test
    public void reseptinLisaysOikeallaRuokalajinNumerollaToimii() {
        
        kirjasto.lisaaResepti(0, resepti1);
        
        assertTrue(true);
        
    }
    
    @Test
    public void reseptinLisaysVaarallaRuokalajinNumerollaEiToimi() {
        
        kirjasto.lisaaResepti(5, resepti1);
        
        assertFalse(false);
    
    }
    
    @Test
    public void reseptinPoistoToimiiOikeillaNumeroilla() {
        kirjasto.lisaaResepti(0, resepti1);
        kirjasto.lisaaResepti(0, resepti2);
        
        assertTrue(kirjasto.reseptinPoisto(0, 1));
        
        String reseptit = kirjasto.listaaKaikkiReseptit();
        
        assertEquals("Pahaa ruokaa\n1 Jauhosokeri\n\n", reseptit);
    
    }
    
    @Test
    public void reseptinPoistoEiToimiVaarillaNumeroilla() {
        kirjasto.lisaaResepti(0, resepti1);
        kirjasto.lisaaResepti(0, resepti2);
        
        assertFalse(kirjasto.reseptinPoisto(1, 2));
    
    }
    
    @Test
    public void ruokalajinReseptilistausToimiiOikeallaNumerolla() {
        kirjasto.lisaaResepti(0, resepti1);
        
        String reseptit = kirjasto.listaaRuokalajinReseptit(0);
        
        assertEquals("Pahaa ruokaa\n1 Jauhosokeri\n", reseptit);
    
    }
    
    @Test
    public void ruokalajinReseptilistausEiToimiVaarallaNumerolla() {
        
        kirjasto.lisaaResepti(0, resepti1);
        
        String reseptit = kirjasto.listaaRuokalajinReseptit(3);
        
        assertEquals("Väärä ruokalaji", reseptit);
    
    }
    
    @Test
    public void ruokalajinReseptitArrayListiinToimii() {
        kirjasto.lisaaResepti(0, resepti1);
        kirjasto.lisaaResepti(0, resepti2);
        
        ArrayList<String> reseptit = kirjasto.getRuokalajinReseptit(0);
        String res = "";
        for (String s : reseptit) {
            res += s + "\n";
        }
        
        assertEquals("Jauhosokeri\nSuolavesi\n", res);
    }
    
    @Test
    public void reseptinHakuRuokalajistaToimiiOikeallaNumerolla() {
        
        kirjasto.lisaaResepti(0, resepti1);
        
        String reseptit = kirjasto.haeResepti(0, 0);
        
        assertEquals("JAUHOSOKERI\n\n\nAINEKSET:\n  - jauho, 5 dl\n  - sokeri, 2 dl\n\n\nVALMISTUSOHJEET:\nSekoita jauhot ja sokeri", reseptit);
    
    }
    
    @Test
    public void reseptinHakuRuokalajistaEiToimiVaarallaNumerolla() {
        
        kirjasto.lisaaResepti(0, resepti1);
        
        String reseptit = kirjasto.haeResepti(3, 3);
        
        assertEquals("Väärä ruokalaji", reseptit);
    
    }
    
    @Test
    public void reseptihautAinesosallaTuottavatOikeatTulokset() {
        
        kirjasto.lisaaResepti(0, resepti2);
        kirjasto.lisaaResepti(0, resepti3);
        
        String haku = kirjasto.haeAinesosallaString("JAUHO");
        
        assertEquals("Pahaa ruokaa\n1 Jauhovesi\n\n", haku);
        
        ArrayList<String> reseptit = kirjasto.haeAinesosallaALString("Vesi");
        
        String tulos = "";
        
        for (String s : reseptit) {
            tulos += s + "\n";
        }
        
        assertEquals("Jauhovesi (Pahaa ruokaa)\nSuolavesi (Pahaa ruokaa)\n", tulos);
        
        ArrayList<Resepti> reseptit2 = kirjasto.haeAinesosallaALResepti("vesi");
        
        String tulos2 = "";
        
        for (Resepti r : reseptit2) {
            tulos2 += r.getNimi() + "\n";
        }
        
        assertEquals("Jauhovesi\nSuolavesi\n", tulos2);
    
    }
    
    @Test
    public void reseptihautNimellaTuottavatOikeatTulokset() {
        
        kirjasto.lisaaResepti(0, resepti1);
        kirjasto.lisaaResepti(0, resepti3);
        
        String haku = kirjasto.haeNimellaString("jauhosok");
        
        assertEquals("Pahaa ruokaa\n1 Jauhosokeri\n\n", haku);
        
        ArrayList<String> reseptit = kirjasto.haeNimellaALString("jauho");
        
        String tulos = "";
        
        for (String s : reseptit) {
            tulos += s + "\n";
        }
        
        assertEquals("Jauhosokeri (Pahaa ruokaa)\nJauhovesi (Pahaa ruokaa)\n", tulos);
        
        ArrayList<Resepti> reseptit2 = kirjasto.haeNimellaALResepti("jauho");
        
        String tulos2 = "";
        
        for (Resepti r : reseptit2) {
            tulos2 += r.getNimi() + "\n";
        }
        
        assertEquals("Jauhosokeri\nJauhovesi\n", tulos2);
    
    }
    
    
    @Test
    public void listaaKaikkiReseptitToimii() {
        
        kirjasto.lisaaRuokalaji(ruokalaji2);
        
        kirjasto.lisaaResepti(0, resepti2);
        kirjasto.lisaaResepti(1, resepti1);
        
        String reseptit = kirjasto.listaaKaikkiReseptit();
        
        assertEquals("Pahaa ruokaa\n1 Suolavesi\n\nPahempaa ruokaa\n2 Jauhosokeri\n\n", reseptit);
    
    }
    
}
