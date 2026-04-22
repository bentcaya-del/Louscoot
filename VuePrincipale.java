import java.awt.*;
import javax.swing.*;
import java.util.*;

public class VuePrincipale extends JFrame implements Observer {
    private JLabel titreApp;
    private JTextField barreRecherche;
    private JPanel panneauScoot ;
    private JPanel panelFilters;
    private JButton btnHistorique;
    private JButton btnProfil;
    private JButton commande;
    private JButton contact; 
    
    public VuePrincipale(){
        // model.addObserver(this);
        this.setTitle("LOUSCOOT - Location de scooters");
        this.setPreferredSize(new Dimension(1000 ,700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(15,15));
        
        //titre de l'application
        titreApp = new JLabel("LOUSCOOT - Location de scooters", SwingConstants.CENTER);
        titreApp.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(titreApp, BorderLayout.NORTH);
        
        //filtres
        panelFilters = new JPanel();
        panelFilters.setLayout(new BoxLayout(panelFilters, BoxLayout.Y_AXIS));
        panelFilters.setBorder(BorderFactory.createTitledBorder("Filtres"));
        panelFilters.setPreferredSize(new Dimension(200, 0));


        //box pour les filtres
        panelFilters.add(new JCheckBox("Prix croissant"));
        panelFilters.add(new JCheckBox("Prix décroissant"));

        // box pour types de permis
        JPanel panelPermis = new JPanel();
        panelPermis.setLayout(new BoxLayout(panelPermis, BoxLayout.Y_AXIS));
        panelPermis.setBorder(BorderFactory.createTitledBorder("Type de permis"));
        String[] permisTypes = {"A", "A1", "Sans permis"};
        JComboBox<String> comboPermis = new JComboBox<>(permisTypes);
        comboPermis.setMaximumSize(new Dimension(180, 30));
        panelPermis.add(comboPermis);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(panelPermis);

        //box pour motorisation
        JPanel pMotorisation = new JPanel();
        pMotorisation.setLayout(new BoxLayout(pMotorisation, BoxLayout.Y_AXIS));
        pMotorisation.setBorder(BorderFactory.createTitledBorder("Motorisation"));
        String[] motorisationT = {"Essence", "Electrique", "Sans permis"};
        JComboBox<String> comboMoto = new JComboBox<>(motorisationT);
        comboMoto.setMaximumSize(new Dimension(180, 30));
        pMotorisation.add(comboMoto);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(pMotorisation);

        //box pour marque
        JPanel pMarque = new JPanel();
        pMarque.setLayout(new BoxLayout(pMarque, BoxLayout.Y_AXIS));
        pMarque.setBorder(BorderFactory.createTitledBorder("Marque"));
        String[] marqueT = {"Yamaha", "Honda", "Piaggio"};
        JComboBox<String> comboMarque = new JComboBox<>(marqueT);
        comboMarque.setMaximumSize(new Dimension(180, 30));
        pMarque.add(comboMarque);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(pMarque);

        //box pour date de disponibilité
        JPanel pDate = new JPanel();
        pDate.setLayout(new BoxLayout(pDate, BoxLayout.Y_AXIS));
        pDate.setBorder(BorderFactory.createTitledBorder("Date de disponibilité"));
        JLabel labelDebut = new JLabel("Date de début:");
        JTextField dateDebut = new JTextField("JJ/MM/AAAA");
        dateDebut.setMaximumSize(new Dimension(180, 30));

        JLabel labelFin = new JLabel("Date de fin:");
        JTextField dateFin = new JTextField("JJ/MM/AAAA");
        dateFin.setMaximumSize(new Dimension(180, 30));

        pDate.add(labelDebut);
        pDate.add(dateDebut);   
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        pDate.add(labelFin);
        pDate.add(dateFin);
        panelFilters.add(pDate);

        //box pour couleur
        JPanel pCouleur = new JPanel();
        pCouleur.setLayout(new BoxLayout(pCouleur, BoxLayout.Y_AXIS));
        pCouleur.setBorder(BorderFactory.createTitledBorder("Couleur"));
        String[] couleurT = {"Rouge", "Bleu", "Noir","Blanc","Gris","Vert","Jaune","Violet","Orange","Rose","Autre"};
        JComboBox<String> comboCouleur = new JComboBox<>(couleurT);
        comboCouleur.setMaximumSize(new Dimension(180, 30));
        pCouleur.add(comboCouleur);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(pCouleur);
        this.add(panelFilters, BorderLayout.EAST);

        




    


        
        this.pack();
        this.setVisible(true);
    }



























}




