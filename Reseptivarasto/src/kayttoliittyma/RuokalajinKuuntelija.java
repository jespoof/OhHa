/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import reseptivarasto.domain.Reseptikirjasto;

/**
 *
 * @author Johanna
 * RuokalajinKuuntelijan avulla voidaan valita ruokalaji reseptien esittämistä
 * varten.
 */
public class RuokalajinKuuntelija implements ActionListener{
    
    private JComboBox reseptinValitsin;
    private Reseptikirjasto kirjasto;
    
    public RuokalajinKuuntelija(JComboBox reseptinValitsin, Reseptikirjasto kirjasto) {
        this.reseptinValitsin = reseptinValitsin;
        this.kirjasto = kirjasto;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JComboBox cb = (JComboBox)ae.getSource();
        int luku = cb.getSelectedIndex()-1;
        
        if (luku < 0) {
            
            String r[] = new String[0];
            reseptinValitsin.setModel(new JComboBox(r).getModel());
        
        } else {
            
            String r[] = new String[kirjasto.getRuokalajinReseptit(luku).size()];
            r = kirjasto.getRuokalajinReseptit(luku).toArray(r);
            reseptinValitsin.setModel(new JComboBox(r).getModel());
        
            if (r.length > 1) {
                reseptinValitsin.setSelectedIndex(0);
            }
        }
        
    }
    
    
}
