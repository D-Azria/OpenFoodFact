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

            Set<Marque> marques = new HashSet<>();
            Set<Categorie> categories = new HashSet<>();
            Set<Additif> allAdditifs = new HashSet<>();
            Set<Allergene> allAllergenes = new HashSet<>();
            Set<Ingredient> allIngredients = new HashSet<>();
            Set<Produit> allProduits = new HashSet<>();

            int iterations = 0;
            System.out.println("START");
            em.getTransaction().begin();
            System.out.println("BEGIN Transaction - INITIAL");

            for (String oneData : allData) {

                iterations++;
                OFFSingleProduct singleData = ParseurLigne.parseLigne(oneData);
                //em.getTransaction().begin();
                //System.out.println("BEGIN");
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

                Categorie categorie = null;
                if (categories.contains(singleData.getOffCategorie())) {
                    for (Categorie cat : categories) {
                        if (cat.equals(singleData.getOffCategorie())) {
                            produit.setCategorie(cat);
                            break;
                        }
                    }
                } else {
                    categorie = new Categorie(singleData.getOffCategorie().getLibelle());
                    categories.add(categorie);
                    produit.setCategorie(categorie);
                    CategorieDao.insert(categorie);
                }


                for (Additif a : singleData.getOffAdditifs()) {
                    if (allAdditifs.contains(a)) {
                        produit.addAdditif(a);
                    } else {
                        AdditifDao.insert(a, produit);
                    }
                }


                for (Allergene a : singleData.getOffAllergenes()) {
                    if (allAllergenes.contains(a)) {
                        produit.addAllergenes(a);
                    } else {
                        AllergeneDao.insert(a, produit);
                    }
                }

                for (Ingredient ing : singleData.getOffIngredients()) {
                    if (allIngredients.contains(ing)) {
                        produit.addIngredient(ing);
                    } else {
                        IngredientDao.insert(ing, produit);
                    }
                }


                if (marques.contains(singleData.getOffMarque())) {
                    for (Marque mar : marques) {
                        if (mar.equals(singleData.getOffMarque())) {
                            produit.setMarque(mar);
                            break;
                        }
                    }
                } else {
                    Marque marque = new Marque(singleData.getOffMarque().getLibelle());
                    marques.add(marque);
                    produit.setMarque(marque);
                    MarqueDao.insert(marque);
                }

                allProduits.add(produit);
                ProduitDao.insert(produit);

                if (iterations % 500 == 0) {
                    em.getTransaction().commit();
                    System.out.println("COMMIT - " + iterations);
                    em.getTransaction().begin();
                    System.out.println("BEGIN Transaction");
                }
                //em.getTransaction().commit();
                //System.out.println("COMMIT");
            }
            em.getTransaction().commit();
            System.out.println("COMMIT - FINAL");
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime / 1000 + " seconds");
    }
}
