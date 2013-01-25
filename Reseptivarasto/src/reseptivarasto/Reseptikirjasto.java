/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto;

import java.util.ArrayList;
import tiedostonkasittelija.RuokalajinKasittelija;

/**
 *
 * @author Johanna
 */
public class Reseptikirjasto {
    
    private ArrayList<Ruokalaji> ruokalajit;
    private ArrayList<Resepti> reseptihaku;
    private int ruokalajienMaara;
    private String tiedostonNimi = "kirjasto.txt";
    RuokalajinKasittelija kasittelija;
    
    public Reseptikirjasto() {
        ruokalajit = new ArrayList<Ruokalaji>();
        ruokalajienMaara = 0;
    }
    
    public Reseptikirjasto(ArrayList<Ruokalaji> ruokalajit) {
        this.ruokalajit = ruokalajit;
        ruokalajienMaara = ruokalajit.size();
    }
    
    public ArrayList<Ruokalaji> getRuokalajit() {
        return ruokalajit;
    }
    
    public boolean lisaaResepti(int numero, Resepti resepti) {
        
        if (numero > ruokalajit.size() || numero < 1) {
            System.out.println("Väärä ruokalaji");
            return false;
            
        }else {
            ruokalajit.get(numero-1).lisaaResepti(resepti);
            return true;
        }
    }
    
    public void lisaaRuokalaji(Ruokalaji ruokalaji) {
        ruokalajit.add(ruokalaji);
        ruokalajienMaara++;
    }
    
    public int getRuokalajienMaara() {
        return ruokalajienMaara;
    }
    
    public String listaaRuokalajinReseptit (int numero) {
        
        if (numero > ruokalajit.size() || numero < 1) {
            return "Väärä ruokalaji";
            
        }else {
            return ruokalajit.get(numero-1).getNimi() + "\n" + ruokalajit.get(numero-1).toString();
        }
    }
    
    public String listaaKaikkiReseptit() {
        reseptihaku = new ArrayList<Resepti>();
        String kaikki = "";
        int i = 1;
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            kaikki += ruokalaji.getNimi() + "\n";
            
            for (Resepti resepti : ruokalaji.getReseptit()) {
                reseptihaku.add(resepti);
                kaikki += i + " " + resepti.getNimi() + "\n";
                i++;
            }
            kaikki += "\n";
        }
        if (reseptihaku.isEmpty()) {
            return "Ei hakutuloksia";
        }
        return kaikki;
    }
    
    public String haeRuokalajista(int numero, int numero2) {
        
        if (numero > ruokalajit.size() || numero < 1) {
            return "Väärä ruokalaji";
            
        }else {
            return ruokalajit.get(numero-1).haeResepti(numero2);
        }
    }
    

    public String haeAinesosalla(String haku) {
        
        reseptihaku = new ArrayList<Resepti>();
        ArrayList<Resepti> lajihaku;
        
        String haetut = "";
        int i = 1;
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            lajihaku = ruokalaji.ainesHaku(haku);
            
            if (lajihaku.isEmpty()) {
            } else {
                haetut += ruokalaji.getNimi() + "\n";
            
                for (Resepti resepti : lajihaku) {
                    reseptihaku.add(resepti);
                    haetut += i + " " + resepti.getNimi() + "\n";
                    i++;
                }
                haetut += "\n";
            }
        }
        
        if (reseptihaku.isEmpty()) {
            return "Ei hakutuloksia";
        }
        return haetut;
    }
    
    public String haeNimella(String haku) {
        
        reseptihaku = new ArrayList<Resepti>();
        ArrayList<Resepti> lajihaku;
        
        String haetut = "";
        int i = 1;
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            lajihaku = ruokalaji.nimiHaku(haku);
            
            if (lajihaku.isEmpty()) {
            
            } else {
                haetut += ruokalaji.getNimi() + "\n";
            
                for (Resepti resepti : lajihaku) {
                    reseptihaku.add(resepti);
                    haetut += i + " " + resepti.getNimi() + "\n";
                    i++;
                }
            }
            haetut += "\n";
        }
        
        if (reseptihaku.isEmpty()) {
            return "Ei hakutuloksia";
        }
        return haetut;
    }
    
    
    public String haeTuloksista(int haku) {
        
        if (haku > ruokalajit.size() || haku < 1) {
            return "Ei löydy";
            
        }else {
            return reseptihaku.get(haku-1).toString();
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
    
    public String getTiedostonNimi() {
        return tiedostonNimi;
    }
    
    public void ruokalajitTiedostoon() throws Exception {
        kasittelija.kirjoita(ruokalajit);
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            ruokalaji.reseptitTiedostoon();
        }
    }
}
