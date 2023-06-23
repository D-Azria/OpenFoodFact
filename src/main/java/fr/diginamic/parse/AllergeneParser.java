package fr.diginamic.parse;

import fr.diginamic.entites.Allergene;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.diginamic.utils.OnlyFirstLetterToUpperCase.onlyFirstLetterToUpperCase;
import static fr.diginamic.utils.RemoveSpaceFirst.removeSpaceFirst;

public class AllergeneParser {

    public static Set<Allergene> parseAllergene(String allergenes){
        Set<Allergene> allergeneSet = new HashSet<>();
        String [] token = allergenes.split(",");
        for (String t:token){
            String cleanedFirstPass = t.replace("en:", "").replace("fr:", "").replace(":", "").replace("  ", " ").replace("ble ", "blé ").replace("Ble ", "blé ").replace("rme", "rème").replace("bié", "blé");
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
