package vue;

import javax.swing.*;
import java.awt.*;
import modele.Parc;
import controleur.ControleurAjoutEmploye;

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

        // Formulaire
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 20));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panelForm.add(new JLabel("Nom :"));
        panelForm.add(txtNom);
        panelForm.add(new JLabel("Prénom :"));
        panelForm.add(txtPrenom);
        panelForm.add(new JLabel("Téléphone :"));
        panelForm.add(txtTel);
        panelForm.add(new JLabel("Salaire (€) :"));
        panelForm.add(txtSalaire);
        panelForm.add(new JLabel("Email :"));
        panelForm.add(txtEmail);
        panelForm.add(new JLabel("Poste / Rôle :"));
        panelForm.add(txtRole);

        add(panelForm, BorderLayout.CENTER);


        JPanel panelBas = new JPanel();
        panelBas.add(btnValider);
        add(panelBas, BorderLayout.SOUTH);

        // Liaison avec le contrôleur
        ControleurAjoutEmploye ctrl = new ControleurAjoutEmploye(modele, this);
        btnValider.addActionListener(ctrl);
    }
}