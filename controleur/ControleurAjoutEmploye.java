package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modele.Employe;
import modele.Parc;
import vue.FenetreAjoutEmploye;

public class ControleurAjoutEmploye implements ActionListener {
    private Parc modele;
    private FenetreAjoutEmploye vue;

    public ControleurAjoutEmploye(Parc modele, FenetreAjoutEmploye vue) {
        this.modele = modele;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //données
            String nom = vue.txtNom.getText();
            String prenom = vue.txtPrenom.getText();
            String tel = vue.txtTel.getText();
            double salaire = Double.parseDouble(vue.txtSalaire.getText());
            String email = vue.txtEmail.getText();
            String role = vue.txtRole.getText();

            Employe nouvelEmploye = new Employe(nom, prenom, tel, salaire, email, role, modele);

            modele.ajoutEmploye(nouvelEmploye);

            JOptionPane.showMessageDialog(vue, "Bienvenue à " + prenom + " ! L'employé a été ajouté au parc.");
            vue.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vue, "Le salaire doit être un nombre !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Erreur lors de l'ajout. Vérifiez les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}