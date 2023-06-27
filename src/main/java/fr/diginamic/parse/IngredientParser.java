package fr.diginamic.parse;

import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.utils.*;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static fr.diginamic.utils.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;

public class IngredientParser {

    public static Set<Ingredient> parseIngredient(String ingredients) {
        Set<Ingredient> ingredientSet = new HashSet<>();

        String firstPass = ingredients.replace("_", "").replaceAll("()", "").replace(",", ";").replaceAll("\\d+\\.\\d+(?=\\s*%)", "").replaceAll("\\d+(?=\\s*%)", "").replace("%", "").replace(".", ";").replace(" - ", ";").replace(" -", ";").replace("- ", ";").replace("  ", " ").replace("(", "").replace(")", "").replace("[", "").replace("]", "");

        String[] token = firstPass.split(";");
        for (String t : token) {
            if (!t.isEmpty()) {
                String t1 = RemoveAfterAsterisk.removeAsterisk(t);
                String t2 = OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase(t1).replace("*", "").replace(",", "").replace("  ", " ");
                String t3 = RemoveSpaceFirst.removeSpaceFirst(t2);
                String t4 = RemoveDoubleDots.removeDoubleDots(t3);
                String t5 = RemoveLastSpaces.removeLastSpaces(t4);
                if (!t5.isEmpty()) {
                    Ingredient ing = new Ingredient(t5);
                    ingredientSet.add(ing);
                    //System.out.println(ing);
                }
            }
        }
    return ingredientSet;
    }
}
