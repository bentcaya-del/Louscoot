

import java.util.*;

/**
 * 
 */
public class Parc extends Observable {

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
    private Vector<Client> Liste_client = new Vector<Client>();

    /**
     * 
     */
    private Vector<Scooter> Liste_scooter = new Vector<Scooter>();

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

    public Vector <Scooter> filtrerMarque(String marqueRecherchee){
        Vector<Scooter> resultats = new Vector<Scooter>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
        Scooter s = Liste_scooter.get(i);
        
        boolean correspondMarque = marqueRecherchee.equals("Toutes") || s.getModele().getMarque().getNomMarque().equalsIgnoreCase(marqueRecherchee);
        if (correspondMarque) {
            resultats.add(s);
            }
        }
        return resultats;
    }
    
    public Vector <Scooter> filtrerPrix(double prixMax, double prixMin){
        Vector<Scooter> resultats = new Vector<Scooter>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
        Scooter s = Liste_scooter.get(i);
        
        boolean correspondPrixMax = (prixMax == 0) || (s.getPrix_jour() <= prixMax);
        boolean correspondPrixMin = (prixMin == 0) || (s.getPrix_jour() >= prixMin);
        if (correspondPrixMax && correspondPrixMin) {
            resultats.add(s);
            }
        }
        return resultats;
    }

    public Vector <Scooter> filtrerDisponibilite(String dateRechercheDebut, String dateRechercheFin){
        Vector<Scooter> resultats = new Vector<Scooter>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
        Scooter s = Liste_scooter.get(i);
        
        boolean correspondDisponibilite = s.estDisponibleAuxDates(dateRechercheDebut, dateRechercheFin);
        if (correspondDisponibilite) {
            resultats.add(s);
            }
        }
        return resultats;
    }

    public Vector <Scooter> filtrerPermis(Type_permis permisRequis){
        Vector<Scooter> resultats = new Vector<Scooter>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
        Scooter s = Liste_scooter.get(i);
        
        boolean correspondPermis = (permisRequis == null) || (s.getModele().getPermis().equals(permisRequis));
        if (correspondPermis) {
            resultats.add(s);
            }
        }
        return resultats;
    }

    public Vector <Scooter> filtrerMotorisation(String motorisationRecherchee){
        Vector<Scooter> resultats = new Vector<Scooter>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
        Scooter s = Liste_scooter.get(i);
        
        boolean correspondMotorisation = (s.getModele().getMotorisation().equalsIgnoreCase(motorisationRecherchee));
        if (correspondMotorisation) {
            resultats.add(s);
            }
        }
        return resultats;
    }

    public Vector <Scooter> filtrerCouleur(String couleurRecherchee){
        Vector<Scooter> resultats = new Vector<Scooter>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
        Scooter s = Liste_scooter.get(i);
        
        boolean correspondCouleur = (s.getColoris().equalsIgnoreCase(couleurRecherchee));
        if (correspondCouleur) {
            resultats.add(s);
            }
        }
        return resultats;
    }
