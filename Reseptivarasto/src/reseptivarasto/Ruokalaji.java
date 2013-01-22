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
    
<<<<<<< HEAD
=======
    public String ainesHaku(String haku) {
        
        String ainesOn = this.nimi + "\n";
        int i = 1;
        
        for (Resepti resepti : reseptit) {
            
            if (resepti.onkoAines(haku) == true) {
                
                ainesOn += i + " " + resepti.getNimi() + "\n";
                i++;
            }
        }
        
        return ainesOn + "\n";
    }
    
    public String nimiHaku(String haku) {
        
        String nimiOn = this.nimi + "\n";
        
        for (Resepti resepti : reseptit) {
            
            if (resepti.onkoNimi(haku) == true) {
                
                nimiOn += resepti.getNimi() + "\n";
            }
        }
        
        return nimiOn + "\n";
    }
>>>>>>> 31f038224b703b6a0a26d682795040762179658b
}
