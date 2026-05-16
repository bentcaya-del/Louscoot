package controleur;
import modele.*; // Pour utiliser le modèle
import vue.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import modele.Parc;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class ControleurFiltre implements ActionListener {
    
    private Parc modele;
    private JComboBox<String> comboPermis;
    private JComboBox<String> comboMoto;
    private JComboBox<String> comboMarque;
    private JComboBox<String> comboCouleur;
    private JTextField txtDateDebut;
    private JTextField txtDateFin;

    // Le constructeur prend le modèle et les 4 boîtes
    public ControleurFiltre(Parc modele, JComboBox<String> permis, JComboBox<String> moto, JComboBox<String> marque, JComboBox<String> couleur, JTextField txtDateDebut, JTextField txtDateFin) {
        this.modele = modele;
        this.comboPermis = permis;
        this.comboMoto = moto;
        this.comboMarque = marque;
        this.comboCouleur = couleur;
        this.txtDateDebut = txtDateDebut;
        this.txtDateFin = txtDateFin;
    }

   @Override
    public void actionPerformed(ActionEvent e) {
        // Recuparations des choix
        String cPermis = (String) comboPermis.getSelectedItem();
        String cMoto = (String) comboMoto.getSelectedItem();
        String cMarque = (String) comboMarque.getSelectedItem();
        String cCouleur = (String) comboCouleur.getSelectedItem();
    
    //anti plantage
    if (cPermis == null || cMoto == null || cMarque == null || cCouleur == null) {
            return; 
        }
    

        String strDebut = txtDateDebut.getText(); 
        String strFin = txtDateFin.getText();

        LocalDate dateDebut = null;
        LocalDate dateFin = null;

        if (!strDebut.isEmpty() && !strFin.isEmpty()) {
            try {
                // On définit le format attendu : Année/Mois/Jour
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
                
                dateDebut = LocalDate.parse(strDebut, format);
                dateFin = LocalDate.parse(strFin, format);
                
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Format de date invalide. Veuillez utiliser AAAA-MM-JJ", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        modele.appliquerFiltresMultiples(cMarque, cPermis, cMoto, cCouleur, dateDebut, dateFin);
}   }