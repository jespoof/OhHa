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
import reseptivarasto.domain.Reseptikirjasto;
import reseptivarasto.domain.Ruokalaji;

/**
 *
 * @author Johanna
 * RuokalajienKasittelija luo ja lukee tiedoston, joka sisältää ruokalajien
 * nimet. RuokalajienKasittelija antaa myös käskyn ReseptinKasittelijalle, jonka
 * avulla myös reseptit saadaan kirjoitettua ja luettua.
 */
public class RuokalajienKasittelija {
    
    private Reseptikirjasto kirjasto;
    private String tiedostonNimi;
    private ArrayList<String> rivit;
    private ArrayList<Ruokalaji> ruokalajit;
    
    private ReseptinKasittelija kasittelija;



    public RuokalajienKasittelija(Reseptikirjasto kirjasto) throws IOException {
        
        this.kirjasto = kirjasto;
        this.tiedostonNimi = kirjasto.getTiedostonNimi();
        this.rivit = new ArrayList<String>();

    }
    
    public RuokalajienKasittelija(String tiedostonNimi) {
        
        this.tiedostonNimi = tiedostonNimi;
        this.rivit = new ArrayList<String>();
        
    }
    
    /**
    * Lukee Ruokalajit tiedostosta ja laitetaan ne ArrayListiin
    */
    public ArrayList<Ruokalaji> lueRuokalajit() throws IOException {
        
        File tiedosto = new File(tiedostonNimi);
        
        try {
            Scanner lukija = new Scanner(tiedosto, "UTF-8");
            while (lukija.hasNextLine()) {
                rivit.add(lukija.nextLine());
            }
            lukija.close();
        } catch (Exception e) {
            uusiTiedosto();
        }
        
        ArrayList luettu = ruokalajienListaus();
        
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
    * Lukee tiedoston Ruokalajit ArrayListille ja käskee RuokalajienKäsittelijän 
    * lukemaan niiden Reseptit
    */
    public ArrayList<Ruokalaji> ruokalajienListaus() throws IOException {
        
        ruokalajit = new ArrayList<Ruokalaji>();
        
        for (String rivi : rivit) {
            kasittelija = new ReseptinKasittelija(rivi+".txt");
            Ruokalaji laji = new Ruokalaji(rivi, kasittelija.lueReseptit());
            ruokalajit.add(laji);
        }
        
        return ruokalajit;
    }
    
    /**
    * Kirjoittaa Ruokalajien nimet tiedostoon ja käskee ReseptienKäsittelijän
    * kirjoittamaan Reseptit
    */
    public void kirjoitaRuokalajit(ArrayList<Ruokalaji> lajit) throws IOException {
        rivit.clear();
        
        File tiedosto = new File(tiedostonNimi);
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        
        for (Ruokalaji laji : lajit) {
            
            kasittelija = new ReseptinKasittelija(laji);
            kasittelija.kirjoitaReseptit(laji.getReseptit());
            kirjoittaja.write(laji.getNimi() + "\n");
        }
        
        kirjoittaja.close();
    }
    
    
}
