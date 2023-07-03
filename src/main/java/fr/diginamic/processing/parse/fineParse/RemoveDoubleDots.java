package fr.diginamic.processing.parse.fineParse;

/**
 * Cette classe fournit une méthode pour supprimer les doubles points (:) à la fin d'une chaîne de caractères.
 */
public class RemoveDoubleDots {

    /**
     * Supprime les doubles points (:) à la fin de la chaîne de caractères spécifiée.
     *
     * @param str la chaîne de caractères à traiter
     * @return la chaîne de caractères sans les doubles points à la fin
     */
    public static String removeDoubleDots(String str) {
        if(str.endsWith(" :")){
            String cleaned = str;
            do {
                cleaned = cleaned.replace(" :","");
                return cleaned;
            } while (cleaned.endsWith(" :"));
        }
        else {
            return str;
        }
    }
}
