package fr.diginamic.processing.parse.fineParse;

/**
 * Cette classe fournit une méthode pour convertir uniquement la première lettre d'une chaîne de caractères en majuscule.
 */
public class OnlyFirstLetterToUpperCase {

    /**
     * Convertit uniquement la première lettre de la chaîne de caractères spécifiée en majuscule.
     *
     * @param str la chaîne de caractères à traiter
     * @return la chaîne de caractères avec la première lettre en majuscule
     */
    public static String onlyFirstLetterToUpperCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String lowercaseStr = str.toLowerCase();
        char firstChar = Character.toUpperCase(lowercaseStr.charAt(0));


        String finalString = firstChar + lowercaseStr.substring(1);
        return finalString;
    }
}
