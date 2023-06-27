package fr.diginamic.dao;

import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class IngredientDao implements IIngredientDao{

    static Set<Ingredient> allIngredients = new HashSet<>();
    public static void insert(Ingredient ingredient, Produit produit) throws Exception{
        EntityManager em = ConnectionEntityManager.getEm();
        produit.addIngredient(ingredient);
        if (!allIngredients.contains(ingredient)) {
            allIngredients.add(ingredient);
            em.persist(ingredient);
        }
    }

    public Set<Ingredient> extraire() throws Exception{
        Set<Ingredient> ingredients = new HashSet<>();
        return ingredients;
    }
}
