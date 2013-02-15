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
    private JList aineslista2;
    private JList hakutulos;
    
    private JTextField nimiTextField;
    private JTextArea ohjeetArea;
    private JComboBox lajinValinta;
    private JComboBox reseptinValinta;
    private JLabel ilmoitus;
    private JTextArea reseptiArea;
    private JTextArea reseptitulos;
    private JTextField hakukentta;
    
    
    public GraafinenKayttoliittyma() throws IOException {
        
        ruokalajikasittelija = new RuokalajienKasittelija("kirjasto.txt");
        kirjasto = new Reseptikirjasto(ruokalajikasittelija.lueRuokalajit());
        if (kirjasto.getRuokalajit().isEmpty()) {
            kirjasto.lisaaRuokalaji(new Ruokalaji("AAMIAINEN"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("LOUNAS"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("PÄIVÄLLINEN"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("JÄLKIRUOKA"));
            kirjasto.lisaaRuokalaji(new Ruokalaji("PIKKUPURTAVAA"));
        }
        ainesosat = new ArrayList<Ainesosa>();
        
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
        
        JPanel nimiPaneeli = new JPanel();
        JLabel nimiLabel = new JLabel("Reseptin nimi:");
        nimiTextField = new JTextField();
        nimiTextField.setPreferredSize(new Dimension(169,20));
        nimiPaneeli.add(nimiLabel);
        nimiPaneeli.add(nimiTextField);
        
        JPanel lajiPaneeli = new JPanel();
        JLabel lajiLabel = new JLabel("Ruokalaji:       ");
        lajinValinta = ruokalajit();
        lajiPaneeli.add(lajiLabel);
        lajiPaneeli.add(lajinValinta);
        
        aineslista = new JList(new String[0]);
        aineslista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JPanel aineksetPaneeli = ainekset(aineslista);
        
        vasen.add(nimiPaneeli);
        vasen.add(lajiPaneeli);
        vasen.add(aineksetPaneeli);
        
        JPanel oikea = ohjePaneeli();
        
        ala.add(vasen);
        ala.add(oikea);
        
        JPanel loppu = new JPanel();
        loppu.setLayout(new GridLayout(1,2));
        
        JPanel ilmoitin = ilmoitin();
        
        JPanel tallennaTaiPeruuta = new JPanel();
        tallennaTaiPeruuta.setPreferredSize(new Dimension(1,1));
        tallennaTaiPeruuta.setLayout(new FlowLayout());
        tallennaTaiPeruuta.setBorder(BorderFactory.createEmptyBorder(5, 55, 0, 0));
        
        JButton tallennaB = new JButton("Tallenna");
        JButton peruB = new JButton("Peruuta");
        
        tallennaB.addActionListener(new ReseptinKuuntelija(lajinValinta, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista, ruokalajikasittelija, ilmoitus));
        peruB.addActionListener(new ReseptinKuuntelija(lajinValinta, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista, ruokalajikasittelija, ilmoitus));
        
        tallennaTaiPeruuta.add(tallennaB);
        tallennaTaiPeruuta.add(peruB);
        loppu.add(ilmoitin);
        loppu.add(tallennaTaiPeruuta);
        
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
        
        JLabel lajiLabel = new JLabel("Valitse ruokalaji:");
        lajinValinta = ruokalajit();
        JLabel reseptiValintaLabel = new JLabel("Valitse resepti:");
        reseptinValinta = lajinReseptit();
        
        lajinValinta.addActionListener(new RuokalajinKuuntelija(reseptinValinta,kirjasto));
        lajiJaResepti.add(lajiLabel);
        lajiJaResepti.add(lajinValinta);
        lajiJaResepti.add(reseptiValintaLabel);
        lajiJaResepti.add(reseptinValinta);
        
        yla.add(lajiJaResepti);
        
        JPanel ala = new JPanel();
        ala.setLayout(new GridLayout(1,2));
        ala.setBorder(BorderFactory.createEmptyBorder(15, 25, 5, 25));
        JPanel vasen = new JPanel();
        vasen.setLayout(new BoxLayout(vasen, BoxLayout.Y_AXIS));
        
        JPanel nimiPaneeli = new JPanel();
        JLabel nimiLabel = new JLabel("Reseptin nimi:");
        nimiTextField = new JTextField();
        nimiTextField.setPreferredSize(new Dimension(169,20));
        nimiPaneeli.add(nimiLabel);
        nimiPaneeli.add(nimiTextField);
        
        aineslista2 = new JList(new String[0]);
        aineslista2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JPanel aineksetPaneeli = ainekset(aineslista2);
        
        vasen.add(nimiPaneeli);
        vasen.add(aineksetPaneeli);
        
        JPanel oikea = ohjePaneeli();
        
        ala.add(vasen);
        ala.add(oikea);
        
        reseptinValinta.addActionListener(new ReseptiValinnanKuuntelija(lajinValinta, nimiTextField, ainesosat, aineslista2, ohjeetArea, kirjasto));
        
        JPanel ilmoitin = ilmoitin();
        
        JPanel tallennaPeruTaiPoistaPaneeli = new JPanel();
        tallennaPeruTaiPoistaPaneeli.setLayout(new FlowLayout());
        
        JButton tallennaB = new JButton("Tallenna ");
        tallennaB.addActionListener(new ReseptinKuuntelija(lajinValinta, reseptinValinta, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista2, ruokalajikasittelija, ilmoitus));
        JButton peruB = new JButton("Peruuta");
        peruB.addActionListener(new ReseptinKuuntelija(lajinValinta, reseptinValinta, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista2, ruokalajikasittelija, ilmoitus));
        JButton poistaB = new JButton("Poista resepti");
        poistaB.addActionListener(new ReseptinKuuntelija(lajinValinta, reseptinValinta, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista2, ruokalajikasittelija, ilmoitus));
        
        tallennaPeruTaiPoistaPaneeli.add(tallennaB);
        tallennaPeruTaiPoistaPaneeli.add(peruB);
        tallennaPeruTaiPoistaPaneeli.add(poistaB);
        
        paa.add(yla);
        paa.add(ala);
        paa.add(tallennaPeruTaiPoistaPaneeli);
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
        
        JPanel lajiPaneeli = new JPanel();
        JLabel lajiLabel = new JLabel("Valitse ruokalaji:");
        lajinValinta = ruokalajit();
        lajiJaResepti.add(lajiLabel);
        lajiJaResepti.add(lajinValinta);
        
        JPanel reseptiPaneeli = new JPanel();
        JLabel reseptiValintaLabel = new JLabel("Valitse resepti:");
        reseptinValinta = lajinReseptit();
        lajiJaResepti.add(reseptiValintaLabel);
        lajiJaResepti.add(reseptinValinta);
        
        JPanel ala = reseptiAlus();
        
        lajinValinta.addActionListener(new RuokalajinKuuntelija(reseptinValinta, kirjasto));
        reseptinValinta.addActionListener(new ReseptinNayttoKuuntelija(lajinValinta, reseptiArea, kirjasto));
        
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
        JPanel paa = paaPaneelinLuominen("Reseptihaku");
        
        JPanel yla = new JPanel();
        yla.setLayout(new GridLayout(2,1));
        
        JPanel haku = new JPanel();
        haku.setLayout(new FlowLayout());
        haku.setBorder(BorderFactory.createEmptyBorder(5, 25, 0, 25));
        
        
        JComboBox nimiVaiAines = new JComboBox();
        nimiVaiAines.addItem("Hae reseptiä nimen mukaan");
        nimiVaiAines.addItem("Hae reseptiä ainesosan mukaan");
        
        haku.add(nimiVaiAines);
        
        hakukentta = new JTextField();
        hakukentta.setPreferredSize((new Dimension(170,20)));
        JPanel kenttaJaNappi = new JPanel();
        JButton hakunappi = new JButton("Hae");
        kenttaJaNappi.add(hakukentta);
        kenttaJaNappi.add(hakunappi);
        
        
        JPanel ala = new JPanel();
        ala.setLayout(new FlowLayout());
        reseptitulos = new JTextArea();
        reseptitulos.setLineWrap(true);
        reseptitulos.setWrapStyleWord(true);
        reseptitulos.setEditable(false);
        
        JPanel tulosPaneeli = new JPanel();
        tulosPaneeli.setLayout(new BoxLayout(tulosPaneeli, BoxLayout.Y_AXIS));
        hakutulos = new JList(new String[0]);
        JScrollPane tulosScrollPane = new JScrollPane(hakutulos);
        tulosScrollPane.setPreferredSize(new Dimension(140,275));
        JButton valitse = new JButton("Valitse");
        tulosPaneeli.add(tulosScrollPane);
        tulosPaneeli.add(valitse);
        
        JScrollPane reseptiAlus = new JScrollPane(reseptitulos);
        reseptiAlus.setPreferredSize(new Dimension(388,300));
        
        hakunappi.addActionListener(new HakusananKuuntelija(nimiVaiAines, hakukentta, hakutulos, kirjasto));
        valitse.addActionListener(new HaunKuuntelija(nimiVaiAines, hakukentta, hakutulos, reseptitulos, kirjasto));
        
        ala.add(tulosPaneeli);
        ala.add(reseptiAlus);
        
        yla.add(haku);
        yla.add(kenttaJaNappi);
        
        paa.add(yla);
        paa.add(ala);
        
        alusta.add(paa);
        
        paneeli.add(alusta);
        
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
        ohjeetArea = new JTextArea();
        ohjeetArea.setLineWrap(true);
        ohjeetArea.setWrapStyleWord(true);
        JScrollPane ohjeetP = new JScrollPane(ohjeetArea);
        oikea.add(ohjeetP);
        return oikea;
    }

    private JPanel ilmoitin() {
        JPanel ilmoitin = new JPanel();
        ilmoitus = new JLabel("");
        ilmoitin.add(ilmoitus);
        return ilmoitin;
    }

    private JPanel ainekset(JList lista) {
        
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        paneeli.setBorder(BorderFactory.createTitledBorder("Ainekset"));
        
        JPanel aines = new JPanel();
        JTextField nimi = new JTextField("Ainesosa");
        nimi.setPreferredSize(new Dimension(100,20));
        JTextField maara = new JTextField("Määrä");
        maara.setPreferredSize(new Dimension(66,20));
        
        JButton lisaa = new JButton("Lisää");
        
        aines.add(nimi);
        aines.add(maara);
        aines.add(lisaa);
        
        JPanel aineksetPaneeli = new JPanel();
        aineksetPaneeli.setLayout(new BoxLayout(aineksetPaneeli, BoxLayout.Y_AXIS));
        
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(100,100));
        
        lisaa.addActionListener(new AineksenKuuntelija(nimi,maara,ainesosat,lista));
        
        aineksetPaneeli.add(scroll);
        
        JPanel poistaNapinPaneeli = new JPanel();
        JButton poista = new JButton("Poista");
        poista.addActionListener(new AineksenKuuntelija(ainesosat,lista));
        
        poistaNapinPaneeli.add(poista);
        
        paneeli.add(aines);
        paneeli.add(aineksetPaneeli);
        paneeli.add(poistaNapinPaneeli);
        
        
        
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
        
        String[] ruokalajit = new String[kirjasto.getRuokalajitString().size()];
        ruokalajit = kirjasto.getRuokalajitString().toArray(ruokalajit);
        JComboBox vaihtoehdot = new JComboBox(ruokalajit);
        vaihtoehdot.insertItemAt("---", 0);
        vaihtoehdot.setSelectedIndex(0);
        vaihtoehdot.setPreferredSize(new Dimension(169, 23));
        
        return vaihtoehdot;
    }
    
    private JPanel reseptiAlus() {
        JPanel ala = new JPanel();
        ala.setPreferredSize(new Dimension(548,300));
        reseptiArea = new JTextArea("");
        reseptiArea.setLineWrap(true);
        reseptiArea.setWrapStyleWord(true);
        reseptiArea.setEditable(false);
        JScrollPane reseptiAlus = new JScrollPane(reseptiArea);
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
        aineslista2.setListData(new String[0]);
        hakutulos.setListData(new String[0]);
        ainesosat.clear();
        nimiTextField.setText("");
        ohjeetArea.setText("");
        ilmoitus.setText("");
        lajinValinta.setSelectedIndex(0);
        reseptinValinta.setSelectedIndex(0);
        reseptiArea.setText("");
        reseptitulos.setText("");
        hakukentta.setText("");
    }
}
