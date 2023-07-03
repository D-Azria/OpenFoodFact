package fr.diginamic.processing;

import fr.diginamic.dao.*;
import fr.diginamic.entites.*;
import fr.diginamic.processing.parse.ParseurLigne;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Cette classe représente le traitement principal du programme.
 * Elle lit les données d'un fichier CSV contenant des informations sur des produits alimentaires :
 * produit, catégorie, marque, allergènes, additifs, ingrédients
 * La classe les stocke ensuite dans une base de données.
 *
 @author David Azria
 */
public class MainProcessing {

    /**
     * Méthode principale de traitement.
     */
    public static void mainProcessing() {
        List<String> allData = null;


        try {
            allData = LectureCsv.lire("open-food-facts.csv");
        } catch (Exception e) {
            System.out.print("Erreur de lecture du fichier : ");
            System.out.println(e.getMessage());
        }

        if (allData != null) {
            long startTime = System.currentTimeMillis();
            try (EntityManager em = ConnectionEntityManager.getEm()) {

                // Création de Set pour contrôler l'unicité de chaque objet avant insertion dans la base
                Set<Allergene> allAllergenes = new HashSet<>();
                Set<Additif> allAdditifs = new HashSet<>();
                Set<Ingredient> allIngredients = new HashSet<>();
                Set<Categorie> allCategories = new HashSet<>();
                Set<Marque> allMarques = new HashSet<>();
                Set<Produit> allProduits = new HashSet<>();

                int iterations = 0;
                System.out.println("START");
                em.getTransaction().begin();
                System.out.println("BEGIN Transaction - INITIAL");

                // Pour chaque ligne extraite, obtention des différentes entités
                for (String oneData : allData) {

                    iterations++;

                    // Parse de chaque ligne renvoit un objet OFF qui contient les 6 entités
                    OFFSingleProduct singleData = ParseurLigne.parseLigne(oneData);

                    // Création d'un nouveau Produit à partir de l'objet OFF récupéré
                    Produit produit = new Produit(
                            singleData.getOffProducts().getNom(),
                            singleData.getOffNutritionGradeFr(),
                            singleData.getOffProducts().getEnergie100g(),
                            singleData.getOffProducts().getGraisse100g(),
                            singleData.getOffProducts().getSucres100g(),
                            singleData.getOffProducts().getFibres100g(),
                            singleData.getOffProducts().getProteines100g(),
                            singleData.getOffProducts().getSel100g(),
                            singleData.getOffProducts().getVitA100g(),
                            singleData.getOffProducts().getVitD100g(),
                            singleData.getOffProducts().getVitE100g(),
                            singleData.getOffProducts().getVitK100g(),
                            singleData.getOffProducts().getVitC100g(),
                            singleData.getOffProducts().getVitB1100g(),
                            singleData.getOffProducts().getVitB2100g(),
                            singleData.getOffProducts().getVitPP100g(),
                            singleData.getOffProducts().getVitB6100g(),
                            singleData.getOffProducts().getVitB9100g(),
                            singleData.getOffProducts().getVitB12100g(),
                            singleData.getOffProducts().getCalcium100g(),
                            singleData.getOffProducts().getMagnesium100g(),
                            singleData.getOffProducts().getIron100g(),
                            singleData.getOffProducts().getFer100g(),
                            singleData.getOffProducts().getBetaCarotene100g(),
                            singleData.getOffProducts().getPresenceHuilePalme());

                    // Pour chaque additif présent dans le Set d'additifs de l'objet OFF, vérification s'il existe dans le Set global d'additif, puis ajout de l'additif au nouvel objet Produit créé et éventuellement au Set global des additifs.
                    for (Additif a : singleData.getOffAdditifs()) {
                        if (allAdditifs.contains(a)) {
                            for (Additif add : allAdditifs) {
                                if (a.equals(add)) {
                                    produit.addAdditif(add);
                                    break;
                                }
                            }
                        } else {
                            allAdditifs.add(a);
                            produit.addAdditif(a);
                        }
                    }

                    // Pour chaque allergene présent dans le Set d'allergenes de l'objet OFF, vérification s'il existe dans le Set global d'additif, puis ajout de l'allergene au nouvel objet Produit créé et éventuellement au Set global des allergenes.
                    for (Allergene a : singleData.getOffAllergenes()) {
                        if (allAllergenes.contains(a)) {
                            for (Allergene all : allAllergenes) {
                                if (a.equals(all)) {
                                    produit.addAllergenes(all);
                                    break;
                                }
                            }
                        } else {
                            allAllergenes.add(a);
                            produit.addAllergenes(a);
                        }
                    }

                    // Pour chaque ingredient présent dans le Set d'ingredients de l'objet OFF, vérification s'il existe dans le Set global d'additif, puis ajout de l'ingredient au nouvel objet Produit créé et éventuellement au Set global des ingrédients.
                    for (Ingredient ing : singleData.getOffIngredients()) {
                        if (allIngredients.contains(ing)) {
                            for (Ingredient ingr : allIngredients) {
                                if (ing.equals(ingr)) {
                                    produit.addIngredient(ingr);
                                    break;
                                }
                            }
                        } else {
                            allIngredients.add(ing);
                            produit.addIngredient(ing);
                        }
                    }

                    // Ajout du produit au Set des produits
                    allProduits.add(produit);

                    // Vérification si la catégorie présente dans l'objet OFF existe dans le Set global des catégories, puis ajout de cette au nouvel objet Produit créé et éventuellement au Set global des catégories.
                    if (allCategories.contains(singleData.getOffCategorie())) {
                        for (Categorie cat : allCategories) {
                            if (cat.equals(singleData.getOffCategorie())) {
                                produit.setCategorie(cat);
                                break;
                            }
                        }
                    } else {
                        allCategories.add(singleData.getOffCategorie());
                        produit.setCategorie(singleData.getOffCategorie());
                        em.persist(singleData.getOffCategorie());
                    }

                    // Vérification si la marque présente dans l'objet OFF existe dans le Set global des marques, puis ajout de cette au nouvel objet Produit créé et éventuellement au Set global des marques.
                    if (allMarques.contains(singleData.getOffMarque())) {
                        for (Marque mar : allMarques) {
                            if (mar.equals(singleData.getOffMarque())) {
                                produit.setMarque(mar);
                                break;
                            }
                        }
                    } else {
                        allMarques.add(singleData.getOffMarque());
                        produit.setMarque(singleData.getOffMarque());
                        em.persist(singleData.getOffMarque());
                    }



                    if (iterations % 500 == 0) {
                        System.out.println(iterations + " itérations.");

                    em.getTransaction().commit();
                    System.out.println("COMMIT - " + iterations + " done.");
                    em.getTransaction().begin();
                    System.out.print("Begin NEW transaction : ");

                    }
                }
                em.getTransaction().commit();
                System.out.println("COMMIT - FINAL");
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Execution time: " + executionTime / 1000 + " seconds");
        }
    }
}
