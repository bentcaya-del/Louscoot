import modele.*;
import vue.*;

public class Main1 {
    public static void main(String[] args) {
        Parc monParc = new Parc("LouScoot", "10 Rue de la Moto", "Paris", "0102030405");
        FenetreAccueil vue = new FenetreAccueil(monParc);
    }
}