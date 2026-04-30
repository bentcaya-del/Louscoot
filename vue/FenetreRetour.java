package vue;
import controleur.*;
import java.awt.*;
import javax.swing.*;
import modele.*;

public class FenetreRetour extends JFrame {
    
    public JTextField txtDate = new JTextField(""); 
    public JTextField txtKilometrage = new JTextField("");
    public JTextField txtPenalite = new JTextField("");
    public JTextArea txtCommentaires = new JTextArea(4, 20);
    public JButton btnValider = new JButton("Clôturer la location");

    public FenetreRetour(Parc modele, Location locationEnCours) {
        setTitle("État des lieux final - " + locationEnCours.getScooter().getModele().getNom_modele());
        setSize(450, 450); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panelHaut = new JPanel(new GridLayout(3, 2, 10, 15)); 
        panelHaut.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30)); 
        
        panelHaut.add(new JLabel("Date de retour (AAAA-MM-JJ) :"));
        panelHaut.add(txtDate);
        panelHaut.add(new JLabel("Kilométrage parcouru :"));
        panelHaut.add(txtKilometrage);
        panelHaut.add(new JLabel("Pénalité dégâts (€) :"));
        panelHaut.add(txtPenalite);
        
        // Zone pour les commentaires 
        JPanel panelCommentaire = new JPanel(new BorderLayout());
        panelCommentaire.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
        panelCommentaire.add(new JLabel("Commentaires sur l'état du scooter :"), BorderLayout.NORTH);
        
        txtCommentaires.setLineWrap(true);
        txtCommentaires.setWrapStyleWord(true);
        panelCommentaire.add(new JScrollPane(txtCommentaires), BorderLayout.CENTER);

        JPanel panelCentre = new JPanel(new BorderLayout());
        panelCentre.add(panelHaut, BorderLayout.NORTH);
        panelCentre.add(panelCommentaire, BorderLayout.CENTER);
        add(panelCentre, BorderLayout.CENTER);
        
        JPanel panelBas = new JPanel();
        panelBas.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); 
        panelBas.add(btnValider);
        add(panelBas, BorderLayout.SOUTH);

        ControleurRetour ctrlRet = new ControleurRetour(modele, locationEnCours, this);
        btnValider.addActionListener(ctrlRet);
    }
}