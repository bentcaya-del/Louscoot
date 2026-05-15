package vue;

import javax.swing.*;
import java.awt.*;
import modele.*;
import controleur.ControleurAjoutScooter;

public class FenetreAjoutScooter extends JFrame {
    
    public JTextField txtImmat = new JTextField();
    public JTextField txtCouleur = new JTextField();
    public JTextField txtKm = new JTextField("0");
    public JTextField txtCaution = new JTextField();
    public JTextField txtPrix = new JTextField();
    
    // Liste pour choisir le modèle
    public JComboBox<Modele> comboModeles;
    public JButton btnValider = new JButton("Ajouter au parc");

    public FenetreAjoutScooter(Parc modeleParc) {
        setTitle("Gestion du Parc - Nouveau Scooter");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // On récupère tous les modèles disponibles dans le parc pour les mettre dans la liste
        // On crée un Vector de Modeles uniques pour la JComboBox
        comboModeles = new JComboBox<>(modeleParc.getCatalogueModeles());

        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 20));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panelForm.add(new JLabel("Immatriculation :"));
        panelForm.add(txtImmat);
        panelForm.add(new JLabel("Couleur :"));
        panelForm.add(txtCouleur);
        panelForm.add(new JLabel("Kilométrage actuel :"));
        panelForm.add(txtKm);
        panelForm.add(new JLabel("Montant Caution (€) :"));
        panelForm.add(txtCaution);
        panelForm.add(new JLabel("Prix / Jour (€) :"));
        panelForm.add(txtPrix);
        panelForm.add(new JLabel("Modèle de véhicule :"));
        panelForm.add(comboModeles);

        add(panelForm, BorderLayout.CENTER);

        // Bouton normal
        JPanel panelBas = new JPanel();
        panelBas.add(btnValider);
        add(panelBas, BorderLayout.SOUTH);

        // Contrôleur
        ControleurAjoutScooter ctrl = new ControleurAjoutScooter(modeleParc, this);
        btnValider.addActionListener(ctrl);
    }
}