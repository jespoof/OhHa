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
    
    public boolean onkoAinesta(String haku) {
        
        if (ainekset.onkoAinesta(haku) == true) {
            return true;
        }
        
        return false;
    }
    
    public boolean onkoNimi(String haku) {
        
        if (this.nimi.equals(haku)) {
            return true;
        }
        
        return false;
    }
    

    @Override
    public String toString() {
        
        return this.nimi + "\n" + "\n" + "Ainekset:" + "\n" + ainekset.toString() + "\n" + "Valmistusohjeet:" + "\n" + ohjeet;
    }
    
    
    public String tiedostoon() {
        return ainekset.tiedostoon();
    }

    @Override
    public int compareTo(Resepti resepti) {
        return this.nimi.compareToIgnoreCase(resepti.getNimi());
    }
    
}
