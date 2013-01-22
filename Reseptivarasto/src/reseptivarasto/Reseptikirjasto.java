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
    private ArrayList<Resepti> reseptihaku;
    
    public Reseptikirjasto() {
        ruokalajit = new ArrayList<Ruokalaji>();
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
        String haetut = "";
        int i = 1;
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            
            haetut += ruokalaji.getNimi() + "\n";
            
            for (Resepti resepti : ruokalaji.ainesHaku(haku)) {
                reseptihaku.add(resepti);
                haetut += i + " " + resepti.getNimi() + "\n";
                i++;
            }
            haetut += "\n";
        }
        return haetut;
    }
    
    public String haeNimella(String haku) {
        
        reseptihaku = new ArrayList<Resepti>();
        String haetut = "";
        int i = 1;
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            haetut += ruokalaji.getNimi() + "\n";
            
            for (Resepti resepti : ruokalaji.nimiHaku(haku)) {
                reseptihaku.add(resepti);
                haetut += i + " " + resepti.getNimi() + "\n";
                i++;
            }
            haetut += "\n";
        }
        return haetut;
    }
    
    
    public String haeTuloksista(int haku) {
        
        if (haku > ruokalajit.size() || haku < 1 || ruokalajit == null) {
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
}
