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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import reseptivarasto.domain.Resepti;
import reseptivarasto.domain.Reseptikirjasto;

/**
 *
 * @author Johanna
 * HaunKuuntelija näyttää hakutuloksista valitun reseptin
 */
public class HaunKuuntelija implements ActionListener{
    
    /**
    * Haluaako käyttäjä hakea nimen vai ainesosan perusteella
    */
    private JComboBox nimiVaiAines;
    /**
    * Hakusana
    */
    private JTextField haku;
    /**
    * Hakutulokset listalla
    */
    private JList tulokset;
    /**
    * Valittu Resepti näytetään tässä
    */
    private JTextArea reseptiA;
    /**
    * Reseptikirjasto, josta haetaan
    */
    private Reseptikirjasto kirjasto;
    
    public HaunKuuntelija (JComboBox nimiVaiAines, JTextField haku, JList tulokset, JTextArea reseptiA, Reseptikirjasto kirjasto){
        this.nimiVaiAines = nimiVaiAines;
        this.reseptiA = reseptiA;
        this.haku = haku;
        this.tulokset = tulokset;
        this.kirjasto = kirjasto;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(tulokset.getLastVisibleIndex()>-1) {
            if (nimiVaiAines.getSelectedIndex()==0) {
                nimiHaku();
            } if (nimiVaiAines.getSelectedIndex()==1) {
                ainesosaHaku();
            }
        }
     }
    
    /**
    * Laittaa nimen perusteella haetut reseptit ArrayListille
    */
    public void nimiHaku() {
        ArrayList<Resepti> haetut = kirjasto.haeNimellaALResepti(haku.getText());
        tuloksetNaytetaan(haetut); 
    }
    
    /**
    * Laittaa ainesosan perusteella haetut reseptit ArrayListille
    */
    public void ainesosaHaku() {
        ArrayList<Resepti> haetut = kirjasto.haeAinesosallaALResepti(haku.getText());
        tuloksetNaytetaan(haetut);
    }
    
    /**
    * Näyttää ArrayListiltä käyttäjän valitseman reseptin JTextAreassa
    */
    public void tuloksetNaytetaan(ArrayList<Resepti> haetut) {
         if (haetut.size()>0) {
            reseptiA.setText(haetut.get(tulokset.getSelectedIndex()).toString());
            reseptiA.setCaretPosition(0);
        }
    }
}
