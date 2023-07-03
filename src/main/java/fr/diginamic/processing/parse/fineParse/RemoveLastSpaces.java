package fr.diginamic.processing.parse.fineParse;

/**
 * Cette classe fournit une méthode pour supprimer les espaces en fin de chaîne de caractères.
 */
public class RemoveLastSpaces {

    /**
     * Supprime les espaces en fin de la chaîne de caractères spécifiée.
     *
     * @param str la chaîne de caractères à traiter
     * @return la chaîne de caractères sans les espaces à la fin
     */
    public static String removeLastSpaces(String str) {
        if(str.endsWith(" ")){
            String cleaned = str;
            do {
                cleaned = cleaned.replace(" ","");
                return cleaned;
            } while (cleaned.endsWith(" "));
        }
        else {
            return str;
        }
    }
}
