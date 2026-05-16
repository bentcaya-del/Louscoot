package vue;

import controleur.ControleurAjoutEmploye;
import java.awt.*;
import javax.swing.*;
import modele.Parc;

public class FenetreAjoutEmploye extends JFrame {
    public JTextField txtNom = new JTextField();
    public JTextField txtPrenom = new JTextField();
    public JTextField txtTel = new JTextField();
    public JTextField txtSalaire = new JTextField();
    public JTextField txtEmail = new JTextField();
    public JTextField txtRole = new JTextField();
    public JButton btnValider = new JButton("Enregistrer l'employé");

    public FenetreAjoutEmploye(Parc modele) {
        setTitle("Recrutement - Nouvel Employé");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // --- DESIGN SOMBRE ---
        getContentPane().setBackground(new Color(45, 45, 45));

        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 20));
        panelForm.setOpaque(false);
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panelForm.add(creerLabel("Nom :"));
        panelForm.add(txtNom);
        panelForm.add(creerLabel("Prénom :"));
        panelForm.add(txtPrenom);
        panelForm.add(creerLabel("Téléphone :"));
        panelForm.add(txtTel);
        panelForm.add(creerLabel("Salaire (€) :"));
        panelForm.add(txtSalaire);
        panelForm.add(creerLabel("Email :"));
        panelForm.add(txtEmail);
        panelForm.add(creerLabel("Poste / Rôle :"));
        panelForm.add(txtRole);

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.setOpaque(false);
        styliserBouton(btnValider, new Color(0, 150, 0), Color.WHITE); // Btn Vert
        panelBas.add(btnValider);
        add(panelBas, BorderLayout.SOUTH);

        ControleurAjoutEmploye ctrl = new ControleurAjoutEmploye(modele, this);
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