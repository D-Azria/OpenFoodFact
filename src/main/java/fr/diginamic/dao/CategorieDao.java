package fr.diginamic.dao;

import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class CategorieDao implements ICategorieDao {

    static Set<Categorie> categories = new HashSet<>();

    public static void insert(Categorie categorie) throws Exception{
        EntityManager em = ConnectionEntityManager.getEm();
        //em.getTransaction().begin();
        categories.add(categorie);
        em.persist(categorie);
        //em.getTransaction().commit();
    }

    @Override
    public Set<Categorie> extraire() throws Exception {
        Set<Categorie> categories = new HashSet<>();

        return null;
    }
}
