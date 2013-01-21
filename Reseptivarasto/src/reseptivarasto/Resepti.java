/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto;

/**
 *
 * @author Johanna
 */
public class Resepti {
    
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
    
    public String getAinekset() {
        
        return ainekset.toString();
        
    }
    
    public String getOhjeet() {
        
        return ohjeet;
    }
    
    public boolean onkoAines(String haku) {
        
        if (ainekset.onkoAines(haku) == true) {
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
        
    
}
