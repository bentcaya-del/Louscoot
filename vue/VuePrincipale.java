package vue;
import controleur.*;
import java.awt.*;
import javax.swing.*;
import modele.*;
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
    private JButton btnAjoutScooter;
    private JButton btnAjoutEmploye;
    private JButton btnBasculer;
    private JButton btnCataMarque;
    private JButton btnCataModele;
    private Parc modele;
    private JComboBox<String> comboMarque;
    private JComboBox<String> comboMoto;
    private JComboBox<String> comboPermis;
    private JComboBox<String> comboCouleur;
    private JRadioButton radioCroissant;
    private JRadioButton radioDecroissant;
    private ButtonGroup groupePrix;
    private boolean estGerant; // Permet de savoir si on affiche les fonctionnalités de gérant ou pas
    private Client clientEnCours = null; // Il n'y a personne de connecté au démarrage
    // private Client clientEnCours = new Client("Dupont", "Jean", "0601020304", "jean.dupont@email.com");
    
    public VuePrincipale(Parc modele, boolean estGerant) {
        this.modele = modele;
        modele.addObserver(this);
        this.setTitle("LOUSCOOT - Location de scooters");
        this.setPreferredSize(new Dimension(1000 ,700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(15,15));
        this.modele = modele;
        this.estGerant = estGerant;

        JPanel backgroundPanel = new JPanel(new BorderLayout(15, 15)) {
            private Image backgroundImage = new ImageIcon("C:\\Users\\HP\\OneDrive - Universite Evry Val d'Essonne\\Programmation orienté\\Louscoot\\degrade_gris.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };
        this.setContentPane(backgroundPanel);
        
        
        
        //titre de l'application
        titreApp = new JLabel("LOUSCOOT - Location de scooters", SwingConstants.CENTER);
        titreApp.setFont(new Font("Arial", Font.BOLD, 30));
        titreApp.setForeground(Color.WHITE);
        
// --- FILTRES ---
        panelFilters = new JPanel();
        panelFilters.setLayout(new BoxLayout(panelFilters, BoxLayout.Y_AXIS));
        // Bordure blanche pour le panneau principal
        panelFilters.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Filtres", 0, 0, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        panelFilters.setPreferredSize(new Dimension(220, 0));
        panelFilters.setOpaque(false); // Fond transparent

        // Outil pour créer des bordures blanches facilement
        javax.swing.border.Border bordureBlanche = BorderFactory.createLineBorder(Color.WHITE);

        // 1. Box pour le Tri par prix
        JPanel pTri = new JPanel();
        pTri.setLayout(new BoxLayout(pTri, BoxLayout.Y_AXIS));
        pTri.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Tri par prix", 0, 0, null, Color.WHITE));
        pTri.setOpaque(false); // Transparent
        
        radioCroissant = new JRadioButton("Prix croissant");
        radioCroissant.setForeground(Color.WHITE); 
        radioCroissant.setOpaque(false);
        
        radioDecroissant = new JRadioButton("Prix décroissant");
        radioDecroissant.setForeground(Color.WHITE); 
        radioDecroissant.setOpaque(false);
        
        groupePrix = new ButtonGroup();
        groupePrix.add(radioCroissant);
        groupePrix.add(radioDecroissant);
        pTri.add(radioCroissant);
        pTri.add(radioDecroissant);
        
        panelFilters.add(pTri);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));

        radioCroissant.addActionListener(e -> modele.trierParPrix(true));
        radioDecroissant.addActionListener(e -> modele.trierParPrix(false));

        // 2. Box pour types de permis
        JPanel panelPermis = new JPanel();
        panelPermis.setLayout(new BoxLayout(panelPermis, BoxLayout.Y_AXIS));
        panelPermis.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Type de permis", 0, 0, null, Color.WHITE));
        panelPermis.setOpaque(false);
        comboPermis = new JComboBox<>();
        comboPermis.setMaximumSize(new Dimension(180, 30));
        panelPermis.add(comboPermis);
        panelFilters.add(panelPermis);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));

        // 3. Box pour motorisation
        JPanel pMotorisation = new JPanel();
        pMotorisation.setLayout(new BoxLayout(pMotorisation, BoxLayout.Y_AXIS));
        pMotorisation.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Motorisation", 0, 0, null, Color.WHITE));
        pMotorisation.setOpaque(false);
        comboMoto = new JComboBox<>();
        comboMoto.setMaximumSize(new Dimension(180, 30));
        pMotorisation.add(comboMoto);
        panelFilters.add(pMotorisation);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));

        // 4. Box pour marque
        JPanel pMarque = new JPanel();
        pMarque.setLayout(new BoxLayout(pMarque, BoxLayout.Y_AXIS));
        pMarque.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Marque", 0, 0, null, Color.WHITE));
        pMarque.setOpaque(false);
        comboMarque = new JComboBox<>();
        comboMarque.setMaximumSize(new Dimension(180, 30));
        pMarque.add(comboMarque);
        panelFilters.add(pMarque);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));

        // 5. Box pour date de disponibilité
        JPanel pDate = new JPanel();
        pDate.setLayout(new BoxLayout(pDate, BoxLayout.Y_AXIS));
        pDate.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Disponibilité", 0, 0, null, Color.WHITE));
        pDate.setOpaque(false);
        
        JLabel labelDebut = new JLabel("Date de début:");
        labelDebut.setForeground(Color.WHITE); // Texte en blanc
        JTextField dateDebut = new JTextField("JJ/MM/AAAA");
        dateDebut.setMaximumSize(new Dimension(180, 30));

        JLabel labelFin = new JLabel("Date de fin:");
        labelFin.setForeground(Color.WHITE); // Texte en blanc
        JTextField dateFin = new JTextField("JJ/MM/AAAA");
        dateFin.setMaximumSize(new Dimension(180, 30));

        pDate.add(labelDebut);
        pDate.add(dateDebut);   
        pDate.add(Box.createRigidArea(new Dimension(0, 5)));
        pDate.add(labelFin);
        pDate.add(dateFin);
        panelFilters.add(pDate);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // 6. Box pour couleur
        JPanel pCouleur = new JPanel();
        pCouleur.setLayout(new BoxLayout(pCouleur, BoxLayout.Y_AXIS));
        pCouleur.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Couleur", 0, 0, null, Color.WHITE));
        pCouleur.setOpaque(false);
        comboCouleur = new JComboBox<>();
        comboCouleur.setMaximumSize(new Dimension(180, 30));
        pCouleur.add(comboCouleur);
        panelFilters.add(pCouleur);

        panelFilters.add(Box.createRigidArea(new Dimension(0, 15))); 
        
        // 7. Bouton Appliquer
        JButton btnAppliquer = new JButton("Appliquer les filtres");
        btnAppliquer.setAlignmentX(Component.CENTER_ALIGNMENT);
        // On utilise ta méthode styliserBouton pour qu'il soit raccord ! (Rouge foncé)
        styliserBouton(btnAppliquer, new Color(200, 0, 0), Color.WHITE);
        panelFilters.add(btnAppliquer);

        panelFilters.setVisible(false);
        // --- FIN FILTRES ---

        JPanel panelMenuDroit = new JPanel(new BorderLayout());
        panelMenuDroit.setOpaque(false); // Transparent pour le fond
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

        this.add(panelMenuDroit, BorderLayout.EAST);

        //CRÉATION DU BLOC DU HAUT 
        JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new BoxLayout(panelHaut, BoxLayout.Y_AXIS));
        panelHaut.setOpaque(false); // Pour que le fond transparent fonctionne
        // Ajout du titre
        titreApp.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelHaut.add(titreApp);

        // Ajout de la navigation (Historique, Profil, etc.)
        JPanel panelNavigation = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
        panelNavigation.setOpaque(false); // Transparent pour le fond
        btnHistorique = new JButton("Historique");
        commande = new JButton("Commandes");
        btnProfil = new JButton("Mon Profil");
        contact = new JButton("Contact");
        btnAjoutScooter = new JButton("Ajouter un Scooter");
        btnAjoutEmploye = new JButton("Ajouter un Employé");
        btnCataMarque = new JButton("Nouv. Marque");
        btnCataModele = new JButton("Nouv. Modèle");


        String texteBouton = estGerant ? "Passer en mode Client" : "Passer en mode Gérant";
        btnBasculer = new JButton(texteBouton);
        Color grisFonce = new Color(50, 50, 50); // Un gris qui ira bien avec ton fond
        
        styliserBouton(btnHistorique, grisFonce, Color.WHITE);
        styliserBouton(commande, grisFonce, Color.WHITE);
        styliserBouton(btnProfil, grisFonce, Color.WHITE);
        styliserBouton(contact, grisFonce, Color.WHITE);
        styliserBouton(btnAjoutScooter, grisFonce, Color.WHITE);
        styliserBouton(btnAjoutEmploye, grisFonce, Color.WHITE);
        styliserBouton(btnBasculer, new Color(255, 140, 0), Color.WHITE); 
        
        // On peut aussi styliser les boutons de recherche et filtres !
        styliserBouton(btnToggleFiltres, grisFonce, Color.WHITE);


        btnAjoutEmploye.addActionListener(e -> {
            new FenetreAjoutEmploye(modele).setVisible(true);
        }); 

        btnCataMarque.addActionListener(e -> {
            new FenetreAjoutMarque(modele).setVisible(true);
        });

        btnCataModele.addActionListener(e -> {
            new FenetreAjoutModele(modele).setVisible(true);
        });

        btnBasculer.addActionListener(e -> {
            this.dispose(); // Ferme la fenêtre actuelle
            new VuePrincipale(modele, !estGerant); // Ouvre la nouvelle avec le booléen inversé
        });

        btnAjoutScooter.addActionListener(e -> {
            new FenetreAjoutScooter(modele).setVisible(true);
        });

        if (estGerant) {
            // Le Gérant voit ça :
            panelNavigation.add(btnAjoutScooter);
            panelNavigation.add(btnAjoutEmploye);
            panelNavigation.add(btnHistorique);
            panelNavigation.add(btnCataMarque);
            panelNavigation.add(btnCataModele);
        } else {
            // Le Client voit ça :
            panelNavigation.add(commande);
            panelNavigation.add(btnProfil);
            panelNavigation.add(contact);
        }

        panelNavigation.add(Box.createRigidArea(new Dimension(30, 0)));
        panelNavigation.add(btnBasculer);
        
        panelHaut.add(panelNavigation);


        btnProfil.addActionListener(e -> {
    // Remplacer 'clientEnCours' par la variable que tu utilises pour stocker le client actuel
    if (clientEnCours != null) {
        new FenetreProfil(clientEnCours);
    } else {
        JOptionPane.showMessageDialog(this, "Aucun utilisateur connecté.", "Erreur", JOptionPane.WARNING_MESSAGE);
    }
});


        // On place ce bloc tout en haut
        this.add(panelHaut, BorderLayout.NORTH);

       // On regroupe la recherche et les scooters dans un seul panneau
        JPanel zoneCentrale = new JPanel(new BorderLayout(10, 10));
        zoneCentrale.setOpaque(false); // Transparent pour le fond
        //La barre de recherche
        JPanel panelRecherche = new JPanel(new BorderLayout(5, 5));
        panelRecherche.setOpaque(false); // Transparent pour le fond
        barreRecherche = new JTextField();
        btnRechercher = new JButton("Rechercher");
        styliserBouton(btnRechercher, new Color(50, 50, 50), Color.WHITE);
        panelRecherche.add(barreRecherche, BorderLayout.CENTER);
        panelRecherche.add(btnRechercher, BorderLayout.EAST);
        
        //en haut de la zone centrale
        zoneCentrale.add(panelRecherche, BorderLayout.NORTH); 

        //Le panneau pour les scooters
        panneauScoot = new JPanel();
        panneauScoot.setLayout(new GridLayout(0, 3, 10, 10)); 
        panneauScoot.setOpaque(false); // Transparent pour le fond
        JScrollPane scrollPane = new JScrollPane(panneauScoot);// Permet d'ajouter une barre de défilement
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Enlève la bordure du scroll
        
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
        this.setLocationRelativeTo(null);

        btnHistorique.addActionListener(e -> {
            FenetreHistorique fenHisto = new FenetreHistorique(modele);
            fenHisto.setVisible(true);
        });

        commande.addActionListener(e -> {
            FenetreCommandes fenCmd = new FenetreCommandes(modele);
            fenCmd.setVisible(true);
        });

        contact.addActionListener(e -> {
            FenetreContact fenContact = new FenetreContact(modele);
            fenContact.setVisible(true);
});
    }

    
    private JPanel creerCarteScooter(Scooter scooter) {
        JPanel carte = new JPanel(new BorderLayout(5, 5));

        // Nom du scooter en haut
        JLabel labelModele = new JLabel(scooter.getModele().getNom_modele(), SwingConstants.CENTER);
        labelModele.setFont(new Font("Arial", Font.BOLD, 18));
        carte.add(labelModele, BorderLayout.NORTH);

        //Infos au centre
        JPanel panelInfos = new JPanel(new GridLayout(4, 1,0,5));
        panelInfos.setBackground(Color.WHITE);
        panelInfos.add(new JLabel("Moteur : " + scooter.getModele().getMotorisation(), SwingConstants.CENTER));
        panelInfos.add(new JLabel("Marque : " + scooter.getModele().getMarque().getNomMarque(),SwingConstants.CENTER));
        panelInfos.add(new JLabel("Couleur : " + scooter.getColoris(), SwingConstants.CENTER));
        
        JLabel labelPrix = new JLabel(scooter.getPrix_jour() + " € / jour", SwingConstants.CENTER);
        labelPrix.setForeground(new Color(0, 150, 0)); // Prix en vert
        panelInfos.add(labelPrix);
        
        carte.add(panelInfos, BorderLayout.CENTER);
        //Bouton "Plus de détails" en bas avec son action !
        JButton btnDetails = new JButton("Plus de détails");
        styliserBouton(btnDetails, new Color(30, 30, 30), Color.WHITE);
        btnDetails.addActionListener(e -> {
            // On envoie le booléen estGerant à la page de détails !
            FenetreDetails fenetre = new FenetreDetails(modele, scooter, estGerant);
            fenetre.setVisible(true);
        });


        
        carte.add(btnDetails, BorderLayout.SOUTH);

        


        return carte;
        
    }
    @Override
    public void update(java.util.Observable o, Object arg) {
        // On vide la grille actuelle
        panneauScoot.removeAll();

        //On récupère la nouvelle liste de scooters envoyée par le Parc
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
    private void styliserBouton(JButton btn, Color fond, Color texte) {
        btn.setBackground(fond);
        btn.setForeground(texte);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false); // Enlève le petit carré pointillé moche au clic
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // Espace autour du texte
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Curseur "main"
    }
}
































