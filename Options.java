

import java.util.*;

/**
 * 
 */
public class Options {

    /**
     * Default constructor
     */
    public Options() {
    }

    /**
     * 
     */
    private String freinage;

    /**
     * 
     */
    private String eclairage;

    /**
     * 
     */
    private String tableau_de_bord;

    /**
     * 
     */
    private String stockage;

    /**
     * 
     */
    private String démarrage;

    /**
     * 
     */
    private String carburant;

    /*
     * 
     */
    private Scooter Scooter;

    public Options(String vfreinage, String veclairage, String vtableau_de_bord, String vstockage, String vdémarrage,
            String vcarburant, Scooter vscooter) {
        freinage = vfreinage;
        eclairage = veclairage;
        tableau_de_bord = vtableau_de_bord;
        stockage = vstockage;
        démarrage = vdémarrage;
        carburant = vcarburant;
        Scooter = vscooter;
    }

    public String getFreinage() {
        return freinage;
    }

    public String getEclairage() {
        return eclairage;
    }

    public String getTableau_de_bord() {
        return tableau_de_bord;
    }

    public String getStockage() {
        return stockage;
    }

    public String getDémarrage() {
        return démarrage;
    }

    public String getCarburant() {
        return carburant;
    }

    public Scooter getScooter() {
        return Scooter;
    }

    public void setFreinage(String freinage) {
        this.freinage = freinage;
    }

    public void setEclairage(String eclairage) {
        this.eclairage = eclairage;
    }

    public void setTableau_de_bord(String tableau_de_bord) {
        this.tableau_de_bord = tableau_de_bord;
    }

    public void setStockage(String stockage) {
        this.stockage = stockage;
    }

    public void setDémarrage(String démarrage) {
        this.démarrage = démarrage;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public void setScooter(Scooter scooter) {
        Scooter = scooter;
    }
    
    public String FicheTechnique() {
        String description = "Fiche technique : Moteur " + this.carburant 
                           + " | Freinage : " + this.freinage 
                           + " | Éclairage : " + this.eclairage 
                           + " | Rangement : " + this.stockage
                           + " | Démarrage : " + this.démarrage;
                           
        return description;
    }
    

}