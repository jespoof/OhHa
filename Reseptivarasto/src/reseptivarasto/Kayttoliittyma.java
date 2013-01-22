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
                    String nimi = lukija.nextLine();
                    
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
                    
                    kirjasto.lisaaResepti(laji, new Resepti(nimi, ainekset, ohjeet));
                    
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
                    
                    if (kirjasto.haeNimella(nimihaku).equals("Ei hakutuloksia")) {
                        System.out.println(kirjasto.haeNimella(nimihaku));
                    }else {
                        System.out.println(kirjasto.haeNimella(nimihaku));
                        mikaHakutuloksista();
                    }
                    
                }
                
                if (kasky.equals("6")) {
                

                    System.out.println("HAKU AINESOSAN MUKAAN");
                    System.out.println("");
                
                    System.out.print("Kirjoita ainesosan nimi: ");
                    String aineshaku = lukija.nextLine();
                    
                    System.out.println("");
                    
                    if (kirjasto.haeAinesosalla(aineshaku).equals("Ei hakutuloksia")) {
                        System.out.println(kirjasto.haeAinesosalla(aineshaku));
                    }else {
                        System.out.println(kirjasto.haeAinesosalla(aineshaku));
                        mikaHakutuloksista();
                    }
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
                        
            int num2 = 0;
            
            while (true) {
                System.out.println("Minkä reseptin haluat?");
            
                try {
                    num2 = Integer.parseInt(lukija.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("Et syöttänyt kunnollista numeroa.");
                }
            }
            
            System.out.println("");
            System.out.println("---");
                        
            System.out.println(kirjasto.haeRuokalajista(num, num2));
            
            System.out.println("---");
            System.out.println("");
                        
            System.out.println("Haetaanko toinen resepti tästä ruokalajista? (K/E)");
            String valinta = lukija.nextLine();
                        
            if (valinta.equals("K")) {
            }else if (valinta.equals("E")) {
                break;
            }
        }
    }
    
    
    public void hakuRuokalajista() {
        
        System.out.println("RESEPTIHAKU RUOKALAJIN MUKAAN");
        System.out.println("");
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
            }
        }
                
        System.out.println("");
        reseptinHaku(num);
    }
    
    
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
        System.out.println(kirjasto.haeTuloksista(haku));
        } catch (Exception e) {
        System.out.println("Reseptiä ei löydy.");
        }
        
        System.out.println("---");
        System.out.println("");
        
    }
}

    

