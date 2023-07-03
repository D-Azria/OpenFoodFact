package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Classe permettant de définir l'entité allergene
 *
 */
@Entity
@Table(name="ALLERGENE")
@Cacheable
public class Allergene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String libelle;

    @ManyToMany(mappedBy = "allergenes")
    private Set<Produit> produits = new HashSet<>();

    public Allergene() {
    }

    public Allergene(String libelle) {
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
     * Ajoute un produit à la liste des produits de l'allergène.
     *
     * @param produit le produit à ajouter
     */
    public void addProduit(Produit produit) {
        produits.add(produit);
    }

    @Override
    public String toString() {
        return "Allergene{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", produits=" + produits +
                '}';
    }

    /**
     * Indique si l'objet spécifié est égal à cet allergène.
     * Deux allergènes sont considérés égaux si ils ont le même libellé.
     *
     * @param o l'objet à comparer avec cet allergène
     * @return true si l'objet spécifié est égal à cet allergène, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Allergene allergene = (Allergene) o;
        return libelle.equals(allergene.libelle);
    }

    /**
     * Retourne un code de hachage pour cet allergène.
     * Le code de hachage est basé sur le libellé de l'allergène.
     *
     * @return le code de hachage de l'allergène
     */
    @Override
    public int hashCode() {
        return libelle.hashCode();
    }
}
