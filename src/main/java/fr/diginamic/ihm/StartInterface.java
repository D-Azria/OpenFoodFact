package fr.diginamic.ihm;

import fr.diginamic.processing.MainProcessing;
import fr.diginamic.dao.recherches.RechercheProduitsMarque;
import java.util.Scanner;

public class StartInterface {

    public static void startInterface() {
        Scanner scanner = new Scanner(System.in);

        int choix = 0;
        do {
            try {
                afficherMenu();
                String choixMenu = scanner.nextLine();
                choix = Integer.parseInt(choixMenu);
                switch (choix) {
                    case 1:
                        MainProcessing.mainProcessing();
                        break;
                    case 2:
                        System.out.print("Choix du nombre de produit à afficher : ");
                        int choixNombreProduit = Integer.parseInt(scanner.nextLine());
                        System.out.print("Choix de la marque : ");
                        String choixMarque = scanner.nextLine();

                        RechercheProduitsMarque.rechercheProduitsMarque(choixNombreProduit,choixMarque);
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                }
            }
            catch(Exception exception) {
                System.out.println(exception.getMessage());
            }
        } while (choix != 99) ;
        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("***** Exploitation données OpenFoodFacts *****");
        System.out.println("1. Extraire les données (2-40 min)");
        System.out.println("2. Rechercher les N meilleurs produits d'une Marque");
        System.out.println("3. Rechercher les N meilleurs produits d'une Catégorie");
        System.out.println("4. Rechercher les N meilleurs produits par Marque et par Catégorie");
        System.out.println("5. Afficher les N ingrédients les plus courants");
        System.out.println("6. Afficher les N allergènes les plus courants");
        System.out.println("7. Afficher les N additifs les plus courants");
        System.out.println("99. Sortir");
    }
}
