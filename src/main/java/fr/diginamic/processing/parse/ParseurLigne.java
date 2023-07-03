package fr.diginamic.processing.parse;

import fr.diginamic.entites.*;
import fr.diginamic.utils.NutritionGradeFr;

import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe fournit des méthodes pour le parsing des lignes de données dans le format spécifique d'OFF (Open Food Facts).
 */
public class ParseurLigne {

    /**
     * Parse la ligne spécifiée et retourne un objet OFFSingleProduct correspondant aux données extraites.
     *
     * @param l la ligne de données à parser
     * @return un objet OFFSingleProduct correspondant aux données extraites de la ligne
     */
    public static OFFSingleProduct parseLigne(String l){
        // Premier parsing et split en fonction des différentes données
        String lineToSplit = l.replaceAll("[^a-zA-ZÉàâäéèêëîïôöùûüçŒœ'\\|\\- 0-9.,;:_*()%\\[\\]]", "");
        String[] token = lineToSplit.split("\\|", -1);
        String categorie = token[0];
        String marque = token[1];
        String nom = token[2];
        String nutritionGradeFr = token[3];
        String ingredients = token[4];
        Double energie = parseTokenDouble(token[5]);
        Double graisse  = parseTokenDouble(token[6]);
        Double sucres = parseTokenDouble(token[7]);
        Double fibres = parseTokenDouble(token[8]);;
        Double proteines  = parseTokenDouble(token[9]);
        Double sel = parseTokenDouble(token[10]);
        Double vitA = parseTokenDouble(token[11]);
        Double vitD = parseTokenDouble(token[12]);
        Double vitE = parseTokenDouble(token[13]);
        Double vitK = parseTokenDouble(token[14]);
        Double vitC = parseTokenDouble(token[15]);
        Double vitB1 = parseTokenDouble(token[16]);
        Double vitB2 = parseTokenDouble(token[17]);
        Double vitPP = parseTokenDouble(token[18]);
        Double vitB6 = parseTokenDouble(token[19]);
        Double vitB9 = parseTokenDouble(token[20]);
        Double vitB12 = parseTokenDouble(token[21]);
        Double calcium = parseTokenDouble(token[22]);
        Double magnesium = parseTokenDouble(token[23]);
        Double iron = parseTokenDouble(token[24]);
        Double fer = parseTokenDouble(token[25]);
        Double betaCarotene = parseTokenDouble(token[26]);
        Boolean presenceHuilePalme = parseTokenBoolean(token[27]);
        String allergenes = token[28];
        String additifs = token[29];

        // Récupération du grade nutritionnel
        NutritionGradeFr thisGrade = null;
        for (NutritionGradeFr enumGrade : NutritionGradeFr.values()) {
            if (enumGrade.toString().equalsIgnoreCase(nutritionGradeFr)) {
                thisGrade = enumGrade;
                break;
            }
        }

        // Création d'un nouveau produit avec ses données
        Produit produit = new Produit(nom, thisGrade, energie, graisse, sucres, fibres, proteines, sel, vitA, vitD, vitE, vitK, vitC, vitB1, vitB2, vitPP, vitB6, vitB9, vitB12, calcium, magnesium, iron, fer, betaCarotene, presenceHuilePalme);

        // Récupération de la marque
        Marque thisMarque = MarqueParser.parseMarque(marque);

        // Récupétation de la catégorie
        Categorie thisCategorie = CategorieParser.parseCategorie(categorie);

        // Récupération des allergènes sous forme de HashSet
        Set<Allergene> allergenesSet = new HashSet<>();
        if(!allergenes.isEmpty()){
            allergenesSet = AllergeneParser.parseAllergene(allergenes);
        }

        // Récupération des additifs sous forme de HashSet
        Set<Additif> additifsSet = new HashSet<>();
        if(!additifs.isEmpty()){
            additifsSet = AdditifParser.parseAdditifs(additifs);
        }

        //Récupération des ingrédients sous forme de HasSet
        Set<Ingredient> ingredientSet = new HashSet<>();
        if(ingredientSet.isEmpty()){
            ingredientSet = IngredientParser.parseIngredient(ingredients);
        }

        // Création d'un objet intermédiaire OFF regroupant les 6 entités obtenues lors du parse de la ligne
        OFFSingleProduct newProduct = new OFFSingleProduct(produit, thisGrade, thisMarque, additifsSet, allergenesSet, thisCategorie, ingredientSet);

        return newProduct;
    }

    /**
     * Convertit une chaîne de caractères en un Double. Si la chaîne est vide, renvoie 0.0.
     *
     * @param token la chaîne de caractères à convertir
     * @return la valeur Double correspondante à la chaîne, ou 0.0 si la chaîne est vide
     */
    private static Double parseTokenDouble(String token){
        if (!token.isEmpty()) {
            return Double.valueOf(token);
        } else {
          return 0.0;
        }
    }

    /**
     * Convertit une chaîne de caractères en un Boolean. Si la chaîne est vide, renvoie false.
     *
     * @param token la chaîne de caractères à convertir
     * @return la valeur Boolean correspondante à la chaîne, ou false si la chaîne est vide
     */
    private static Boolean parseTokenBoolean(String token){
        if (!token.isEmpty()) {
            return Boolean.valueOf(token);
        } else {
            return false;
        }
    }
}