// Méthode pour chercher un scooter par son nom de modèle
    public void rechercherScooters(String motCle) {
        Vector<Scooter> resultats = new Vector<>();
        String rechercheMin = motCle.toLowerCase();
        // On fouille dans la liste de tous les scooters
        for (int i = 0; i < Liste_scooter.size(); i++) {
            Scooter s = Liste_scooter.get(i);
            String nomModele = s.getModele().getNom_modele().toLowerCase();
            if (nomModele.contains(rechercheMin)) {
                resultats.add(s);
            }
        }
        this.setChanged(); // On prévient Java qu'il y a du changement (actualise)
        this.notifyObservers(resultats); // On envoie les résultats à la vue
    }

        //combiner tous tes filtres
    public void appliquerFiltresMultiples(String marqueReq, String permisReq, String motoReq, String couleurReq) {
        Vector<Scooter> resultats = new Vector<Scooter>();

        for (int i = 0; i < Liste_scooter.size(); i++) {
            Scooter s = Liste_scooter.get(i);
            //on vérifie si l'utilisateur a choisi "Tout"
            boolean okMarque = marqueReq.equals("Tout") || s.getModele().getMarque().getNomMarque().equalsIgnoreCase(marqueReq);
            //toString parce que c'est un enum, et on veut comparer avec le string de la combo box
            boolean okPermis = permisReq.equals("Tout") || s.getModele().getPermis().toString().equalsIgnoreCase(permisReq);
            boolean okMoto = motoReq.equals("Tout") || s.getModele().getMotorisation().equalsIgnoreCase(motoReq);
            boolean okCouleur = couleurReq.equals("Tout") || s.getColoris().equalsIgnoreCase(couleurReq);
            // Si le scooter respecte TOUS les choix, on le garde
            if (okMarque && okPermis && okMoto && okCouleur) {
                resultats.add(s);
            }
        }
        // --- LA MAGIE DU MVC ---
        this.setChanged(); // On prévient Java qu'il y a du changement
        this.notifyObservers(resultats); // On envoie les résultats à la vue !
    }

    public Vector <Scooter> AjouterScooter(Scooter s){
        Vector<Scooter> resultats = new Vector<Scooter>();
        Liste_scooter.add(s);
        for (int i = 0; i < Liste_scooter.size(); i++) {
            Scooter sc = Liste_scooter.get(i);
            resultats.add(sc);
        }
        return resultats;
    }

    public Vector<String> getMarquesUniques() {
    // On utilise un TreeSet pour ne pas avoir de doublons et trier de A à Z
    Set<String> marques = new TreeSet<>();

    for (int i = 0; i < Liste_scooter.size(); i++) {
        marques.add(Liste_scooter.get(i).getModele().getMarque().getNomMarque());
    }
    Vector<String> listeFinale = new Vector<>();
    listeFinale.add("Tout"); // On ajoute l'option "Tout" en premier
    listeFinale.addAll(marques);
    // On transforme l'ensemble en Vector pour que la JComboBox puisse le lire
    return listeFinale;
    }
    
    public Vector<String> getCouleursUniques() {
        Set<String> couleurs = new TreeSet<>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
            couleurs.add(Liste_scooter.get(i).getColoris());
        }
        Vector<String> listeFinale = new Vector<>();
        listeFinale.add("Tout");
        listeFinale.addAll(couleurs);
        return listeFinale;
    }

    public Vector<String> getMotorisationsUniques() {
        Set<String> moteurs = new TreeSet<>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
            moteurs.add(Liste_scooter.get(i).getModele().getMotorisation());
        }
        Vector<String> listeFinale = new Vector<>();
        listeFinale.add("Tout");
        listeFinale.addAll(moteurs);
        return listeFinale;
    }

    public Vector<String> getPermisUniques() {
        Set<String> permis = new TreeSet<>();
        for (int i = 0; i < Liste_scooter.size(); i++) {
            // On met .toString() car c'est un Enum
            permis.add(Liste_scooter.get(i).getModele().getPermis().toString());
        }
        Vector<String> listeFinale = new Vector<>();
        listeFinale.add("Tout");
        listeFinale.addAll(permis);
        return listeFinale;
    }

    public void trierParPrix(boolean croissant) {
        
        Liste_scooter.sort(new java.util.Comparator<Scooter>() {
            @Override
            public int compare(Scooter s1, Scooter s2) {
                if (croissant) {
                    // Du moins cher au plus cher
                    return Double.compare(s1.getPrix_jour(), s2.getPrix_jour());
                } else {
                    // Du plus cher au moins cher
                    return Double.compare(s2.getPrix_jour(), s1.getPrix_jour());
                }
            }
        });
        this.setChanged();
        this.notifyObservers(Liste_scooter);
    }
    





}