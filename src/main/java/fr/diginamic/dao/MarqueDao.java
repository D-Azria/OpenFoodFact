package fr.diginamic.dao;


import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class MarqueDao implements IMarqueDao{
    static Set<Marque> marques = new HashSet<>();

    public static void insert(Marque marque) throws Exception{
        EntityManager em = ConnectionEntityManager.getEm();
        //em.getTransaction().begin();
        marques.add(marque);
        em.persist(marque);

        //em.getTransaction().commit();
    }
    @Override
    public Set<Marque> extraire() throws Exception {
        Set<Marque> marques = new HashSet<>();

        return null;
    }
}
