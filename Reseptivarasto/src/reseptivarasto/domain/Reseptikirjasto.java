/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto.domain;

import java.util.ArrayList;

/**
 *
 * @author Johanna
 * Reseptikirjastossa säilytetään ruokalajit ja täältä päästään käsiin myös
 * resepteihin Ruokalajien kautta. Reseptit voi listata, niitä voi lisätä, 
 * poistaa ja hakea.
 */
public class Reseptikirjasto {
    /**
    * Kaikki Reseptikirjaston ruokalajit
    */
    private ArrayList<Ruokalaji> ruokalajit;
    /**
    * Listalle lisätään hakua vastaavat reseptit
    */
    private ArrayList<Resepti> reseptihaku;
    /**
    * Kirjastoon tallennettujen ruokalajien määrä
    */
    private int ruokalajienMaara;
    /**
    * Reseptikirjastotiedoston nimi on aina kirjasto.txt
    */
    private String tiedostonNimi = "kirjasto.txt";
    
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
    
    /**
    * @return String-lista, joka sisältää kaikien Ruokalajien nimet
    */
    public ArrayList<String> getRuokalajitString() {
        ArrayList<String> ruokalaj = new ArrayList<String>();
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            ruokalaj.add(ruokalaji.getNimi());
        }
        
        return ruokalaj;
    }
    
    public Resepti getResepti(int laji, int resepti) {
        return ruokalajit.get(laji).getResepti(resepti);
    }
    
    /**
    * Lisätään resepti kirjastoon
    * 
    * @param numero Ruokalajin numero, johon Resepti lisätään (indeksi 
    * ArrayListissä)
    * @param resepti Resepti, joka lisätään kirjastoon
    * 
    * @return false, jos Ruokalajia ei ole, true, jos Resepti lisättiin
    * onnistuneesti
    */
    public boolean lisaaResepti(int numero, Resepti resepti) {
        if (numero+1 > ruokalajit.size() || numero+1 < 1) {
            System.out.println("Väärä ruokalaji");
            return false;
            
        } else {
            ruokalajit.get(numero).lisaaResepti(resepti);
            return true;
        }
    }
    
    /**
    * Lisätään uusi Ruokalaji Reseptikirjastoon
    * 
    * @param ruokalaji Lisättävä Ruokalaji
    */
    public void lisaaRuokalaji(Ruokalaji ruokalaji) {
        ruokalajit.add(ruokalaji);
        ruokalajienMaara++;
    }
    
    public int getRuokalajienMaara() {
        return ruokalajienMaara;
    }
    
    /**
    * Listataan kaikki halutun Ruokalajin reseptit
    * 
    * @param numero Ruokalajin numero (indeksi ArrayListissä)
    * 
    * @return "Väärä ruokalaji" jos Ruokalajia ei ole, muutoin Ruokalajin nimi
    * sekä siihen sisältyvien Reseptien nimet
    */
    public String listaaRuokalajinReseptit (int numero) {
        if (numero+1 > ruokalajit.size() || numero+1 < 1) {
            return "Väärä ruokalaji";
            
        }else {
            return ruokalajit.get(numero).getNimi() + "\n" + ruokalajit.get(numero).toString();
        }
    }
    
    /**
    * Haetaan halutun Ruokalajin reseptit ArrayListiin
    * 
    * @param numero Halutun reseptin numero (indeksi ruokalajit-ArrayListissä)
    * 
    * @return ArrayList, joka sisältää kaikki Ruokalajin reseptien nimet
    */
    public ArrayList<String> getRuokalajinReseptit (int numero) {
        ArrayList<String> reseptit = new ArrayList<String>();
        
        for (Resepti resepti : ruokalajit.get(numero).getReseptit()) {
            reseptit.add(resepti.getNimi());
        }
        
        return reseptit;
    }
    
    /**
    * Listaa kaikki Reseptikirjastossa olevat Reseptit
    * 
    * @return String, joka sisältää kaikki Reseptikirjaston Reseptien nimet
    */
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
    
    /**
    * Haetaan Reseptiä tietystä Ruokalajista
    * 
    * @param numero Ruokalajin numero (indeksi)
    * @param numero2 Reseptin numero (indeksi)
    * 
    * @return Väärä ruokalaji, jos Ruokalajia ei ole, muutoin haettu Resepti
    * toString
    */
    public String haeResepti(int numero, int numero2) {
        if (numero+1 > ruokalajit.size() || numero+1 < 1) {
            return "Väärä ruokalaji";
            
        }else {
            return ruokalajit.get(numero).haeResepti(numero2);
        }
    }
    
    /**
    * Poistetaan resepti
    * 
    * @param numero Ruokalajin numero (indeksi)
    * @param numero2 Reseptin numero (indeksi)
    * 
    * @return false, jos Ruokalajia ei ole, false, jos Reseptiä ei ole, true
    * jos poisto onnistui
    */
    public boolean reseptinPoisto(int numero, int numero2) {
        if (numero+1 > ruokalajit.size() || numero+1 < 1) {
            System.out.println("Väärä ruokalaji");
            return false;
            
        }else {
            if(ruokalajit.get(numero).poistaResepti(numero2)==true) {
                return true;
            }
            return false;
        }
    }
    
    /**
    * Haetaan Ruokalajeista Reseptejä, jossa on käyttäjän haluama Ainesosa
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return String, jossa listattuna kaikki Reseptien nimet, joissa on haettava
    * Ainesosa. Jos tämä on tyhjä, palautetaan "Ei hakutuloksia"
    */
    public String haeAinesosallaString(String haku) {
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
    
    /**
    * Haetaan Ruokalajeista Reseptejä, jossa on käyttäjän haluama Ainesosa
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return ArrayList, joka sisältää kaikkien Reseptien nimet, joissa on haettava
    * Ainesosa.
    */
    public ArrayList<String> haeAinesosallaALString(String haku) {
        reseptihaku = new ArrayList<Resepti>();
        ArrayList<String> hakutulokset = new ArrayList<String>();
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            reseptihaku = ruokalaji.ainesHaku(haku);
            for (Resepti resepti : reseptihaku) {
                hakutulokset.add(resepti.getNimi()+" ("+ruokalaji.getNimi()+")");
            }
        }
        
        return hakutulokset;
    }
    
    /**
    * Haetaan Ruokalajeista Reseptejä, jossa on käyttäjän haluama Ainesosa
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return ArrayList, joka sisältää kaikki Reseptit, joissa on haettava
    * Ainesosa.
    */
    public ArrayList<Resepti> haeAinesosallaALResepti(String haku) {
        reseptihaku = new ArrayList<Resepti>();
        ArrayList<Resepti> hakutulokset = new ArrayList<Resepti>();
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            reseptihaku = ruokalaji.ainesHaku(haku);
            for (Resepti resepti : reseptihaku) {
                hakutulokset.add(resepti);
            }
        }
        
        return hakutulokset;
    }
    
    /**
    * Haetaan Reseptejä, joilla on käyttäjän haluama nimi
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return String, jossa listattuna kaikki Reseptien nimet, joissa hakusana
    * Jos tyhjä, palautetaan "Ei hakutuloksia"
    */
    public String haeNimellaString(String haku) {
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
    
    /**
    * Haetaan Ruokalajeista Reseptejä, joilla on käyttäjän haluama nimi
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return ArrayList, joka sisältää kaikkien Reseptien nimet, joilla on haettava
    * nimi.
    */
    public ArrayList<String> haeNimellaALString(String haku) {
        reseptihaku = new ArrayList<Resepti>();
        ArrayList<String> hakutulokset = new ArrayList<String>();
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            reseptihaku = ruokalaji.nimiHaku(haku);
            for (Resepti resepti : reseptihaku) {
                hakutulokset.add(resepti.getNimi()+" ("+ruokalaji.getNimi()+")");
            }
        }
        
        return hakutulokset;
    }
    
    /**
    * Haetaan Ruokalajeista Reseptejä, joilla on käyttäjän haluama nimi
    * 
    * @param haku Käyttäjän antama hakusana
    * 
    * @return ArrayList, joka sisältää kaikki Reseptit, joilla on haettava
    * nimi.
    */
    public ArrayList<Resepti> haeNimellaALResepti(String haku) {
        reseptihaku = new ArrayList<Resepti>();
        ArrayList<Resepti> hakutulokset = new ArrayList<Resepti>();
        
        for (Ruokalaji ruokalaji : ruokalajit) {
            reseptihaku = ruokalaji.nimiHaku(haku);
            for (Resepti resepti : reseptihaku) {
                hakutulokset.add(resepti);
            }
        }
        
        return hakutulokset;
    }
    
    /**
    * Haetaan Reseptiä hakutuloksista
    * 
    * @param haku Reseptin numero
    * 
    * @return Jos Reseptiä ei ole, palautetaan Ei löydy, muutoin palautetaan
    * haettu Resepti toString
    */
    public String haeTuloksista(int haku) {
        
        if (haku+1 > ruokalajit.size() || haku+1 < 1) {
            return "Ei löydy";
            
        }else {
            return reseptihaku.get(haku).toString();
        }
    }
    
    /**
    * @return Ruokalajien nimet
    */
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
}
