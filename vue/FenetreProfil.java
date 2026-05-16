package vue;
import java.awt.*;
import javax.swing.*;
import modele.*;

public class FenetreProfil extends JFrame {
    
    public FenetreProfil(Client client) {
        setTitle("Mon Profil - " + client.getPrenom());
        setSize(350, 300);
        setLocationRelativeTo(null); 
        
        getContentPane().setBackground(new Color(45, 45, 45));
        
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titre = new JLabel("Informations Personnelles", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        titre.setForeground(Color.WHITE);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        mainPanel.add(titre);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        
        mainPanel.add(creerLabel("Nom : " + client.getNom()));
        mainPanel.add(creerLabel("Prénom : " + client.getPrenom()));
        mainPanel.add(creerLabel("Tel : " + client.getNumero_tel()));
        mainPanel.add(creerLabel("Email : " + client.getEmail()));
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        String statut = client.EstFidele() ? "Statut : Client Privilège" : "Statut : Client standard";
        JLabel labelStatut = new JLabel(statut);
        labelStatut.setFont(new Font("Arial", Font.BOLD, 14));
        labelStatut.setForeground(client.EstFidele() ? new Color(255, 215, 0) : Color.WHITE); 
        mainPanel.add(labelStatut);
        
        JButton btnFermer = new JButton("Fermer");
        styliserBouton(btnFermer, new Color(80, 80, 80), Color.WHITE);
        btnFermer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFermer.addActionListener(e -> dispose());
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnFermer);
        
        add(mainPanel);
        setVisible(true);
    }

    private JLabel creerLabel(String texte) {
        JLabel label = new JLabel(texte);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
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