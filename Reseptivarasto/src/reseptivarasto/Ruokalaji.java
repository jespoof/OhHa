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
    private ArrayList<Resepti> reseptihaku;
    
    public Ruokalaji (String nimi) {
        this.nimi = nimi;
        reseptit = new ArrayList<Resepti>();
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public ArrayList<Resepti> getReseptit() {
        return this.reseptit;
    }
    
    public void lisaaResepti (Resepti resepti) {
        reseptit.add(resepti);
    }
    
    public String haeResepti(int numero) {
        
        if (numero > reseptit.size() || numero < 1) {
            return "Ei reseptiä";
        
        }else {
            return reseptit.get(numero-1).toString();
        }
    }
    
    public ArrayList<Resepti> ainesHaku(String haku) {
        reseptihaku = new ArrayList<Resepti>();
        
        for (Resepti resepti : reseptit) {
            
            if (resepti.onkoAinesta(haku) == true) {
                reseptihaku.add(resepti);
            }
        }
        return reseptihaku;
    }
    
    
    public ArrayList<Resepti> nimiHaku(String haku) {
        reseptihaku = new ArrayList<Resepti>();
        
        for (Resepti resepti : reseptit) {
            
            if (resepti.onkoNimi(haku) == true) {
               reseptihaku.add(resepti);
            }
        }
        return reseptihaku;
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
    


}
