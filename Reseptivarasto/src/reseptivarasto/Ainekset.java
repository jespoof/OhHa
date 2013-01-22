/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptivarasto;

import java.util.ArrayList;

/**
 *
 * @author Johanna
 */
public class Ainekset {
    
    private ArrayList<Ainesosa> aines;
    private ArrayList<String> lista;
    
    public Ainekset () {
        
        this.aines = new ArrayList<Ainesosa>();
        this.lista = new ArrayList<String>();
        
    }
    
    public void lisaaAines(Ainesosa ainesosa) {
        
        this.aines.add(ainesosa);
        this.lista.add(ainesosa.toString());
        
    }
    
<<<<<<< HEAD
=======
    public boolean onkoAines (String haku) {
        
        for (Ainesosa ainesosa : aines) {
            
            if (ainesosa.getNimi().equals(haku)){
                return true;
            }
            
        }
        
        return false;
    }
    
>>>>>>> 31f038224b703b6a0a26d682795040762179658b
    
    @Override
    public String toString() {
        
        String ainekset = "";
        
        for (String ainesosaJaMaara : lista) {
            ainekset += ainesosaJaMaara + "\n";
        }
        
        return ainekset;
    }
}
