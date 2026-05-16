package vue;
import java.awt.*;
import javax.swing.*;
import modele.*;

public class FenetreDetails extends JFrame {
    
    public FenetreDetails(Parc modele, Scooter scooter, boolean estGerant) {
        setTitle("Détails : " + scooter.getModele().getNom_modele());
        setSize(400, 500);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        
        getContentPane().setBackground(new Color(45, 45, 45));

        JPanel panelInfos = new JPanel(new GridLayout(7, 1, 5, 5));
        panelInfos.setOpaque(false); 
        panelInfos.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        panelInfos.add(creerLabel("Marque : " + scooter.getModele().getMarque().getNomMarque()));
        panelInfos.add(creerLabel("Modèle : " + scooter.getModele().getNom_modele()));
        panelInfos.add(creerLabel("Motorisation : " + scooter.getModele().getMotorisation()));
        panelInfos.add(creerLabel("Couleur : " + scooter.getColoris()));
        panelInfos.add(creerLabel("Permis requis : " + scooter.getModele().getPermis()));
        // panelInfos.add(creerLabel("Statut : " + (scooter.isDisponible() ? "Disponible" : "Loué")));
        panelInfos.add(creerLabel("Carburant : " + scooter.getOptions().getCarburant()));
        panelInfos.add(creerLabel("Freinage : " + scooter.getOptions().getFreinage()));
        panelInfos.add(creerLabel("Eclairage : " + scooter.getOptions().getEclairage()));
        panelInfos.add(creerLabel("Stockage : " + scooter.getOptions().getStockage()));
        panelInfos.add(creerLabel("Demarage : " + scooter.getOptions().getDémarrage()));
        
        JLabel prixLabel = new JLabel("Prix : " + scooter.getPrix_jour() + " € / jour");
        prixLabel.setFont(new Font("Arial", Font.BOLD, 15));
        prixLabel.setForeground(new Color(100, 255, 100)); // Vert clair
        panelInfos.add(prixLabel);
        
        add(panelInfos, BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBas.setOpaque(false);
        panelBas.setPreferredSize(new Dimension(0, 100));
        
        if (!estGerant) {
            JButton btnLouer = new JButton("Louer");
            styliserBouton(btnLouer, new Color(0, 120, 215), Color.WHITE);
            btnLouer.setPreferredSize(new Dimension(140, 40));
            btnLouer.addActionListener(e -> {
                new FenetreLouer(modele, scooter).setVisible(true);
                dispose();
            });
            panelBas.add(btnLouer);
        } else {
            JButton btnRetour = new JButton("Gérer le retour");
            styliserBouton(btnRetour, new Color(220, 120, 0), Color.WHITE);
            btnRetour.setPreferredSize(new Dimension(140, 40));
            btnRetour.addActionListener(e -> {
                if (!scooter.getListe_location().isEmpty()) {
                    Location derniereLoc = scooter.getListe_location().lastElement();
                    new FenetreRetour(modele, derniereLoc).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Ce scooter n'a aucune location en cours.", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            });
            panelBas.add(btnRetour);
        }

        JButton btnSupprimer = new JButton("Supprimer le scooter");
        styliserBouton(btnSupprimer, new Color(200, 40, 40), Color.WHITE);
        btnSupprimer.setPreferredSize(new Dimension(180, 40));
        btnSupprimer.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Voulez-vous vraiment supprimer ce scooter ?",
                    "Confirmation de suppression",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (confirmation == JOptionPane.YES_OPTION) {
                modele.supprimerScooter(scooter);
                JOptionPane.showMessageDialog(this, "Scooter supprimé.", "Suppression", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        panelBas.add(btnSupprimer);

        JButton btnFermer = new JButton("Fermer");
        styliserBouton(btnFermer, new Color(100, 100, 100), Color.WHITE);
        btnFermer.setPreferredSize(new Dimension(140, 40));
        btnFermer.addActionListener(e -> dispose());
        panelBas.add(btnFermer);

        add(panelBas, BorderLayout.SOUTH);
    }

    private JLabel creerLabel(String texte) {
        JLabel label = new JLabel(texte);
        label.setForeground(Color.WHITE);
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