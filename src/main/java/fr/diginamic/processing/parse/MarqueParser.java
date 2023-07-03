package fr.diginamic.processing.parse;

import fr.diginamic.entites.Marque;

import static fr.diginamic.processing.parse.fineParse.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;
import static fr.diginamic.processing.parse.fineParse.RemoveSpaceFirst.removeSpaceFirst;

/**
 * Cette classe fournit des méthodes pour le parsing des données liées aux marques.
 */
public class MarqueParser {

    /**
     * Parse la chaîne de caractères spécifiée et retourne un objet Marque correspondant à la marque.
     *
     * @param m la chaîne de caractères représentant la marque à parser
     * @return un objet Marque correspondant à la marque
     */
    public static Marque parseMarque(String m) {
        Marque marque;
        // Nettoyage de la chaîne de caractères
        String cleanedFirstPass = m.replace(",", ", ").replace(":", "").replace("  ", " ");
        String cleanedSecondPass = removeSpaceFirst(cleanedFirstPass);
        String cleaned = onlyFirstLetterToUpperCase(cleanedSecondPass);
        marque = new Marque(cleaned);
        return marque;
    }
}
