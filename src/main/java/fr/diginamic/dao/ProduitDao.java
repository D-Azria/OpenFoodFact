package fr.diginamic.dao;

import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class ProduitDao implements IProduitDao{

    private static Set<Produit> allProduits = new HashSet<>();

    public static void insert(Produit produit) throws Exception{
            EntityManager em = ConnectionEntityManager.getEm();
            allProduits.add(produit);
            em.persist(produit);
        }



    public static Set<Produit> extraire() {
        Set<Produit> produits = new HashSet<>();
        EntityManager em = ConnectionEntityManager.getEm();
        TypedQuery<Produit> p = em.createQuery("select p from Produit p", Produit.class);
        produits = (Set) p.getResultList();
        ConnectionEntityManager.closeEMF();
        return produits;
    }

    public static Set<Produit> getAllProduits() {
        return allProduits;
    }

    public static void setAllProduits(Set<Produit> allProduits) {
        ProduitDao.allProduits = allProduits;
    }

    public static void addProduit (Produit produit){
        allProduits.add(produit);
    }
}
