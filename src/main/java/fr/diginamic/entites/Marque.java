package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Override
    public String toString() {
        return "Marque : " + id + " - " + libelle;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(libelle);
    }
}
