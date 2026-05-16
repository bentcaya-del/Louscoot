package vue;
import java.awt.*;
import javax.swing.*;
import modele.*;

public class FenetreContact extends JFrame {

    public FenetreContact(Parc modele) {
        String nomAgence = (modele.getNom() != null) ? modele.getNom() : "LouScoot";
        String telAgence = (modele.getNumero_tel() != null) ? modele.getNumero_tel() : "Non renseigné";
        String adresseAgence = (modele.getAdresse() != null && modele.getVille() != null) 
                                ? modele.getAdresse() + ", " + modele.getVille() 
                                : "Adresse non renseignée";

        setTitle("Contactez l'agence " + nomAgence);
        setSize(400, 250);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        getContentPane().setBackground(new Color(45, 45, 45));

        JPanel panelCentre = new JPanel(new GridLayout(4, 1, 10, 10));
        panelCentre.setOpaque(false);
        panelCentre.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblMessage = new JLabel("Une question ? Notre équipe est à votre écoute !", SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMessage.setForeground(Color.WHITE);
        panelCentre.add(lblMessage);

        JLabel lblTel = new JLabel("Téléphone : " + telAgence, SwingConstants.CENTER);
        lblTel.setFont(new Font("Arial", Font.BOLD, 15));
        lblTel.setForeground(new Color(255, 140, 0)); // Orange
        panelCentre.add(lblTel);

        JLabel lblMail = new JLabel("Email : contact@" + nomAgence.toLowerCase().replace(" ", "") + ".fr", SwingConstants.CENTER);
        lblMail.setFont(new Font("Arial", Font.BOLD, 15));
        lblMail.setForeground(new Color(0, 150, 255)); // Bleu clair
        panelCentre.add(lblMail);
        
        JLabel lblAdresse = new JLabel(adresseAgence, SwingConstants.CENTER);
        lblAdresse.setFont(new Font("Arial", Font.PLAIN, 12));
        lblAdresse.setForeground(Color.LIGHT_GRAY);
        panelCentre.add(lblAdresse);

        add(panelCentre, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.setOpaque(false);
        JButton btnFermer = new JButton("Fermer");
        styliserBouton(btnFermer, new Color(80, 80, 80), Color.WHITE);
        btnFermer.addActionListener(e -> dispose());
        panelBas.add(btnFermer);
        add(panelBas, BorderLayout.SOUTH);
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