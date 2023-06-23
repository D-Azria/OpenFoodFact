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

        List<String> allData = LectureCsv.lire("open-food-facts.csv");
        // System.out.println(produits);

        /*
        String ligne = produits.get(33);
        String l = ligne.replaceAll("[^a-zA-Zàâäéèêëîïôöùûüç'\\|,:\\- 0-9.]", "");
        String[] token = l.split("\\|", -1);
        int i= 0;
        for (String t:token){

            if (t.isEmpty()){
                t="filled";
            }
            i++;
            System.out.println(i + " - " + t);
        }
        System.out.println(ligne.replaceAll("[^a-zA-Zàâäéèêëîïôöùûüç'\\|,:\\- 0-9.]", ""));
        //OFFSignleProduct produit = new OFFSignleProduct();
        */

        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("open_food_fact");
            EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();

            Set<Marque> marques = new HashSet<>();
            Set<Categorie> categories = new HashSet<>();
            Set<Additif> allAdditifs = new HashSet<>();
            Set<Allergene> allAllergenes = new HashSet<>();
            Set<Ingredient> allIngredients = new HashSet<>();
            Set<Produit> allProduits = new HashSet<>();

            for(String oneData:allData){
                OFFSignleProduct singleData = ParseurLigne.parseLigne(oneData);

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

                if(categories.contains(singleData.getOffCategorie())){
                    //System.out.println("CAT : false");
                    produit.setCategorie(singleData.getOffCategorie());
                } else {
                    //System.out.println("CAT : true");
                    Categorie categorie = new Categorie(singleData.getOffCategorie().getLibelle());
                    categories.add(categorie);
                    produit.setCategorie(categorie);
                    //em.persist(categorie);
                }

                if(marques.contains(singleData.getOffMarque())){
                    //System.out.println("MARQUE : false");
                    produit.setMarque(singleData.getOffMarque());
                } else {
                    //System.out.println("MARQUE : true");
                    Marque marque = new Marque(singleData.getOffMarque().getLibelle());
                    marques.add(marque);
                    produit.setMarque(marque);
                    //em.persist(marque);
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
                        //em.persist(additif);
                    }
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
                        //em.persist(allergene);
                    }
                }

                for (Ingredient ing:singleData.getOffIngredients()){
                    if(allIngredients.contains(ing)){
                        produit.addIngredient(ing);
                    }else {
                        Ingredient ingredient = new Ingredient(ing.getLibelle());
                        allIngredients.add(ingredient);
                        produit.addIngredient(ingredient);
                        //em.persist(ingredient);
                    }
                }

                allProduits.add(produit);
                //System.out.println("PRODUIT");
                em.persist(produit);

            }
            int i = 1;
            for (Produit p:allProduits){
                System.out.println(i + " - " + p.getNom());
                i++;
            }

            em.getTransaction().commit();

        }
    }
}
