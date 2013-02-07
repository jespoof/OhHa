/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

/**
 *
 * @author Johanna
 * GraafinenKayttoliittyma on ohjelman graafinen käyttöliittymä
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import reseptivarasto.domain.Ainesosa;
import reseptivarasto.domain.Resepti;
import reseptivarasto.domain.Reseptikirjasto;
import reseptivarasto.domain.Ruokalaji;
import tiedostonkasittelija.RuokalajienKasittelija;

public class GraafinenKayttoliittyma implements ActionListener {

    private static final String ALKU = "AlkuP";
    private static final String LISAA = "LiaaaP";
    private static final String MUOKKAA = "MuokkaaP";
    private static final String LAJILISTAUS = "LajilistausP";
    private static final String HAKU = "HakuP";
    
    private JPanel kortit;
    private JButton lisaaB = new JButton("Lisää resepti");
    private JButton muokkaaB = new JButton("Muokkaa reseptiä");
    private JButton hakuB = new JButton("Reseptihaku");
    private JButton lajiListausB = new JButton("Reseptit");
    private JButton lopetaB = new JButton("Lopeta");
    
    private Reseptikirjasto kirjasto;
    private RuokalajienKasittelija ruokalajikasittelija;
    
    private ArrayList<Ainesosa> ainesosat;
    private JList aineslista;
    
    private ArrayList<Resepti> haku;
    
    private JTextField nimiT;
    private JTextArea ohjeetA;
    private JComboBox lajiValinta;
    private JComboBox reseptiValinta;
    private JLabel ilmoitus;
    private JTextArea reseptiA;
    
    
    public GraafinenKayttoliittyma() throws IOException {
        
        ruokalajikasittelija = new RuokalajienKasittelija("kirjasto.txt");
        kirjasto = new Reseptikirjasto(ruokalajikasittelija.lueRuokalajit());
        ainesosat = new ArrayList<Ainesosa>();
        haku = new ArrayList<Resepti>();
        
    }
    
    public void kaynnista(){
        
        JFrame frame = new JFrame("RESEPTIVARASTO");
        
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(800, 500));
        frame.add(nappiPaneeli(), BorderLayout.WEST);
        frame.add(korttiPaneeli(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    private JPanel nappiPaneeli() {
        JPanel napit = new JPanel(new GridLayout(5, 1, 5, 5));
        
        napit.add(lisaaB);
        napit.add(muokkaaB);
        napit.add(lajiListausB);
        napit.add(hakuB);
        napit.add(lopetaB);
        
        lisaaB.addActionListener(this);
        muokkaaB.addActionListener(this);
        lajiListausB.addActionListener(this);
        hakuB.addActionListener(this);
        lopetaB.addActionListener(this);
        
        return napit;
    }

    private JPanel korttiPaneeli() {
        kortit = new JPanel(new CardLayout());
        kortit.add(alku(), ALKU);
        kortit.add(lisaa(), LISAA);
        kortit.add(muokkaa(), MUOKKAA);
        kortit.add(reseptit(), LAJILISTAUS);
        kortit.add(haku(), HAKU);

        return kortit;
    }

    private JPanel alku() {
        JPanel paneeli = new JPanel();
        JLabel alkuL = new JLabel("Tervetuloa Reseptivarastoon!");
        alkuL.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        paneeli.add(alkuL);
        
        return paneeli;
    }
    
    private JPanel lisaa() {
        
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        
        JPanel alusta = new JPanel();
        JPanel paa = paaPaneelinLuominen("Lisää resepti");
        
        JPanel ala = new JPanel();
        ala.setLayout(new GridLayout(1,2));
        ala.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        JPanel vasen = new JPanel();
        vasen.setLayout(new BoxLayout(vasen, BoxLayout.Y_AXIS));
        
        JPanel nimiP = new JPanel();
        JLabel nimiL = new JLabel("Reseptin nimi:");
        nimiT = new JTextField();
        nimiT.setPreferredSize(new Dimension(169,23));
        nimiP.add(nimiL);
        nimiP.add(nimiT);
        
        JPanel lajiP = new JPanel();
        JLabel lajiL = new JLabel("Ruokalaji:       ");
        lajiValinta = ruokalajit();
        lajiP.add(lajiL);
        lajiP.add(lajiValinta);
        
        JPanel aineksetP = ainekset();
        
        vasen.add(nimiP);
        vasen.add(lajiP);
        vasen.add(aineksetP);
        
        JPanel oikea = ohjePaneeli();
        
        ala.add(vasen);
        ala.add(oikea);
        
        JPanel loppu = new JPanel();
        loppu.setLayout(new GridLayout(1,2));
        
        JPanel ilmoitin = ilmoitin();
        
        JPanel vaih = new JPanel();
        vaih.setPreferredSize(new Dimension(1,1));
        vaih.setLayout(new FlowLayout());
        vaih.setBorder(BorderFactory.createEmptyBorder(5, 55, 0, 0));
        
        JButton tallennaB = new JButton("Tallenna");
        JButton peruB = new JButton("Peruuta");
        
        tallennaB.addActionListener(new ReseptinKuuntelija(lajiValinta, nimiT, ainesosat, ohjeetA, kirjasto, aineslista, ruokalajikasittelija, ilmoitus));
        peruB.addActionListener(new ReseptinKuuntelija(lajiValinta, nimiT, ainesosat, ohjeetA, kirjasto, aineslista, ruokalajikasittelija, ilmoitus));
        
        vaih.add(tallennaB);
        vaih.add(peruB);
        loppu.add(ilmoitin);
        loppu.add(vaih);
        
        paa.add(ala);
        paa.add(loppu);
        alusta.add(paa);
        paneeli.add(alusta);
        
        return paneeli;
    }
    
    private JPanel muokkaa() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        
        JPanel alusta = new JPanel();
        JPanel paa = paaPaneelinLuominen("Muokkaa reseptiä");
        
        JPanel yla = new JPanel();
        yla.setLayout(new BoxLayout(yla, BoxLayout.Y_AXIS));
        
        JPanel lajiJaResepti = new JPanel();
        lajiJaResepti.setLayout(new GridLayout(2,3));
        lajiJaResepti.setBorder(BorderFactory.createEmptyBorder(25, 25, 0, 25));
        
        JLabel lajiL = new JLabel("Valitse ruokalaji:");
        lajiValinta = ruokalajit();
        JLabel reseptiValintaL = new JLabel("Valitse resepti:");
        reseptiValinta = lajinReseptit();
        
        lajiValinta.addActionListener(new RuokalajinKuuntelija(reseptiValinta,kirjasto));
        lajiJaResepti.add(lajiL);
        lajiJaResepti.add(lajiValinta);
        lajiJaResepti.add(reseptiValintaL);
        lajiJaResepti.add(reseptiValinta);
        
        yla.add(lajiJaResepti);
        
        JPanel ala = new JPanel();
        ala.setLayout(new GridLayout(1,2));
        ala.setBorder(BorderFactory.createEmptyBorder(15, 25, 5, 25));
        JPanel vasen = new JPanel();
        vasen.setLayout(new BoxLayout(vasen, BoxLayout.Y_AXIS));
        
        JPanel nimiP = new JPanel();
        JLabel nimiL = new JLabel("Reseptin nimi:");
        nimiT = new JTextField();
        nimiT.setPreferredSize(new Dimension(169,23));
        nimiP.add(nimiL);
        nimiP.add(nimiT);
        
        JPanel aineksetP = ainekset();
        
        vasen.add(nimiP);
        vasen.add(aineksetP);
        
        JPanel oikea = ohjePaneeli();
        
        ala.add(vasen);
        ala.add(oikea);
        
        reseptiValinta.addActionListener(new ReseptiValinnanKuuntelija(lajiValinta, nimiT, ainesosat, aineslista, ohjeetA, kirjasto));
        
        JPanel ilmoitin = ilmoitin();
        
        JPanel vaih = new JPanel();
        vaih.setLayout(new FlowLayout());
        
        JButton tallennaB = new JButton("Tallenna ");
        tallennaB.addActionListener(new ReseptinKuuntelija(lajiValinta, reseptiValinta, nimiT, ainesosat, ohjeetA, kirjasto, aineslista, ruokalajikasittelija, ilmoitus));
        JButton peruB = new JButton("Peruuta");
        peruB.addActionListener(new ReseptinKuuntelija(lajiValinta, reseptiValinta, nimiT, ainesosat, ohjeetA, kirjasto, aineslista, ruokalajikasittelija, ilmoitus));
        JButton poistaB = new JButton("Poista resepti");
        poistaB.addActionListener(new ReseptinKuuntelija(lajiValinta, reseptiValinta, nimiT, ainesosat, ohjeetA, kirjasto, aineslista, ruokalajikasittelija, ilmoitus));
        
        vaih.add(tallennaB);
        vaih.add(peruB);
        vaih.add(poistaB);
        
        paa.add(yla);
        paa.add(ala);
        paa.add(vaih);
        paa.add(ilmoitin);
        
        alusta.add(paa);
        paneeli.add(alusta);
        
        return paneeli;
    }
    
    private JPanel reseptit() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        
        JPanel alusta = new JPanel();
        JPanel paa = paaPaneelinLuominen("Reseptit");
        
        JPanel yla = new JPanel();
        yla.setLayout(new BoxLayout(yla, BoxLayout.Y_AXIS));
        
        JPanel lajiJaResepti = new JPanel();
        lajiJaResepti.setLayout(new GridLayout(2,2));
        lajiJaResepti.setBorder(BorderFactory.createEmptyBorder(5, 25, 0, 25));
        
        JPanel lajiP = new JPanel();
        JLabel lajiL = new JLabel("Valitse ruokalaji:");
        lajiValinta = ruokalajit();
        lajiJaResepti.add(lajiL);
        lajiJaResepti.add(lajiValinta);
        
        JPanel reseptiP = new JPanel();
        JLabel reseptiValintaL = new JLabel("Valitse resepti:");
        reseptiValinta = lajinReseptit();
        lajiJaResepti.add(reseptiValintaL);
        lajiJaResepti.add(reseptiValinta);
        
        JPanel ala = reseptiAlus();
        
        lajiValinta.addActionListener(new RuokalajinKuuntelija(reseptiValinta, kirjasto));
        reseptiValinta.addActionListener(new ReseptinNayttoKuuntelija(lajiValinta, reseptiA, kirjasto));
        
        yla.add(lajiJaResepti);
        paa.add(yla);
        paa.add(ala);
        alusta.add(paa);
        paneeli.add(alusta);
        
        return paneeli;
    }
    
    private JPanel haku() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        
        JPanel alusta = new JPanel();
        JPanel paa = paaPaneelinLuominen("Hae reseptiä");
        
        JPanel yla = new JPanel();
        yla.setLayout(new BoxLayout(yla, BoxLayout.Y_AXIS));
        
        JPanel haut = new JPanel();
        haut.setLayout(new GridLayout(3,3));
        haut.setBorder(BorderFactory.createEmptyBorder(5, 25, 0, 25));
        
        JPanel nimiHP = new JPanel();
        JLabel nimiHL = new JLabel("Hae reseptin nimellä:");
        JTextField nimiH = new JTextField();
        JPanel nappi1 = new JPanel();
        JButton haeNimi = new JButton("Hae");
        nappi1.add(haeNimi);
        
        haut.add(nimiHL);
        haut.add(nimiH);
        haut.add(nappi1);
        
        JPanel ainesHP = new JPanel();
        JLabel ainesHL = new JLabel("Hae ainesosalla:");
        JTextField ainesH = new JTextField();
        JPanel nappi2 = new JPanel();
        JButton haeAines = new JButton("Hae ");
        nappi2.add(haeAines);
        
        haut.add(ainesHL);
        haut.add(ainesH);
        haut.add(nappi2);
        
        JLabel reseptiValintaL = new JLabel("");
        reseptiValinta = lajinReseptit();
        haut.add(reseptiValintaL);
        haut.add(reseptiValinta);
        
        JPanel ala = reseptiAlus();
        
        yla.add(haut);
        paa.add(yla);
        paa.add(ala);
        alusta.add(paa);
        paneeli.add(alusta);
        
        haeNimi.addActionListener(new HakusananKuuntelija(nimiH, reseptiA, kirjasto));
        haeAines.addActionListener(new HakusananKuuntelija(ainesH, reseptiValinta, reseptiValintaL, kirjasto));
        
        reseptiValinta.addActionListener(new AinesHaunKuuntelija(ainesH, reseptiA, kirjasto));
        
        
        return paneeli;
    }
    
    private JPanel paaPaneelinLuominen(String otsikko){
        JPanel paa = new JPanel();
        paa.setPreferredSize(new Dimension(600,460));
        paa.setLayout(new BoxLayout(paa, BoxLayout.Y_AXIS));
        paa.setBorder(BorderFactory.createTitledBorder(otsikko));
        return paa;
    }

    private JPanel ohjePaneeli(){
        JPanel oikea = new JPanel();
        oikea.setLayout(new BoxLayout(oikea, BoxLayout.Y_AXIS));
        oikea.setBorder(BorderFactory.createTitledBorder("Ohjeet"));
        ohjeetA = new JTextArea();
        ohjeetA.setLineWrap(true);
        ohjeetA.setWrapStyleWord(true);
        JScrollPane ohjeetP = new JScrollPane(ohjeetA);
        oikea.add(ohjeetP);
        return oikea;
    }

    private JPanel ilmoitin() {
        JPanel ilmoitin = new JPanel();
        ilmoitus = new JLabel("");
        ilmoitin.add(ilmoitus);
        return ilmoitin;
    }

    private JPanel ainekset() {
        
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        paneeli.setBorder(BorderFactory.createTitledBorder("Ainekset"));
        
        JPanel aines = new JPanel();
        JTextField nimi = new JTextField("Ainesosa");
        nimi.setPreferredSize(new Dimension(100,23));
        JTextField maara = new JTextField("Määrä");
        maara.setPreferredSize(new Dimension(66,23));
        
        JButton lisaaAB = new JButton("Lisää");
        
        aines.add(nimi);
        aines.add(maara);
        aines.add(lisaaAB);
        
        JPanel aineksetP = new JPanel();
        aineksetP.setLayout(new BoxLayout(aineksetP, BoxLayout.Y_AXIS));
        String[] ain = {};
        
        aineslista = new JList(ain);
        aineslista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(aineslista);
        scroll.setPreferredSize(new Dimension(100,100));
        
        lisaaAB.addActionListener(new AineksenKuuntelija(nimi,maara,ainesosat,aineslista));
        
        aineksetP.add(scroll);
        
        JPanel lisaaC = new JPanel();
        JButton poistaAB = new JButton("Poista");
        poistaAB.addActionListener(new AineksenKuuntelija(ainesosat,aineslista));
        
        lisaaC.add(poistaAB);
        
        paneeli.add(aines);
        paneeli.add(aineksetP);
        paneeli.add(lisaaC);
        
        
        
        return paneeli;
    }
        
    private JComboBox lajinReseptit() {
        
        String r[] = new String[0];
        JComboBox vaihtoehdot = new JComboBox(r);
        vaihtoehdot.insertItemAt("---", 0);
        vaihtoehdot.setSelectedIndex(0);
        
        return vaihtoehdot;
    }
    
    private JComboBox ruokalajit() {
        
        String[] vaihE = new String[kirjasto.getRuokalajitString().size()];
        vaihE = kirjasto.getRuokalajitString().toArray(vaihE);
        JComboBox vaihtoehdot = new JComboBox(vaihE);
        vaihtoehdot.insertItemAt("---", 0);
        vaihtoehdot.setSelectedIndex(0);
        vaihtoehdot.setPreferredSize(new Dimension(169, 23));
        
        return vaihtoehdot;
    }
    
    private JPanel reseptiAlus() {
        JPanel ala = new JPanel();
        ala.setPreferredSize(new Dimension(548,300));
        reseptiA = new JTextArea("");
        reseptiA.setLineWrap(true);
        reseptiA.setWrapStyleWord(true);
        reseptiA.setEditable(false);
        JScrollPane reseptiAlus = new JScrollPane(reseptiA);
        reseptiAlus.setPreferredSize(new Dimension(530,315));
        ala.add(reseptiAlus);
        return ala;
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) (kortit.getLayout());
        
        if (e.getSource() == lisaaB) {
            cardLayout.show(kortit, LISAA);
            tyhjennys();
        }
        if (e.getSource() == muokkaaB) {
            cardLayout.show(kortit, MUOKKAA);
            tyhjennys();
        }
        if (e.getSource() == lajiListausB) {
            cardLayout.show(kortit, LAJILISTAUS);
            tyhjennys();
        }
        
        if (e.getSource() == hakuB) {
            cardLayout.show(kortit, HAKU);
            tyhjennys();
        }
        if (e.getSource() == lopetaB) {
            System.exit(0);
        }
    }
    
    private void tyhjennys() {
        aineslista.setListData(new String[0]);
        ainesosat.clear();
        nimiT.setText("");
        ohjeetA.setText("");
        ilmoitus.setText("");
        lajiValinta.setSelectedIndex(0);
        reseptiValinta.setSelectedIndex(0);
        reseptiA.setText("");
    }
}
