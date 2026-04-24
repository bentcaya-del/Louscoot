import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class ControleurRecherche implements ActionListener {
    private Parc modele;
    private JTextField champRecherche;

    public ControleurRecherche(Parc vmodele, JTextField vchampRecherche) {
        vmodele = modele;
        vchampRecherche = champRecherche;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. On récupère ce que l'utilisateur a tapé
        String motCle = champRecherche.getText();
        
        // Petite sécurité : si c'est le texte par défaut, on cherche tout (mot clé vide)
        if (motCle.equals("Rechercher un scooter...")) {
            motCle = ""; 
        }

        System.out.println("Clic détecté ! Recherche de : " + motCle);
        
        // 2. On donne l'ordre au modèle de chercher
        modele.rechercherScooters(motCle); 
    }


















}
