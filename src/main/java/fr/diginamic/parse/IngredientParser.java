package fr.diginamic.parse;

import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Ingredient;

import java.util.HashSet;
import java.util.Set;

public class IngredientParser {

    public static Set<Ingredient> parseIngredient(String ingredients){
        Set<Ingredient> ingredientSet = new HashSet<>();
        String [] token = ingredients.split(",");
        for (String t:token){
            //String cleanedAllergenes = t.replace("en:", "");
            Ingredient i = new Ingredient(t);
            ingredientSet.add(i);
            //System.out.println(i);
        }
        return ingredientSet;
    }
}
