package modele;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Employe {


    /*
     * 
     */
    private String nom;

    /**
     * 
     */
    private String prenom;

    /**
     * 
     */
    private String numero_tel;

    /**
     * 
     */
    private double salaire;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String role;

    /**
     * 
     */
    private Parc Parc;

    /**
     * 
     */
    private Vector<Location> Liste_location;

    public Employe(String vnom, String vprenom, String vnumero_tel, double vsalaire, String vemail, String vrole, Parc vparc) {
        nom = vnom;
        prenom = vprenom;
        numero_tel = vnumero_tel;
        salaire = vsalaire;
        email = vemail;
        role = vrole;
        Parc = vparc;
        Liste_location = new Vector<Location>();
    }
        
    public void ajoutLocation(Location vlocation){
        Liste_location.add(vlocation);        
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public double getSalaire() {
        return salaire;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Parc getParc() {
        return Parc;
    }

    public Vector<Location> getListe_location() {
        return Liste_location;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setParc(Parc parc) {
        Parc = parc;
    }

    @Override
    public String toString() {
        return this.prenom + " " + this.nom + " (" + this.role + ")";
    }

    public Location creerContrat(String vdate_debut, String vdate_fin, int vnombre_jour, Scooter vscooter, Client vclient){
        Location contrat = new Location(vdate_debut, vdate_fin, vnombre_jour, vscooter, null, this, vclient);
        vclient.ajoutLocation(contrat);
        System.out.println("Contrat créé pour le client " + vclient.getNom() + " " + vclient.getPrenom() + " par l'employé " + this.getNom() + " " + this.getPrenom()+" pour le scooter " + vscooter.getModele().getNom_modele()+" pour la période du " + vdate_debut + " au " + vdate_fin);
        return contrat;
    }
    
    public int getNbLocationsGerees() {
        return Liste_location.size();
    }
    

}