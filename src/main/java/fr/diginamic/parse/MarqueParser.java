package fr.diginamic.parse;

import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Marque;

import java.util.Set;

import static fr.diginamic.utils.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;
import static fr.diginamic.utils.RemoveSpaceFirst.removeSpaceFirst;

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
