package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modele.*;

public class FenetreCommandes extends JFrame {

    public FenetreCommandes(Parc modele) {
        JLabel labelTitre = new JLabel("Toutes les commandes (Locations)", SwingConstants.CENTER);
        labelTitre.setForeground(Color.WHITE);
        labelTitre.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(45, 45, 45));
        
        add(labelTitre, BorderLayout.NORTH);

        String[] colonnes = {"Nom Client", "Prénom Client", "Marque", "Modèle", "Statut"};
        DefaultTableModel modeleTableau = new DefaultTableModel(colonnes, 0);
        JTable tableCommandes = new JTable(modeleTableau);
        
        // --- STYLE SOMBRE POUR LE TABLEAU ---
        tableCommandes.setBackground(new Color(60, 60, 60));
        tableCommandes.setForeground(Color.WHITE);
        tableCommandes.setGridColor(new Color(80, 80, 80));
        tableCommandes.getTableHeader().setBackground(new Color(30, 30, 30));
        tableCommandes.getTableHeader().setForeground(Color.WHITE);
        tableCommandes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        for (int i = 0; i < modele.getListe_scooter().size(); i++) {
            Scooter scooter = modele.getListe_scooter().get(i);
            for (Location loc : scooter.getListe_location()) {
                String statut = (loc.getEtatDesLieux() != null) ? "Terminée" : "En cours";
                String[] ligne = {
                    loc.getClient().getNom(), loc.getClient().getPrenom(),
                    scooter.getModele().getMarque().getNomMarque(), scooter.getModele().getNom_modele(), statut
                };
                modeleTableau.addRow(ligne);
            }
        }

        JScrollPane scrollPane = new JScrollPane(tableCommandes);
        scrollPane.getViewport().setBackground(new Color(45, 45, 45)); // Fond derrière le tableau
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Liste globale des locations", 0, 0, null, Color.WHITE));
        
        JPanel pCenter = new JPanel(new BorderLayout());
        pCenter.setOpaque(false);
        pCenter.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        pCenter.add(scrollPane, BorderLayout.CENTER);
        add(pCenter, BorderLayout.CENTER);

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