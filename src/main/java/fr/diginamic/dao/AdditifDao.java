package fr.diginamic.dao;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class AdditifDao implements IAdditifDao{
    static Set<Additif> allAdditifs = new HashSet<>();
    public static void insert(Additif additif) throws Exception{
        EntityManager em = ConnectionEntityManager.getEm();
        //em.getTransaction().begin();
        allAdditifs.add(additif);
        em.persist(additif);
        //em.getTransaction().commit();
    }

    @Override
    public Set<Additif> extraire() throws Exception {
        Set<Additif> additifs = new HashSet<>();
        return additifs;
    }
}
