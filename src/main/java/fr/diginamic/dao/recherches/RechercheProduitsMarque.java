package fr.diginamic.dao.recherches;

import fr.diginamic.dao.ProduitDao;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.Set;

public class RechercheProduitsMarque {
    public static void rechercheProduitsMarque(int number, String marque){
        try(EntityManager em = ConnectionEntityManager.getEm()){
            System.out.println("Bon choix !! XD");
            Set<Produit> produits = ProduitDao.extraire();

        }
    }
}
