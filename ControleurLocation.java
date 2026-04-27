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
        Client nouveauClient = new Client(nom, prenom, tel, email); 
        Employe employeAccueil = new Employe("Admin", "Accueil", "0000", 1500.0, "admin@louscoot.fr", "Gérant", modele);
        modele.ajoutEmploye(employeAccueil);        
        
        // Cette ligne appelle LocalDate.parse() -> Format AAAA-MM-JJ obligatoire !
        employeAccueil.creerContrat(dateDebut, dateFin, nbJours, scooter, nouveauClient);
        
        //Mise à jour du statut
        scooter.setEstDisponible(false); 

        //Succès
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