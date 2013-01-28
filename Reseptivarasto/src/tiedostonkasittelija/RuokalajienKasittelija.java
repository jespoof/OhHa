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
import reseptivarasto.Reseptikirjasto;
import reseptivarasto.Ruokalaji;

/**
 *
 * @author Johanna
 */
public class RuokalajienKasittelija {
    
    private Reseptikirjasto kirjasto;
    private String tiedostonNimi;
    private ArrayList<String> rivit;
    private ArrayList<Ruokalaji> ruokalajit;
    
    ReseptinKasittelija kasittelija;



    public RuokalajienKasittelija(Reseptikirjasto kirjasto) throws IOException {
        
        this.kirjasto = kirjasto;
        this.tiedostonNimi = kirjasto.getTiedostonNimi();
        this.rivit = new ArrayList<String>();

    }
    
    public RuokalajienKasittelija(String tiedostonNimi) {
        
        this.tiedostonNimi = tiedostonNimi;
        this.rivit = new ArrayList<String>();
        
    }
    
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
    
    public void uusiTiedosto() throws IOException {
        FileWriter kirjoittaja = null;
        
        try{
            File tiedosto = new File(tiedostonNimi);
            kirjoittaja = new FileWriter(tiedosto);
        } catch (Exception e) {
            System.out.println("Virhe.");
        }
    }
    
    public ArrayList<Ruokalaji> ruokalajienListaus() throws IOException {
        
        ruokalajit = new ArrayList<Ruokalaji>();
        
        for (String rivi : rivit) {
            kasittelija = new ReseptinKasittelija(rivi+".txt");
            Ruokalaji laji = new Ruokalaji(rivi, kasittelija.lueReseptit());
            ruokalajit.add(laji);
        }
        
        return ruokalajit;
    }
    
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
