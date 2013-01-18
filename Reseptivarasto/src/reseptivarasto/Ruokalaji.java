/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto;

import java.util.ArrayList;

/**
 *
 * @author Johanna
 */
public class Ruokalaji {
    
    private String nimi;
    private ArrayList<Resepti> reseptit;
    
    public Ruokalaji (String nimi) {
        
        this.nimi = nimi;
        reseptit = new ArrayList<Resepti>();
        
    }
    
    public String getNimi() {
       
        return this.nimi;
        
    }
    
    public void lisaaResepti (Resepti resepti) {
        
        reseptit.add(resepti);
        
    }
    
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
    
    public String haeResepti(int numero) {
        
        if (numero > reseptit.size() || numero < 1) {
            
            return "Ei reseptiä";
            
        }
        
        else {
            
            return reseptit.get(numero-1).toString();
            
        }
    }
    
}
