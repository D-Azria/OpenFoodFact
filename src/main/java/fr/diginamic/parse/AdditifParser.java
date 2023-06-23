package fr.diginamic.parse;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Allergene;

import java.util.HashSet;
import java.util.Set;

public class AdditifParser {

    public static Set<Additif> parseAdditifs(String additifs){
        Set<Additif> additifSet = new HashSet<>();
        String [] token = additifs.split(",");
        //int nombreAdditif = token.length;

        for (String t:token){
            additifSet.add(AdditifParseEach.parseEachAdditif(t));
        }
        return additifSet;
    }
}
