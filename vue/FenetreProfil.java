package vue;
import modele.*;
import java.awt.*;
import javax.swing.*;

public class FenetreProfil extends JFrame {
    
    public FenetreProfil(Client client) {
        setTitle("Mon Profil - " + client.getPrenom());
        setSize(350, 300);
        setLocationRelativeTo(null); // Centrer sur l'écran
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Titre
        JLabel titre = new JLabel("Informations Personnelles", SwingConstants.CENTER);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Ajout des infos
        mainPanel.add(titre);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        
        mainPanel.add(new JLabel("Nom : " + client.getNom()));
        mainPanel.add(new JLabel("Prénom : " + client.getPrenom()));
        mainPanel.add(new JLabel("Tel : " + client.getNumero_tel()));
        mainPanel.add(new JLabel("Email : " + client.getEmail()));
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        String statut = client.EstFidele() ? "Statut : Client Privilège" : "Statut : Client standard";
        JLabel labelStatut = new JLabel(statut);
        if(client.EstFidele()) labelStatut.setForeground(new Color(200, 150, 0)); 
        
        mainPanel.add(labelStatut);
        
        JButton btnFermer = new JButton("Fermer");
        btnFermer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFermer.addActionListener(e -> dispose());
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnFermer);
        
        add(mainPanel);
        setVisible(true);
    }
}