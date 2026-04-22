
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Scooter {

    /**
     * Default constructor
     */
    public Scooter() {
    }

    /**
     * 
     */
    private String immatriculation;

    /**
     * 
     */
    private String coloris;

    /**
     * 
     */
    private double kilometrage_total;

    /**
     * 
     */
    private double prix_caution;

    /**
     * 
     */
    private double prix_jour;

    /**
     * 
     */
    private  Modele Modele;

    /**
     * 
     */
    private Vector<Location> Liste_location;

    /**
     * 
     */
    private Parc client;

    /**
     * 
     */
    private Vector<Options> liste_option;

    public Scooter(String vimmatriculation, String vcoloris, double vkilometrage_total, double vprix_caution,
            double vprix_jour, Modele vmodele, Parc vclient) {
        immatriculation = vimmatriculation;
        coloris = vcoloris;
        kilometrage_total = vkilometrage_total;
        prix_caution = vprix_caution;
        prix_jour = vprix_jour;
        Modele = vmodele;
        client = vclient;
        Liste_location = new Vector<Location>();
        liste_option = new Vector<Options>();
    }

    public void ajoutLocation(Location vlocation){
        Liste_location.add(vlocation);
    }

    public void ajoutOption(Options voption){
        liste_option.add(voption);
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getColoris() {
        return coloris;
    }

    public double getKilometrage_total() {
        return kilometrage_total;
    }

    public double getPrix_caution() {
        return prix_caution;
    }

    public double getPrix_jour() {
        return prix_jour;
    }

    public Modele getModele() {
        return Modele;
    }

    public Vector<Location> getListe_location() {
        return Liste_location;
    }

    public Parc getClient() {
        return client;
    }

    public Vector<Options> getListe_option() {
        return liste_option;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setColoris(String coloris) {
        this.coloris = coloris;
    }

    public void setKilometrage_total(double kilometrage_total) {
        this.kilometrage_total = kilometrage_total;
    }

    public void setPrix_caution(double prix_caution) {
        this.prix_caution = prix_caution;
    }

    public void setPrix_jour(double prix_jour) {
        this.prix_jour = prix_jour;
    }

    public void setModele(Modele modele) {
        Modele = modele;
    }

    public void setClient(Parc client) {
        this.client = client;
    }
     public void rouler(double kilometrage_parcourus){
            kilometrage_total += kilometrage_parcourus;
        }
    
        public boolean verifierPermisClient(Client vClient) {
            Type_permis permisRequis = this.getModele().getPermis();
            for (int i = 0; i < vClient.getListe_permis().size(); i++) {
                if (vClient.getListe_permis().get(i).equals(permisRequis)) {
                    return true; 
                }
            }
            return false;
        }
    
    
    public void ajouterLocation(Location loc) {
        this.Liste_location.add(loc);
    }
    
    public boolean estDisponibleAuxDates(String debutPrevu, String finPrevue) {
        LocalDate debutDemande = LocalDate.parse(debutPrevu);
        LocalDate finDemande = LocalDate.parse(finPrevue);
    
        for (Location x : Liste_location){
            if (x.conflit(debutDemande, finDemande)) {
                return false; 
            }
            
        } 
          return true;
        }
    
    public void setEstDisponible(boolean dispo) {
        // Méthode nécessaire pour cloturerLocation
    }
        }
    
    
    }
    

    
