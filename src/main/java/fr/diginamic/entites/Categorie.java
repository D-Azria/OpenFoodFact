package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="CATEGORIE")
@Cacheable
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String libelle;

    /** Classe permettant de définir l'entité catégorie
     *
     */
    @OneToMany(mappedBy = "categorie",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Produit> produits = new HashSet<>();

    public Categorie() {
    }

    public Categorie(String libelle) {
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
     * Ajoute un produit à la liste des produits de la catégorie.
     *
     * @param produit le produit à ajouter
     */
    public void addProduit(Produit produit) {
        produits.add(produit);
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", produits=" + produits +
                '}';
    }

    /**
     * Indique si l'objet spécifié est égal à cette catégorie.
     * Deux catégories sont considérées égales si elles ont le même libellé.
     *
     * @param o l'objet à comparer avec cette catégorie
     * @return true si l'objet spécifié est égal à cette catégorie, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Categorie categorie = (Categorie) o;
        return Objects.equals(libelle, categorie.libelle);
    }

    /**
     * Retourne un code de hachage pour cette catégorie.
     * Le code de hachage est basé sur le libellé de la catégorie.
     *
     * @return le code de hachage de la catégorie
     */
    @Override
    public int hashCode() {
        return Objects.hash(libelle);
    }
}
