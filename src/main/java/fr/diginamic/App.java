package fr.diginamic;

import fr.diginamic.entites.*;
import fr.diginamic.parse.LectureCsv;
import fr.diginamic.parse.ParseurLigne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        List<String> allData = LectureCsv.lire("open-food-facts.csv");
        // System.out.println(produits);

        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("open_food_fact");
            EntityManager em = emf.createEntityManager()){


            Set<Marque> marques = new HashSet<>();
            Set<Categorie> categories = new HashSet<>();
            Set<Additif> allAdditifs = new HashSet<>();
            Set<Allergene> allAllergenes = new HashSet<>();
            Set<Ingredient> allIngredients = new HashSet<>();
            Set<Produit> allProduits = new HashSet<>();
/*            int k =1;
            int al = 1;
            int ad = 1;
            int pr = 1;
            int m = 1;
            int c= 1;
            int i = 1;*/

            for(String oneData:allData){
                OFFSignleProduct singleData = ParseurLigne.parseLigne(oneData);

                em.getTransaction().begin();
/*                System.out.println("OPEN : " + k);
                k++;*/

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
                //System.out.println(pr + " - " + produit.getNom());

                Categorie categorie = null;
                if(categories.contains(singleData.getOffCategorie())){
                        for(Categorie cat : categories){
                            if(cat.equals(singleData.getOffCategorie())){
                                produit.setCategorie(cat);
                                break;
                            }
                    }
                } else {
                    categorie = new Categorie(singleData.getOffCategorie().getLibelle());
                    categories.add(categorie);
                    produit.setCategorie(categorie);
                    em.persist(categorie);
/*                    System.out.println(c + " - " + categorie.getLibelle());
                    c++;*/
                }

                for (Additif a:singleData.getOffAdditifs()){
                    if(allAdditifs.contains(a)){
                        //System.out.println("ADDITIF : false");
                        produit.addAdditif(a);
                    } else {
                        //System.out.println("ADDITIF : true");
                        Additif additif = new Additif(a.getCode(), a.getLibelle());
                        allAdditifs.add(additif);
                        produit.addAdditif(additif);
                        em.persist(additif);
                    }
/*                    System.out.println(ad + " - " + a);
                    ad++;*/
                }

                for (Allergene a:singleData.getOffAllergenes()) {
                    if (allAllergenes.contains(a)) {
                        //System.out.println("ALLERGENE : false");
                        produit.addAllergenes(a);
                    } else {
                        //System.out.println("ALLERGENE : true");
                        Allergene allergene = new Allergene(a.getLibelle());
                        allAllergenes.add(allergene);
                        produit.addAllergenes(allergene);
                        em.persist(allergene);
                    }
/*                    System.out.println(al + " - " + a);
                    al++;*/
                }

                for (Ingredient ing:singleData.getOffIngredients()){
                    if(allIngredients.contains(ing)){
                        produit.addIngredient(ing);
                    }else {
                        Ingredient ingredient = new Ingredient(ing.getLibelle());
                        allIngredients.add(ingredient);
                        produit.addIngredient(ingredient);
                        em.persist(ingredient);
                    }
/*                    System.out.println(i + " - " + ing);
                    i++;*/
                }


                if(marques.contains(singleData.getOffMarque())){
                    for(Marque mar : marques){
                        if(mar.equals(singleData.getOffMarque())){
                            produit.setMarque(mar);
                            break;
                        }
                    }
                } else {
                    //System.out.println("MARQUE : true");
                    Marque marque = new Marque(singleData.getOffMarque().getLibelle());
                    marques.add(marque);
                    produit.setMarque(marque);
                    em.persist(marque);
/*                    System.out.println(m + " - " + marque.getLibelle());
                    m++;*/
                }

                allProduits.add(produit);
                em.persist(produit);
/*                System.out.println(pr + " - " + produit.getNom());
                System.out.println(produit);
                pr++;
                System.out.println("COMMIT");*/
                em.getTransaction().commit();
            }
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime/1000 + " seconds");
    }
}
