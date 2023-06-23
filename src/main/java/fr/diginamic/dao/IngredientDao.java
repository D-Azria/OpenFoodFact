package fr.diginamic.dao;

import fr.diginamic.entites.Ingredient;
import java.util.HashSet;
import java.util.Set;

public class IngredientDao implements IIngredientDao{

    public Set<Ingredient> extraire() throws Exception{
        Set<Ingredient> ingredients = new HashSet<>();
        return ingredients;
    }
}
