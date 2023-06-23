package fr.diginamic.parse;

import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.utils.RemoveSpaceFirst;

import java.util.HashSet;
import java.util.Set;

import static fr.diginamic.utils.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;

public class IngredientParser {

    public static Set<Ingredient> parseIngredient(String ingredients){
        Set<Ingredient> ingredientSet = new HashSet<>();
        String [] token = ingredients.split("[,\\-_\\.]");
        int i = 1;
        for (String t:token){

            String cleanedFirstPass = t.replace("en:", "");
            String cleanedSecondPass = cleanedFirstPass.replace("-", " ");
            String cleanedThirdPass = RemoveSpaceFirst.removeSpaceFirst(cleanedSecondPass);
            String cleanedFinalPass = onlyFirstLetterToUpperCase(cleanedThirdPass);

            Ingredient ing = new Ingredient(cleanedFinalPass);
            ingredientSet.add(ing);
            //System.out.println(i + " - " + ing);
            i++;
        }
        return ingredientSet;
    }
}
