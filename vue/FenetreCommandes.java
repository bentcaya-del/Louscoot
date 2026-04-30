package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modele.*;

public class FenetreCommandes extends JFrame {

    public FenetreCommandes(Parc modele) {
        JLabel labelTitre = new JLabel("Toutes les commandes (Locations)", SwingConstants.CENTER);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        add(labelTitre, BorderLayout.NORTH);

        String[] colonnes = {"Nom Client", "Prénom Client", "Marque", "Modèle", "Statut"};
        DefaultTableModel modeleTableau = new DefaultTableModel(colonnes, 0);
        JTable tableCommandes = new JTable(modeleTableau);

        for (int i = 0; i < modele.getListe_scooter().size(); i++) {
            Scooter scooter = modele.getListe_scooter().get(i);
            
            // Pour chaque scooter, on regarde son historique de locations
            for (Location loc : scooter.getListe_location()) {
                String statut = (loc.getEtatDesLieux() != null) ? "Terminée" : "En cours";
                
                String[] ligne = {
                    loc.getClient().getNom(),
                    loc.getClient().getPrenom(),
                    scooter.getModele().getMarque().getNomMarque(),
                    scooter.getModele().getNom_modele(),
                    statut
                };
                modeleTableau.addRow(ligne);
            }
        }

        JScrollPane scrollPane = new JScrollPane(tableCommandes);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste globale des locations"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        JButton btnFermer = new JButton("Fermer");
        btnFermer.addActionListener(e -> dispose());
        panelBas.add(btnFermer);
        add(panelBas, BorderLayout.SOUTH);
    }
}
