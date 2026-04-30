package controleur;
import java.awt.event.ActionEvent; // Pour utiliser le modèle
import java.awt.event.ActionListener;
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
    System.out.println("Bouton cliqué !"); // Pour vérifier que le contrôleur réagit

    try {
        //Récupération des textes
        String nom = vue.txtNom.getText();
        String prenom = vue.txtPrenom.getText();
        String tel = vue.txtNum.getText();
        String email = vue.txtMail.getText();
        String dateDebut = vue.txtDateDebut.getText();
        String dateFin = vue.txtDateFin.getText();
        
        // Risque d'erreur ici si c'est vide
        int nbJours = Integer.parseInt(vue.txtNbJours.getText());

        //Vérification de sécurité
        if(nom.isEmpty() || prenom.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Le nom et le prénom sont obligatoires !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Données lues : " + nom + " " + prenom);

        //Création des objets
        Client clientActuel = null;
        
        for (int i = 0; i < modele.getListe_client().size(); i++) {
            Client c = modele.getListe_client().get(i);
            // On vérifie si le nom et prénom correspondent
            if (c.getNom().equalsIgnoreCase(nom) && c.getPrenom().equalsIgnoreCase(prenom)) {
                clientActuel = c;
                break; //On a trouvé le client on stop
            }
        }
        
        // CLient pas trouvé = on le crée et on l'ajoute au Parc
        if (clientActuel == null) {
            clientActuel = new Client(nom, prenom, tel, email);
            modele.ajoutClient(clientActuel);
            System.out.println("Nouveau client ajouté au parc : " + nom);
        } else {
            System.out.println("Client existant retrouvé : " + nom);
        }

        Employe employeAccueil = new Employe("Admin", "Accueil", "0000", 1500.0, "admin@louscoot.fr", "Gérant", modele);
        modele.ajoutEmploye(employeAccueil);        
        
        employeAccueil.creerContrat(dateDebut, dateFin, nbJours, scooter, clientActuel);     
        
        scooter.setEstDisponible(false); 


        JOptionPane.showMessageDialog(vue, "Location validée avec succès !");
        vue.dispose();

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(vue, "Le nombre de jours doit être un chiffre !", "Erreur", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        // Affiche l'erreur réelle dans la console pour t'aider
        ex.printStackTrace(); 
        JOptionPane.showMessageDialog(vue, "Erreur : " + ex.getMessage() + "\n(Vérifiez le format date: AAAA-MM-JJ)", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
}