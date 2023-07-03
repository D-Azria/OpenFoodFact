package fr.diginamic.processing.parse;

import fr.diginamic.entites.Additif;

/**
 * Cette classe fournit une méthode pour le parsing des données liées à un additif individuel.
 */
public class AdditifParseEach {

    /**
     * Parse la chaîne de caractères spécifiée et retourne un objet Additif correspondant à l'additif.
     *
     * @param a la chaîne de caractères contenant l'additif à parser
     * @return un objet Additif correspondant à l'additif
     */
    public static Additif parseEachAdditif(String a){

        String[] token = a.split(" - ");

        String code = token[0];
        String libelleToParse = token[1];

        String[] words = libelleToParse.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {  // Ensure the word is not empty
                char firstLetter = Character.toUpperCase(word.charAt(0));
                String capitalizedWord = firstLetter + word.substring(1);
                result.append(capitalizedWord).append(" ");
            }
        }

        String libelle = result.toString().trim();

        Additif additif = new Additif(code, libelle);
        return additif;
    }
}
