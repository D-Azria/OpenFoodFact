package fr.diginamic.parse;


import fr.diginamic.entites.Categorie;
import static fr.diginamic.utils.OnlyFirstLetterToUpperCase.*;

public class CategorieParser {
    public static Categorie parseCategorie(String categorieToParse){
        String cleanedFirstPass = categorieToParse.replace("en:", "");
        String cleanedSecondPass = cleanedFirstPass.replace("-", " ");
        String cleanedFinalPass = onlyFirstLetterToUpperCase(cleanedSecondPass);
        Categorie categorie = new Categorie(cleanedFinalPass);
        return categorie;
    }

}

