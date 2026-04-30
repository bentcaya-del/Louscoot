package vue;
import modele.*;
import java.awt.*;
import javax.swing.*;

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

        JPanel panelCentre = new JPanel(new GridLayout(4, 1, 10, 10));
        panelCentre.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblMessage = new JLabel("Une question ? Notre équipe est à votre écoute !", SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        panelCentre.add(lblMessage);

        JLabel lblTel = new JLabel("Téléphone : " + telAgence, SwingConstants.CENTER);
        lblTel.setFont(new Font("Arial", Font.BOLD, 15));
        panelCentre.add(lblTel);

        JLabel lblMail = new JLabel("Email : contact@" + nomAgence.toLowerCase().replace(" ", "") + ".fr", SwingConstants.CENTER);
        lblMail.setFont(new Font("Arial", Font.BOLD, 15));
        panelCentre.add(lblMail);
        
        JLabel lblAdresse = new JLabel(adresseAgence, SwingConstants.CENTER);
        lblAdresse.setFont(new Font("Arial", Font.PLAIN, 12));
        panelCentre.add(lblAdresse);

        add(panelCentre, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        JButton btnFermer = new JButton("Fermer");
        btnFermer.addActionListener(e -> dispose());
        panelBas.add(btnFermer);
        add(panelBas, BorderLayout.SOUTH);
    }
}