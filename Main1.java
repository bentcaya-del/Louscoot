import modele.*;
import vue.*;

public class Main1 {
    public static void main(String[] args) {
        Parc monParc = new Parc("LouScoot", "10 Rue de la Moto", "Paris", "0102030405");
        
        
        Marque yam = new Marque("Yamaha", "Japon");
        Marque bmw = new Marque("BMW", "Allemagne");
        Marque honda = new Marque("Honda", "Japon");

        
        Modele nmax = new Modele("NMAX", "Essence", "Classique", "LED", "2022", Type_permis.A, yam);
        Modele ce04 = new Modele("CE-04", "Electrique", "Moderne", "LED", "2023", Type_permis.A, bmw);
        Modele pcx = new Modele("PCX", "Essence", "Classique", "Halogene", "2021", Type_permis.A1, honda);

        Scooter s1 = new Scooter("AB-123-CD", "Noir", 100.0, 500.0, 40.0, nmax, null);
        monParc.AjouterScooter(s1);

        Scooter s2 = new Scooter("EF-456-GH", "Blanc", 50.0, 1000.0, 85.0, ce04, null);
        monParc.AjouterScooter(s2);

        Scooter s3 = new Scooter("IJ-789-KL", "Rouge", 1200.0, 400.0, 35.0, pcx, null);
        monParc.AjouterScooter(s3);
        
        Scooter s4 = new Scooter("MN-012-OP", "Bleu", 300.0, 500.0, 45.0, nmax, null);
        monParc.AjouterScooter(s4);
        

        FenetreAccueil vue = new FenetreAccueil(monParc);
    }
}