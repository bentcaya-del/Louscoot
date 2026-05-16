package vue;
import controleur.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modele.*;

public class FenetreHistorique extends JFrame {

    public JComboBox<Client> comboClients;
    public JTable tableCommandes;
    public DefaultTableModel modeleTableau;

    public FenetreHistorique(Parc modele) {
        setTitle("Historique des commandes clients");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        getContentPane().setBackground(new Color(45, 45, 45));

        JPanel panelHaut = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHaut.setOpaque(false);
        panelHaut.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblChoisir = new JLabel("Choisir un client : ");
        lblChoisir.setForeground(Color.WHITE);
        lblChoisir.setFont(new Font("Arial", Font.BOLD, 14));
        panelHaut.add(lblChoisir);
        
        comboClients = new JComboBox<>(modele.getListe_client());
        panelHaut.add(comboClients);
        add(panelHaut, BorderLayout.NORTH);

        String[] colonnes = {"Date Début", "Date Fin", "Scooter", "Prix Total", "Statut"};
        modeleTableau = new DefaultTableModel(colonnes, 0);
        tableCommandes = new JTable(modeleTableau);
        
        // --- STYLE SOMBRE POUR LE TABLEAU ---
        tableCommandes.setBackground(new Color(60, 60, 60));
        tableCommandes.setForeground(Color.WHITE);
        tableCommandes.setGridColor(new Color(80, 80, 80));
        tableCommandes.getTableHeader().setBackground(new Color(30, 30, 30));
        tableCommandes.getTableHeader().setForeground(Color.WHITE);
        tableCommandes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(tableCommandes);
        scrollPane.getViewport().setBackground(new Color(45, 45, 45));
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Liste des locations", 0, 0, null, Color.WHITE));
        
        JPanel pCenter = new JPanel(new BorderLayout());
        pCenter.setOpaque(false);
        pCenter.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        pCenter.add(scrollPane, BorderLayout.CENTER);
        add(pCenter, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.setOpaque(false);
        JButton btnFermer = new JButton("Fermer");
        styliserBouton(btnFermer, new Color(80, 80, 80), Color.WHITE);
        btnFermer.addActionListener(e -> dispose());
        panelBas.add(btnFermer);
        add(panelBas, BorderLayout.SOUTH);

        ControleurHistorique ctrl = new ControleurHistorique(this);
        comboClients.addActionListener(ctrl);
        
        if (comboClients.getItemCount() > 0) {
            ctrl.actionPerformed(null);
        }
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