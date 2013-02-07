/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import reseptivarasto.domain.Resepti;
import reseptivarasto.domain.Reseptikirjasto;

/**
 *
 * @author Johanna
 */
public class NimiHaunKuuntelija implements ActionListener{
    
    private JTextField haku;
    private JTextArea reseptiA;
    private Reseptikirjasto kirjasto;
    
    public NimiHaunKuuntelija (JTextField haku, JTextArea reseptiA, Reseptikirjasto kirjasto){
        this.reseptiA = reseptiA;
        this.haku = haku;
        this.kirjasto = kirjasto;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JComboBox cb = (JComboBox)ae.getSource();
        int luku = cb.getSelectedIndex();
        
        ArrayList<Resepti> haetut = kirjasto.haeNimellaALResepti(haku.getText());
        
        if (haetut.isEmpty()) {
            
        } else {
            reseptiA.setText(haetut.get(luku).toString());
        }
     }
    
}
