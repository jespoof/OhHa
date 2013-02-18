/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import reseptivarasto.domain.Ainesosa;

/**
 *
 * @author Johanna
 * Aineksenkuuntelija lisää ja poistaa käyttäjän antamia ainesosia reseptin kokoamista varten
 */
public class AineksenKuuntelija implements ActionListener{
    
    /**
    * Ainesosan nimi
    */
    private JTextField nimi;
    /**
    * Ainesosan määrä
    */
    private JTextField maara;
    /**
    * Ainesosien toString-muodot
    */
    private String[] aineksetToString;
    /**
    * Tallennetut Ainesosat
    */
    private ArrayList<Ainesosa> ainesosat;
    /**
    * Ainesosat listalla
    */
    private JList lista;
    
    public AineksenKuuntelija(JTextField nimi, JTextField maara, ArrayList<Ainesosa> ainesosat, JList lista) {
        this.nimi = nimi;
        this.maara = maara;
        this.ainesosat = ainesosat;
        this.lista = lista;
    }
    
    public AineksenKuuntelija(ArrayList<Ainesosa> ainesosat, JList lista) {
        this.ainesosat = ainesosat;
        this.lista = lista;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String napinNimi = ((JButton)ae.getSource()).getText();
        
        if (napinNimi.equals("Lisää")) {
            lisaa();
        } if (napinNimi.equals("Poista")) {
            poista();
        }
    }
    
    /**
    * Lisää Ainesosan
    */
    public void lisaa() {
        if(!nimi.getText().isEmpty() && !maara.getText().isEmpty()) {
                ainesosat.add(new Ainesosa(nimi.getText(),maara.getText()));
                listaaAinekset();
                nimi.setText("Ainesosa");
                maara.setText("Määrä");
            }
    }
    
    /**
    * Poistaa Ainesosan
    */
    public void poista() {
        int poistettava = lista.getSelectedIndex();
            
            if (!ainesosat.isEmpty() && poistettava > -1) {
                ainesosat.remove(poistettava);
                listaaAinekset();
            }
    }
    
    /**
    * Laittaa Ainesosat näkyviin listalle
    */
    public void listaaAinekset() {
        ArrayList<String> osat = new ArrayList<String>();
            
        for (Ainesosa osa : ainesosat) {
            osat.add(osa.toString());
        }
            
        aineksetToString = new String[osat.size()];
        aineksetToString = osat.toArray(aineksetToString);
        lista.setListData(aineksetToString);
    }
}
