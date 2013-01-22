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
public class Reseptikirjasto {
    
    private ArrayList<Ruokalaji> ruokalajit;
    
    public Reseptikirjasto() {
        
        ruokalajit = new ArrayList<Ruokalaji>();
    }
    
    public boolean lisaaResepti(int numero, Resepti resepti) {
        
        if (numero > ruokalajit.size() || numero < 1) {
            
            System.out.println("Väärä ruokalaji");
            return false;
            
        }
        
        else {
            
            ruokalajit.get(numero-1).lisaaResepti(resepti);
            return true;
            
        }
        
        
    }
    
    @Override
    public String toString() {
        
        String lista = "";
        int i = 1;
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            lista += i + " " + ruokalaji.getNimi() + "\n";
            i++;
        }
        
        return lista;
    }
    
    public void lisaaRuokalaji(Ruokalaji ruokalaji) {
        
        ruokalajit.add(ruokalaji);
    }
    
    public String listaaRuokalajinReseptit (int numero) {
        
        if (numero > ruokalajit.size() || numero < 1) {
            
            return "Väärä ruokalaji";
            
        }
        
        else {
            
            return ruokalajit.get(numero-1).getNimi() + "\n" + ruokalajit.get(numero-1).toString();
            
        }
        
    }
    
    public String listaaKaikkiReseptit() {
        
        String kaikki = "";
        int i = 1;
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            kaikki += i + " " + ruokalaji.getNimi() + "\n" + ruokalaji.toString() + "\n";
            i++;
        }
        
        return kaikki;
    }
    
    public String haeRuokalajista(int numero, int numero2) {
        
        if (numero > ruokalajit.size() || numero < 1) {
            
            return "Väärä ruokalaji";
            
        }
        
        else {
            
            return ruokalajit.get(numero-1).haeResepti(numero2);
            
        }
        
    }
    
<<<<<<< HEAD
=======
    public String haeAinesosalla(String haku) {
        
        String reseptitJoissaAines = "";
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            
            reseptitJoissaAines += ruokalaji.ainesHaku(haku);
        }
        
        return reseptitJoissaAines;
    }
    
    public String haeNimella(String haku) {
        
        String reseptitJoidenNimi = "";
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            
            reseptitJoidenNimi += ruokalaji.nimiHaku(haku);
        }
        
        return reseptitJoidenNimi;
    }
    
>>>>>>> 31f038224b703b6a0a26d682795040762179658b
}
