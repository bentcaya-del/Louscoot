package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modele.*;
import vue.FenetreAjoutScooter;

public class ControleurAjoutScooter implements ActionListener {
    private Parc modeleParc;
    private FenetreAjoutScooter vue;

    public ControleurAjoutScooter(Parc modeleParc, FenetreAjoutScooter vue) {
        this.modeleParc = modeleParc;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Récupération des informations de la fenêtre
            String immat = vue.txtImmat.getText();
            String couleur = vue.txtCouleur.getText();
            double km = Double.parseDouble(vue.txtKm.getText());
            double caution = Double.parseDouble(vue.txtCaution.getText());
            double prix = Double.parseDouble(vue.txtPrix.getText());
            Modele mod = (Modele) vue.comboModeles.getSelectedItem();
            
            String cheminPhoto = vue.txtPhoto.getText(); 

            if (immat.isEmpty() || mod == null) {
                JOptionPane.showMessageDialog(vue, "Informations manquantes !", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Création du scooter avec la photo
            // ATTENTION : L'ordre des paramètres ici doit correspondre exactement 
            // à l'ordre dans le constructeur de ton fichier Scooter.java !
            // J'ai ajouté 'cheminPhoto' à la fin.
            Scooter nouveauScooter = new Scooter(immat, couleur, km, caution, prix, mod, modeleParc, cheminPhoto);

            // Ajout au modèle
            modeleParc.AjouterScooter(nouveauScooter);

            // Refresh de la grille pour l'afficher
            modeleParc.appliquerFiltresMultiples("Tout", "Tout", "Tout", "Tout","Tout","Tout");

            JOptionPane.showMessageDialog(vue, "Le scooter " + immat + " a été ajouté avec succès !");
            vue.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vue, "Vérifiez les prix et le kilométrage (nombres uniquement) !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ajout : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}