package fr.diginamic.processing.parse;

import fr.diginamic.entites.Ingredient;
import fr.diginamic.processing.parse.fineParse.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe fournit des méthodes pour le parsing de chaînes de caractères contenant des ingrédients.
 */
public class IngredientParser {

    /**
     * Parse la chaîne de caractères contenant des ingrédients et retourne un ensemble d'objets Ingredient correspondant.
     *
     * @param ingredients la chaîne de caractères contenant des ingrédients à parser
     * @return un ensemble d'objets Ingredient correspondant aux ingrédients présents dans la chaîne
     */
    public static Set<Ingredient> parseIngredient(String ingredients) {
        Set<Ingredient> ingredientSet = new HashSet<>();

        // Rempacement de caractères permettant un split plus fin des ingrédients
        String firstPass = ingredients.replace("_", "")
                .replaceAll("()", "")
                .replace(",", ";")
                // Ci-dessous : enlève les % et les chiffres qui précèdent et le mot qui suit.
                .replaceAll("\\d+\\s*%\\s*\\w*", "")
                .replace(".", ";")
                .replaceAll(" - ", ";")
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
                // Une fois splitté, traitement pour formater les ingrédients
                String t1 = RemoveAfterAsterisk.removeAsterisk(t);
                String t2 = removeTypes(t1);

                String t3 = t2.replace("*", "")
                        .replace(",", "")
                        .replaceAll("\\s+", " ")
                        // Enlève tous les chiffres sauf ceux immédiatement précédés d'une lettre, ex : E440
                        .replaceAll("(?<![a-zA-Z])\\d+", "");
                String t4 = RemoveSpaceFirst.removeSpaceFirst(t3);
                String t5 = RemoveDoubleDots.removeDoubleDots(t4);
                String t6 = RemoveLastSpaces.removeLastSpaces(t5);
                if (!t6.isEmpty()) {
/*                    if (t6.matches("\\d+")) {
                        break;
                    }*/
                    t6 = OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase(t6);
                    if (t6.endsWith(":")){
                        t6 = t6.replaceAll("\\s*:", "");
                    }
                    if (t6.startsWith("de ") || t6.startsWith("De ")){
                        t6 = t6.replaceAll("(?i)de ", "");
                    }
                    Ingredient ing = new Ingredient(t6);
                    ingredientSet.add(ing);
                }
            }
        }
        return ingredientSet;
    }

    /**
     * Supprime les types spécifiques de la chaîne de caractères fournie.
     *
     * @param string la chaîne de caractères à nettoyer
     * @return la chaîne de caractères avec les types spécifiques supprimés
     */
    private static String removeTypes(String string){
        string = string.replaceAll("(?i)Garnitures?\\s*:?\\s*", "")
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
        return string;
    }

}
