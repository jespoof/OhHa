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
import reseptivarasto.domain.Reseptikirjasto;
import reseptivarasto.domain.Ruokalaji;
import tiedostonkasittelija.RuokalajienKasittelija;

public class GraafinenKayttoliittyma implements ActionListener {
    
    /**
    * Alku-paneelin nimi
    */
    private static final String ALKU = "AlkuP";
    /**
    * Lisää-paneelin nimi
    */
    private static final String LISAA = "LisaaP";
    /**
    * Muokkaa-paneelin nimi
    */
    private static final String MUOKKAA = "MuokkaaP";
    /**
    * Reseptit-paneelin nimi
    */
    private static final String RESEPTIT = "ReseptitP";
    /**
    * Reseptihaku-paneelin nimi
    */
    private static final String HAKU = "HakuP";
    /**
    * CardLayoutia käyttävä pääpaneeli
    */
    private JPanel kortit;
    /**
    * Painike, jolla pääsee Lisää reseptit -paneeliin
    */
    private JButton lisaaB = new JButton("Lisää resepti");
    /**
    * Painike, jolla pääsee Muokkaa reseptiä -paneeliin
    */
    private JButton muokkaaB = new JButton("Muokkaa reseptiä");
    /**
    * Painike, jolla pääsee Reseptihaku-paneeliin
    */
    private JButton hakuB = new JButton("Reseptihaku");
    /**
    * Painike, jolla pääsee Reseptit-paneeliin
    */
    private JButton reseptitB = new JButton("Reseptit");
    /**
    * Painike, joka lopettaa ohjelman
    */
    private JButton lopetaB = new JButton("Lopeta");
    /**
    * Käsiteltävä Reseptikirjasto
    */
    private Reseptikirjasto kirjasto;
    /**
    * Tiedoston käsittelemistä varten
    */
    private RuokalajienKasittelija ruokalajikasittelija;
    /**
    * ArrayList, johon tallennetaan käyttäjän lisäämät Ainesosat Reseptin
    * kokoamista varten
    */
    private ArrayList<Ainesosa> ainesosat;
    /**
    * Lista, jossa on käyttäjän lisäämät Ainesosat Lisää resepti -kohdassa
    */
    private JList aineslista;
    /**
    * Lista, jossa on käyttäjän lisäämät Ainesosat Muokkaa reseptiä -kohdassa
    */
    private JList aineslista2;
    /**
    * Lista, joka sisältää hakutulokset Reseptihaussa
    */
    private JList hakutulos;
    /**
    * Kenttä, johon kirjoitetaan hakusana
    */
    private JTextField hakukentta;
    /**
    * Reseptin nimi kirjoitetaan tähän
    */
    private JTextField nimiTextField;
    /**
    * Reseptin ohjeet kirjoitetaan tähän
    */
    private JTextArea ohjeetArea;
    /**
    * Valittu resepti näytetään, 
    */
    private JTextArea reseptiArea;
    /**
    * Valittu resepti näytetään
    */
    private JTextArea reseptitulos;
    /**
    * ComboBox, josta valitaan Ruokalaji Lisää resepti -paneelissa
    */
    private JComboBox lajinValintaLisaa;
    /**
    * ComboBox, josta valitaan Ruokalaji, Muokkaa reseptiä -paneelissa
    */
    private JComboBox lajinValintaMuokkaa;
    /**
    * ComboBox, josta valitaan Ruokalaji Reseptit-paneelissa
    */
    private JComboBox lajinValintaReseptit;
    /**
    * ComboBox, josta valitaan näytettävä Resepti Muokkaa reseptiä -paneelissa
    */
    private JComboBox reseptinValintaMuokkaa;
    /**
    * ComboBox, josta valitaan näytettävä Resepti Reseptit-paneelissa
    */
    private JComboBox reseptinValintaReseptit;
    /**
    * Sisältää ilmoitukset joko toiminnon onnistumiseta tai epäonnistumisesta
    * Lisää resepti -paneelissa
    */
    private JLabel lisaaIlmoitus;
    /**
    * Sisältää ilmoitukset joko toiminnon onnistumiseta tai epäonnistumisesta
    * Muokkaa reseptiä -paneelissa
    */
    private JLabel muokkaaIlmoitus;
    
    
    
    
    
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
    
    /**
    * Käynnistää graafisen käyttöliittymän
    */
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
    
