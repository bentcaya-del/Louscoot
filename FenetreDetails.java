import java.awt.*;
import javax.swing.*;

public class FenetreDetails extends JFrame {
    
    public FenetreDetails(Scooter scooter) {
        setTitle("Détails : " + scooter.getModele().getNom_modele());
        setSize(400, 350);
        setLocationRelativeTo(null); // Centré à l'écran
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // Panneau des infos
        JPanel panelInfos = new JPanel(new GridLayout(6, 1, 5, 5));
        panelInfos.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        panelInfos.add(new JLabel("➤ Marque : " + scooter.getModele().getMarque().getNomMarque()));
        panelInfos.add(new JLabel("➤ Modèle : " + scooter.getModele().getNom_modele()));
        panelInfos.add(new JLabel("➤ Motorisation : " + scooter.getModele().getMotorisation()));
        panelInfos.add(new JLabel("➤ Couleur : " + scooter.getColoris()));
        panelInfos.add(new JLabel("➤ Permis requis : " + scooter.getModele().getPermis()));
        
        JLabel prixLabel = new JLabel("Prix : " + scooter.getPrix_jour() + " € / jour");
        prixLabel.setForeground(new Color(0, 150, 0));
        panelInfos.add(prixLabel);
        
        add(panelInfos, BorderLayout.CENTER);

        // Bouton fermer
        JButton btnFermer = new JButton("Fermer");
        btnFermer.addActionListener(e -> dispose());
        add(btnFermer, BorderLayout.SOUTH);
    }
}