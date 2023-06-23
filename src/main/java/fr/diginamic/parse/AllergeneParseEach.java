package fr.diginamic.parse;

import fr.diginamic.entites.Additif;

public class AllergeneParseEach {

    public static void parseEachAllergene(String a){

        String[] token = a.split(",");

        String code = token[0];
        String libelle = token[1];

        Additif additif = new Additif(code, libelle);
        //System.out.println(additif);
    }
}