    /**
    * Luo paneelin, joka sisältää napit, joita klikkaamalla pääsee muihin paneeleihin
    */
    private JPanel nappiPaneeli() {
        JPanel napit = new JPanel(new GridLayout(5, 1, 5, 5));
        
        napit.add(lisaaB);
        napit.add(muokkaaB);
        napit.add(reseptitB);
        napit.add(hakuB);
        napit.add(lopetaB);
        
        lisaaB.addActionListener(this);
        muokkaaB.addActionListener(this);
        reseptitB.addActionListener(this);
        hakuB.addActionListener(this);
        lopetaB.addActionListener(this);
        
        return napit;
    }
    
    /**
    * Sisältää muut pääpaneelit
    */
    private JPanel korttiPaneeli() {
        kortit = new JPanel(new CardLayout());
        kortit.add(alku(), ALKU);
        kortit.add(lisaa(), LISAA);
        kortit.add(muokkaa(), MUOKKAA);
        kortit.add(reseptit(), RESEPTIT);
        kortit.add(haku(), HAKU);

        return kortit;
    }
    
    /**
    * Ohjelman käynnityksen alussa näytettävä paneeli, joka toivottaa käyttäjän
    * tervetulleeksi Reseptivarastoon
    */
    private JPanel alku() {
        JPanel paneeli = new JPanel();
        JLabel alkuL = new JLabel("Tervetuloa Reseptivarastoon!");
        alkuL.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        paneeli.add(alkuL);
        
        return paneeli;
    }
    
    /**
    * Paneeli, jossa lisätään uusia Reseptejä kirjastoon
    */
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
        
        JPanel nimiPaneeli = nimiPaneeli();
        
        JPanel lajiPaneeli = new JPanel();
        JLabel lajiLabel = new JLabel("Ruokalaji:       ");
        lajinValintaLisaa = ruokalajit();
        lajiPaneeli.add(lajiLabel);
        lajiPaneeli.add(lajinValintaLisaa);
        
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
        
        JPanel ilmoitin = new JPanel();
        lisaaIlmoitus = new JLabel("");
        ilmoitin.add(lisaaIlmoitus);
        
        JPanel tallennaTaiPeruuta = new JPanel();
        tallennaTaiPeruuta.setPreferredSize(new Dimension(1,1));
        tallennaTaiPeruuta.setLayout(new FlowLayout());
        tallennaTaiPeruuta.setBorder(BorderFactory.createEmptyBorder(5, 55, 0, 0));
        
        JButton tallennaB = new JButton("Tallenna");
        JButton peruB = new JButton("Peruuta");
        
        tallennaB.addActionListener(new ReseptinKuuntelija(lajinValintaLisaa, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista, ruokalajikasittelija, lisaaIlmoitus));
        peruB.addActionListener(new ReseptinKuuntelija(lajinValintaLisaa, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista, ruokalajikasittelija, lisaaIlmoitus));
        
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
    
    /**
    * Paneeli, jossa muokataan kirjastoon jo lisättyjä reseptejä
    */
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
        lajinValintaMuokkaa = ruokalajit();
        JLabel reseptiValintaLabel = new JLabel("Valitse resepti:");
        reseptinValintaMuokkaa = lajinReseptit();
        
        lajinValintaMuokkaa.addActionListener(new RuokalajinKuuntelija(reseptinValintaMuokkaa,kirjasto));
        lajiJaResepti.add(lajiLabel);
        lajiJaResepti.add(lajinValintaMuokkaa);
        lajiJaResepti.add(reseptiValintaLabel);
        lajiJaResepti.add(reseptinValintaMuokkaa);
        
        yla.add(lajiJaResepti);
        
        JPanel ala = new JPanel();
        ala.setLayout(new GridLayout(1,2));
        ala.setBorder(BorderFactory.createEmptyBorder(15, 25, 5, 25));
        JPanel vasen = new JPanel();
        vasen.setLayout(new BoxLayout(vasen, BoxLayout.Y_AXIS));
        
        JPanel nimiPaneeli = nimiPaneeli();
        
        aineslista2 = new JList(new String[0]);
        aineslista2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JPanel aineksetPaneeli = ainekset(aineslista2);
        
        vasen.add(nimiPaneeli);
        vasen.add(aineksetPaneeli);
        
