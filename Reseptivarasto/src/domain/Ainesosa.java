/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto;

/**
 *
 * @author Johanna
 */
public class Ainesosa {
    
    private String nimi;
    private String maara;
    
    public Ainesosa (String nimi, String maara) {
        
        this.nimi= nimi;
        this.maara = maara;
    }
    
    public String getNimi() {
        
        return nimi;
    }
    
    public String getMaara() {
        
        return maara;
    }
    
    @Override
    public String toString() {
        
        return "  " + nimi + ", " + maara;
    }
    
}
