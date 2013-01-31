/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

/**
 *
 * @author Johanna
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraafinenKayttoliittyma implements ActionListener {

    private static final String ALKU = "AlkuP";
    private static final String LISAA = "LiaaaP";
    private static final String MUOKKAA = "MuokkaaP";
    private static final String LAJILISTAUS = "LajilistausP";
    private static final String KAIKKI = "KaikkiP";
    private static final String HAKU = "HakuP";
    private JPanel kortit;
    private JButton lisaaB = new JButton("Lisää resepti");
    private JButton muokkaaB = new JButton("Muokkaa reseptiä");
    private JButton hakuB = new JButton("Hae resepti");
    private JButton lajiListausB = new JButton("Listaa lajin mukaan");
    private JButton kaikkiListausB = new JButton("Listaa kaikki reseptit");
    private JButton lopetaB = new JButton("Lopeta");
    
    public GraafinenKayttoliittyma() {
        
    }
    
    public void kaynnista() {
        JFrame frame = new JFrame("RESEPTIVARASTO");
        
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(800, 500));
        frame.add(nappiPaneeli(), BorderLayout.WEST);
        frame.add(korttiPaneeli(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public JPanel nappiPaneeli() {
        JPanel napit = new JPanel(new GridLayout(6, 1));
        
        napit.add(lisaaB);
        napit.add(muokkaaB);
        napit.add(hakuB);
        napit.add(lajiListausB);
        napit.add(kaikkiListausB);
        napit.add(lopetaB);
        
        lisaaB.addActionListener(this);
        muokkaaB.addActionListener(this);
        lajiListausB.addActionListener(this);
        kaikkiListausB.addActionListener(this);
        hakuB.addActionListener(this);
        lopetaB.addActionListener(this);
        
        return napit;
    }

    public JPanel korttiPaneeli() {
        kortit = new JPanel(new CardLayout());
        kortit.add(alku(), ALKU);
        kortit.add(lisaa(), LISAA);
        kortit.add(muokkaa(), MUOKKAA);
        kortit.add(listaaLaji(), LAJILISTAUS);
        kortit.add(listaaKaikki(), KAIKKI);
        kortit.add(haku(), HAKU);

        return kortit;
    }

    private JPanel alku() {
        JPanel paneeli = new JPanel();
        JLabel alkuL = new JLabel("Tervetuloa Reseptivarastoon!");
        paneeli.add(alkuL);
        
        return paneeli;
    }
    
    private JPanel lisaa() {
        JPanel paneeli = new JPanel();
        JLabel lisaaL = new JLabel("Lisää");
        paneeli.add(lisaaL);
        
        return paneeli;
    }
    
    private JPanel muokkaa() {
        JPanel paneeli = new JPanel();
        JLabel muokkaaL = new JLabel("Muokkaa");
        paneeli.add(muokkaaL);
        
        return paneeli;
    }
    
    private JPanel listaaLaji() {
        JPanel paneeli = new JPanel();
        JLabel lajiL = new JLabel("Lajilistaus");
        paneeli.add(lajiL);
        
        return paneeli;
    }
    
    private JPanel listaaKaikki() {
        JPanel paneeli = new JPanel();
        JLabel kaikkiL = new JLabel("Kaikki");
        paneeli.add(kaikkiL);
        
        return paneeli;
    }
    
    private JPanel haku() {
        JPanel paneeli = new JPanel();
        JLabel hakuL = new JLabel("Haku");
        paneeli.add(hakuL);
        
        return paneeli;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) (kortit.getLayout());
        
        if (e.getSource() == lisaaB) {
            cardLayout.show(kortit, LISAA);
        }
        if (e.getSource() == muokkaaB) {
            cardLayout.show(kortit, MUOKKAA);
        }
        if (e.getSource() == lajiListausB) {
            cardLayout.show(kortit, LAJILISTAUS);
        }
        if (e.getSource() == kaikkiListausB) {
            cardLayout.show(kortit, KAIKKI);
        }
        if (e.getSource() == hakuB) {
            cardLayout.show(kortit, HAKU);
        }
        if (e.getSource() == lopetaB) {
            System.exit(0);
        }
    }
}
