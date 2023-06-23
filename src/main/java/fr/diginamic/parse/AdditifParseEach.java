package fr.diginamic.parse;

import fr.diginamic.entites.Additif;

public class AdditifParseEach {

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
        //System.out.println(additif);
        return additif;
    }
}
