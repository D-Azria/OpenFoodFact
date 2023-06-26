package fr.diginamic.dao;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Produit;

import java.util.Set;

public interface IAdditifDao {

    static void insert(Additif additif) throws Exception{

    }
    Set<Additif> extraire() throws Exception;
}
