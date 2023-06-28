package fr.diginamic.processing.parse;

import fr.diginamic.entites.Additif;

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
