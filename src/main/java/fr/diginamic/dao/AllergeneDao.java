package fr.diginamic.dao;


import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class AllergeneDao implements IAllergeneDao{

    private static Set<Allergene> allAllergenes = new HashSet<>();
    public static void insert(Allergene allergene, Produit produit) throws Exception{
        EntityManager em = ConnectionEntityManager.getEm();
        produit.addAllergenes(allergene);
        if(!allAllergenes.contains(allergene)){
            allAllergenes.add(allergene);
            em.persist(allergene);
        }
        //em.getTransaction().commit();
    }

    @Override
    public Set<Allergene> extraire() throws Exception {
        Set<Allergene> allergenes = new HashSet<>();

        return null;
    }

    public static Set<Allergene> getAllAllergenes() {
        return allAllergenes;
    }

    public static void setAllAllergenes(Set<Allergene> allAllergenes) {
        AllergeneDao.allAllergenes = allAllergenes;
    }
}
