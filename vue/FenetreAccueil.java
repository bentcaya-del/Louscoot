package vue;

import java.awt.*;
import javax.swing.*;
import modele.Parc;

public class FenetreAccueil extends JFrame {
    
    public FenetreAccueil(Parc modele) {
        setTitle("LOUSCOOT - Bienvenue");
        setSize(600, 400); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panneau de fond avec l'image
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            private Image backgroundImage;

            {
                try {
                    backgroundImage = new ImageIcon("images/Background_image.jpeg").getImage();
                } catch (Exception e) {}
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    //image prend toute la fenetre
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };
        setContentPane(backgroundPanel);

        // Titre
        JLabel titre = new JLabel("Bienvenue sur Louscoot", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 28));
        titre.setForeground(Color.WHITE);
        titre.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        backgroundPanel.add(titre, BorderLayout.NORTH);

        // Conteneur des boutons transparent
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        panelBoutons.setOpaque(false); 
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(80, 30, 30, 30));

        // Boutons sobres sans effet de survol
        JButton btnClient = new BoutonSimple("Espace Client", new Color(40, 40, 40));
        JButton btnGerant = new BoutonSimple("Espace Gérant", new Color(80, 80, 80));

        btnClient.addActionListener(e -> {
            new VuePrincipale(modele, false);
            dispose();
        });

        btnGerant.addActionListener(e -> {
            new VuePrincipale(modele, true);
            dispose();
        });

        panelBoutons.add(btnClient);
        panelBoutons.add(btnGerant);
        backgroundPanel.add(panelBoutons, BorderLayout.CENTER);
        
        setVisible(true);
    }

    // Classe pour arrondir les boutons
    class BoutonSimple extends JButton {
        private Color couleurFond;

        public BoutonSimple(String text, Color fond) {
            super(text);
            this.couleurFond = fond;

            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 15));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(170, 45));
            
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            //anti-aliasing pour activer le lissage 
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(couleurFond);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); 
            
            super.paintComponent(g2);
            g2.dispose();
        }
    }
}