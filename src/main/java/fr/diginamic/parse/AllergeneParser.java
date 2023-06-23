package fr.diginamic.parse;

import fr.diginamic.entites.Allergene;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllergeneParser {

    public static Set<Allergene> parseAllergene(String allergenes){
        Set<Allergene> allergeneSet = new HashSet<>();
        String [] token = allergenes.split(",");
        for (String t:token){
            String cleanedAllergenes = t.replace("en:", "");
            Allergene a = new Allergene(cleanedAllergenes);
            allergeneSet.add(a);
            //System.out.println(a);
        }
        return allergeneSet;
    }
}
