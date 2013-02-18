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
import reseptivarasto.domain.Ainesosa;
import reseptivarasto.domain.Resepti;
import reseptivarasto.domain.Reseptikirjasto;

/**
 *
 * @author Johanna
 * ReseptiValinnanKuuntelija mahdollistaa valitun reseptin tietojen näyttämisen 
 * Muokkaa reseptiä -kohdassa.
 */
public class ReseptiValinnanKuuntelija implements ActionListener{
    
    /**
    * Mihin ruokalajiin Resepti kuuluu
    */
    private JComboBox laji;
    /**
    * Reseptin nimi
    */
    private JTextField nimi;
    /**
    * Reseptin ainesosat
    */
    private ArrayList<Ainesosa> ainesosat;
    /**
    * Reseptin ainesosat ennen muokkauksen alkua
    */
    private ArrayList<Ainesosa> alunAinesosat;
    /**
    * Ainesosat listalla
    */
    private JList aineslista;
    /**
    * Reseptin ohjeet
    */
    private JTextArea ohjeet;
    /**
    * Reseptikirjasto, johon resepti tallennetaan
    */
    private Reseptikirjasto kirjasto;
    
    public ReseptiValinnanKuuntelija(JComboBox laji, JTextField nimi, ArrayList<Ainesosa> ainesosat, JList aineslista, JTextArea ohjeet, Reseptikirjasto kirjasto) {
        this.laji = laji;
        this.nimi = nimi;
        this.ainesosat = ainesosat;
        this.aineslista = aineslista;
        this.ohjeet = ohjeet;
        this.kirjasto = kirjasto;
        alunAinesosat = new ArrayList<Ainesosa>();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JComboBox cb = (JComboBox)ae.getSource();
        int reseptiNumero = cb.getSelectedIndex()-1;
        
        if (reseptiNumero > -1) {
            naytaResepti(reseptiNumero);
        } else {
            tyhjennys();
        }
    }
    
    /**
    * Näyttää reseptin tiedot kentissä
    */
    public void naytaResepti(int reseptiNumero) {
        Resepti resepti = kirjasto.getResepti(laji.getSelectedIndex()-1, reseptiNumero);
        nimi.setText(resepti.getNimi());
        siirraAinesosatUuteenArrayListiin(resepti);
        ainesosatStringille();
        ohjeet.setText(resepti.getOhjeet());
    }
    
    /**
    * Siirtää reseptiin tallennetut Ainesosat toiseen ArrayListiin muokkaamista
    * varten
    */
    public void siirraAinesosatUuteenArrayListiin(Resepti resepti) {
        alunAinesosat  = resepti.getAineksetLista();
        ainesosat.clear();
        
        for (Ainesosa osa : alunAinesosat) {
            ainesosat.add(osa);
        }
    }
    
    /**
    * Laittaa Reseptiin tallennettujen Ainesosien toString-muodot ArrayListille ja käyttäjän näkyville
    */
    public void ainesosatStringille() {
        ArrayList<String> osatToString = new ArrayList<String>();
            
        for (Ainesosa osa : alunAinesosat) {
            osatToString.add(osa.toString());
        }
                   
        String [] lista = new String[osatToString.size()];
        lista = osatToString.toArray(lista);
        aineslista.setListData(lista);
    }
    
    /**
    * Tyhjentää kentät
    */
    public void tyhjennys() {
        nimi.setText("");
        ainesosat.clear();
        aineslista.setListData(new String[0]);
        ohjeet.setText("");
    }
}