        JPanel oikea = ohjePaneeli();
        
        ala.add(vasen);
        ala.add(oikea);
        
        reseptinValintaMuokkaa.addActionListener(new ReseptiValinnanKuuntelija(lajinValintaMuokkaa, nimiTextField, ainesosat, aineslista2, ohjeetArea, kirjasto));
        
        JPanel ilmoitin = new JPanel();
        muokkaaIlmoitus = new JLabel("");
        ilmoitin.add(muokkaaIlmoitus);
        
        JPanel tallennaPeruTaiPoistaPaneeli = new JPanel();
        tallennaPeruTaiPoistaPaneeli.setLayout(new FlowLayout());
        
        JButton tallennaB = new JButton("Tallenna ");
        tallennaB.addActionListener(new ReseptinKuuntelija(lajinValintaMuokkaa, reseptinValintaMuokkaa, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista2, ruokalajikasittelija, muokkaaIlmoitus));
        JButton peruB = new JButton("Peruuta");
        peruB.addActionListener(new ReseptinKuuntelija(lajinValintaMuokkaa, reseptinValintaMuokkaa, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista2, ruokalajikasittelija, muokkaaIlmoitus));
        JButton poistaB = new JButton("Poista resepti");
        poistaB.addActionListener(new ReseptinKuuntelija(lajinValintaMuokkaa, reseptinValintaMuokkaa, nimiTextField, ainesosat, ohjeetArea, kirjasto, aineslista2, ruokalajikasittelija, muokkaaIlmoitus));
        
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
    
    /**
    * Paneeli, joissa käyttäjä voi valita ensin Ruokalajin ja sitten jonkun
    * siihen tallennetuista Resepteistä ja katsoa tämän, Reseptiä ei voi muokata
    */
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
        lajinValintaReseptit = ruokalajit();
        lajiJaResepti.add(lajiLabel);
        lajiJaResepti.add(lajinValintaReseptit);
        
        JPanel reseptiPaneeli = new JPanel();
        JLabel reseptiValintaLabel = new JLabel("Valitse resepti:");
        reseptinValintaReseptit = lajinReseptit();
        lajiJaResepti.add(reseptiValintaLabel);
        lajiJaResepti.add(reseptinValintaReseptit);
        
        JPanel ala = reseptiAlus();
        
        lajinValintaReseptit.addActionListener(new RuokalajinKuuntelija(reseptinValintaReseptit, kirjasto));
        reseptinValintaReseptit.addActionListener(new ReseptinNayttoKuuntelija(lajinValintaReseptit, reseptiArea, kirjasto));
        
        yla.add(lajiJaResepti);
        paa.add(yla);
        paa.add(ala);
        alusta.add(paa);
        paneeli.add(alusta);
        
