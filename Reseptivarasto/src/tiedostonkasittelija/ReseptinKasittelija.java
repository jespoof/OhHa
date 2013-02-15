/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonkasittelija;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import reseptivarasto.domain.Ainekset;
import reseptivarasto.domain.Ainesosa;
import reseptivarasto.domain.Resepti;
import reseptivarasto.domain.Ruokalaji;

/**
 *
 * @author Johanna
 * ReseptinKasittelija kirjoittaa ja lukee ruokalajien mukaan nimettyjä
 * tiedostoja, jotka sisältävät reseptit.
 */
public class ReseptinKasittelija {
    private Ruokalaji ruokalaji;
    private String tiedostonNimi;
    private String reseptitS;

    public ReseptinKasittelija(Ruokalaji ruokalaji) throws IOException {
        
        this.ruokalaji = ruokalaji;
        this.tiedostonNimi = ruokalaji.getNimi()+".txt";
        this.reseptitS = new String();

    }
    
    public ReseptinKasittelija(String tiedostonNimi) {
        
        this.tiedostonNimi = tiedostonNimi;
        this.reseptitS = new String();
        
    }
    
    /**
    * Lukee Reseptit tiedostosta
    */
    public ArrayList<Resepti> lueReseptit() throws IOException {
        
        File tiedosto = new File(tiedostonNimi);
        
        try {
            Scanner lukija = new Scanner(tiedosto, "UTF-8");
            while (lukija.hasNextLine()) {
                reseptitS += lukija.nextLine();
            }
            lukija.close();
        } catch (Exception eiTiedostoa) {
            uusiTiedosto();
        }
        
        ArrayList luettu = listaaReseptit();
        
        return luettu;
    }
    
    /**
    * Luo uuden tiedoston jos tiedostoa ei ole
    */
    public void uusiTiedosto() throws IOException {
        FileWriter kirjoittaja = null;
        
        try{
            File tiedosto = new File(tiedostonNimi);
            kirjoittaja = new FileWriter(tiedosto);
        } catch (Exception e) {
            System.out.println("Virhe.");
        
        }
    }
    
    /**
    * Jakaa tiedostot Resepteihin
    */
    public ArrayList<Resepti> listaaReseptit() throws IOException {
        
        String res[] = reseptitS.split("///=");
        ArrayList<Resepti> reseptit = new ArrayList<Resepti>();
        
        for (String s : res) {
            if (!s.isEmpty()) {
                Resepti r = luoResepti(s);
                reseptit.add(r);
            }
        }
        
        return reseptit;
    }
    
    /**
    * Erottaa Reseptin osat, eli nimen, ainekset ja ohjeet, ja luo niistä
    * Reseptin
    */
    public Resepti luoResepti(String lue) {
        String r[] = lue.split("&!");
        
        String nimi = r[0];
        String ainekset1 = r[1];
        String ohjeet = r[2];
        
        Ainekset ainekset = kasitteleAinekset(ainekset1);
        
        Resepti uusi = new Resepti(nimi, ainekset, ohjeet);
        
        return uusi;
        
    }
    
    /**
    * Erottaa Reseptin Aineosat
    */
    public Ainekset kasitteleAinekset (String ainekset1){
        
        String r[] = ainekset1.split("#/");
        Ainekset a = new Ainekset();
        
        for (String s : r) {
            a.lisaaAines(luoAinesosa(s));
        }
        
        return a;
    }
    
    /**
    * Luo Ainesosan
    */
    public Ainesosa luoAinesosa(String aines) {
        
        String a[] = aines.split("%@");
        
        String nimi = a[0];
        String maara = a[1];
        
        Ainesosa osa = new Ainesosa(nimi,maara);
        
        return osa;
    }
    
    /**
    * Kirjoittaa Reseptit tiedostoon ja lisää erotuskohtamerkit
    */
    public void kirjoitaReseptit(ArrayList<Resepti> reseptit) throws IOException{
        reseptitS = "";
        
        File tiedosto = new File(tiedostonNimi);
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        
        for (Resepti r : reseptit) {
            kirjoittaja.write(r.getNimi() + "&!" + r.tiedostoon() + "&!" + r.getOhjeet() + "///=");
        }
        
        kirjoittaja.close();
    }
    
    
    
}
