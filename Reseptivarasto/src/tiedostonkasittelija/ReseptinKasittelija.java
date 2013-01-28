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
import reseptivarasto.Ainekset;
import reseptivarasto.Ainesosa;
import reseptivarasto.Resepti;
import reseptivarasto.Ruokalaji;

/**
 *
 * @author Johanna
 */
public class ReseptinKasittelija {
    private Ruokalaji ruokalaji;
    private String tiedostonNimi;
    private ArrayList<String> rivit;

    public ReseptinKasittelija(Ruokalaji ruokalaji) throws IOException {
        
        this.ruokalaji = ruokalaji;
        this.tiedostonNimi = ruokalaji.getNimi()+".txt";
        this.rivit = new ArrayList<String>();

    }
    
    public ReseptinKasittelija(String tiedostonNimi) {
        
        this.tiedostonNimi = tiedostonNimi;
        this.rivit = new ArrayList<String>();
        
    }
    
    public ArrayList<Resepti> lueReseptit() throws IOException {
        
        File tiedosto = new File(tiedostonNimi);
        
        try {
            Scanner lukija = new Scanner(tiedosto, "UTF-8");
            while (lukija.hasNextLine()) {
                rivit.add(lukija.nextLine());
            }
            lukija.close();
        } catch (Exception eiTiedostoa) {
            uusiTiedosto();
        }
        
        ArrayList luettu = listaaReseptit();
        
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
    
    public ArrayList<Resepti> listaaReseptit() throws IOException {
        
        ArrayList<Resepti> reseptit = new ArrayList<Resepti>();
        
        for (String rivi : rivit) {
            Resepti r = luoResepti(rivi);
            reseptit.add(r);
        }
        
        return reseptit;
    }
    
    public Resepti luoResepti(String lue) {
        String r[] = lue.split("&!");
        
        String nimi = r[0];
        String ainekset1 = r[1];
        String ohjeet = r[2];
        
        Ainekset ainekset = kasitteleAinekset(ainekset1);
        
        Resepti uusi = new Resepti(nimi, ainekset, ohjeet);
        
        return uusi;
        
    }
    
    public Ainekset kasitteleAinekset (String ainekset1){
        
        String r[] = ainekset1.split("#/");
        Ainekset a = new Ainekset();
        
        for (String s : r) {
            a.lisaaAines(kasitteleAinesosat(s));
        }
        
        return a;
    }
    
    public Ainesosa kasitteleAinesosat(String aines) {
        
        String a[] = aines.split("%@");
        
        String nimi = a[0];
        String maara = a[1];
        
        Ainesosa osa = new Ainesosa(nimi,maara);
        
        return osa;
    }
    
    public void kirjoitaReseptit(ArrayList<Resepti> reseptit) throws IOException{
        rivit.clear();
        
        File tiedosto = new File(tiedostonNimi);
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        
        for (Resepti r : reseptit) {
            kirjoittaja.write(r.getNimi() + "&!" + r.tiedostoon() + "&!" + r.getOhjeet() + "\n");
        }
        
        kirjoittaja.close();
    }
    
    
    
}