        return paneeli;
    }
    
    /**
    * Paneeli, jossa käyttäjä voi hakea reseptiä joko nimen tai ainesosan perusteella
    */
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
        reseptitulos.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        reseptitulos.setLineWrap(true);
        reseptitulos.setWrapStyleWord(true);
        reseptitulos.setEditable(false);
        
        JPanel tulosPaneeli = new JPanel();
        tulosPaneeli.setLayout(new BoxLayout(tulosPaneeli, BoxLayout.Y_AXIS));
        tulosPaneeli.setBorder(BorderFactory.createTitledBorder("Hakutulokset"));
        hakutulos = new JList(new String[0]);
        JScrollPane tulosScrollPane = new JScrollPane(hakutulos);
        tulosScrollPane.setPreferredSize(new Dimension(190,237));
        JPanel valitseNappiPaneeli = new JPanel();
        valitseNappiPaneeli.setLayout(new FlowLayout());
        JButton valitse = new JButton("Valitse");
        valitseNappiPaneeli.add(valitse);
        tulosPaneeli.add(tulosScrollPane);
        tulosPaneeli.add(valitseNappiPaneeli);
        
        JScrollPane reseptiAlus = new JScrollPane(reseptitulos);
        reseptiAlus.setPreferredSize(new Dimension(310,300));
        
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
    
    /**
    * Luo otsikoidun paneelin, jonka sisältä löytyvät toiminnot
    */
    private JPanel paaPaneelinLuominen(String otsikko){
        JPanel paa = new JPanel();
        paa.setPreferredSize(new Dimension(600,460));
        paa.setLayout(new BoxLayout(paa, BoxLayout.Y_AXIS));
        paa.setBorder(BorderFactory.createTitledBorder(otsikko));
        return paa;
    }
    
    /**
    * Luo paneelin, joka sisältää kentän Reseptin nimeä varten 
    */
    private JPanel nimiPaneeli() {
        JPanel nimiPaneeli = new JPanel();
        JLabel nimiLabel = new JLabel("Reseptin nimi:");
        nimiTextField = new JTextField();
        nimiTextField.setPreferredSize(new Dimension(157,20));
        nimiPaneeli.add(nimiLabel);
        nimiPaneeli.add(nimiTextField);
        return nimiPaneeli;
    }
    
    /**
    * Luo paneelin, joka sisältää paneelin ohjeiden kirjoittamista tai muokkaamista varten 
    */
    private JPanel ohjePaneeli(){
        JPanel oikea = new JPanel();
        oikea.setLayout(new BoxLayout(oikea, BoxLayout.Y_AXIS));
        oikea.setBorder(BorderFactory.createTitledBorder("Ohjeet"));
        ohjeetArea = new JTextArea();
        ohjeetArea.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        ohjeetArea.setLineWrap(true);
        ohjeetArea.setWrapStyleWord(true);
        JScrollPane ohjeetP = new JScrollPane(ohjeetArea);
        oikea.add(ohjeetP);
        return oikea;
    }
    
    /**
    * Luo paneelin, jossa voi lisätä ja poistaa ainesosia
    */
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
        scroll.setPreferredSize(new Dimension(100,120));
        
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
    
    /**
    * Luo ComboBoxin, joka on alussa tyhjä, mutta johon tulee valitun ruokalajin
    * sisältämät reseptit
    */
    private JComboBox lajinReseptit() {
        
        String r[] = new String[0];
        JComboBox vaihtoehdot = new JComboBox(r);
        vaihtoehdot.insertItemAt("---", 0);
        vaihtoehdot.setSelectedIndex(0);
        
        return vaihtoehdot;
    }
    
    /**
    * Luo ComboBoxin, josta voi valita Ruokalajin
    */
    private JComboBox ruokalajit() {
        
        String[] ruokalajit = new String[kirjasto.getRuokalajitString().size()];
        ruokalajit = kirjasto.getRuokalajitString().toArray(ruokalajit);
        JComboBox vaihtoehdot = new JComboBox(ruokalajit);
        vaihtoehdot.insertItemAt("---", 0);
        vaihtoehdot.setSelectedIndex(0);
        vaihtoehdot.setPreferredSize(new Dimension(160, 23));
        
        return vaihtoehdot;
    }
    
    /**
    * Luo paneelin reseptin näyttämistä varten
    */
    private JPanel reseptiAlus() {
        JPanel ala = new JPanel();
        ala.setPreferredSize(new Dimension(548,300));
        reseptiArea = new JTextArea("");
        reseptiArea.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        reseptiArea.setLineWrap(true);
        reseptiArea.setWrapStyleWord(true);
        reseptiArea.setEditable(false);
        JScrollPane reseptiAlus = new JScrollPane(reseptiArea);
        reseptiAlus.setPreferredSize(new Dimension(530,315));
        ala.add(reseptiAlus);
        return ala;
    }
    
    /**
    * Kuuntelee, mikä paneeli pitäisi näyttää 
    */
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
        if (e.getSource() == reseptitB) {
            cardLayout.show(kortit, RESEPTIT);
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
    
    /**
    * Tyhjentää kentät
    */
    private void tyhjennys() {
        aineslista.setListData(new String[0]);
        aineslista2.setListData(new String[0]);
        hakutulos.setListData(new String[0]);
        ainesosat.clear();
        nimiTextField.setText("");
        ohjeetArea.setText("");
        lisaaIlmoitus.setText("");
        muokkaaIlmoitus.setText("");
        lajinValintaLisaa.setSelectedIndex(0);
        lajinValintaMuokkaa.setSelectedIndex(0);
        lajinValintaReseptit.setSelectedIndex(0);
        reseptinValintaMuokkaa.setSelectedIndex(0);
        reseptinValintaReseptit.setSelectedIndex(0);
        reseptiArea.setText("");
        reseptitulos.setText("");
        hakukentta.setText("");
    }
}
