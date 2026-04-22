

import java.util.*;

/**
 * 
 */
public class Parc {

    /**
     * Default constructor
     */
    public Parc() {
    }

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private String adresse;

    /**
     * 
     */
    private String ville;

    /**
     * 
     */
    private String numero_tel;

    /**
     * 
     */
    private Vector<Client> Liste_client;

    /**
     * 
     */
    private Vector<Scooter> Liste_scooter;

    /**
     * 
     */
    private Vector<Employe> Liste_employe;

    public Parc(String vnom, String vadresse, String vville,String vnumero_tel) {
        nom = vnom;
        adresse = vadresse;
        ville = vville;
        numero_tel = vnumero_tel;
        Liste_client = new Vector<Client>();
        Liste_scooter = new Vector<Scooter>();
        Liste_employe = new Vector<Employe>();
    }

    public void ajoutClient(Client vclient){
        Liste_client.add(vclient);

    }

    public void ajoutScooter(Scooter vscooter){
        Liste_scooter.add(vscooter);
    }

    public void ajoutEmploye(Employe vemploye){
        Liste_employe.add(vemploye);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String vnom) {
        nom = vnom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String vadresse) {
        adresse = vadresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String vville) {
        ville = vville;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(String vnumero_tel) {
        numero_tel = vnumero_tel;
    }

    public Vector<Client> getListe_client() {
        return Liste_client;
    }


    public Vector<Scooter> getListe_scooter() {
        return Liste_scooter;
    }


    public Vector<Scooter> getScootersDisponibles(String dateRechercheDebut, String dateRechercheFin) {
        
        Vector<Scooter> listeDispo = new Vector<Scooter>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
            Scooter s = Liste_scooter.get(i);
            
            if (s.estDisponibleAuxDates(dateRechercheDebut, dateRechercheFin)) {
                listeDispo.add(s);
            }
        }

        return listeDispo;
    }

    public Employe getEmployeDuMois() {
        if (Liste_employe.isEmpty()) {
            return null;
        }

        Employe meilleurEmploye = Liste_employe.get(0);

        for (int i = 1; i < Liste_employe.size(); i++) {
            Employe employeCourant = Liste_employe.get(i);
            if (employeCourant.getNbLocationsGerees() > meilleurEmploye.getNbLocationsGerees()) {
                meilleurEmploye = employeCourant;
            }
        }
        
        return meilleurEmploye;
    }

    public Vector<Scooter> filtrerScooters(String marqueRecherchee, double prixMax, double prixMin, String dateRechercheDebut, String dateRechercheFin, Type_permis permisRequis, String motorisationRecherchee) {
    Vector<Scooter> resultats = new Vector<Scooter>();

    for (int i = 0; i < Liste_scooter.size(); i++) {
        Scooter s = Liste_scooter.get(i);
        
        boolean correspondMarque = marqueRecherchee.equals("Toutes") || s.getModele().getMarque().getNomMarque().equalsIgnoreCase(marqueRecherchee);
        boolean correspondPrixMax = (prixMax == 0) || (s.getPrix_jour() <= prixMax);
        boolean correspondPrixMin = (prixMin == 0) || (s.getPrix_jour() >= prixMin);
        boolean correspondPermis = (permisRequis == null) || (s.getModele().getPermis().equals(permisRequis));
        boolean correspondDisponibilite = s.estDisponibleAuxDates(dateRechercheDebut, dateRechercheFin);
        boolean correspondMotorisation = (s.getModele().getMotorisation().equalsIgnoreCase(motorisationRecherchee));
        boolean correspondCouleur = (s.getColoris().equalsIgnoreCase(motorisationRecherchee));
        
        if (correspondMarque && correspondPrixMax && correspondPrixMin && correspondPermis && correspondDisponibilite && correspondMotorisation && correspondCouleur) {
            resultats.add(s);
            }
        }
    return resultats;
    }




    

}