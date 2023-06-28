package fr.diginamic.dao;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class AdditifDao implements IAdditifDao {
    static Set<Additif> allAdditifs = new HashSet<>();

    public static void insert(Additif additif, Produit produit) throws Exception {
        EntityManager em = ConnectionEntityManager.getEm();

        if (allAdditifs.contains(additif)) {
            for (Additif a : allAdditifs) {
                if (a.equals(additif)) {
                    produit.addAdditif(a);
                    break;
                }
            }
        } else {
            allAdditifs.add(additif);
            produit.addAdditif(additif);
            em.persist(additif);
        }
    }

    @Override
    public Set<Additif> extraire() throws Exception {
        Set<Additif> additifs = new HashSet<>();
        return additifs;
    }
}
