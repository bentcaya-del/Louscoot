import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        
        Marque yamaha = new Marque("Yamaha", "Japon");
        Modele nmax = new Modele("NMAX", "Electrique", "Vurb", "LED", "2022", Type_permis.A, yamaha);
        Modele liberty = new Modele("Liberty", "Essence", "Classique", "Halogène", "2021", Type_permis.B, new Marque("Piaggio", "Italie"));

        Parc monParc = new Parc("LouScoot Evry", "10 Rue de la Paix", "Evry", "0102030405");
        
        Scooter s1 = new Scooter("AA-111-AA","Noir", 100.0, 50.0, nmax, monParc);
        Scooter s2 = new Scooter("BB-222-BB", "Blanc", 500.0, 40.0, liberty, monParc);
        monParc.ajoutScooter(s1);
        monParc.ajoutScooter(s2);

        Employe jean = new Employe("Dupont", "Jean", "0600000001", 1600.0 , "jean@mail.com", "Vendeur", monParc);
        monParc.ajoutEmploye(jean);

        Client samy = new Client("Younsi", "Samy", "0600000002", "samy@mail.com");
        samy.ajoutPermis(Type_permis.B); 
        monParc.ajoutClient(samy);

        Vector<Scooter> dispo = monParc.getScootersDisponibles("2024-06-01", "2024-06-05");
        System.out.println(dispo.size());

        if (s1.verifierPermisClient(samy)) {
            Location loc1 = jean.creerContrat("2024-06-01", "2024-06-05", 4, s1, samy);
        } else {
            System.out.println(samy.getPrenom() + " n'a pas le permis requis.");
        }

        jean.creerContrat("2024-01-01", "2024-01-02", 1, s2, samy);
        jean.creerContrat("2024-02-01", "2024-02-02", 1, s2, samy);

        Location loc3 = jean.creerContrat("2024-03-01", "2024-03-05", 4, s2, samy);
        loc3.ajoutEquipement(new Equipements("Casque", "L", 5, "Noir"));
        
        System.out.println(loc3.toString()); 
        System.out.println(loc3.afficherFacture(3)); 

        EtatDesLieuxFinal retour = new EtatDesLieuxFinal("2024-03-05", "", 0, 0, loc3);
        retour.cloturerLocation(150.0, "Rayure profonde"); 
        
        System.out.println(loc3.afficherFacture(3));

        Employe gagnant = monParc.getEmployeDuMois();
        if (gagnant != null) {
            System.out.println("\nL'employé du mois est : " + gagnant.getPrenom() + " " + gagnant.getNom());
            System.out.println("Score : " + gagnant.getNbLocationsGerees() + " locations.");
        }
    }
}
