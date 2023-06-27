package fr.diginamic.parse;

import fr.diginamic.entites.Ingredient;
import fr.diginamic.utils.*;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static fr.diginamic.utils.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;

public class TestIngredientParsing {
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
        System.out.println(ingredientSet);
        //System.out.println(firstPass);


/*

        Set<Ingredient> ingredientSet = new HashSet<>();
        //String cleaningFirst = RemoveAfterAsterisk.removeAsterisk(ingredients);

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
       *//* String [] token = string.split("\\s*[;,]\\s*");
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
        }*//*
        return ingredientSet;
    }

    //replaceAll("(?<![a-zA-Z])\\d+(\\.\\d+)?%?|(?<![a-zA-Z])\\d{2,3}(?![\\d.])", "")
    private static StringTokenizer getStringTokenizer(String ingredients) {
        String stringFirstPass = ingredients.replaceAll("\\d+\\.\\d+(?=\\s*%)", "").replaceAll("\\d+(?=\\s*%)", "").replaceAll("()","").replace("*", "").replace("%", "").replace("_","").replace("(", "").replace(")", "").replace("[","").replaceAll("  ", " ");
        String stringToTokenize = stringFirstPass.replace(",",";").replace("-", ";").replace(".",";").replace(" et ", ";").replaceAll("\\s*g$","");
        StringTokenizer tokenizer = new StringTokenizer(stringToTokenize, ";");
        return tokenizer;
    }*/


        return ingredientSet;
    }
}
