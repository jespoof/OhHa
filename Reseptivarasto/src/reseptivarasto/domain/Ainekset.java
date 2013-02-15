/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto.domain;

import java.util.ArrayList;

/**
 *
 * @author Johanna
 * Ainekset-luokassa säilytetään reseptiin kuuluvia ainesosia. Ainesosia
 * voidaan myös lisätä ja hakea tästä.
 */
public class Ainekset {
    /**
    * ArrayList, joka sisältää Ainesosat
    */
    private ArrayList<Ainesosa> aines;
    /**
    * ArrayList, joka sisältää String-versiot Ainesosista
    */
    private ArrayList<String> lista;
    
    public Ainekset () {
        
        this.aines = new ArrayList<Ainesosa>();
        this.lista = new ArrayList<String>();
        
    }
    
    public ArrayList<Ainesosa> getAinekset() {
        return aines;
    }
 
    /**
    * Lisää Ainesosan aines-ArrayListiin ja sen toString-muodon 
    * lista-ArrayListiin
    */
    public void lisaaAines(Ainesosa ainesosa) {
        
        this.aines.add(ainesosa);
        this.lista.add(ainesosa.toString());
    }
    
    /**
    * Haetaan Ainesosien nimistä, löytyykö käyttäjän hakemaa ainesosaa
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return true, jos löytyy hakua vastaava Ainesosa, false jos ei löydy
    */
    public boolean onkoAinesta (String haku) {
        
        for (Ainesosa ainesosa : aines) {
            
            if (ainesosa.getNimi().equalsIgnoreCase(haku)){
                return true;
            }
        }
        
        return false;
    }
    
    /**
    * @return lisätyt Ainesosat String-muodossa
    */
    @Override
    public String toString() {
        
        String ainekset = "";
        
        for (String ainesosaJaMaara : lista) {
            ainekset += ainesosaJaMaara + "\n";
        }
        
        return ainekset;
    }
    
    /**
    * Tiedoston kirjoittamista varten
    */
    public String tiedostoon() {
        String tiedosto = "";
        
        for (Ainesosa a : aines) {
            tiedosto += a.getNimi()+"%@"+a.getMaara()+"#/";
        }
        
        return tiedosto;
    }
}
