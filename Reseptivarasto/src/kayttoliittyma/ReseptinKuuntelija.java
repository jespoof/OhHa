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
    
    private JComboBox laji;
    private JComboBox reseptiC;
    private JTextField nimi;
    private Ainekset ainekset;
    private ArrayList<Ainesosa> ainesosat;
    private JTextArea ohjeet;
    private Reseptikirjasto kirjasto;
    private JList aineslista;
    private RuokalajienKasittelija ruokalajikasittelija;
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
        this.reseptiC = reseptiC;
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
        }
        if (napinNimi.equals("Tallenna ")) {
            
            kirjasto.reseptinPoisto(laji.getSelectedIndex()-1, reseptiC.getSelectedIndex()-1);
            tallennus();
            reseptiC.setSelectedIndex(0);
        }
        if (napinNimi.equals("Peruuta")) {
            tyhjennys();
        }
        if (napinNimi.equals("Poista resepti")) {
            
            if (laji.getSelectedIndex() != 0 && reseptiC.getSelectedIndex() !=0) {
                kirjasto.reseptinPoisto(laji.getSelectedIndex()-1, reseptiC.getSelectedIndex()-1);
            
                String r[] = new String[kirjasto.getRuokalajinReseptit(laji.getSelectedIndex()-1).size()];
                r = kirjasto.getRuokalajinReseptit(laji.getSelectedIndex()-1).toArray(r);
                reseptiC.setModel(new JComboBox(r).getModel());
                reseptiC.insertItemAt("---", 0);
                reseptiC.setSelectedIndex(0);
            
                try {
                    ruokalajikasittelija.kirjoitaRuokalajit(kirjasto.getRuokalajit());
                    } catch (IOException ex) {
                        Logger.getLogger(ReseptinKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
                       ilmoitus.setText("Reseptin poistaminen epäonnistui");
                }
                
                tyhjennys();
                ilmoitus.setText("Resepti poistettu");
            } else {
            ilmoitus.setText("Olet tehnyt vääriä valintoja");
            }
        }
    }
    
    public void tyhjennys() {
        nimi.setText("");
        ohjeet.setText("");
        ainesosat.clear();
        ainekset = new Ainekset();
        aineslista.setListData(new String[0]);
        laji.setSelectedIndex(0);
    }
    
    public void tallennus() {
        
        if (laji.getSelectedIndex()!=0 && !ainesosat.isEmpty() && !nimi.getText().isEmpty() && !ohjeet.getText().isEmpty()) {
                    for (Ainesosa ainesosa : ainesosat) {
                    ainekset.lisaaAines(ainesosa);
                }
        
                    String nimiS = nimi.getText();
                    String ohjeetS = ohjeet.getText();
                    int lajiI = laji.getSelectedIndex()-1;
        
                    kirjasto.lisaaResepti(lajiI, new Resepti (nimiS, ainekset, ohjeetS));
                
                    try {
                        ruokalajikasittelija.kirjoitaRuokalajit(kirjasto.getRuokalajit());
                    } catch (IOException ex) {
                        Logger.getLogger(ReseptinKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
                        ilmoitus.setText("Reseptin tallentaminen epäonnistui");
                    }
                
                    tyhjennys();
                    ilmoitus.setText("Resepti " + nimiS + " tallennettu");
        } else {
            ilmoitus.setText("Olet tehnyt vääriä valintoja");
        }
    }
    
}
