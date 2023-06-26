package fr.diginamic.parse;

import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.utils.RemoveSpaceFirst;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static fr.diginamic.utils.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;

public class IngredientParser {

    public static Set<Ingredient> parseIngredient(String ingredients){
        Set<Ingredient> ingredientSet = new HashSet<>();
        StringTokenizer tokenizer = getStringTokenizer(ingredients);
        int i = 1;
        while (tokenizer.hasMoreTokens()) {
            String part = tokenizer.nextToken().trim();
            String cleanedSecondPass = part.replace("-", " ");
            String cleanedThirdPass = RemoveSpaceFirst.removeSpaceFirst(cleanedSecondPass);
            String cleanedFinalPass = onlyFirstLetterToUpperCase(cleanedThirdPass);
            Ingredient ing = new Ingredient(cleanedFinalPass);
            ingredientSet.add(ing);
            //System.out.println(i + " - " + ing);
            i++;

        }
       /* String [] token = string.split("\\s*[;,]\\s*");
        int i = 1;
        for (String t:token){

            String cleanedFirstPass = t.replace("*", "");
            String cleanedSecondPass = cleanedFirstPass.replace("-", " ");
            String cleanedThirdPass = RemoveSpaceFirst.removeSpaceFirst(cleanedSecondPass);
            String cleanedFinalPass = onlyFirstLetterToUpperCase(cleanedThirdPass);

            Ingredient ing = new Ingredient(cleanedFinalPass);
            ingredientSet.add(ing);
            //System.out.println(i + " - " + ing);
            i++;
        }*/
        return ingredientSet;
    }

    private static StringTokenizer getStringTokenizer(String ingredients) {
        String stringFirstPass = ingredients.replaceAll("(?<![a-zA-Z])\\d+(\\.\\d+)?%?|(?<![a-zA-Z])\\d{2,3}(?![\\d.])", "").replaceAll("()","").replace("*", "").replace("%", "").replace("_","").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\[","");
        String stringSecondPass = stringFirstPass.replaceAll("  ", " ");
        String stringToTokenize = stringSecondPass.replace(",",";").replace("-", ";").replace(".",";").replaceAll("\\s*g$","");
        StringTokenizer tokenizer = new StringTokenizer(stringToTokenize, ";");
        return tokenizer;
    }
}
