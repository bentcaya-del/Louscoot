package vue;
import javax.swing.*;
import java.awt.*;
import modele.*;

public class FenetreAjoutModele extends JFrame {
    private JTextField txtNom = new JTextField();
    private JTextField txtMoteur = new JTextField();
    private JComboBox<Type_permis> comboPermis = new JComboBox<>(Type_permis.values());
    private JComboBox<Marque> comboMarques;
    private JButton btnValider = new JButton("Enregistrer le Modèle");

    public FenetreAjoutModele(Parc modeleParc) {
        setTitle("Catalogue - Nouveau Modèle");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));

        comboMarques = new JComboBox<>(modeleParc.getCatalogueMarques());

        JPanel p = new JPanel(new GridLayout(4, 2, 10, 20));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        p.add(new JLabel("Marque :")); p.add(comboMarques);
        p.add(new JLabel("Nom du modèle :")); p.add(txtNom);
        p.add(new JLabel("Motorisation :")); p.add(txtMoteur);
        p.add(new JLabel("Permis requis :")); p.add(comboPermis);
        
        add(p, BorderLayout.CENTER);

        JPanel pBas = new JPanel();
        pBas.add(btnValider);
        add(pBas, BorderLayout.SOUTH);

        btnValider.addActionListener(e -> {
            Marque m = (Marque) comboMarques.getSelectedItem();
            if(m != null && !txtNom.getText().isEmpty()) {
                //Constructeur
                Modele mod = new Modele(txtNom.getText(), txtMoteur.getText(), "Standard", "LED", "2024", (Type_permis)comboPermis.getSelectedItem(), m);
                modeleParc.ajouterModele(mod);
                JOptionPane.showMessageDialog(this, "Modèle ajouté au catalogue !");
                dispose();
            }
        });

        setLocationRelativeTo(null);
    }
}