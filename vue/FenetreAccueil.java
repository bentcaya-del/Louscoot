package vue;

import java.awt.*;
import javax.swing.*;
import modele.Parc;

public class FenetreAccueil extends JFrame{
    public FenetreAccueil(Parc modele) {
        setTitle("LOUSCOOT - Bienvenue");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Bienvenue sur Louscoot", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        add(titre, BorderLayout.NORTH);

        JPanel panelBoutons = new JPanel(new GridLayout(1, 2, 20, 20));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnClient = new JButton("Espace Client");
        btnClient.setBackground(new Color(0, 120, 215));
        btnClient.setForeground(Color.WHITE);

        JButton btnGerant = new JButton("Espace Gérant");
        btnGerant.setBackground(new Color(50, 50, 50));
        btnGerant.setForeground(Color.WHITE);

        btnClient.addActionListener(e -> {
            new VuePrincipale(modele, false); // false = pas gérant
            dispose(); // Ferme l'accueil
        });

        btnGerant.addActionListener(e -> {
            new VuePrincipale(modele, true); // true = gérant
            dispose(); // Ferme l'accueil
        });

        panelBoutons.add(btnClient);
        panelBoutons.add(btnGerant);
        add(panelBoutons, BorderLayout.CENTER);
        
        setVisible(true);
    }
}

