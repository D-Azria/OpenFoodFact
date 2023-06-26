package fr.diginamic.entites;

import fr.diginamic.utils.NutritionGradeFr;
import java.util.Set;

public class OFFSignleProduct {
    private Produit offProducts;
    private NutritionGradeFr offNutritionGradeFr;
    private Marque offMarque;
    private Set<Additif> offAdditifs;
    private Set<Allergene> offAllergenes;
    private Categorie offCategorie;
    private Set<Ingredient> offIngredients;

    public OFFSignleProduct(Produit offProducts, NutritionGradeFr offNutritionGradeFr, Marque offMarque, Set<Additif> offAdditifs, Set<Allergene> offAllergenes, Categorie offCategorie, Set<Ingredient> offIngredients) {
        this.offProducts = offProducts;
        this.offNutritionGradeFr = offNutritionGradeFr;
        this.offMarque = offMarque;
        this.offAdditifs = offAdditifs;
        this.offAllergenes = offAllergenes;
        this.offCategorie = offCategorie;
        this.offIngredients = offIngredients;
    }

    public Produit getOffProducts() {
        return offProducts;
    }

    public void setOffProducts(Produit offProducts) {
        this.offProducts = offProducts;
    }

    public NutritionGradeFr getOffNutritionGradeFr() {
        return offNutritionGradeFr;
    }

    public void setOffNutritionGradeFr(NutritionGradeFr offNutritionGradeFr) {
        this.offNutritionGradeFr = offNutritionGradeFr;
    }

    public Marque getOffMarque() {
        return offMarque;
    }

    public void setOffMarque(Marque offMarque) {
        this.offMarque = offMarque;
    }

    public Set<Additif> getOffAdditifs() {
        return offAdditifs;
    }

    public void setOffAdditifs(Set<Additif> offAdditifs) {
        this.offAdditifs = offAdditifs;
    }

    public Set<Allergene> getOffAllergenes() {
        return offAllergenes;
    }

    public void setOffAllergenes(Set<Allergene> offAllergenes) {
        this.offAllergenes = offAllergenes;
    }

    public Categorie getOffCategorie() {
        return offCategorie;
    }

    public void setOffCategorie(Categorie offCategorie) {
        this.offCategorie = offCategorie;
    }

    public Set<Ingredient> getOffIngredients() {
        return offIngredients;
    }

    public void setOffIngredients(Set<Ingredient> offIngredients) {
        this.offIngredients = offIngredients;
    }

    @Override
    public String toString() {
        return "OFFSignleProduct{" +
                "offProducts=" + offProducts +
                ", offNutritionGradeFr=" + offNutritionGradeFr +
                ", offMarque=" + offMarque +
                ", offAdditifs=" + offAdditifs +
                ", offAllergenes=" + offAllergenes +
                ", offCategorie=" + offCategorie +
                ", offIngredients=" + offIngredients +
                '}';
    }
}
