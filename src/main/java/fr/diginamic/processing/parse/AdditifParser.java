package fr.diginamic.processing.parse;

import fr.diginamic.entites.Additif;
import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe fournit des méthodes pour le parsing des données liées aux additifs.
 */
public class AdditifParser {

    /**
     * Parse la chaîne de caractères spécifiée et retourne un ensemble d'objets Additif correspondant aux additifs.
     *
     * @param additifs la chaîne de caractères contenant les additifs à parser
     * @return un ensemble d'objets Additif correspondant aux additifs présents dans la chaîne
     */
    public static Set<Additif> parseAdditifs(String additifs){
        Set<Additif> additifSet = new HashSet<>();
        String [] token = additifs.split(",");

        for (String t:token){
            additifSet.add(AdditifParseEach.parseEachAdditif(t));
        }
        return additifSet;
    }
}
