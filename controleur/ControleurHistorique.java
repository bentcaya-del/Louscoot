package controleur;
import modele.*; // Pour utiliser le modèle
import vue.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurHistorique implements ActionListener {

    private FenetreHistorique vue;

    public ControleurHistorique(FenetreHistorique vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Client clientSelectionne = (Client) vue.comboClients.getSelectedItem();
        
        if (clientSelectionne != null) {
            vue.modeleTableau.setRowCount(0);

            for (Location loc : clientSelectionne.getListe_location()) {
                
                String statut = (loc.getEtatDesLieux() != null) ? "Terminée" : "En cours";
                Object[] ligne = {
                    loc.getDate_debut(),
                    loc.getDate_fin(),
                    loc.getScooter().getModele().getNom_modele(),
                    loc.calculer_prix_total() + " €",
                    statut
                };
                
                vue.modeleTableau.addRow(ligne);
            }
        }
    }
}    

