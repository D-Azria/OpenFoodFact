package fr.diginamic.processing.parse;

import fr.diginamic.entites.Marque;

import static fr.diginamic.processing.parse.fineParse.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;
import static fr.diginamic.processing.parse.fineParse.RemoveSpaceFirst.removeSpaceFirst;

public class MarqueParser {

    public static Marque parseMarque(String m) {
        Marque marque;
        String cleanedFirstPass = m.replace(",", ", ").replace(":", "").replace("  ", " ");
        String cleanedSecondPass = removeSpaceFirst(cleanedFirstPass);
        String cleaned = onlyFirstLetterToUpperCase(cleanedSecondPass);
        marque = new Marque(cleaned);
        return marque;
    }
}
