/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.io.IOException;
import java.util.Scanner;
import reseptivarasto.domain.Ainekset;
import reseptivarasto.domain.Ainesosa;
import reseptivarasto.domain.Resepti;
import reseptivarasto.domain.Reseptikirjasto;
import reseptivarasto.domain.Ruokalaji;
import tiedostonkasittelija.RuokalajienKasittelija;

/**
 *
 * @author Johanna
 * Tekstikayttoliittyma on ohjelman tekstikayttoliittyma.
 */
public class Tekstikayttoliittyma {
    
    /**
    * Lukee käyttäjän syötteet
    */
    private Scanner lukija = new Scanner(System.in);
    /**
    * Ainekset Reseptin muodostamista varten
    */
    private Ainekset ainekset;
    /**
    * Reseptikirjasto, jota käytetään
    */
    private Reseptikirjasto kirjasto;
    /**
    * Tiedoston käsittelemistä varten
    */
    RuokalajienKasittelija ruokalajikasittelija;
    
    public Tekstikayttoliittyma() {
    }
    
    /**
    * Käynnistää tekstikäyttöliittymän, josta valitaan haluttu toiminto
    */
    public void kaynnista() throws IOException {
        
            ruokalajikasittelija = new RuokalajienKasittelija("kirjasto.txt");
            
            kirjasto = new Reseptikirjasto(ruokalajikasittelija.lueRuokalajit());
            
            if (kirjasto.getRuokalajit().isEmpty()) {
            kirjasto.lisaaRuokalaji(new Ruokalaji("AAMIAINEN"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("LOUNAS"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("PÄIVÄLLINEN"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("JÄLKIRUOKA"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("PIKKUPURTAVAA"));
        }
            
            while (true) {
                
                System.out.println("");
                System.out.println("**************");
                System.out.println("RESEPTIVARASTO");
                System.out.println("**************");
                System.out.println("");
                System.out.println("Valitse toiminnon numero:");
                System.out.println("1 Lisää resepti");
                System.out.println("2 Poista resepti");
                System.out.println("3 Reseptihaku ruokalajin mukaan");
                System.out.println("4 Listaa kaikki reseptit");
                System.out.println("5 Etsi reseptiä nimen mukaan");
                System.out.println("6 Etsi reseptiä ainesosan mukaan");
                System.out.println("7 Lopeta");
                System.out.println("");
                
                String kasky = lukija.nextLine();
                System.out.println("");
                
                if (kasky.equals("1")) {
                    
                    System.out.println("LISÄÄ RESEPTI");
                    System.out.println("");
                    int laji = 0;
                    
                    while (true) {
                        while (true) {
                            System.out.println("Mikä ruokalaji?");
                            System.out.println(kirjasto.toString());
                        
                            try {
                                laji = Integer.parseInt(lukija.nextLine());
                                break;
                            } catch (Exception e) {
                                System.out.println("Et syöttänyt kunnollista numeroa.");
                                System.out.println("");
                            }
                        }
                        
                        if (laji <= kirjasto.getRuokalajienMaara() && laji > 0) {
                            break;
                        }else{
                            System.out.println("Ei ruokalajia! Valitse uudestaan");
                            System.out.println("");
                        }
                    }
                    
                    System.out.println("");
                    System.out.print("Reseptin nimi: ");
                    String nimi = tyhjanTarkistus(lukija.nextLine());
                    
                    ainekset = new Ainekset();
                    
                    lisaaAineksia();
                    
                    while (true) {
                        System.out.println("Lisätäänkö lisää aineksia? (K/E)");
                        
                        String komento = lukija.nextLine();
                        
                        if (komento.equals("K")) {
                            lisaaAineksia();
                        }else if (komento.equals("E")) {
                            break;
                        }
                    }
                    
                    System.out.println("");
                    System.out.println("Kirjoita valmistusohjeet:");
                    String ohjeet = lukija.nextLine();
                    
                    kirjasto.lisaaResepti(laji-1, new Resepti(nimi, ainekset, ohjeet));
                    
                    System.out.println("");
                }
                
                if (kasky.equals("2")) {
                    System.out.println("POISTA RESEPTI");
                    System.out.println("");
                    
                    int laji = valitseRuokalaji();
                    int poisto = valitseResepti(laji);
                    
                    System.out.println("Poistetaanko varmasti? (K/E)");
                    String varmistus = lukija.nextLine();
                    
                    if (varmistus.equals("K")) {
                        kirjasto.reseptinPoisto(laji-1, poisto-1);
                    } else if (varmistus.equals("E")) {
                    }
                
                }
                
                if (kasky.equals("3")) {
                    
                    System.out.println("RESEPTIHAKU RUOKALAJIN MUKAAN");
                    System.out.println("");
                    
                    int num = valitseRuokalaji();
                    int num2 = valitseResepti(num);
                    
                    System.out.println("");
                    System.out.println("---");
                        
                    System.out.println(kirjasto.haeResepti(num-1, num2-1));
            
                    System.out.println("---");
                    System.out.println("");
                        
                    System.out.println("Lopeta ohjelma? (K/E)");
                    String valinta = lukija.nextLine();
                        
                    if (valinta.equals("K")) {
                        return;
                    }else if (valinta.equals("E")) {
                    }
                 }
                
                if (kasky.equals("4")) {
                    
                    System.out.println("KAIKKI RESEPTIT");
                    System.out.println("");
                
                    System.out.println(kirjasto.listaaKaikkiReseptit());
                    
                    System.out.println("Hae resepti? (K/E)");
                    String haetaanko = lukija.nextLine();
                    
                    if (haetaanko.equals("K")) {
                        mikaHakutuloksista();
                    } else if (haetaanko.equals("E")) {
                    }
                    
                }
                
                if (kasky.equals("5")) {
           
                    System.out.println("HAKU RESEPTIN NIMEN MUKAAN");
                    System.out.println("");
                
                    System.out.print("Kirjoita reseptin nimi: ");
                    String nimihaku = lukija.nextLine();
                    
                    System.out.println("");
                    
                    if (kirjasto.haeNimellaString(nimihaku).equals("Ei hakutuloksia")) {
                        System.out.println(kirjasto.haeNimellaString(nimihaku));
                    }else {
                        System.out.println(kirjasto.haeNimellaString(nimihaku));
                        mikaHakutuloksista();
                    }
                    
                }
                
                if (kasky.equals("6")) {
                

                    System.out.println("HAKU AINESOSAN MUKAAN");
                    System.out.println("");
                
                    System.out.print("Kirjoita ainesosan nimi: ");
                    String aineshaku = lukija.nextLine();
                    
                    System.out.println("");
                    
                    if (kirjasto.haeAinesosallaString(aineshaku).equals("Ei hakutuloksia")) {
                        System.out.println(kirjasto.haeAinesosallaString(aineshaku));
                    }else {
                        System.out.println(kirjasto.haeAinesosallaString(aineshaku));
                        mikaHakutuloksista();
                    }
                }
                
                if (kasky.equals("7")) {
                    
                    ruokalajikasittelija.kirjoitaRuokalajit(kirjasto.getRuokalajit());
                    break;
                }
            }
    }

    
    /**
    * Käyttäjä lisää Ainesosan
    */
    public void lisaaAineksia() {
        
        System.out.println("");
        
        System.out.println("Lisää ainesosa");
        
        System.out.print("Aines: ");
        String ainesNimi = tyhjanTarkistus(lukija.nextLine());
                        
        System.out.print("Määrä: ");
        String ainesMaara = tyhjanTarkistus(lukija.nextLine());
                        
        ainekset.lisaaAines(new Ainesosa(ainesNimi, ainesMaara));
        
        System.out.println("");
    
    }
    
    
    /**
    * Käyttäjä valitsee haluamansa Reseptin numeron Ruokalajin reseptilistalta
    */
    public int valitseResepti(int num) {
        
        while (true) {
                        
            System.out.println(kirjasto.listaaRuokalajinReseptit(num-1));
            System.out.println("");
                        
            int num2 = 0;
            
            while (true) {
                System.out.println("Mikä resepti?");
            
                try {
                    num2 = Integer.parseInt(lukija.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("Et syöttänyt kunnollista numeroa.");
                }
            }
            
            return num2;
            
        }
    }
    
    /**
    * Käyttäjä valitsee haluamansa Ruokalajin numeron
    */
    public int valitseRuokalaji() {
        
        int num = 0;
        
        while (true) {
            System.out.println("Valitse ruokalaji:");
            System.out.println(kirjasto.toString());
            try {
                num = Integer.parseInt(lukija.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Et syöttänyt kunnollista numeroa.");
                System.out.println("");
                valitseRuokalaji();
            }
        }
                
        System.out.println("");
        return num;
    }
    
    /**
    * Käyttäjä valitsee, minkä Reseptin hakutuloksista haluaa näkyville
    */
    public void mikaHakutuloksista() {
        
        int haku = 0;
        
        while (true) {
            System.out.println("Minkä reseptin ohjeen haluat?");
            try {
                haku = Integer.parseInt(lukija.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Et syöttänyt kunnollista numeroa.");
                System.out.println("");
            }
        }
        
        System.out.println("");
        System.out.println("---");
        
        try {
        System.out.println(kirjasto.haeTuloksista(haku-1));
        } catch (Exception e) {
        System.out.println("Reseptiä ei löydy.");
        }
        
        System.out.println("---");
        System.out.println("");
        
    }
    
    /**
    * Tarkistaa, että käyttäjä ei ole syöttänyt tyhjää
    */
    public String tyhjanTarkistus(String sana) {
        
        if (sana.equals("")) {
            System.out.println("Et syöttänyt mitään");
            System.out.print("Syötä uudelleen:");
            
            return tyhjanTarkistus(lukija.nextLine());
        }
        
        return sana;
    }
}

    

