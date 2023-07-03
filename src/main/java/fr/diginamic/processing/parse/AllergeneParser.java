package fr.diginamic.processing.parse;

import fr.diginamic.entites.Allergene;
import java.util.HashSet;
import java.util.Set;

import static fr.diginamic.processing.parse.fineParse.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;
import static fr.diginamic.processing.parse.fineParse.RemoveSpaceFirst.removeSpaceFirst;

/**
 * Cette classe fournit des méthodes pour le parsing des données liées aux allergènes.
 */
public class AllergeneParser {

    /**
     * Parse la chaîne de caractères spécifiée et retourne un ensemble d'objets Allergene correspondant aux allergènes.
     *
     * @param allergenes la chaîne de caractères contenant les allergènes à parser
     * @return un ensemble d'objets Allergene correspondant aux allergènes présents dans la chaîne
     */
    public static Set<Allergene> parseAllergene(String allergenes){
        Set<Allergene> allergeneSet = new HashSet<>();
        String [] token = allergenes.split(",");
        for (String t:token){
            String cleanedFirstPass = t.replace("en:", "").replace("fr:", "").replace(":", "").replace("  ", " ").replaceAll("(?i)ble ", "blé ").replace("rme", "rème").replace("bié", "blé").replace("*", "");
            String cleanedSecondPass = cleanedFirstPass.replace("-", " ");
            String cleanedThirdPass = removeSpaceFirst(cleanedSecondPass);
            String cleanedAllergenes = onlyFirstLetterToUpperCase(cleanedThirdPass);
            Allergene a = new Allergene(cleanedAllergenes);
            allergeneSet.add(a);

        }
        return allergeneSet;
    }
}
