/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto.domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Johanna
 * Ruokalajissa säilytetään reseptejä, joita voi hakea ja poistaa tätä kautta
 */
public class Ruokalaji {
    
    private String nimi;
    /**
    *Kaikki Ruokalajiin lisätyt reseptit
    */
    private ArrayList<Resepti> reseptit;
    /**
    * Lista, johon laitetaan hakua vastaavat reseptit
    */
    private ArrayList<Resepti> reseptihaku;
    
    public Ruokalaji (String nimi) {
        this.nimi = nimi;
        reseptit = new ArrayList<Resepti>();
    }
    
    public Ruokalaji (String nimi, ArrayList<Resepti> reseptit) {
        this.nimi = nimi;
        this.reseptit = reseptit;
        Collections.sort(reseptit);
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public ArrayList<Resepti> getReseptit() {
        
        Collections.sort(reseptit);
        return this.reseptit;
    }
    
    public Resepti getResepti(int luku) {
        return reseptit.get(luku);
    }
    
    public void lisaaResepti (Resepti resepti) {
        reseptit.add(resepti);
    }
    
    /**
    * Haetaan resepti
    * 
    * @param numero Reseptin numero (indeksi ArrayListissä +1)
    *
    * @return Jos numerolla ei löydy reseptiä, palautetaan "Ei reseptiä",
    * muutoin palautetaan valittu Resepti toString
    */
    public String haeResepti(int numero) {
        
        if (numero > reseptit.size() || numero < 1) {
            return "Ei reseptiä";
        
        }else {
            return reseptit.get(numero-1).toString();
        }
    }
    
    /**
    * Poistetaan resepti
    * 
    * @param numero Reseptin numero (indeksi ArrayListissä +1)
    * 
    * @return "Ei reseptiä" jos numerolla ei löydy reseptiä, muutoin poistetaan
    * kyseessä oleva Resepti
    * 
    */
    public boolean poistaResepti(int numero) {
        
        if (numero > reseptit.size() || numero < 1) {
            System.out.println("Ei reseptiä");
            return false;
        
        }else {
            reseptit.remove(numero-1);
            return true;
        }
    }
    
    /**
    * Haetaan Reseptiä, jossa on käyttäjän haluama Ainesosa
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return ArrayList, joka sisältää kaikki Reseptit, joissa on haettava
    * Ainesosa (voi olla tyhjä, jos sellaisia ei löydy)
    */
    public ArrayList<Resepti> ainesHaku(String haku) {
        reseptihaku = new ArrayList<Resepti>();
        
        for (Resepti resepti : reseptit) {
            
            if (resepti.onkoAinesta(haku) == true) {
                reseptihaku.add(resepti);
            }
        }
        
        Collections.sort(reseptihaku);
        return reseptihaku;
    }
    
    /**
    * Haetaan Reseptiä, jonka nimi on käyttäjän antama hakusana
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return ArrayList, joka sisältää kaikki Reseptit, joilla on haettava
    * nimi (voi olla tyhjä, jos sellaisia ei löydy)
    */
    public ArrayList<Resepti> nimiHaku(String haku) {
        reseptihaku = new ArrayList<Resepti>();
        
        for (Resepti resepti : reseptit) {
            
            if (resepti.onkoNimi(haku) == true) {
               reseptihaku.add(resepti);
            }
        }
        
        Collections.sort(reseptihaku);
        return reseptihaku;
    }
    
    /**
    * @return Kaikki Ruokalajin Reseptit toString
    */
    @Override
    public String toString() {
        String reseptilista = "";
        int i = 1;
        
        for (Resepti resepti : reseptit) {
            reseptilista += i + " " + resepti.getNimi() + "\n";
            i++;
        }
        return reseptilista;
    }
}
