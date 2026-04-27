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

        JPanel panelHaut = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHaut.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelHaut.add(new JLabel("Choisir un client : "));
        
        comboClients = new JComboBox<>(modele.getListe_client());
        panelHaut.add(comboClients);
        add(panelHaut, BorderLayout.NORTH);

        String[] colonnes = {"Date Début", "Date Fin", "Scooter", "Prix Total", "Statut"};
        modeleTableau = new DefaultTableModel(colonnes, 0);
        tableCommandes = new JTable(modeleTableau);
        
        JScrollPane scrollPane = new JScrollPane(tableCommandes);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des locations"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        JButton btnFermer = new JButton("Fermer");
        btnFermer.addActionListener(e -> dispose());
        panelBas.add(btnFermer);
        add(panelBas, BorderLayout.SOUTH);

        ControleurHistorique ctrl = new ControleurHistorique(this);
        comboClients.addActionListener(ctrl);
        
        if (comboClients.getItemCount() > 0) {
            ctrl.actionPerformed(null);
        }
    }
}
    
