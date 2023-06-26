package fr.diginamic.dao;

import fr.diginamic.entites.Categorie;

import java.util.Set;

public interface ICategorieDao {

    static void insert(Categorie categorie) throws Exception {

    }

    Set<Categorie> extraire() throws Exception;
}
