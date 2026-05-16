package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modele.*; // Pour utiliser le modèle
import vue.*;

public class ControleurRetour implements ActionListener {
    
    private Parc modele;
    private Location locationEnCours;
    private FenetreRetour vue;

    public ControleurRetour(Parc vmodele, Location vlocationEnCours, FenetreRetour vvue) {
        modele = vmodele;
        locationEnCours = vlocationEnCours;
        vue = vvue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String date = vue.txtDate.getText();
            String commentaires = vue.txtCommentaires.getText();
            double km = Double.parseDouble(vue.txtKilometrage.getText());
            double penalite = Double.parseDouble(vue.txtPenalite.getText());
            
            Employe empSelectionne = (Employe) vue.comboEmployes.getSelectedItem();

            if (empSelectionne == null) {
                JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un employé !", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //etat des lieux final
            EtatDesLieuxFinal etatFinal = new EtatDesLieuxFinal(date, commentaires, km, penalite, locationEnCours);
            
            etatFinal.cloturerLocation(km, commentaires);
            
            locationEnCours.setEtatDesLieux(etatFinal);
            
            // Rafraîchissement
            modele.appliquerFiltresMultiples("Tout", "Tout", "Tout", "Tout","Tout","Tout");
            
            vue.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vue, "Erreur : Le kilométrage et la pénalité doivent être des nombres !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors du retour. Vérifiez vos données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}