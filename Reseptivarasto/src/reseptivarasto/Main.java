/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto;

import kayttoliittyma.Tekstikayttoliittyma;
import java.io.IOException;
import kayttoliittyma.GraafinenKayttoliittyma;

/**
 *
 * @author Johanna
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //Tekstikayttoliittyma kayttoliittyma = new Tekstikayttoliittyma();
        //kayttoliittyma.kaynnista();  
        
        GraafinenKayttoliittyma gKayttoliittyma = new GraafinenKayttoliittyma();
        gKayttoliittyma.kaynnista();
        
    }
}
