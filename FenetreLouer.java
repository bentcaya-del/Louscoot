import java.awt.*;
import javax.swing.*;

public class FenetreLouer extends JFrame {
    
    // On met les champs en 'public' (ou avec des getters) pour que le contrôleur puisse les lire
    public JTextField txtNom = new JTextField();
    public JTextField txtPrenom = new JTextField();
    public JTextField txtNum = new JTextField();
    public JTextField txtMail = new JTextField();

    public JTextField txtDateDebut = new JTextField("2026-04-24"); // Format AAAA-MM-JJ obligatoire
    public JTextField txtDateFin = new JTextField("2026-04-26");
    public JTextField txtNbJours = new JTextField("2");

    public JButton btnValider = new JButton("Valider la location");

    public FenetreLouer(Parc modele, Scooter scooter) {
        setTitle("Location : " + scooter.getModele().getNom_modele());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panelFormulaire = new JPanel(new GridLayout(2, 2, 10, 10));
        panelFormulaire.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panelFormulaire.add(new JLabel("Nom:"));
        panelFormulaire.add(txtNom);
        
        panelFormulaire.add(new JLabel("Prénom:"));
        panelFormulaire.add(txtPrenom);

        panelFormulaire.add(new JLabel("Numéro tel :"));
        panelFormulaire.add(txtNum);
        
        panelFormulaire.add(new JLabel("Email :"));
        panelFormulaire.add(txtMail);

        panelFormulaire.add(new JLabel("Date début (AAAA-MM-JJ) :"));
        panelFormulaire.add(txtDateDebut);
        panelFormulaire.add(new JLabel("Date fin (AAAA-MM-JJ) :"));
        panelFormulaire.add(txtDateFin);
        panelFormulaire.add(new JLabel("Nombre de jours :"));
        panelFormulaire.add(txtNbJours);
        
        add(panelFormulaire, BorderLayout.CENTER);
        
        JPanel panelBas = new JPanel();
        panelBas.add(btnValider);
        add(panelBas, BorderLayout.SOUTH);

        // On branche le contrôleur au bouton
        ControleurLocation ctrlLoc = new ControleurLocation(modele, scooter, this);
        btnValider.addActionListener(ctrlLoc);
    }
}