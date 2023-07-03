package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Classe permettant de définir l'entité additif
 *
 */
@Entity
@Table(name="ADDITIF")
@Cacheable
public class Additif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String libelle;

    @ManyToMany(mappedBy = "additifs")
    private Set<Produit> produits = new HashSet<>();

    public Additif() {
    }

    public Additif(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
     * Ajoute un produit à la liste des produits de l'additif.
     *
     * @param produit le produit à ajouter
     */
    public void addProduit(Produit produit) {
        produits.add(produit);
    }

    @Override
    public String toString() {
        return "Additif : " + code + " - " + libelle ;
    }

    /**
     * Indique si l'objet spécifié est égal à cet additif.
     * Deux additifs sont considérés égaux si ils ont le même code et le même libellé.
     *
     * @param o l'objet à comparer avec cet additif
     * @return true si l'objet spécifié est égal à cet additif, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Additif additif = (Additif) o;
        return Objects.equals(code, additif.code) && Objects.equals(libelle, additif.libelle);
    }

    /**
     * Retourne un code de hachage pour cet additif.
     * Le code de hachage est basé sur le code et le libellé de l'additif.
     *
     * @return le code de hachage de l'additif
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, libelle);
    }
}
