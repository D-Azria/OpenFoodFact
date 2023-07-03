package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Classe permettant de définir l'entité ingredient
 *
 */
@Entity
@Table(name="INGREDIENT")
@Cacheable
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String libelle;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Produit> produits = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(String libelle) {
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Ajoute un produit à la liste des produits.
     *
     * @param produit le produit à ajouter
     */
    public void addProduit(Produit produit) {
        produits.add(produit);
    }

    @Override
    public String toString() {
        return libelle ;
    }

    /**
     * Indique si l'objet spécifié est égal à cet ingrédient.
     * Deux ingrédients sont considérés égaux s'ils ont le même libellé.
     *
     * @param o l'objet à comparer avec cet ingrédient
     * @return true si l'objet spécifié est égal à cet ingrédient, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient ingredient = (Ingredient) o;
        return Objects.equals(libelle, ingredient.libelle);
    }

    /**
     * Retourne un code de hachage pour cet ingrédient.
     * Le code de hachage est basé sur le libellé de l'ingrédient.
     *
     * @return le code de hachage de l'ingrédient
     */
    @Override
    public int hashCode() {
        return Objects.hash(libelle);
    }
}
