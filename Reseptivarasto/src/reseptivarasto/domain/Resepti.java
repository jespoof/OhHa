/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto.domain;

import java.util.ArrayList;

/**
 *
 * @author Johanna
 * Resepti-luokassa on reseptin nimi, ainekset ja ohjeet. Ainekset voi myös
 * listata täältä.
 */
public class Resepti implements Comparable<Resepti>{
    
    private String nimi;
    private Ainekset ainekset;
    private String ohjeet;
    
    public Resepti (String nimi, Ainekset ainekset, String ohjeet) {
        
        this.nimi = nimi;
        this.ainekset = ainekset;
        this.ohjeet = ohjeet;
    }
    
    public String getNimi() {
        
        return this.nimi;
    }
    
    public Ainekset getAinekset() {
        
        return ainekset;
    }
    
    public ArrayList<Ainesosa> getAineksetLista() {
        return ainekset.getAinekset();
    }
    
    public String getOhjeet() {
        
        return ohjeet;
    }
    
    /**
    * Haetaan Aineksista, löytyykö hakua vastaavaa Ainesosaa
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return true, jos löytyy, false jos ei löydy
    */
    public boolean onkoAinesta(String haku) {
        
        if (ainekset.onkoAinesta(haku) == true) {
            return true;
        }
        
        return false;
    }
    
    /**
    * Katsotaan vastaako reseptin nimi hakusanaa
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return true, jos Reseptin nimi on sama kuin hakusana, false jos ei
    */
    public boolean onkoNimi(String haku) {
        
        if (this.nimi.equals(haku)) {
            return true;
        }
        
        return false;
    }
    
    /**
    * @return Reseptin nimi, ainesosat ja ohjeet String-muodossa
    */
    @Override
    public String toString() {
        
        return this.nimi + "\n" + "\n" + "Ainekset:" + "\n" + ainekset.toString() + "\n" + "Valmistusohjeet:" + "\n" + ohjeet;
    }
    
    /**
    * Ainesten tiedostoon kirjoittamista varten
    */
    public String tiedostoon() {
        return ainekset.tiedostoon();
    }
    
    /**
    * Reseptien järjestäminen aakkosjärjestykseen
    */
    @Override
    public int compareTo(Resepti resepti) {
        return this.nimi.compareToIgnoreCase(resepti.getNimi());
    }
    
}
