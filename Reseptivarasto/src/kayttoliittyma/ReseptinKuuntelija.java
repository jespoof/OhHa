/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import reseptivarasto.domain.Ainekset;
import reseptivarasto.domain.Ainesosa;
import reseptivarasto.domain.Resepti;
import reseptivarasto.domain.Reseptikirjasto;
import tiedostonkasittelija.RuokalajienKasittelija;

/**
 *
 * @author Johanna
 * ReseptinKuuntelijalla tallennetaan uusia ja muokattuja reseptejä 
 * sekä myös poistetaan niitä.
 */
public class ReseptinKuuntelija implements ActionListener{
    /**
    * Reseptin Ruokalajin valitaan
    */
    private JComboBox laji;
    /**
    * Jos kyseessä on Reseptin muokkaus, muokattava Resepti valitaan
    */
    private JComboBox reseptiValinta;
    /**
    * Reseptin nimi
    */
    private JTextField nimi;
    /**
    * Reseptin Ainekset
    */
    private Ainekset ainekset;
    /**
    * Reseptin Ainesosat
    */
    private ArrayList<Ainesosa> ainesosat;
    /**
    * Reseptin ohjeet
    */
    private JTextArea ohjeet;
    /**
    * Reseptiirjasto, johon Resepti tallennetaan/poistetaan
    */
    private Reseptikirjasto kirjasto;
    /**
    * Ainesosat listalla
    */
    private JList aineslista;
    /**
    * Tallentaa Reseptin tiedostoon
    */
    private RuokalajienKasittelija ruokalajikasittelija;
    /**
    * Ilmoittaa toiminnon onnistumisen tai epäonnistumisen
    */
    private JLabel ilmoitus;
    
    public ReseptinKuuntelija(JComboBox laji, JTextField nimi, ArrayList<Ainesosa> ainesosat, JTextArea ohjeet, Reseptikirjasto kirjasto, JList aineslista, RuokalajienKasittelija ruokalajikasittelija, JLabel ilmoitus) {
        this.laji = laji;
        this.nimi = nimi;
        this.ainesosat = ainesosat;
        this.ohjeet = ohjeet;
        this.kirjasto = kirjasto;
        this.aineslista = aineslista;
        this.ruokalajikasittelija = ruokalajikasittelija;
        this.ilmoitus = ilmoitus;
        ainekset = new Ainekset();
    }
    
    public ReseptinKuuntelija(JComboBox laji, JComboBox reseptiC, JTextField nimi, ArrayList<Ainesosa> ainesosat, JTextArea ohjeet, Reseptikirjasto kirjasto, JList aineslista, RuokalajienKasittelija ruokalajikasittelija, JLabel ilmoitus) {
        this.laji = laji;
        this.reseptiValinta = reseptiC;
        this.nimi = nimi;
        this.ainesosat = ainesosat;
        this.ohjeet = ohjeet;
        this.kirjasto = kirjasto;
        this.aineslista = aineslista;
        this.ruokalajikasittelija = ruokalajikasittelija;
        this.ilmoitus = ilmoitus;
        ainekset = new Ainekset();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String napinNimi = ((JButton)ae.getSource()).getText();
        
        if (napinNimi.equals("Tallenna")) {
            tallennus();
        } if (napinNimi.equals("Tallenna ")) {
            kirjasto.reseptinPoisto(laji.getSelectedIndex()-1, reseptiValinta.getSelectedIndex()-1);
            tallennus();
            reseptiValinta.setSelectedIndex(0);
        } if (napinNimi.equals("Peruuta")) {
            tyhjennys();
        } if (napinNimi.equals("Poista resepti")) {
            poisto();
        }
    }
    
    /**
    * Tyhjentää kentät
    */
    public void tyhjennys() {
        nimi.setText("");
        ohjeet.setText("");
        ainesosat.clear();
        ainekset = new Ainekset();
        aineslista.setListData(new String[0]);
        laji.setSelectedIndex(0);
    }
    
    /**
    * Tallentaa reseptin
    */
    public void tallennus() {
        
        if (laji.getSelectedIndex()!=0 && !ainesosat.isEmpty() && !nimi.getText().isEmpty() && !ohjeet.getText().isEmpty() && onkoSamannimisia() == false) {
            for (Ainesosa ainesosa : ainesosat) {
                ainekset.lisaaAines(ainesosa);
            }
        
            String nimiS = nimi.getText();
            String ohjeetS = ohjeet.getText();
            int lajiI = laji.getSelectedIndex()-1;
            kirjasto.lisaaResepti(lajiI, new Resepti (nimiS, ainekset, ohjeetS));
            tiedostoonKirjoittaminen("Reseptin tallentaminen epäonnistui");
            tyhjennys();
            ilmoitus.setText("Resepti tallennettu");
        } else {
            ilmoitus.setText("Olet tehnyt vääriä valintoja");
        }
    }
    
    /**
    * Poistaa reseptin
    */
    public void poisto() {
        if (laji.getSelectedIndex() != 0 && reseptiValinta.getSelectedIndex() !=0) {
            kirjasto.reseptinPoisto(laji.getSelectedIndex()-1, reseptiValinta.getSelectedIndex()-1);
            String r[] = new String[kirjasto.getRuokalajinReseptit(laji.getSelectedIndex()-1).size()];
            r = kirjasto.getRuokalajinReseptit(laji.getSelectedIndex()-1).toArray(r);
            reseptiValinta.setModel(new JComboBox(r).getModel());
            reseptiValinta.insertItemAt("---", 0);
            reseptiValinta.setSelectedIndex(0);
            tiedostoonKirjoittaminen("Reseptin poistaminen epäonnistui");
            tyhjennys();
            ilmoitus.setText("Resepti poistettu");
        } else {
            ilmoitus.setText("Olet tehnyt vääriä valintoja");
        }
    }
    
    /**
    * Tarkistaa, onko Ruokalajissa samannimisiä reseptejä
    */
    public boolean onkoSamannimisia() {
        for (String n : kirjasto.getRuokalajinReseptit(laji.getSelectedIndex()-1)) {
            if (n.equals(nimi.getText())) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * Kirjoittaa reseptit tiedostoon
    */
    public void tiedostoonKirjoittaminen(String ilm) {
        try {
            ruokalajikasittelija.kirjoitaRuokalajit(kirjasto.getRuokalajit());
        } catch (IOException ex) {
            Logger.getLogger(ReseptinKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
            ilmoitus.setText(ilm);
        }
    }
}
