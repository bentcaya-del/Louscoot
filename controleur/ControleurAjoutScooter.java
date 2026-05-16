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
            String immat = vue.txtImmat.getText();
            String couleur = vue.txtCouleur.getText();
            double km = Double.parseDouble(vue.txtKm.getText());
            double caution = Double.parseDouble(vue.txtCaution.getText());
            double prix = Double.parseDouble(vue.txtPrix.getText());
            Modele mod = (Modele) vue.comboModeles.getSelectedItem();
            String cheminPhoto = vue.txtPhoto.getText(); 

            //options
            String carburant = (String) vue.comboCarburant.getSelectedItem();
            String freinage = (String) vue.comboFreinage.getSelectedItem();
            String eclairage = (String) vue.comboEclairage.getSelectedItem();
            String tableau = (String) vue.comboTableau.getSelectedItem();
            String stockage = (String) vue.comboStockage.getSelectedItem();
            String demarrage = (String) vue.comboDemarrage.getSelectedItem();

            if (immat.isEmpty() || mod == null) {
                JOptionPane.showMessageDialog(vue, "Informations manquantes !", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Scooter nouveauScooter = new Scooter(immat, couleur, km, caution, prix, mod, modeleParc, cheminPhoto);

            Options opt = new Options(freinage, eclairage, tableau, stockage, demarrage, carburant, nouveauScooter);
            nouveauScooter.setOptions(opt);

            // Ajout au modèle
            modeleParc.AjouterScooter(nouveauScooter);
            modeleParc.appliquerFiltresMultiples("Tout", "Tout", "Tout", "Tout","Tout","Tout");
            vue.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vue, "Vérifiez les prix et le kilométrage (nombres) !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ajout : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}