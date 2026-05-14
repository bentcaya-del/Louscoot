package modele;

import java.util.*;

/**
 * 
 */
public class Modele {

   

    /*
     * 
     */
    private String nom_modele;

    /**
     * 
     */
    private String motorisation;

    /**
     * 
     */
    private String chassis;

    /**
     * 
     */
    private String options_dispo;

    /**
     * 
     */
    private String année;

    /**
     * 
     */
    private Vector<Scooter> Liste_scooter;

    /**
     * 
     */
    private Type_permis Permis;

    /**
     * 
     */
    private Marque marque;

    public Modele(String vnom_modele, String vmotorisation, String vchassis, String voptions, String vannée,
            Type_permis vpermis, Marque vmarque) {
        nom_modele = vnom_modele;
        motorisation = vmotorisation;
        chassis = vchassis;
        options_dispo = voptions;
        année = vannée;
        Permis = vpermis;
        marque = vmarque;
        Liste_scooter = new Vector<Scooter>();
    }

    public void ajoutScooter(Scooter vscooter){
        Liste_scooter.add(vscooter);
    }

    public String getNom_modele() {
        return nom_modele;
    }

    public String getMotorisation() {
        return motorisation;
    }

    public String getChassis() {
        return chassis;
    }

    public String getOptions_dispo() {
        return options_dispo;
    }

    public String getAnnée() {
        return année;
    }

    public Vector<Scooter> getListe_scooter() {
        return Liste_scooter;
    }

    public Type_permis getPermis() {
        return Permis;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setNom_modele(String nom_modele) {
        this.nom_modele = nom_modele;
    }

    public void setMotorisation(String motorisation) {
        this.motorisation = motorisation;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public void setOptions_dispo(String options_dispo) {
        this.options_dispo = options_dispo;
    }

    public void setAnnée(String année) {
        this.année = année;
    }

    public void setPermis(Type_permis permis) {
        Permis = permis;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public int NbrScooter() {
        return this.Liste_scooter.size();
    }

    public String FicheTechnique() {
        String fiche = "Fiche Technique du Modèle : " + this.nom_modele + "\n"
                      + "Année : " + this.année + "\n"
                      + "Motorisation : " + this.motorisation + "\n"
                      + "Chassis : " + this.chassis + "\n"
                      + "Options Disponibles : " + this.options_dispo + "\n"
                      + "Permis Requis : " + this.Permis + "\n";
        return fiche;
    }

}