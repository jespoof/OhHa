/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import reseptivarasto.domain.Resepti;
import reseptivarasto.domain.Reseptikirjasto;

/**
 *
 * @author Johanna
 */
public class HakusananKuuntelija implements ActionListener{
    
    private JTextField hakusana;
    private JComboBox nimiVaiAines;
    private JList hakutulos;
    private Reseptikirjasto kirjasto;
    
    public HakusananKuuntelija (JComboBox nimiVaiAines, JTextField hakusana, JList hakutulos, Reseptikirjasto kirjasto) {
        this.hakusana = hakusana;
        this.nimiVaiAines = nimiVaiAines;
        this.hakutulos = hakutulos;
        this.kirjasto = kirjasto;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (!hakusana.getText().equals("")) {
           if (nimiVaiAines.getSelectedIndex()==0) {
                haeNimella();
            } if (nimiVaiAines.getSelectedIndex()==1) {
                haeAinesosalla();
            } 
        }
    }
    
    public void haeNimella() {
        ArrayList<String> hakutuloksetS = kirjasto.haeNimellaALString(hakusana.getText());
        
        if (hakutuloksetS.size() > 0) {
            String r[] = new String[hakutuloksetS.size()];
            r = hakutuloksetS.toArray(r);
            hakutulos.setListData(r);
        }
    }
    
    public void haeAinesosalla() {
        ArrayList<String> hakutuloksetS = kirjasto.haeAinesosallaALString(hakusana.getText());
        
        if (hakutuloksetS.size() > 0) {
            String r[] = new String[hakutuloksetS.size()];
            r = hakutuloksetS.toArray(r);
            hakutulos.setListData(r);
        }
    }
}
    

