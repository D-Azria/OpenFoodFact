package fr.diginamic.processing.parse;


import fr.diginamic.entites.Categorie;
import static fr.diginamic.processing.parse.fineParse.OnlyFirstLetterToUpperCase.*;

/**
 * Cette classe fournit des méthodes pour le parsing des données liées aux catégories.
 */
public class CategorieParser {

    /**
     * Parse la chaîne de caractères spécifiée et retourne un objet Categorie correspondant à la catégorie.
     *
     * @param categorieToParse la chaîne de caractères représentant la catégorie à parser
     * @return un objet Categorie correspondant à la catégorie
     */
    public static Categorie parseCategorie(String categorieToParse){
        String cleanedFirstPass = categorieToParse.replace("en:", "");
        String cleanedSecondPass = cleanedFirstPass.replace("-", " ");
        String cleanedFinalPass = onlyFirstLetterToUpperCase(cleanedSecondPass);
        Categorie categorie = new Categorie(cleanedFinalPass);
        return categorie;
    }
}

