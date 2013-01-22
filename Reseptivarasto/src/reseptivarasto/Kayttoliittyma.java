/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto;

import java.util.Scanner;

/**
 *
 * @author Johanna
 */
public class Kayttoliittyma {
    
    
    Scanner lukija = new Scanner(System.in);
    private Ainekset ainekset;
    private Reseptikirjasto kirjasto;
    
    public Kayttoliittyma() {
  
    }

    public void kaynnista() {
        
            kirjasto = new Reseptikirjasto();
            
            kirjasto.lisaaRuokalaji(new Ruokalaji("AAMIAINEN"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("LOUNAS"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("PÄIVÄLLINEN"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("JÄLKIRUOKA"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("PIKKUPURTAVAA"));
            
      
            while (true) {
                
                System.out.println("");
                System.out.println("**************");
                System.out.println("RESEPTIVARASTO");
                System.out.println("**************");
                System.out.println("");
                System.out.println("Valitse toiminnon numero:");
                System.out.println("1 Lisää resepti");
                System.out.println("2 Muokkaa reseptiä");
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
                    
                    System.out.println("Mikä ruokalaji?");
                    System.out.println(kirjasto.toString());
                    int laji = Integer.parseInt(lukija.nextLine());
                    System.out.println("");
                
                    System.out.print("Reseptin nimi: ");
                    String nimi = lukija.nextLine();
                    
                    ainekset = new Ainekset();
                    
                    lisaaAineksia();
                    
                    while (true) {
                        
                        System.out.println("Lisätäänkö lisää aineksia? (K/E)");
                        
                        String komento = lukija.nextLine();
                        
                        if (komento.equals("K")) {
                            lisaaAineksia();
                        }
                        
                        if (komento.equals("E")) {
                            break;
                        }
                        
                    }
                    
                    System.out.println("");
                    System.out.println("Kirjoita valmistusohjeet:");
                    String ohjeet = lukija.nextLine();
                    
                    kirjasto.lisaaResepti(laji, new Resepti(nimi, ainekset, ohjeet));
                    
                    System.out.println("");
                    System.out.println("");
                }
                
                if (kasky.equals("2")) {
                
                    //Toteutetaan myöhemmin
                }
                
                if (kasky.equals("3")) {
                    
                    hakuRuokalajista();
                       
                }
                
                if (kasky.equals("4")) {
                    
                    System.out.println("KAIKKI RESEPTIT");
                    System.out.println("");
                
                    System.out.println(kirjasto.listaaKaikkiReseptit());
                    
                    System.out.println("Hae resepti? (K/E)");
                    String haetaanko = lukija.nextLine();
                    
                    if (haetaanko.equals("K")) {
                        
                        hakuRuokalajista();
                        
                    }
                    
                    if (haetaanko.equals("E")) {
                        
                    }
                    
                }
                
                if (kasky.equals("5")) {
                
<<<<<<< HEAD
                    //Toteutetaan myöhemmin
=======
                    System.out.println("HAKU RESEPTIN NIMEN MUKAAN");
                    System.out.println("");
                
                    System.out.print("Kirjoita reseptin nimi: ");
                    String nimihaku = lukija.nextLine();
                    
                    System.out.println("");
                    System.out.println(kirjasto.haeNimella(nimihaku));
                    
>>>>>>> 31f038224b703b6a0a26d682795040762179658b
                }
                
                if (kasky.equals("6")) {
                
<<<<<<< HEAD
                    //Toteutetaan myöhemmin
=======
                    System.out.println("HAKU AINESOSAN MUKAAN");
                    System.out.println("");
                
                    System.out.print("Kirjoita ainesosan nimi: ");
                    String aineshaku = lukija.nextLine();
                    
                    System.out.println("");
                    System.out.println(kirjasto.haeAinesosalla(aineshaku));
                    
>>>>>>> 31f038224b703b6a0a26d682795040762179658b
                }
                
                if (kasky.equals("7")) {
                
                    break;
                }
                
            }
                
    }

    
    
    public void lisaaAineksia() {
        
        System.out.println("");
        
        System.out.println("Lisää ainesosa");
        
        System.out.print("Aines: ");
        String ainesNimi = lukija.nextLine();
                        
        System.out.print("Määrä: ");
        String ainesMaara = lukija.nextLine();
                        
        ainekset.lisaaAines(new Ainesosa(ainesNimi, ainesMaara));
        
        System.out.println("");
                        
        
    }
    
    public void reseptinHaku(int num) {
        
        while (true) {
                        
            System.out.println(kirjasto.listaaRuokalajinReseptit(num));
            System.out.println("");
                        
            System.out.println("Minkä reseptin haluat?");
            int num2 = Integer.parseInt(lukija.nextLine());
            
            System.out.println("");
            System.out.println("---");
                        
            System.out.println(kirjasto.haeRuokalajista(num, num2));
            
            System.out.println("---");
            System.out.println("");
                        
            System.out.println("Haetaanko toinen resepti tästä ruokalajista? (K/E)");
            String valinta = lukija.nextLine();
                        
            if (valinta.equals("K")) {
                            
            }
                        
            if (valinta.equals("E")) {
                break;
            }
                        
        }
        
    }
    
    public void hakuRuokalajista() {
        
        System.out.println("RESEPTIHAKU RUOKALAJIN MUKAAN");
        System.out.println("");
                
        System.out.println("Valitse ruokalaji:");
        System.out.println(kirjasto.toString());
                    
        int num = Integer.parseInt(lukija.nextLine());
        System.out.println("");
                    
        reseptinHaku(num);
    }
}

    

