package modele;
import java.util.*;

/**
 * 
 */
public class Client {

    /**
     * Default constructor
     */
    public Client() {
    }

    /**
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
    private String email;

    /**
     * 
     */
    private Vector<Parc> Liste_parc;

    /**
     * 
     */
    private Vector<Type_permis> Liste_permis;

    /**
     * 
     */
    private Vector<Location> Liste_location;

    public Client(String vnom, String vprenom, String vnumero_tel, String vemail) {
        nom = vnom;
        prenom = vprenom;
        numero_tel = vnumero_tel;
        email = vemail;
        Liste_parc = new Vector<Parc>();
        Liste_permis = new Vector<Type_permis>();
        Liste_location = new Vector<Location>();
    }

    public void ajoutLocation(Location vlocation){
        Liste_location.add(vlocation);
      
        
    }
        
    public void ajoutParc(Parc vparc){
        Liste_parc.add(vparc);
    }
        
    public void ajoutPermis(Type_permis vpermis){
        Liste_permis.add(vpermis);
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

    public String getEmail() {
        return email;
    }

    public Vector<Parc> getListe_parc() {
        return Liste_parc;
    }

    public Vector<Type_permis> getListe_permis() {
        return Liste_permis;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean EstFidele(){
        int nbr_location = Liste_location.size();
        if (nbr_location >= 3){
            return true;
        }else{
            return false;
        }

    }

    public String getHistoriqueCommandes() {
        if (Liste_location == null || Liste_location.isEmpty()) {
            return "Aucune commande passée par " + this.prenom + " " + this.nom + ".";
        }

        String historique = "HISTORIQUE DE " + this.prenom.toUpperCase() + " " + this.nom.toUpperCase() + "\n";
        historique += "Nombre total de locations : " + Liste_location.size() + "\n";
        historique += "-------------------------------------------\n";

        for (int i = 0; i < Liste_location.size(); i++) {
            Location loc = Liste_location.get(i);
            
            historique += "Commande N°" + (i + 1) + "\n";
            historique += "Scooter : " + loc.getScooter().getModele().getNom_modele() + " (" + loc.getScooter().getImmatriculation() + ")\n";
            historique += "Période : du " + loc.getDate_debut() + " au " + loc.getDate_fin() + "\n";
            historique += "Montant payé : " + loc.calculer_prix_total() + " €\n";
            historique += "-------------------------------------------\n";
        }

        return historique;
    }

    public Vector<Location> getListeLocations() {
        return this.Liste_location;
    }
    
    @Override
    public String toString() {
    return this.prenom + " " + this.nom;
}

}