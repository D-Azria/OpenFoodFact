package fr.diginamic.parse;

import fr.diginamic.dao.*;
import fr.diginamic.entites.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestParse {
    public static void main(String[] args) {
        List<String> allData = LectureCsv.lire("open-food-facts.csv");


        Set<Marque> marques = new HashSet<>();
        Set<Categorie> categories = new HashSet<>();
        Set<Additif> allAdditifs = new HashSet<>();
        Set<Allergene> allAllergenes = new HashSet<>();
        Set<Ingredient> allIngredients = new HashSet<>();
        Set<Produit> allProduits = new HashSet<>();
        for (String oneData : allData) {
            //iterations++;
            OFFSingleProduct singleData = TestParseurLigne.parseLigne(oneData);

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
                        //cat.addProduit(produit);
                        break;
                    }
                }
            } else {
                categorie = new Categorie(singleData.getOffCategorie().getLibelle());
                categories.add(categorie);
                produit.setCategorie(categorie);
                //categorie.addProduit(produit);
            }

            for (Additif a : singleData.getOffAdditifs()) {
                if (allAdditifs.contains(a)) {
                    produit.addAdditif(a);
                    //a.addProduit(produit);
                } else {
                    Additif additif = new Additif(a.getCode(), a.getLibelle());
                    allAdditifs.add(additif);
                    produit.addAdditif(additif);
                    //additif.addProduit(produit);
                }
            }


            for (Allergene a : singleData.getOffAllergenes()) {
                if (allAllergenes.contains(a)) {
                    produit.addAllergenes(a);
                    //a.addProduit(produit);
                } else {
                    Allergene allergene = new Allergene(a.getLibelle());
                    allAllergenes.add(allergene);
                    produit.addAllergenes(allergene);
                    //allergene.addProduit(produit);
                }
            }

            for (Ingredient ing : singleData.getOffIngredients()) {
                if (allIngredients.contains(ing)) {
                    produit.addIngredient(ing);
                    //ing.addProduit(produit);
                } else {
                    Ingredient ingredient = new Ingredient(ing.getLibelle());
                    allIngredients.add(ingredient);
                    produit.addIngredient(ingredient);
                    //ingredient.addProduit(produit);
                    //em.persist(ingredient);
                }
            }


            if (marques.contains(singleData.getOffMarque())) {
                for (Marque mar : marques) {
                    if (mar.equals(singleData.getOffMarque())) {
                        produit.setMarque(mar);
                        //mar.addProduit(produit);
                        break;
                    }
                }
            } else {
                Marque marque = new Marque(singleData.getOffMarque().getLibelle());
                marques.add(marque);
                produit.setMarque(marque);
                //marque.addProduit(produit);

            }

            allProduits.add(produit);

        }
        //System.out.println(allIngredients);
    }
}
