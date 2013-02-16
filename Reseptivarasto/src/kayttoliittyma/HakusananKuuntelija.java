/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import reseptivarasto.domain.Reseptikirjasto;

/**
 *
 * @author Johanna
 * 
 * HakusananKuuntelija näyttää käyttäjän antaman hakusanan hakutulokset listana
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
    
    /**
    * Hakee nimen perusteella
    */
    public void haeNimella() {
        ArrayList<String> hakutulokset = kirjasto.haeNimellaALString(hakusana.getText());
        
        hakuListalle(hakutulokset);
       
    }
    
    /**
    * Hakee ainesosan perusteella
    */
    public void haeAinesosalla() {
        ArrayList<String> hakutulokset = kirjasto.haeAinesosallaALString(hakusana.getText());
        
        hakuListalle(hakutulokset);
        
    }
    
    /**
    * Laittaa hakutulokset näkyviin listalle
    */
    public void hakuListalle(ArrayList<String> hakutulokset) {
        if (hakutulokset.size() > 0) {
            String r[] = new String[hakutulokset.size()];
            r = hakutulokset.toArray(r);
            hakutulos.setListData(r);
        }
    } 
}
    

