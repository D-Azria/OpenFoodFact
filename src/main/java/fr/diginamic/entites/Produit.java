package fr.diginamic.entites;

import fr.diginamic.utils.NutritionGradeFr;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


/** Classe permettant de définir l'entité produit
 *
 */
@Entity
@Table(name="PRODUIT")
@Cacheable
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nom;

    @Enumerated
    private NutritionGradeFr nutritionGradeFr;

    private Double energie100g;
    private Double graisse100g ;
    private Double sucres100g;
    private Double fibres100g ;
    private Double proteines100g ;
    private Double sel100g;
    private Double vitA100g;
    private Double vitD100g;
    private Double vitE100g;
    private Double vitK100g;
    private Double vitC100g;
    private Double vitB1100g;
    private Double vitB2100g;
    private Double vitPP100g;
    private Double vitB6100g;
    private Double vitB9100g;
    private Double vitB12100g;
    private Double calcium100g;
    private Double magnesium100g;
    private Double iron100g;
    private Double fer100g;
    private Double betaCarotene100g;
    private Boolean presenceHuilePalme;

    @ManyToOne
    @JoinColumn(name="ID_MARQUE")
    private Marque marque;

    @ManyToOne
    @JoinColumn(name = "ID_CAT")
    private Categorie categorie;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ADDITIFS_PRODUITS",
            joinColumns = @JoinColumn(name="ID_PRODUIT", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_ADDITIF", referencedColumnName = "ID"))
    private Set<Additif> additifs = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ALLERGENES_PRODUITS",
            joinColumns = @JoinColumn(name="ID_PRODUIT", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_ALLERGENE", referencedColumnName = "ID"))
    private Set<Allergene> allergenes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "INGREDIENTS_PRODUITS",
            joinColumns = @JoinColumn(name="ID_PRODUIT", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_INGREDIENT", referencedColumnName = "ID"))
    private Set<Ingredient> ingredients = new HashSet<>();


    public Produit() {
    }

    public Produit(String nom, NutritionGradeFr nutritionGradeFr, Double energie100g, Double graisse100g, Double sucres100g, Double fibres100g, Double proteines100g, Double sel100g, Double vitA100g, Double vitD100g, Double vitE100g, Double vitK100g, Double vitC100g, Double vitB1100g, Double vitB2100g, Double vitPP100g, Double vitB6100g, Double vitB9100g, Double vitB12100g, Double calcium100g, Double magnesium100g, Double iron100g, Double fer100g, Double betaCarotene100g, Boolean presenceHuilePalme) {
        this.nom = nom;
        this.nutritionGradeFr = nutritionGradeFr;
        this.energie100g = energie100g;
        this.graisse100g = graisse100g;
        this.sucres100g = sucres100g;
        this.fibres100g = fibres100g;
        this.proteines100g = proteines100g;
        this.sel100g = sel100g;
        this.vitA100g = vitA100g;
        this.vitD100g = vitD100g;
        this.vitE100g = vitE100g;
        this.vitK100g = vitK100g;
        this.vitC100g = vitC100g;
        this.vitB1100g = vitB1100g;
        this.vitB2100g = vitB2100g;
        this.vitPP100g = vitPP100g;
        this.vitB6100g = vitB6100g;
        this.vitB9100g = vitB9100g;
        this.vitB12100g = vitB12100g;
        this.calcium100g = calcium100g;
        this.magnesium100g = magnesium100g;
        this.iron100g = iron100g;
        this.fer100g = fer100g;
        this.betaCarotene100g = betaCarotene100g;
        this.presenceHuilePalme = presenceHuilePalme;
    }

    public Produit(String nom, NutritionGradeFr nutritionGradeFr, Double energie100g, Double graisse100g, Double sucres100g, Double fibres100g, Double proteines100g, Double sel100g, Double vitA100g, Double vitD100g, Double vitE100g, Double vitK100g, Double vitC100g, Double vitB1100g, Double vitB2100g, Double vitPP100g, Double vitB6100g, Double vitB9100g, Double vitB12100g, Double calcium100g, Double magnesium100g, Double iron100g, Double fer100g, Double betaCarotene100g, Boolean presenceHuilePalme, Marque marque, Categorie categorie) {
        this.nom = nom;
        this.nutritionGradeFr = nutritionGradeFr;
        this.energie100g = energie100g;
        this.graisse100g = graisse100g;
        this.sucres100g = sucres100g;
        this.fibres100g = fibres100g;
        this.proteines100g = proteines100g;
        this.sel100g = sel100g;
        this.vitA100g = vitA100g;
        this.vitD100g = vitD100g;
        this.vitE100g = vitE100g;
        this.vitK100g = vitK100g;
        this.vitC100g = vitC100g;
        this.vitB1100g = vitB1100g;
        this.vitB2100g = vitB2100g;
        this.vitPP100g = vitPP100g;
        this.vitB6100g = vitB6100g;
        this.vitB9100g = vitB9100g;
        this.vitB12100g = vitB12100g;
        this.calcium100g = calcium100g;
        this.magnesium100g = magnesium100g;
        this.iron100g = iron100g;
        this.fer100g = fer100g;
        this.betaCarotene100g = betaCarotene100g;
        this.presenceHuilePalme = presenceHuilePalme;
        this.marque = marque;
        this.categorie = categorie;
    }

    /**
     * Ajoute un additif au produit.
     * Si l'additif est différent de null, il est ajouté à la liste des additifs du produit
     * et le produit est ajouté à la liste des produits de l'additif.
     *
     * @param additif l'additif à ajouter
     */
    public void addAdditif(Additif additif){
        if(additif != null){
            additifs.add(additif);
            additif.addProduit(this);
        }
    }

    /**
     * Ajoute un allergène au produit.
     * Si l'allergène est différent de null, il est ajouté à la liste des allergènes du produit
     * et le produit est ajouté à la liste des produits de l'allergène.
     *
     * @param allergene l'allergène à ajouter
     */
    public void addAllergenes(Allergene allergene){
        if(allergene != null){
            allergenes.add(allergene);
            allergene.addProduit(this);
        }
    }

    /**
     * Ajoute un ingrédient au produit.
     * Si l'ingrédient est différent de null, il est ajouté à la liste des ingrédients du produit
     * et le produit est ajouté à la liste des produits de l'ingrédient.
     *
     * @param ingredient l'ingrédient à ajouter
     */
    public void addIngredient(Ingredient ingredient){
        if(ingredient != null){
            ingredients.add(ingredient);
            ingredient.addProduit(this);
        }
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public NutritionGradeFr getNutritionGradeFr() {
        return nutritionGradeFr;
    }

    public void setNutritionGradeFr(NutritionGradeFr nutritionGradeFr) {
        this.nutritionGradeFr = nutritionGradeFr;
    }

    public Double getEnergie100g() {
        return energie100g;
    }

    public void setEnergie100g(Double energie100g) {
        this.energie100g = energie100g;
    }

    public Double getGraisse100g() {
        return graisse100g;
    }

    public void setGraisse100g(Double graisse100g) {
        this.graisse100g = graisse100g;
    }

    public Double getSucres100g() {
        return sucres100g;
    }

    public void setSucres100g(Double sucres100g) {
        this.sucres100g = sucres100g;
    }

    public Double getFibres100g() {
        return fibres100g;
    }

    public void setFibres100g(Double fibres100g) {
        this.fibres100g = fibres100g;
    }

    public Double getProteines100g() {
        return proteines100g;
    }

    public void setProteines100g(Double proteines100g) {
        this.proteines100g = proteines100g;
    }

    public Double getSel100g() {
        return sel100g;
    }

    public void setSel100g(Double sel100g) {
        this.sel100g = sel100g;
    }

    public Double getVitA100g() {
        return vitA100g;
    }

    public void setVitA100g(Double vitA100g) {
        this.vitA100g = vitA100g;
    }

    public Double getVitD100g() {
        return vitD100g;
    }

    public void setVitD100g(Double vitD100g) {
        this.vitD100g = vitD100g;
    }

    public Double getVitE100g() {
        return vitE100g;
    }

    public void setVitE100g(Double vitE100g) {
        this.vitE100g = vitE100g;
    }

    public Double getVitK100g() {
        return vitK100g;
    }

    public void setVitK100g(Double vitK100g) {
        this.vitK100g = vitK100g;
    }

    public Double getVitC100g() {
        return vitC100g;
    }

    public void setVitC100g(Double vitC100g) {
        this.vitC100g = vitC100g;
    }

    public Double getVitB1100g() {
        return vitB1100g;
    }

    public void setVitB1100g(Double vitB1100g) {
        this.vitB1100g = vitB1100g;
    }

    public Double getVitB2100g() {
        return vitB2100g;
    }

    public void setVitB2100g(Double vitB2100g) {
        this.vitB2100g = vitB2100g;
    }

    public Double getVitPP100g() {
        return vitPP100g;
    }

    public void setVitPP100g(Double vitPP100g) {
        this.vitPP100g = vitPP100g;
    }

    public Double getVitB6100g() {
        return vitB6100g;
    }

    public void setVitB6100g(Double vitB6100g) {
        this.vitB6100g = vitB6100g;
    }

    public Double getVitB9100g() {
        return vitB9100g;
    }

    public void setVitB9100g(Double vitB9100g) {
        this.vitB9100g = vitB9100g;
    }

    public Double getVitB12100g() {
        return vitB12100g;
    }

    public void setVitB12100g(Double vitB12100g) {
        this.vitB12100g = vitB12100g;
    }

    public Double getCalcium100g() {
        return calcium100g;
    }

    public void setCalcium100g(Double calcium100g) {
        this.calcium100g = calcium100g;
    }

    public Double getMagnesium100g() {
        return magnesium100g;
    }

    public void setMagnesium100g(Double magnesium100g) {
        this.magnesium100g = magnesium100g;
    }

    public Double getIron100g() {
        return iron100g;
    }

    public void setIron100g(Double iron100g) {
        this.iron100g = iron100g;
    }

    public Double getFer100g() {
        return fer100g;
    }

    public void setFer100g(Double fer100g) {
        this.fer100g = fer100g;
    }

    public Double getBetaCarotene100g() {
        return betaCarotene100g;
    }

    public void setBetaCarotene100g(Double betaCarotene100g) {
        this.betaCarotene100g = betaCarotene100g;
    }

    public Boolean getPresenceHuilePalme() {
        return presenceHuilePalme;
    }

    public void setPresenceHuilePalme(Boolean presenceHuilePalme) {
        this.presenceHuilePalme = presenceHuilePalme;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
        marque.addProduit(this);
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
        categorie.addProduit(this);
    }

    public Set<Additif> getAdditifs() {
        return additifs;
    }

    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }

    public Set<Allergene> getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(Set<Allergene> allergenes) {
        this.allergenes = allergenes;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", nutritionGradeFr=" + nutritionGradeFr +
                ", energie100g=" + energie100g +
                ", graisse100g=" + graisse100g +
                ", sucres100g=" + sucres100g +
                ", fibres100g=" + fibres100g +
                ", proteines100g=" + proteines100g +
                ", sel100g=" + sel100g +
                ", vitA100g=" + vitA100g +
                ", vitD100g=" + vitD100g +
                ", vitE100g=" + vitE100g +
                ", vitK100g=" + vitK100g +
                ", vitC100g=" + vitC100g +
                ", vitB1100g=" + vitB1100g +
                ", vitB2100g=" + vitB2100g +
                ", vitPP100g=" + vitPP100g +
                ", vitB6100g=" + vitB6100g +
                ", vitB9100g=" + vitB9100g +
                ", vitB12100g=" + vitB12100g +
                ", calcium100g=" + calcium100g +
                ", magnesium100g=" + magnesium100g +
                ", iron100g=" + iron100g +
                ", fer100g=" + fer100g +
                ", betaCarotene100g=" + betaCarotene100g +
                ", presenceHuilePalme=" + presenceHuilePalme +
                ", marque=" + marque.getLibelle() +
                ", categorie=" + categorie.getLibelle() +
                ", additifs=" + additifs +
                ", allergenes=" + allergenes +
                ", ingredients=" + ingredients +
                '}';
    }
}
