package fr.diginamic.dao;

import fr.diginamic.entites.*;
import fr.diginamic.parse.LectureCsv;
import fr.diginamic.parse.ParseurLigne;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainDao {

    public static void mainDao() throws Exception {
        List<String> allData = LectureCsv.lire("open-food-facts.csv");
        long startTime = System.currentTimeMillis();
        try (EntityManager em = ConnectionEntityManager.getEm()) {

            Set<Produit> allProduits = new HashSet<>();

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

                allProduits.add(produit);
                ProduitDao.insert(produit);

                if (iterations % 500 == 0) {
                    System.out.println(iterations + " - itérations");
/*                    em.getTransaction().commit();
                    System.out.println("COMMIT - " + iterations);
                    em.getTransaction().begin();
                    System.out.println("BEGIN Transaction");*/
                }
            }
            em.getTransaction().commit();
            System.out.println("COMMIT - FINAL");
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime / 1000 + " seconds");
    }
}
