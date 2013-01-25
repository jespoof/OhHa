/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto;

import java.io.IOException;

/**
 *
 * @author Johanna
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Tekstikayttoliittyma kayttoliittyma = new Tekstikayttoliittyma();
        kayttoliittyma.kaynnista();    
    }
}
