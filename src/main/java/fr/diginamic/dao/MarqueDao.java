package fr.diginamic.dao;


import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class MarqueDao implements IMarqueDao {
    static Set<Marque> marques = new HashSet<>();

    public static void insert(Marque marque, Produit produit) throws Exception {
        EntityManager em = ConnectionEntityManager.getEm();

        if (marques.contains(marque)) {
            for (Marque mar : marques) {
                if (mar.equals(marque)) {
                    produit.setMarque(mar);
                    break;
                }
            }
        } else {
            marques.add(marque);
            produit.setMarque(marque);
            em.persist(marque);
        }
    }

    @Override
    public Set<Marque> extraire() throws Exception {
        Set<Marque> marques = new HashSet<>();

        return null;
    }
}
