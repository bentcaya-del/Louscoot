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

    public FenetreAjoutScooter(Parc modeleParc) {
        setTitle("Gestion du Parc - Nouveau Scooter");
        setSize(450, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // --- DESIGN SOMBRE ---
        getContentPane().setBackground(new Color(45, 45, 45));

        comboModeles = new JComboBox<>(modeleParc.getCatalogueModeles());

        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 20));
        panelForm.setOpaque(false); // Fond transparent
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panelForm.add(creerLabel("Immatriculation :"));
        panelForm.add(txtImmat);
        panelForm.add(creerLabel("Couleur :"));
        panelForm.add(txtCouleur);
        panelForm.add(creerLabel("Kilométrage actuel :"));
        panelForm.add(txtKm);
        panelForm.add(creerLabel("Montant Caution (€) :"));
        panelForm.add(txtCaution);
        panelForm.add(creerLabel("Prix / Jour (€) :"));
        panelForm.add(txtPrix);
        panelForm.add(creerLabel("Modèle de véhicule :"));
        panelForm.add(comboModeles);
        
        // Zone Photo
        panelForm.add(creerLabel("Photo du scooter :"));
        JPanel panelPhoto = new JPanel(new BorderLayout(5, 0));
        panelPhoto.setOpaque(false);
        txtPhoto.setEditable(false); 
        styliserBouton(btnParcourir, new Color(80, 80, 80), Color.WHITE); // Bouton Gris
        panelPhoto.add(txtPhoto, BorderLayout.CENTER);
        panelPhoto.add(btnParcourir, BorderLayout.EAST);
        panelForm.add(panelPhoto);

        btnParcourir.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                txtPhoto.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
            }
        });

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.setOpaque(false);
        styliserBouton(btnValider, new Color(255, 140, 0), Color.WHITE); // Bouton Orange
        panelBas.add(btnValider);
        add(panelBas, BorderLayout.SOUTH);

        ControleurAjoutScooter ctrl = new ControleurAjoutScooter(modeleParc, this);
        btnValider.addActionListener(ctrl);
    }

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