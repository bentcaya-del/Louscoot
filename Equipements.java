

import java.util.*;

/*
 * 
 */
public class Equipements {

    /**
     * Default constructor
     */
    public Equipements() {
    }

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private String taille;

    /**
     * 
     */
    private double prix_jour;


    /**
     * 
     */
    private String couleur;

    /**
     * 
     */
    private Vector<Location> Liste_location;

    public Equipements(String vnom, String vtaille, double vprix_jour, String vcouleur) {
        nom = vnom;
        taille = vtaille;
        prix_jour = vprix_jour;
        couleur = vcouleur;
        Liste_location = new Vector<Location>();
    }

    public void ajoutLocation(Location vlocation){
        Liste_location.add(vlocation);
    }

    public String getNom() {
        return nom;
    }

    public String getTaille() {
        return taille;
    }

    public double getPrix_jour() {
        return prix_jour;
    }

    public String getCouleur() {
        return couleur;
    }

    public Vector<Location> getListe_location() {
        return Liste_location;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public void setPrix_jour(double prix_jour) {
        this.prix_jour = prix_jour;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    

}