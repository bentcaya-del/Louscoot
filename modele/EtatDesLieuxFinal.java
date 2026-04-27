package modele;
/**
 * 
 */
public class EtatDesLieuxFinal {

    /**
     * Default constructor
     */
    public EtatDesLieuxFinal() {
    }

    /**
     * 
     */
    private String date;

    /**
     * 
     */
    private String commentaires;

    /**
     * 
     */
    private double kilometrage;

    /**
     * 
     */
    private double valeur_penalite;

    /**
     * 
     */
    private Location Location;

    public EtatDesLieuxFinal(String vdate, String vcommentaires, double vkilometrage, double vvaleur_penalite,
            Location vlocation) {
        date = vdate;
        commentaires = vcommentaires;
        kilometrage = vkilometrage;
        valeur_penalite = vvaleur_penalite;
        Location = vlocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String vdate) {
        date = vdate;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String vcommentaires) {
        commentaires = vcommentaires;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double vkilometrage) {
        kilometrage = vkilometrage;
    }

    public double getValeur_penalite() {
        return valeur_penalite;
    }
    

    public void setValeur_penalite(double vvaleur_penalite) {
        valeur_penalite = vvaleur_penalite;
    }

    public Location getLocation() {
        return Location;
    }

    public void setLocation(Location vlocation) {
        Location = vlocation;
    }


    public void cloturerLocation(double vkilometrage, String vcommentaires){
        kilometrage = vkilometrage;
        if(Location != null){
            Location.getScooter().rouler(vkilometrage);
            Location.setEtatDesLieux(this);
            Location.getScooter().setEstDisponible(true); // Le scooter est de nouveau dispo
        }
        commentaires = vcommentaires;
        if(!commentaires.equals("") && !vcommentaires.equals("Aucun")){
            valeur_penalite = 150;
        }else{
            valeur_penalite = 0;
        }

    }
}