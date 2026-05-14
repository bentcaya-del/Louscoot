package modele;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Marque {

    /**
     * Default constructor
     */
    
    /**
     * 
     */
    private String nom_marque;

    /**
     * 
     */
    private String pays_origine;

    /*
     * 
     */
    private Vector<Modele> Liste_modele;

    public Marque(String vnom_marque, String vpays_origine) {
        nom_marque = vnom_marque;
        pays_origine = vpays_origine;
        Liste_modele = new Vector<Modele>();
    }

    public void ajoutModele(Modele vmodele){
        Liste_modele.add(vmodele);
    }

    public String getNomMarque() {
        return nom_marque;
    }

    public String getPays_origine() {
        return pays_origine;
    }

    public Vector<Modele> getListe_modele() {
        return Liste_modele;
    }

    public void setNom_marque(String nom_marque) {
        this.nom_marque = nom_marque;
    }

    public void setPays_origine(String pays_origine) {
        this.pays_origine = pays_origine;
    }

    
}