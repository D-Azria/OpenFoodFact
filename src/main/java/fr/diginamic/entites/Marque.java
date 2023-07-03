package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Classe permettant de définir l'entité marque
 *
 */
@Entity
@Table(name="MARQUE")
@Cacheable
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String libelle;

    @OneToMany(mappedBy = "marque",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Produit> produits = new HashSet<>();
    public Marque() {
    }

    public Marque(String libelle) {
        this.libelle = libelle;
    }

/*    public void addProduit(Produit produit){
        if(produit != null){
            produits.add(produit);
            //produit.setMarque(this);
        }
    }*/

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
     * Ajoute un produit à la liste des produits de la marque.
     *
     * @param produit le produit à ajouter
     */
    public void addProduit(Produit produit) {
        produits.add(produit);
    }

    @Override
    public String toString() {
        return "Marque : " + id + " - " + libelle;
    }

    /**
     * Indique si l'objet spécifié est égal à cette marque.
     * Deux marques sont considérées égales si elles ont le même libellé.
     *
     * @param o l'objet à comparer avec cette marque
     * @return true si l'objet spécifié est égal à cette marque, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Marque marque = (Marque) o;
        return Objects.equals(libelle, marque.libelle);
    }

    /**
     * Retourne un code de hachage pour cette marque.
     * Le code de hachage est basé sur le libellé de la marque.
     *
     * @return le code de hachage de la marque
     */
    @Override
    public int hashCode() {
        return Objects.hash(libelle);
    }
}
