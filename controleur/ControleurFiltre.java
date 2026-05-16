package controleur;
import modele.*; // Pour utiliser le modèle
import vue.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;



public class ControleurFiltre implements ActionListener {
    
    private Parc modele;
    private JComboBox<String> comboPermis;
    private JComboBox<String> comboMoto;
    private JComboBox<String> comboMarque;
    private JComboBox<String> comboCouleur;

    // Le constructeur prend le modèle et les 4 boîtes
    public ControleurFiltre(Parc modele, JComboBox<String> permis, JComboBox<String> moto, JComboBox<String> marque, JComboBox<String> couleur) {
        this.modele = modele;
        this.comboPermis = permis;
        this.comboMoto = moto;
        this.comboMarque = marque;
        this.comboCouleur = couleur;
    }

   @Override
    public void actionPerformed(ActionEvent e) {
        // Recuparations des choix
        String cPermis = (String) comboPermis.getSelectedItem();
        String cMoto = (String) comboMoto.getSelectedItem();
        String cMarque = (String) comboMarque.getSelectedItem();
        String cCouleur = (String) comboCouleur.getSelectedItem();
        String dateDebut = vue.txtDateDebut.getText(); 
        String dateFin = vue.txtDateFin.getText();
    
    //anti plantage
    if (cPermis == null || cMoto == null || cMarque == null || cCouleur == null) {
            return; 
        }
    }

}   