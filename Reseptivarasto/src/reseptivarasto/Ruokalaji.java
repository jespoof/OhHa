/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto;

import java.util.ArrayList;
import java.util.Collections;
import tiedostonkasittelija.ReseptinKasittelija;

/**
 *
 * @author Johanna
 */
public class Ruokalaji {
    
    private String nimi;
    private ArrayList<Resepti> reseptit;
    private ArrayList<Resepti> reseptihaku;
    ReseptinKasittelija kasittelija;
    
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
    
    public boolean poistaResepti(int numero) {
        
        if (numero > reseptit.size() || numero < 1) {
            System.out.println("Ei reseptiä");
            return false;
        
        }else {
            reseptit.remove(numero-1);
            return true;
        }
    }
    
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
    
    public void reseptitTiedostoon() throws Exception {
        kasittelija.kirjoita(reseptit);
        
    }


}
