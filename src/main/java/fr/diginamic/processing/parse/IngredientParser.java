package fr.diginamic.processing.parse;

import fr.diginamic.entites.Ingredient;
import fr.diginamic.processing.parse.fineParse.*;

import java.util.HashSet;
import java.util.Set;

public class IngredientParser {

    public static Set<Ingredient> parseIngredient(String ingredients) {
        Set<Ingredient> ingredientSet = new HashSet<>();

        String firstPass = ingredients.replace("_", "")
                .replaceAll("()", "")
                .replace(",", ";")
                //.replaceAll("\\d+\\.\\d+(?=\\s*%)", "")
                //.replaceAll("\\d+(?=\\s*%)", "")
                .replaceAll("\\d+\\s*%\\s*\\w*", "")
                .replace(".", ";")
                .replace(" - ", ";")
                .replace(" -", ";")
                .replace("- ", ";")
                .replaceAll("\\s+", " ")
                .replace("(", "")
                .replace(")", "")
                .replace("[", "")
                .replace("]", "")
                .replaceAll(" et (?!de)", ";");

        String[] token = firstPass.split(";");
        for (String t : token) {
            if (!t.isEmpty()) {
                String t1 = RemoveAfterAsterisk.removeAsterisk(t);
                String t2 = t1.replaceAll("(?i)Garnitures?\\s*:?\\s*", "")
                        .replaceAll("(?i)épaississants?\\s*:?\\s*", "")
                        .replaceAll("(?i)contient?\\s*:?\\s*", "")
                        .replaceAll("(?i)gélifiants?\\s*:?\\s*", "")
                        .replaceAll("(?i)ingrédients?\\s*:?\\s*", "")
                        .replaceAll("(?i)acidifiants?\\s*:?\\s*", "")
                        .replaceAll("(?i)conservateurs?\\s*:?\\s*", "")
                        .replaceAll("(?i)émulsifiants?\\s*:?\\s*", "")
                        .replaceAll("(?i)colorants?\\s*:?\\s*", "")
                        .replaceAll("(?i)stabilisants?\\s*:?\\s*", "")
                        .replaceAll("(?i)vitamines?\\s*:?\\s*", "")
                        .replaceAll("(?i)arômes?\\s*:?\\s*", "")
                        .replaceAll("(?i)antioxydant?\\s*:?\\s*", "")
                        .replaceAll("(?i):?pulpes?\\s*de\\s*fruits?\\s*:?", "");


                String t3 = t2.replace("*", "").replace(",", "").replaceAll("\\s+", " ").replaceAll("(?<![a-zA-Z])\\d+", "");
                String t4 = RemoveSpaceFirst.removeSpaceFirst(t3);
                String t5 = RemoveDoubleDots.removeDoubleDots(t4);
                String t6 = RemoveLastSpaces.removeLastSpaces(t5);
                if (!t6.isEmpty()) {
                    if (t6.matches("\\d+")) {
                        break;
                    }
                    t6 = OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase(t6);
                    if (t6.endsWith(":")){
                        t6 = t6.replaceAll("\\s*:", "");
                    }
                    if (t6.startsWith("de ") || t6.startsWith("De ")){
                        t6 = t6.replaceAll("(?i)de ", "");
                    }
                    Ingredient ing = new Ingredient(t6);
                    ingredientSet.add(ing);
                    //System.out.println(ing);
                }
            }
        }
        return ingredientSet;
    }
}
