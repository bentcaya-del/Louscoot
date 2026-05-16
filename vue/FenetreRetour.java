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
        
        getContentPane().setBackground(new Color(45, 45, 45));
        
        JPanel panelHaut = new JPanel(new GridLayout(3, 2, 10, 15)); 
        panelHaut.setOpaque(false);
        panelHaut.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30)); 
        
        panelHaut.add(creerLabel("Date de retour (AAAA-MM-JJ) :")); panelHaut.add(txtDate);
        panelHaut.add(creerLabel("Kilométrage parcouru :")); panelHaut.add(txtKilometrage);
        panelHaut.add(creerLabel("Pénalité dégâts (€) :")); panelHaut.add(txtPenalite);
        
        JPanel panelCommentaire = new JPanel(new BorderLayout());
        panelCommentaire.setOpaque(false);
        panelCommentaire.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
        panelCommentaire.add(creerLabel("Commentaires sur l'état du scooter :"), BorderLayout.NORTH);
        
        txtCommentaires.setLineWrap(true);
        txtCommentaires.setWrapStyleWord(true);
        panelCommentaire.add(new JScrollPane(txtCommentaires), BorderLayout.CENTER);

        JPanel panelCentre = new JPanel(new BorderLayout());
        panelCentre.setOpaque(false);
        panelCentre.add(panelHaut, BorderLayout.NORTH);
        panelCentre.add(panelCommentaire, BorderLayout.CENTER);
        add(panelCentre, BorderLayout.CENTER);
        
        JPanel panelBas = new JPanel();
        panelBas.setOpaque(false);
        panelBas.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); 
        styliserBouton(btnValider, new Color(220, 50, 50), Color.WHITE); // Rouge
        panelBas.add(btnValider);
        add(panelBas, BorderLayout.SOUTH);

        ControleurRetour ctrlRet = new ControleurRetour(modele, locationEnCours, this);
        btnValider.addActionListener(ctrlRet);
    }

    private JLabel creerLabel(String texte) {
        JLabel label = new JLabel(texte);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        return label;
    }

    private void styliserBouton(JButton btn, Color fond, Color texte) {
        btn.setBackground(fond);
        btn.setForeground(texte);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}