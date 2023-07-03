package fr.diginamic.processing.parse.fineParse;

/**
 * Cette classe fournit une méthode pour supprimer tout ce qui se trouve après le symbole d'astérisque (*) dans une chaîne de caractères.
 */
public class RemoveAfterAsterisk {

    /**
     * Supprime tout ce qui se trouve après le premier symbole d'astérisque (*) dans la chaîne de caractères spécifiée.
     *
     * @param input la chaîne de caractères à traiter
     * @return la chaîne de caractères sans ce qui se trouve après le premier astérisque
     */
    public static String removeAsterisk(String input) {
        StringBuilder modifiedString = new StringBuilder();
        String[] words = input.split(" ");
        for (String word : words) {
            if (word.contains("*")) {
                int asteriskIndex = word.indexOf("*");
                if (asteriskIndex + 1 < word.length() && word.charAt(asteriskIndex + 1) != ' ') {
                    modifiedString.append(word, 0, asteriskIndex + 1);
                } else {
                    modifiedString.append(word).append(" ");
                }
            } else {
                modifiedString.append(word).append(" ");
            }
        }
        return modifiedString.toString().trim();
    }
}
