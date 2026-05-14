package vue;
import modele.*;
import java.awt.*;
import javax.swing.*;

public class FenetreDetails extends JFrame {
    
    public FenetreDetails(Parc modele, Scooter scooter, boolean estGerant) {
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

        JPanel panelBas = new JPanel();
        
        // Separation gerant client

        if (!estGerant) {
            // Client
            JButton btnLouer = new JButton("Louer");
            btnLouer.setBackground(new Color(0, 120, 215));
            btnLouer.setForeground(Color.WHITE);
            btnLouer.addActionListener(e -> {
                FenetreLouer fenetre = new FenetreLouer(modele, scooter);
                fenetre.setVisible(true);
                dispose();
            });
            panelBas.add(btnLouer);

        } else {
            // Gerant
            JButton btnRetour = new JButton("Gérer le retour");
            btnRetour.setBackground(new Color(220, 50, 50));
            btnRetour.setForeground(Color.WHITE);
            
            btnRetour.addActionListener(e -> {
                //On vérifie que le scooter a bien été loué au moins une fois
                if (!scooter.getListe_location().isEmpty()) {
                    // recupere la dernière location du scooter 
                    Location derniereLoc = scooter.getListe_location().lastElement();
                    FenetreRetour fenRet = new FenetreRetour(modele, derniereLoc);
                    fenRet.setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Ce scooter n'a aucune location en cours.", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            });
            panelBas.add(btnRetour);
        }

        // Bouton fermer
        JButton btnFermer = new JButton("Fermer");
        btnFermer.addActionListener(e -> dispose());
        panelBas.add(btnFermer);
        
        add(panelBas, BorderLayout.SOUTH);
    }
}