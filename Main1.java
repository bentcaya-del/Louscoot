
public class Main1 {
    public static void main(String[] args) {
        Parc monParc = new Parc();
        Marque bmw = new Marque("BMW", "Allemagne");
        Modele c400 = new Modele("c400", "Essence", "Classique", "LED", "2021", Type_permis.A, bmw);
        Marque yam = new Marque("Yamaha", "Japon");
        Modele nmax = new Modele("nmax", "Essence", "Classique", "LED", "2022", Type_permis.A1, yam);
        
        // On ajoute le scooter au Parc
        Scooter s1 = new Scooter("nmax", "Noir", 100.0, 50.0, 40.0, nmax , null);
        monParc.AjouterScooter(s1);
         Scooter s2 = new Scooter("c400", "Noir", 100.0, 50.0, 40.0, c400 , null);
        monParc.AjouterScooter(s2);
        

        // Créer une instance de la classe VuePrincipale
        VuePrincipale vue = new VuePrincipale(monParc);

    }
}