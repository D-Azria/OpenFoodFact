package fr.diginamic.processing.parse;

import fr.diginamic.entites.Allergene;

import java.util.HashSet;
import java.util.Set;

import static fr.diginamic.processing.parse.fineParse.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;
import static fr.diginamic.processing.parse.fineParse.RemoveSpaceFirst.removeSpaceFirst;

public class AllergeneParser {

    public static Set<Allergene> parseAllergene(String allergenes){
        Set<Allergene> allergeneSet = new HashSet<>();
        String [] token = allergenes.split(",");
        for (String t:token){
            String cleanedFirstPass = t.replace("en:", "").replace("fr:", "").replace(":", "").replace("  ", " ").replace("ble ", "blé ").replace("Ble ", "blé ").replace("rme", "rème").replace("bié", "blé").replace("\\*", "");
            String cleanedSecondPass = cleanedFirstPass.replace("-", " ");
            String cleanedThirdPass = removeSpaceFirst(cleanedSecondPass);
            String cleanedAllergenes = onlyFirstLetterToUpperCase(cleanedThirdPass);
            Allergene a = new Allergene(cleanedAllergenes);
            allergeneSet.add(a);
            //System.out.println(a);
        }
        return allergeneSet;
    }
}