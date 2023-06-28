package fr.diginamic.processing;

import fr.diginamic.dao.*;
import fr.diginamic.entites.*;
import fr.diginamic.processing.parse.ParseurLigne;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MainProcessing {

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

                int iterations = 0;
                System.out.println("START");
                em.getTransaction().begin();
                System.out.println("BEGIN Transaction - INITIAL");

                for (String oneData : allData) {

                    iterations++;
                    OFFSingleProduct singleData = ParseurLigne.parseLigne(oneData);

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


                    for (Additif a : singleData.getOffAdditifs()) {
                        AdditifDao.insert(a, produit);
                    }

                    for (Allergene a : singleData.getOffAllergenes()) {
                        AllergeneDao.insert(a, produit);
                    }

                    for (Ingredient ing : singleData.getOffIngredients()) {
                        IngredientDao.insert(ing, produit);
                    }

                    CategorieDao.insert(singleData.getOffCategorie(), produit);
                    MarqueDao.insert(singleData.getOffMarque(), produit);

                    ProduitDao.insert(produit);

                    if (iterations % 500 == 0) {
                        System.out.println(iterations + " itérations.");
                    /*
                    em.getTransaction().commit();
                    System.out.println("COMMIT - " + iterations + " done.");
                    em.getTransaction().begin();
                    System.out.print("Begin NEW transaction : ");
                    */
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
