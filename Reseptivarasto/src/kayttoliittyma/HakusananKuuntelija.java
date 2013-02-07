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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import reseptivarasto.domain.Resepti;
import reseptivarasto.domain.Reseptikirjasto;

/**
 *
 * @author Johanna
 */
public class HakusananKuuntelija implements ActionListener{
    
    private JTextField nimiH;
    private JTextArea reseptiA;
    private JComboBox reseptiValinta;
    private JLabel reseptiValintaL;
    private Reseptikirjasto kirjasto;
    
    public HakusananKuuntelija (JTextField nimiH, JComboBox reseptiValinta, JLabel reseptiValintaL, Reseptikirjasto kirjasto) {
        this.nimiH = nimiH;
        this.reseptiValinta = reseptiValinta;
        this.reseptiValintaL = reseptiValintaL;
        this.kirjasto = kirjasto;
    }
    
    public HakusananKuuntelija (JTextField nimiH, JTextArea reseptiA, Reseptikirjasto kirjasto) {
        this.nimiH = nimiH;
        this.reseptiA = reseptiA;
        this.kirjasto = kirjasto;
    }
     
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String napinNimi = ((JButton)ae.getSource()).getText();
        
        if (napinNimi.equals("Hae")) {
            
            reseptiA.setText(kirjasto.haeNimellaALResepti(nimiH.getText()).get(0).toString());
        }
        if (napinNimi.equals("Hae ")) {
            ArrayList<String> hakutuloksetS = kirjasto.haeAinesosallaALString(nimiH.getText());
        
            if (hakutuloksetS.size() > 0) {
                String r[] = new String[hakutuloksetS.size()];
                r = hakutuloksetS.toArray(r);
                reseptiValinta.setModel(new JComboBox(r).getModel());
                reseptiValinta.setSelectedIndex(0);
                reseptiValintaL.setText("       Valitse resepti-->");
            } else {
                String r[] = new String[0];
                reseptiValinta.setModel(new JComboBox(r).getModel());
                reseptiValinta.insertItemAt("---", 0);
                reseptiValinta.setSelectedIndex(0);
            }
        }
        
    }

}
    

