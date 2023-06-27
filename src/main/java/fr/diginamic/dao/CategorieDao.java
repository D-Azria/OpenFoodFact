package fr.diginamic.dao;

import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class CategorieDao implements ICategorieDao {

    static Set<Categorie> categories = new HashSet<>();

    public static void insert(Categorie categorie, Produit produit) throws Exception {
        EntityManager em = ConnectionEntityManager.getEm();

        if (categories.contains(categorie)) {
            for (Categorie cat : categories) {
                if (cat.equals(categorie)) {
                    produit.setCategorie(cat);
                    break;
                }
            }
        } else {
            categories.add(categorie);
            produit.setCategorie(categorie);
            em.persist(categorie);
        }
    }

    @Override
    public Set<Categorie> extraire() throws Exception {
        Set<Categorie> categories = new HashSet<>();

        return null;
    }
}
