package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import modele.*;
import vue.*;

public class ControleurLocation implements ActionListener {
    
    private Parc modele;
    private Scooter scooter;
    private FenetreLouer vue;

    public ControleurLocation(Parc modele, Scooter scooter, FenetreLouer vue) {
        this.modele = modele;
        this.scooter = scooter;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String nom = vue.txtNom.getText();
            String prenom = vue.txtPrenom.getText();
            String tel = vue.txtNum.getText();
            String email = vue.txtMail.getText();
            String dateDebut = vue.txtDateDebut.getText();
            String dateFin = vue.txtDateFin.getText();

            if(nom.isEmpty() || prenom.isEmpty() || dateDebut.isEmpty() || dateFin.isEmpty()) {
                JOptionPane.showMessageDialog(vue, "Veuillez remplir tous les champs obligatoires !", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //CALCUL DU NOMBRE DE JOURS
            LocalDate dDebut = LocalDate.parse(dateDebut);
            LocalDate dFin = LocalDate.parse(dateFin);
            
            //la différence en jours entre les deux dates
            int nbJoursCalc = (int) ChronoUnit.DAYS.between(dDebut, dFin);
            
            if (nbJoursCalc <= 0) {
                JOptionPane.showMessageDialog(vue, "La date de fin doit être strictement après la date de début !", "Erreur dates", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Recherche ou Création du client
            Client clientActuel = null;
            for (int i = 0; i < modele.getListe_client().size(); i++) {
                Client c = modele.getListe_client().get(i);
                if (c.getNom().equalsIgnoreCase(nom) && c.getPrenom().equalsIgnoreCase(prenom)) {
                    clientActuel = c;
                    break;
                }
            }
            
            if (clientActuel == null) {
                clientActuel = new Client(nom, prenom, tel, email);
                modele.ajoutClient(clientActuel);
            }

            //Création du contrat avec le nombre de jours calculé
            Employe employeAccueil = new Employe("Admin", "Accueil", "0000", 1500.0, "admin@louscoot.fr", "Gérant", modele);
            modele.ajoutEmploye(employeAccueil);        
            
            employeAccueil.creerContrat(dateDebut, dateFin, nbJoursCalc, scooter, clientActuel);     
            scooter.setEstDisponible(false); 

            JOptionPane.showMessageDialog(vue, "Location validée avec succès pour " + nbJoursCalc + " jour(s) !");
            vue.dispose();

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(vue, "Format de date invalide ! Veuillez utiliser AAAA-MM-JJ.", "Erreur Date", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue, "Une erreur est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}