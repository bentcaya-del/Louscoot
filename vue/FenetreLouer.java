package vue;
import controleur.*;
import java.awt.*;
import javax.swing.*;
import modele.*;

public class FenetreLouer extends JFrame {
    
    public JTextField txtNom = new JTextField();
    public JTextField txtPrenom = new JTextField();
    public JTextField txtNum = new JTextField();
    public JTextField txtMail = new JTextField();
    public JTextField txtDateDebut = new JTextField(""); 
    public JTextField txtDateFin = new JTextField("");
    public JTextField txtNbJours = new JTextField("");
    public JButton btnValider = new JButton("Valider la location");

    public FenetreLouer(Parc modele, Scooter scooter) {
        setTitle("Location : " + scooter.getModele().getNom_modele());
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // --- DESIGN SOMBRE ---
        getContentPane().setBackground(new Color(45, 45, 45));
        
        JPanel panelFormulaire = new JPanel(new GridLayout(7, 2, 10, 15));
        panelFormulaire.setOpaque(false);
        panelFormulaire.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        panelFormulaire.add(creerLabel("Nom:")); panelFormulaire.add(txtNom);
        panelFormulaire.add(creerLabel("Prénom:")); panelFormulaire.add(txtPrenom);
        panelFormulaire.add(creerLabel("Numéro tel :")); panelFormulaire.add(txtNum);
        panelFormulaire.add(creerLabel("Email :")); panelFormulaire.add(txtMail);
        panelFormulaire.add(creerLabel("Date début (AAAA-MM-JJ) :")); panelFormulaire.add(txtDateDebut);
        panelFormulaire.add(creerLabel("Date fin (AAAA-MM-JJ) :")); panelFormulaire.add(txtDateFin);
        panelFormulaire.add(creerLabel("Nombre de jours :")); panelFormulaire.add(txtNbJours);
        
        add(panelFormulaire, BorderLayout.CENTER);
        
        JPanel panelBas = new JPanel();
        panelBas.setOpaque(false);
        styliserBouton(btnValider, new Color(0, 150, 0), Color.WHITE); // Vert
        panelBas.add(btnValider);
        add(panelBas, BorderLayout.SOUTH);

        ControleurLocation ctrlLoc = new ControleurLocation(modele, scooter, this);
        btnValider.addActionListener(ctrlLoc);
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