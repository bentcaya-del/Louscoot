import java.awt.*;
import javax.swing.*;

public class VuePrincipale extends JFrame implements java.util.Observer{   
    private JLabel titreApp;
    private JTextField barreRecherche;
    private JButton btnRechercher;
    private JPanel panneauScoot ;
    private JPanel panelFilters;
    private JButton btnHistorique;
    private JButton btnProfil;
    private JButton commande;
    private JButton contact; 
    private JButton retour;
    private Parc modele;
    private JComboBox<String> comboMarque;
    private JComboBox<String> comboMoto;
    private JComboBox<String> comboPermis;
    private JComboBox<String> comboCouleur;
    private JRadioButton radioCroissant;
    private JRadioButton radioDecroissant;
    private ButtonGroup groupePrix;

    public VuePrincipale(Parc modele) {
        this.modele = modele;
        modele.addObserver(this);
        this.setTitle("LOUSCOOT - Location de scooters");
        this.setPreferredSize(new Dimension(1000 ,700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(15,15));
        
        //titre de l'application
        titreApp = new JLabel("LOUSCOOT - Location de scooters", SwingConstants.CENTER);
        titreApp.setFont(new Font("Arial", Font.BOLD, 30));
        
        //filtres
        panelFilters = new JPanel();
        panelFilters.setLayout(new BoxLayout(panelFilters, BoxLayout.Y_AXIS));
        panelFilters.setBorder(BorderFactory.createTitledBorder("Filtres"));
        panelFilters.setPreferredSize(new Dimension(200, 0));


        //box pour les filtres
        radioCroissant = new JRadioButton("Prix croissant");
        radioDecroissant = new JRadioButton("Prix décroissant");
        groupePrix = new ButtonGroup();
        
        groupePrix.add(radioCroissant);
        groupePrix.add(radioDecroissant);
        
        panelFilters.add(radioCroissant);
        panelFilters.add(radioDecroissant);

        radioCroissant.addActionListener(e -> modele.trierParPrix(true));
        radioDecroissant.addActionListener(e -> modele.trierParPrix(false));

        // box pour types de permis
        JPanel panelPermis = new JPanel();
        panelPermis.setLayout(new BoxLayout(panelPermis, BoxLayout.Y_AXIS));
        panelPermis.setBorder(BorderFactory.createTitledBorder("Type de permis"));
        comboPermis = new JComboBox<>();
        comboPermis.setMaximumSize(new Dimension(180, 30));
        panelPermis.add(comboPermis);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(panelPermis);

        //box pour motorisation
        JPanel pMotorisation = new JPanel();
        pMotorisation.setLayout(new BoxLayout(pMotorisation, BoxLayout.Y_AXIS));
        pMotorisation.setBorder(BorderFactory.createTitledBorder("Motorisation"));
        comboMoto = new JComboBox<>();
        comboMoto.setMaximumSize(new Dimension(180, 30));
        pMotorisation.add(comboMoto);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(pMotorisation);

        //box pour marque
        JPanel pMarque = new JPanel();
        pMarque.setLayout(new BoxLayout(pMarque, BoxLayout.Y_AXIS));
        pMarque.setBorder(BorderFactory.createTitledBorder("Marque"));;
        comboMarque = new JComboBox<>();
        comboMarque.setMaximumSize(new Dimension(180, 30));
        pMarque.add(comboMarque);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(pMarque);

        //box pour date de disponibilité
        JPanel pDate = new JPanel();
        pDate.setLayout(new BoxLayout(pDate, BoxLayout.Y_AXIS));
        pDate.setBorder(BorderFactory.createTitledBorder("Disponibilité"));
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
        comboCouleur = new JComboBox<>();
        comboCouleur.setMaximumSize(new Dimension(180, 30));
        pCouleur.add(comboCouleur);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(pCouleur);
        

        panelFilters.add(Box.createRigidArea(new Dimension(0, 15))); // Grand espace
        JButton btnAppliquer = new JButton("Appliquer les filtres");
        btnAppliquer.setAlignmentX(Component.CENTER_ALIGNMENT); // Pour le centrer
        btnAppliquer.setBackground(new Color(255, 0,0 )); 
        btnAppliquer.setForeground(Color.BLACK); // Texte en NOIR
        panelFilters.add(btnAppliquer);


        panelFilters.setVisible(false);

        JPanel panelMenuDroit = new JPanel(new BorderLayout());
        JButton btnToggleFiltres = new JButton("Filtres ◀");
        btnToggleFiltres.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Action pour afficher/masquer les filtres
        btnToggleFiltres.addActionListener(e -> {
            boolean estVisible = panelFilters.isVisible();
            panelFilters.setVisible(!estVisible);
            if (estVisible) {
                btnToggleFiltres.setText("Filtres ◀");
            } else {
                btnToggleFiltres.setText("Filtres ▼");
            }
            this.revalidate();// Recalcule la mise en page
            this.repaint();//refresh de la fenêtre pour que les changements soient pris en compte
        });

        panelMenuDroit.add(btnToggleFiltres, BorderLayout.NORTH); // Bouton en haut
        panelMenuDroit.add(panelFilters, BorderLayout.CENTER);    // Filtres en dessous

        // Et on ajoute ce conteneur à droite de la fenêtre principale !
        this.add(panelMenuDroit, BorderLayout.EAST);

        //CRÉATION DU BLOC DU HAUT (Titre + Navigation)
        JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new BoxLayout(panelHaut, BoxLayout.Y_AXIS));

        // Ajout du titre
        titreApp.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelHaut.add(titreApp);

        // Ajout de la navigation (Historique, Profil, etc.)
        JPanel panelNavigation = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        btnHistorique = new JButton("Historique");
        commande = new JButton("Commandes");
        btnProfil = new JButton("Mon Profil");
        contact = new JButton("Contact");
        retour = new JButton("Retour");

        
        panelNavigation.add(btnHistorique);
        panelNavigation.add(commande);
        panelNavigation.add(btnProfil);
        panelNavigation.add(contact);        
        panelHaut.add(panelNavigation);
        panelNavigation.add(retour);

        retour.addActionListener(e -> {
            FenetreRetour fenetreRetour = new FenetreRetour();
            fenetreRetour.setVisible(true);
        });

        // On place ce bloc tout en haut
        this.add(panelHaut, BorderLayout.NORTH);

       // On regroupe la recherche et les scooters dans un seul panneau
        JPanel zoneCentrale = new JPanel(new BorderLayout(10, 10));

        //La barre de recherche
        JPanel panelRecherche = new JPanel(new BorderLayout(5, 5));
        barreRecherche = new JTextField();
        btnRechercher = new JButton("Rechercher");
        panelRecherche.add(barreRecherche, BorderLayout.CENTER);
        panelRecherche.add(btnRechercher, BorderLayout.EAST);
        
        //en haut de la zone centrale
        zoneCentrale.add(panelRecherche, BorderLayout.NORTH); 

        //Le panneau pour les scooters
        panneauScoot = new JPanel();
        panneauScoot.setLayout(new GridLayout(0, 3, 10, 10)); 
        JScrollPane scrollPane = new JScrollPane(panneauScoot);// Permet d'ajouter une barre de défilement
        
        //centre de la zone centrale
        zoneCentrale.add(scrollPane, BorderLayout.CENTER); 
        this.add(zoneCentrale, BorderLayout.CENTER);


        ControleurRecherche ctrlRecherche = new ControleurRecherche(modele, barreRecherche);
        
        // On attache l'espion au bouton "Rechercher"
        btnRechercher.addActionListener(ctrlRecherche);

        ControleurFiltre ctrlFiltre = new ControleurFiltre(modele, comboPermis, comboMoto, comboMarque, comboCouleur);
        btnAppliquer.addActionListener(ctrlFiltre);

        for (int i = 0; i < modele.getListe_scooter().size(); i++) {
            Scooter s = modele.getListe_scooter().get(i);
            panneauScoot.add(creerCarteScooter(s));
        }

        actualiserTousLesFiltres();

        this.pack();
        this.setVisible(true);
    }

    
    private JPanel creerCarteScooter(Scooter scooter) {
        JPanel carte = new JPanel(new BorderLayout(5, 5));

        // Nom du scooter en haut
        JLabel labelModele = new JLabel(scooter.getModele().getNom_modele(), SwingConstants.CENTER);
        labelModele.setFont(new Font("Arial", Font.BOLD, 18));
        carte.add(labelModele, BorderLayout.NORTH);

        // 2. Infos rapides au centre (Moteur et Prix)
        JPanel panelInfos = new JPanel(new GridLayout(4, 1,0,5));
        panelInfos.setBackground(Color.WHITE);
        panelInfos.add(new JLabel("Moteur : " + scooter.getModele().getMotorisation(), SwingConstants.CENTER));
        panelInfos.add(new JLabel("Marque : " + scooter.getModele().getMarque().getNomMarque()));
        panelInfos.add(new JLabel("Couleur : " + scooter.getColoris(), SwingConstants.CENTER));
        
        JLabel labelPrix = new JLabel(scooter.getPrix_jour() + " € / jour", SwingConstants.CENTER);
        labelPrix.setForeground(new Color(0, 150, 0)); // Prix en vert
        panelInfos.add(labelPrix);
        
        carte.add(panelInfos, BorderLayout.CENTER);
        // 3. Bouton "Plus de détails" en bas avec son action !
        JButton btnDetails = new JButton("Plus de détails");
        btnDetails.addActionListener(e -> {
            FenetreDetails fenetre = new FenetreDetails(modele,scooter);
             fenetre.setVisible(true);
        });
        
        carte.add(btnDetails, BorderLayout.SOUTH);


        return carte;
    }
    @Override
    public void update(java.util.Observable o, Object arg) {
        // On vide la grille actuelle
        panneauScoot.removeAll();

        //On récupère la nouvelle liste de scooters envoyée par le Parc (après un filtre ou une recherche)
        java.util.Vector<Scooter> listeScooters = (java.util.Vector<Scooter>) arg;

        //On crée une carte pour chaque scooter et on l'ajoute à la grille
        if (listeScooters != null) {
            for (int i = 0; i < listeScooters.size(); i++) {
                Scooter s = listeScooters.get(i);
                panneauScoot.add(creerCarteScooter(s));
            }
        }

        //On force la fenêtre à se redessiner
        panneauScoot.revalidate();
        panneauScoot.repaint();
    }
    public void actualiserTousLesFiltres() {
        // On mémorise ce que l'utilisateur avait sélectionné (pour ne pas le perdre au rafraîchissement)
        Object marqueSel = comboMarque.getSelectedItem();
        Object motoSel = comboMoto.getSelectedItem();
        Object permisSel = comboPermis.getSelectedItem();
        Object couleurSel = comboCouleur.getSelectedItem();

        // On vide complètement toutes les boîtes
        comboMarque.removeAllItems();
        comboMoto.removeAllItems();
        comboPermis.removeAllItems();
        comboCouleur.removeAllItems();

        // On demande au Parc toutes les listes à jour
        for (String m : modele.getMarquesUniques()) comboMarque.addItem(m);
        for (String m : modele.getMotorisationsUniques()) comboMoto.addItem(m);
        for (String p : modele.getPermisUniques()) comboPermis.addItem(p);
        for (String c : modele.getCouleursUniques()) comboCouleur.addItem(c);

        //On remet la sélection d'avant
        if (marqueSel != null) comboMarque.setSelectedItem(marqueSel);
        if (motoSel != null) comboMoto.setSelectedItem(motoSel);
        if (permisSel != null) comboPermis.setSelectedItem(permisSel);
        if (couleurSel != null) comboCouleur.setSelectedItem(couleurSel);
    }
}
































