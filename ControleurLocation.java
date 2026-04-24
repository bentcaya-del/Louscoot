import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

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
        // 1. On lit les informations tapées par l'utilisateur dans la Vue
            String nom = vue.txtNom.getText();
            String prenom = vue.txtPrenom.getText();
            String tel = vue.txtNum.getText();
            String email = vue.txtMail.getText();
            String dateDebut = vue.txtDateDebut.getText();
            String dateFin = vue.txtDateFin.getText();
            int nbJours = Integer.parseInt(vue.txtNbJours.getText());

        
        if(nom.isEmpty() || prenom.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Veuillez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // A. Créer le client avec TON constructeur (ajuste les paramètres si besoin)
        Client nouveauClient = new Client(nom, prenom,tel,email); 
        
        Employe employeAccueil = new Employe("Admin", "Accueil", "0000", 1500.0, "admin@louscoot.fr", "Gérant", modele);
            modele.ajoutEmploye(employeAccueil);        //    public Location(String vdate_debut, String vdate_fin, int vnombre_jour,Scooter vscooter, EtatDesLieuxFinal vetatDesLieux, Employe vemploye, Client vclient)
        
            employeAccueil.creerContrat(dateDebut, dateFin, nbJours, scooter, nouveauClient);
        
        // D. (Optionnel) Changer le statut du scooter pour dire qu'il n'est plus dispo
        scooter.setEstDisponible(false); 
        
        // =========================================================
        
    JOptionPane.showMessageDialog(vue, "Location validée avec succès pour " + prenom + " " + nom + " !");
            vue.dispose();
    }
        // } catch (Exception ex) {
        //     // Si l'utilisateur a mal tapé la date ou le nombre de jours
        //     JOptionPane.showMessageDialog(vue, "Erreur dans le format des données (Vérifiez les dates et le nombre de jours).", "Erreur", JOptionPane.ERROR_MESSAGE);
        // }
}