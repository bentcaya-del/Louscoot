

import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Location {

    /**
     * Default constructor
     */
    public Location() {
    }

    /**
     * 
     */
    private LocalDate date_debut;

    /**
     * 
     */
    private LocalDate date_fin;

  /**
     * 
     */
    private int nombre_jour;


    /**
     * 
     */
    private Scooter Scooter;

    /**
     * 
     */
    private EtatDesLieuxFinal EtatDesLieux;

    /**
     * 
     */
    private Vector<Equipements> Liste_Equipement;

    /**
     * 
     */
    private Employe Employe;

    /**
     * 
     */
    public Client client;

    public Location(String vdate_debut, String vdate_fin, int vnombre_jour,Scooter vscooter, EtatDesLieuxFinal vetatDesLieux,
            Employe vemploye, Client vclient) {
        date_debut = LocalDate.parse(vdate_debut);
        date_fin = LocalDate.parse(vdate_fin);
        nombre_jour = vnombre_jour;
        Scooter = vscooter;
        EtatDesLieux = vetatDesLieux;
        Employe = vemploye;
        Employe.ajouterLocationGeree();
        client = vclient;
        Liste_Equipement = new Vector<Equipements>();
        this.Scooter.ajouterLocation(this);
        
    }

    public void ajoutEquipement(Equipements equipement){
        Liste_Equipement.add(equipement);
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public int getNombre_jour() {
        return nombre_jour;
    }

    public Scooter getScooter() {
        return Scooter;
    }

    public EtatDesLieuxFinal getEtatDesLieux() {
        return EtatDesLieux;
    }

    public Vector<Equipements> getListe_Equipement() {
        return Liste_Equipement;
    }

    public Employe getEmploye() {
        return Employe;
    }

    public Client getClient() {
        return client;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public void setNombre_jour(int nombre_jour) {
        this.nombre_jour = nombre_jour;
    }

    public void setScooter(Scooter scooter) {
        Scooter = scooter;
    }

    public void setEtatDesLieux(EtatDesLieuxFinal etatDesLieux) {
        EtatDesLieux = etatDesLieux;
    }

    public void setEmploye(Employe employe) {
        Employe = employe;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public double calculer_prix_total(){
        double prixTotal = 0.0;
        double prixScoot = Scooter.getPrix_jour()*nombre_jour;
        prixTotal += prixScoot;
        for (int i =0; i<Liste_Equipement.size();i++){
            Equipements equipementsActuel =Liste_Equipement.get(i);
            double prixParJour= equipementsActuel.getPrix_jour();
            double prixTotalEquip = prixParJour*nombre_jour;
            prixTotal += prixTotalEquip;
        }
            if (this.client.EstFidele()){
                prixTotal = prixTotal*0.9;  
                System.out.println("Remise fidèlité de 10% appliquée"); 
            }
            if (this.EtatDesLieux != null) {
            prixTotal += this.EtatDesLieux.getValeur_penalite();
        }
        return prixTotal;
    }
    

    
        

    public String afficherFacture(int i) {
        String facture = "--- FACTURE LOUSCOOT ---\n";
        facture += "Client : " + this.client.getPrenom() + " " + this.client.getNom() + "\n";
        facture += "Dates : du " + this.date_debut + " au " + this.date_fin + "\n";
        facture += "Scooter : " + this.Scooter.getModele().getNom_modele() + " (" + this.Scooter.getImmatriculation() + ")\n";
        
        if (!Liste_Equipement.isEmpty()) {
            facture += "Équipements : ";
            for (Equipements e : Liste_Equipement) {
                facture += e.getNom() + " ";
            }
            facture += "\n";
        }
        
        if (this.EtatDesLieux != null && this.EtatDesLieux.getValeur_penalite() > 0) {
            facture += "Pénalité dégâts : " + this.EtatDesLieux.getValeur_penalite() + " Euro\n";
        }

        facture += "------------------------\n";
        facture += "TOTAL À PAYER : " + this.calculer_prix_total() + " Euro\n";
        
        return facture;

    }

        public String toString() {
        return "Contrat de location : Client " + this.client.getNom() + " " + this.client.getPrenom() 
             + " | Créé par : " + this.Employe.getNom() + " " + this.Employe.getPrenom() 
             + " | Scooter : " + this.Scooter.getModele().getNom_modele() 
             + " | Période : du " + this.date_debut + " au " + this.date_fin;
    }


    public boolean conflit(LocalDate dateD, LocalDate dateF){
        return(dateD.isBefore(date_fin) && (dateF.isAfter(date_debut)));

    }
















}


