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
    private boolean estGerant; 
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
            private Image backgroundImage = new ImageIcon("images/degrade_gris.jpg").getImage();

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
        
        // Filtre
        panelFilters = new JPanel();
        panelFilters.setLayout(new BoxLayout(panelFilters, BoxLayout.Y_AXIS));
        // Bordure blanche pour le panneau principal
        panelFilters.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Filtres", 0, 0, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        panelFilters.setPreferredSize(new Dimension(220, 0));
        panelFilters.setOpaque(false); // Fond transparent
        javax.swing.border.Border bordureBlanche = BorderFactory.createLineBorder(Color.WHITE);

        panneauScoot = new JPanel();
        
        panneauScoot.setLayout(new GridLayout(0, 3, 15, 15)); 
        JPanel conteneurHaut = new JPanel(new BorderLayout());
        conteneurHaut.setOpaque(false);
        conteneurHaut.add(panneauScoot, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(conteneurHaut);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        
        //Tri par prix
        JPanel pTri = new JPanel();
        pTri.setLayout(new BoxLayout(pTri, BoxLayout.Y_AXIS));
        pTri.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Tri par prix", 0, 0, null, Color.WHITE));
        pTri.setOpaque(false);
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

        //types de permis
        JPanel panelPermis = new JPanel();
        panelPermis.setLayout(new BoxLayout(panelPermis, BoxLayout.Y_AXIS));
        panelPermis.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Type de permis", 0, 0, null, Color.WHITE));
        panelPermis.setOpaque(false);
        comboPermis = new JComboBox<>();
        comboPermis.setMaximumSize(new Dimension(180, 30));
        panelPermis.add(comboPermis);
        panelFilters.add(panelPermis);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));

        //motorisation
        JPanel pMotorisation = new JPanel();
        pMotorisation.setLayout(new BoxLayout(pMotorisation, BoxLayout.Y_AXIS));
        pMotorisation.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Motorisation", 0, 0, null, Color.WHITE));
        pMotorisation.setOpaque(false);
        comboMoto = new JComboBox<>();
        comboMoto.setMaximumSize(new Dimension(180, 30));
        pMotorisation.add(comboMoto);
        panelFilters.add(pMotorisation);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));

        //marque
        JPanel pMarque = new JPanel();
        pMarque.setLayout(new BoxLayout(pMarque, BoxLayout.Y_AXIS));
        pMarque.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Marque", 0, 0, null, Color.WHITE));
        pMarque.setOpaque(false);
        comboMarque = new JComboBox<>();
        comboMarque.setMaximumSize(new Dimension(180, 30));
        pMarque.add(comboMarque);
        panelFilters.add(pMarque);
        panelFilters.add(Box.createRigidArea(new Dimension(0, 10)));

        //date de disponibilité
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
        
        //couleur
        JPanel pCouleur = new JPanel();
        pCouleur.setLayout(new BoxLayout(pCouleur, BoxLayout.Y_AXIS));
        pCouleur.setBorder(BorderFactory.createTitledBorder(bordureBlanche, "Couleur", 0, 0, null, Color.WHITE));
        pCouleur.setOpaque(false);
        comboCouleur = new JComboBox<>();
        comboCouleur.setMaximumSize(new Dimension(180, 30));
        pCouleur.add(comboCouleur);
        panelFilters.add(pCouleur);

        panelFilters.add(Box.createRigidArea(new Dimension(0, 15))); 
        
        //Bouton Appliquer les filtres
        JButton btnAppliquer = new JButton("Appliquer les filtres");
        btnAppliquer.setAlignmentX(Component.CENTER_ALIGNMENT);
        // On utilise ta méthode styliserBouton pour qu'il soit raccord ! (Rouge foncé)
        styliserBouton(btnAppliquer, new Color(200, 0, 0), Color.WHITE);
        panelFilters.add(btnAppliquer);

        panelFilters.setVisible(false);
        //                          FIN FILTRES

        //filtre a droite
        JPanel panelMenuDroit = new JPanel(new BorderLayout());
        panelMenuDroit.setOpaque(false); // Transparent pour le fond
        JButton btnToggleFiltres = new JButton("Filtres ");
        btnToggleFiltres.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Action pour afficher/masquer les filtres
        btnToggleFiltres.addActionListener(e -> {
            boolean estVisible = panelFilters.isVisible();
            panelFilters.setVisible(!estVisible);
            this.revalidate();// Recalcule la mise en page
            this.repaint();//reinitialiser la fenetre pour que le changement soit pris en compte
        });

        panelMenuDroit.add(btnToggleFiltres, BorderLayout.NORTH); // Bouton en haut
        panelMenuDroit.add(panelFilters, BorderLayout.CENTER);    // Filtres en dessous

        this.add(panelMenuDroit, BorderLayout.EAST);

        //                                BLOC DU HAUT 
        JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new BoxLayout(panelHaut, BoxLayout.Y_AXIS));
        panelHaut.setOpaque(false);
        titreApp.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelHaut.add(titreApp);

        //la navigation (Historique, Profil, etc.)
        JPanel panelNavigation = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
        panelNavigation.setOpaque(false);
        btnHistorique = new JButton("Historique");
        commande = new JButton("Commandes");
        btnProfil = new JButton("Mon Profil");
        contact = new JButton("Contact");
        btnCataMarque = new JButton("Nouv. Marque");
        btnCataModele = new JButton("Nouv. Modèle");
        btnAjoutScooter = new JButton("Ajouter un Scooter");
        btnAjoutEmploye = new JButton("Ajouter un Employé");

        String texteBouton = estGerant ? "Passer en mode Client" : "Passer en mode Gérant";
        btnBasculer = new JButton(texteBouton);
        Color grisFonce = new Color(50, 50, 50);//gris
        
        styliserBouton(btnHistorique, grisFonce, Color.WHITE);
        styliserBouton(commande, grisFonce, Color.WHITE);
        styliserBouton(btnProfil, grisFonce, Color.WHITE);
        styliserBouton(contact, grisFonce, Color.WHITE);
        styliserBouton(btnCataMarque, grisFonce, Color.WHITE);
        styliserBouton(btnCataModele, grisFonce, Color.WHITE);
        styliserBouton(btnAjoutScooter, grisFonce, Color.WHITE);
        styliserBouton(btnAjoutEmploye, grisFonce, Color.WHITE);;
        styliserBouton(btnBasculer, new Color(255, 140, 0), Color.WHITE); 
        
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
            panelNavigation.add(btnCataMarque);
            panelNavigation.add(btnCataModele);
            panelNavigation.add(btnAjoutScooter);
            panelNavigation.add(btnAjoutEmploye);
            panelNavigation.add(btnHistorique);
        } else {
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

        // Configuration des barres de défilement
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // Vitesse du scroll
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        // Taille de la zone visible
        scrollPane.setPreferredSize(new Dimension(800, 500));
        add(scrollPane, BorderLayout.CENTER);
        panneauScoot.setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        
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
        // 1. Panneau avec dessin de l'image en fond
        JPanel carte = new JPanel(new BorderLayout(5, 5)) {
            private Image imgFond;
            {
                try {
                    String chemin = scooter.getCheminPhoto();
                    if (chemin != null && !chemin.isEmpty()) {
                        imgFond = new ImageIcon(chemin).getImage();
                    }
                } catch (Exception e) {}
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imgFond != null) {
                    // On dessine l'image sur toute la carte
                    g.drawImage(imgFond, 0, 0, getWidth(), getHeight(), this);
                    // On ajoute un voile noir semi-transparent pour la lisibilité
                    g.setColor(new Color(0, 0, 0, 140)); 
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    // Fond sombre par défaut si pas de photo
                    g.setColor(new Color(45, 45, 45));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        carte.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        // Nom du scooter (Blanc)
        JLabel labelModele = new JLabel(scooter.getModele().getNom_modele(), SwingConstants.CENTER);
        labelModele.setFont(new Font("Arial", Font.BOLD, 20));
        labelModele.setForeground(Color.WHITE);
        carte.add(labelModele, BorderLayout.NORTH);

        JPanel panelInfos = new JPanel(new GridLayout(4, 1, 0, 5));
        panelInfos.setOpaque(false); //transparent le fond
        
        JLabel lblMoteur = new JLabel("Moteur : " + scooter.getModele().getMotorisation(), SwingConstants.CENTER);
        lblMoteur.setForeground(Color.WHITE);
        panelInfos.add(lblMoteur);
        
        JLabel lblMarque = new JLabel("Marque : " + scooter.getModele().getMarque().getNomMarque(), SwingConstants.CENTER);
        lblMarque.setForeground(Color.WHITE);
        panelInfos.add(lblMarque);
        
        JLabel lblCouleur = new JLabel("Couleur : " + scooter.getColoris(), SwingConstants.CENTER);
        lblCouleur.setForeground(Color.WHITE);
        panelInfos.add(lblCouleur);
        
        JLabel labelPrix = new JLabel(scooter.getPrix_jour() + " € / jour", SwingConstants.CENTER);
        labelPrix.setFont(new Font("Arial", Font.BOLD, 14));
        labelPrix.setForeground(new Color(100, 255, 100)); // Vert fluo pour le prix
        panelInfos.add(labelPrix);
        
        carte.add(panelInfos, BorderLayout.CENTER);

        // 4. Bouton "Plus de détails"
        JButton btnDetails = new JButton("Plus de détails");
        // On réutilise ta méthode styliserBouton pour qu'il soit beau (ex: Orange)
        styliserBouton(btnDetails, new Color(255, 140, 0), Color.WHITE);
        btnDetails.addActionListener(e -> {
            new FenetreDetails(modele, scooter, estGerant).setVisible(true);
        });

        JPanel pBas = new JPanel();
        pBas.setOpaque(false);
        pBas.add(btnDetails);
        carte.add(pBas, BorderLayout.SOUTH);

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
        actualiserTousLesFiltres();
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