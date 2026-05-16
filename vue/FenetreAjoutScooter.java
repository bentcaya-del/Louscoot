package vue;

import controleur.ControleurAjoutScooter;
import java.awt.*;
import javax.swing.*;
import modele.*;

public class FenetreAjoutScooter extends JFrame {
    
    public JTextField txtImmat = new JTextField();
    public JTextField txtCouleur = new JTextField();
    public JTextField txtKm = new JTextField("0");
    public JTextField txtCaution = new JTextField();
    public JTextField txtPrix = new JTextField();
    public JTextField txtPhoto = new JTextField(); 
    public JButton btnParcourir = new JButton("Parcourir...");
    public JComboBox<Modele> comboModeles;
    public JButton btnValider = new JButton("Ajouter au parc");

    //Options
    public JComboBox<String> comboCarburant = new JComboBox<>(new String[]{"Essence", "Électrique", "Hybride"});
    public JComboBox<String> comboFreinage = new JComboBox<>(new String[]{"Disque", "Tambour", "ABS", "Couplé (CBS)"});
    public JComboBox<String> comboEclairage = new JComboBox<>(new String[]{"LED", "Halogène", "Xénon"});
    public JComboBox<String> comboTableau = new JComboBox<>(new String[]{"Digital", "Analogique", "Écran TFT"});
    public JComboBox<String> comboStockage = new JComboBox<>(new String[]{"Sous la selle", "Top case", "Aucun"});
    public JComboBox<String> comboDemarrage = new JComboBox<>(new String[]{"Électrique", "Kick", "Keyless (Sans clé)"});

    public FenetreAjoutScooter(Parc modeleParc) {
        setTitle("Gestion du Parc - Nouveau Scooter");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(45, 45, 45));

        comboModeles = new JComboBox<>(modeleParc.getCatalogueModeles());

        JPanel panelForm = new JPanel(new GridLayout(14, 2, 10, 10));
        panelForm.setOpaque(false);
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Infos de base
        panelForm.add(creerLabel("Immatriculation :")); panelForm.add(txtImmat);
        panelForm.add(creerLabel("Couleur :")); panelForm.add(txtCouleur);
        panelForm.add(creerLabel("Kilométrage :")); panelForm.add(txtKm);
        panelForm.add(creerLabel("Caution (€) :")); panelForm.add(txtCaution);
        panelForm.add(creerLabel("Prix / Jour (€) :")); panelForm.add(txtPrix);
        panelForm.add(creerLabel("Modèle :")); panelForm.add(comboModeles);
        
        // Options
        panelForm.add(creerLabel("--- OPTIONS ---")); panelForm.add(new JLabel("")); 
        panelForm.add(creerLabel("Carburant :")); panelForm.add(comboCarburant);
        panelForm.add(creerLabel("Freinage :")); panelForm.add(comboFreinage);
        panelForm.add(creerLabel("Éclairage :")); panelForm.add(comboEclairage);
        panelForm.add(creerLabel("Tableau de bord :")); panelForm.add(comboTableau);
        panelForm.add(creerLabel("Stockage :")); panelForm.add(comboStockage);
        panelForm.add(creerLabel("Démarrage :")); panelForm.add(comboDemarrage);
        
        // Zone Photo
        panelForm.add(creerLabel("Photo :"));
        JPanel panelPhoto = new JPanel(new BorderLayout(5, 0));
        panelPhoto.setOpaque(false);
        txtPhoto.setEditable(false); 
        styliserBouton(btnParcourir, new Color(80, 80, 80), Color.WHITE);
        panelPhoto.add(txtPhoto, BorderLayout.CENTER);
        panelPhoto.add(btnParcourir, BorderLayout.EAST);
        panelForm.add(panelPhoto);

        btnParcourir.addActionListener(e -> {
            //JFileChooser pour choisir une image
            JFileChooser fileChooser = new JFileChooser();
            //afficher que les fichiers images
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                txtPhoto.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
            }
        });

        // JScroll  pour defiler 
        JScrollPane scrollPane = new JScrollPane(panelForm);
        scrollPane.setOpaque(false);//scroll transparent
        scrollPane.getViewport().setOpaque(false);//bordure
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.setOpaque(false);
        styliserBouton(btnValider, new Color(255, 140, 0), Color.WHITE);
        panelBas.add(btnValider);
        add(panelBas, BorderLayout.SOUTH);

        ControleurAjoutScooter ctrl = new ControleurAjoutScooter(modeleParc, this);
        btnValider.addActionListener(ctrl);
    }
    //methode pour rendre plus beau 
    private JLabel creerLabel(String texte) {
        JLabel label = new JLabel(texte);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        return label;
    }

    private void styliserBouton(JButton btn, Color fond, Color texte) {
        btn.setBackground(fond);
        btn.setForeground(texte);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}