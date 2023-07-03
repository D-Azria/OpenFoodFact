package fr.diginamic.processing.parse.fineParse;

/**
 * Cette classe fournit une méthode pour supprimer les espaces en début de chaîne de caractères.
 */
public class RemoveSpaceFirst {

    /**
     * Supprime les espaces en début de la chaîne de caractères spécifiée.
     *
     * @param str la chaîne de caractères à traiter
     * @return la chaîne de caractères sans les espaces en début
     */
    public static String removeSpaceFirst(String str) {
        if(str.startsWith(" ")){
            String cleaned = str;
            do {
                cleaned = cleaned.replaceFirst(" ","");
                return cleaned;
            } while (cleaned.startsWith(" "));
        }
        else {
            return str;
        }
    }
}
