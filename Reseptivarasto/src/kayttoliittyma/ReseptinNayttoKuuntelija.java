/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import reseptivarasto.domain.Reseptikirjasto;

/**
 *
 * @author Johanna
 */
public class ReseptinNayttoKuuntelija implements ActionListener{
    
    private JComboBox laji;
    private JTextArea reseptiNaytto;
    private Reseptikirjasto kirjasto;
    
    public ReseptinNayttoKuuntelija(JComboBox laji, JTextArea reseptiNaytto, Reseptikirjasto kirjasto) {
        this.laji = laji;
        this.reseptiNaytto = reseptiNaytto;
        this.kirjasto = kirjasto;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JComboBox cb = (JComboBox)ae.getSource();
        int reseptiNumero = cb.getSelectedIndex()-1;
        
        if (reseptiNumero > -1) {
            reseptiNaytto.setText(kirjasto.getResepti(laji.getSelectedIndex()-1, reseptiNumero).toString());
        }
      }
}
