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

    public VuePrincipale(Parc modele) {
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
        panelFilters.add(new JCheckBox("Prix croissant"));
        panelFilters.add(new JCheckBox("Prix décroissant"));

        // box pour types de permis
        JPanel panelPermis = new JPanel();
        panelPermis.setLayout(new BoxLayout(panelPermis, BoxLayout.Y_AXIS));
        panelPermis.setBorder(BorderFactory.createTitledBorder("Type de permis"));
        String[] permisTypes = {"Tout","A", "A1", "Sans permis"};
        JComboBox<String> comboPermis = new JComboBox<>(permisTypes);
        comboPermis.setMaximumSize(new Dimension(180, 30));
        panelPermis.add(comboPermis);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(panelPermis);

        //box pour motorisation
        JPanel pMotorisation = new JPanel();
        pMotorisation.setLayout(new BoxLayout(pMotorisation, BoxLayout.Y_AXIS));
        pMotorisation.setBorder(BorderFactory.createTitledBorder("Motorisation"));
        String[] motorisationT = {"Tout","Essence", "Electrique"};
        JComboBox<String> comboMoto = new JComboBox<>(motorisationT);
        comboMoto.setMaximumSize(new Dimension(180, 30));
        pMotorisation.add(comboMoto);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        panelFilters.add(pMotorisation);

        //box pour marque
        JPanel pMarque = new JPanel();
        pMarque.setLayout(new BoxLayout(pMarque, BoxLayout.Y_AXIS));
        pMarque.setBorder(BorderFactory.createTitledBorder("Marque"));
        String[] marqueT = {"Tout","Yamaha", "Honda", "Piaggio"};
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
        String[] couleurT = {"Tout","Rouge", "Bleu", "Noir","Blanc","Gris","Vert","Jaune","Violet","Orange","Rose","Autre"};
        JComboBox<String> comboCouleur = new JComboBox<>(couleurT);
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
        
        panelNavigation.add(btnHistorique);
        panelNavigation.add(commande);
        panelNavigation.add(btnProfil);
        panelNavigation.add(contact);        
        panelHaut.add(panelNavigation);

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

        this.pack();
        this.setVisible(true);
    }
private JPanel creerCarteScooter(Scooter scooter) {
        JPanel carte = new JPanel(new BorderLayout(5, 5));
        carte.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2)); 
        carte.setBackground(Color.WHITE);

        // 1. Nom du scooter en haut
        JLabel labelModele = new JLabel(scooter.getModele().getNom_modele(), SwingConstants.CENTER);
        labelModele.setFont(new Font("Arial", Font.BOLD, 18));
        carte.add(labelModele, BorderLayout.NORTH);

        // 2. Infos rapides au centre (Moteur et Prix)
        JPanel panelInfos = new JPanel(new GridLayout(2, 1));
        panelInfos.setBackground(Color.WHITE);
        panelInfos.add(new JLabel("Moteur : " + scooter.getModele().getMotorisation(), SwingConstants.CENTER));
        
        JLabel labelPrix = new JLabel(scooter.getPrix_jour() + " € / jour", SwingConstants.CENTER);
        labelPrix.setForeground(new Color(0, 150, 0)); // Prix en vert
        panelInfos.add(labelPrix);
        
        carte.add(panelInfos, BorderLayout.CENTER);
        // 3. Bouton "Plus de détails" en bas avec son action !
        JButton btnDetails = new JButton("Plus de détails");
        btnDetails.addActionListener(e -> {
            
            // 1. Création de la nouvelle fenêtre
            JFrame fenetreDetails = new JFrame("Détails : " + scooter.getModele().getNom_modele());
            fenetreDetails.setSize(400, 350); // Taille de la fenêtre
            fenetreDetails.setLocationRelativeTo(this); // Centre la fenêtre par rapport à la principale
            
            // TRÈS IMPORTANT : On utilise DISPOSE_ON_CLOSE pour ne fermer QUE cette petite fenêtre. 
            // Si tu mets EXIT_ON_CLOSE, fermer les détails fermera toute ton application !
            fenetreDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fenetreDetails.setLayout(new BorderLayout(15, 15));
            
            // 2. Un titre en haut
            JLabel titreFenetre = new JLabel("Fiche complète", SwingConstants.CENTER);
            titreFenetre.setFont(new Font("Arial", Font.BOLD, 22));
            fenetreDetails.add(titreFenetre, BorderLayout.NORTH);
            
            // 3. Les informations au centre
            JPanel panelInfosD = new JPanel(new GridLayout(6, 1, 5, 5));
            panelInfos.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Des marges invisibles
            
            panelInfosD.add(new JLabel("➤ Marque : " + scooter.getModele().getMarque().getNomMarque()));
            panelInfosD.add(new JLabel("➤ Modèle : " + scooter.getModele().getNom_modele()));
            panelInfosD.add(new JLabel("➤ Motorisation : " + scooter.getModele().getMotorisation()));
            panelInfosD.add(new JLabel("➤ Couleur : " + scooter.getColoris()));
            panelInfosD.add(new JLabel("➤ Permis requis : " + scooter.getModele().getPermis()));
            
            JLabel prixLabel = new JLabel("Prix : " + scooter.getPrix_jour() + " € / jour");
            prixLabel.setForeground(new Color(0, 150, 0)); // Texte en vert
            prixLabel.setFont(new Font("Arial", Font.BOLD, 16));
            panelInfosD.add(prixLabel);
            
            fenetreDetails.add(panelInfosD, BorderLayout.CENTER);
            
            // 4. Boutons en bas (Fermer, et la place pour "Louer" plus tard)
            JPanel panelBas = new JPanel();
            JButton btnFermer = new JButton("Fermer");
            
            // L'action du bouton "Fermer" détruit juste cette fenêtre
            btnFermer.addActionListener(event -> fenetreDetails.dispose()); 
            
            panelBas.add(btnFermer);
            fenetreDetails.add(panelBas, BorderLayout.SOUTH);
            
            // 5. On affiche la fenêtre !
            fenetreDetails.setVisible(true);
        });
        
        carte.add(btnDetails, BorderLayout.SOUTH);

        return carte;
    }
    @Override
    public void update(java.util.Observable o, Object arg) {
        // 1. On vide la grille actuelle
        panneauScoot.removeAll();

        // 2. On récupère la nouvelle liste de scooters envoyée par le Parc (après un filtre ou une recherche)
        java.util.Vector<Scooter> listeScooters = (java.util.Vector<Scooter>) arg;

        // 3. On crée une carte pour chaque scooter et on l'ajoute à la grille
        if (listeScooters != null) {
            for (int i = 0; i < listeScooters.size(); i++) {
                Scooter s = listeScooters.get(i);
                panneauScoot.add(creerCarteScooter(s));
            }
        }

        // 4. On force la fenêtre à se redessiner
        panneauScoot.revalidate();
        panneauScoot.repaint();
    }
}
































