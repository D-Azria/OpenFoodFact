package fr.diginamic.dao;

import fr.diginamic.entites.OFFSignleProduct;
import fr.diginamic.entites.Produit;

import java.util.Set;

public interface IProduitDao {

    static void insert(OFFSignleProduct singleData) throws Exception {

    }

    Set<Produit> extraire() throws Exception;

}
