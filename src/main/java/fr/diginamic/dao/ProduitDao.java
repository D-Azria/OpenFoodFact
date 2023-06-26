package fr.diginamic.dao;

import fr.diginamic.entites.OFFSignleProduct;
import fr.diginamic.entites.Produit;
import fr.diginamic.parse.ParseurLigne;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class ProduitDao implements IProduitDao{

    private static int produitId;
    private static Set<Produit> allProduits = new HashSet<>();

    public static void insert(Produit produit) throws Exception{
            EntityManager em = ConnectionEntityManager.getEm();
            //em.getTransaction().begin();
            allProduits.add(produit);
            em.persist(produit);
            produitId = produit.getId();
            //em.getTransaction().commit();
        }


    @Override
    public Set<Produit> extraire() {
        Set<Produit> produits = new HashSet<>();
        EntityManager em = ConnectionEntityManager.getEm();
        TypedQuery<Produit> p = em.createQuery("select p from Produit p", Produit.class);
        produits = (Set) p.getResultList();
        em.close();
        ConnectionEntityManager.closeEMF();
        return produits;
    }

    public static int getProduitId() {
        return produitId;
    }
}
