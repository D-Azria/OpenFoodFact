package fr.diginamic.dao;


import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Allergene;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class AllergeneDao implements IAllergeneDao{

    static Set<Allergene> allAllergenes = new HashSet<>();
    public static void insert(Allergene allergene) throws Exception{
        EntityManager em = ConnectionEntityManager.getEm();
        //em.getTransaction().begin();
        allAllergenes.add(allergene);
        em.persist(allergene);
        //em.getTransaction().commit();
    }

    @Override
    public Set<Allergene> extraire() throws Exception {
        Set<Allergene> allergenes = new HashSet<>();

        return null;
    }
}
