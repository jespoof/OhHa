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
 * ReseptiValinnanKuuntelija mahdollistaa valitun reseptin tietojen näyttämisen Muokkaa reseptiä -kohdassa.
 */
public class ReseptiValinnanKuuntelija implements ActionListener{
    private JComboBox laji;
    private JTextField nimi;
    private GraafinenKayttoliittyma GUI;
    private ArrayList<Ainesosa> ainesosat;
    private JList aineslista;
    private JTextArea ohjeet;
    private Reseptikirjasto kirjasto;
    
    public ReseptiValinnanKuuntelija(JComboBox laji, JTextField nimi, ArrayList<Ainesosa> ainesosat, JList aineslista, JTextArea ohjeet, Reseptikirjasto kirjasto) {
        this.laji = laji;
        this.nimi = nimi;
        this.ainesosat = ainesosat;
        this.aineslista = aineslista;
        this.ohjeet = ohjeet;
        this.kirjasto = kirjasto;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JComboBox cb = (JComboBox)ae.getSource();
        int reseptiNumero = cb.getSelectedIndex();
        
        Resepti resepti = kirjasto.getResepti(laji.getSelectedIndex()-1, reseptiNumero);
        
        nimi.setText(resepti.getNimi());
        ArrayList<Ainesosa> ainesosat1  = resepti.getAineksetLista();
        
        ainesosat.clear();
        
        for (Ainesosa osa : ainesosat1) {
            ainesosat.add(osa);
        }
        
        ArrayList<String> osat = new ArrayList<String>();
            
                for (Ainesosa osa : ainesosat1) {
                    osat.add(osa.toString());
                }
            
                String [] lista = new String[osat.size()];
                lista = osat.toArray(lista);
                aineslista.setListData(lista);
        
        ohjeet.setText(resepti.getOhjeet());
    }
    
}
