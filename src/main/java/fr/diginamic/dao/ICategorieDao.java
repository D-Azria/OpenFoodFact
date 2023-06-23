package fr.diginamic.dao;

import fr.diginamic.entites.Categorie;

import java.util.Set;

public interface ICategorieDao {
    Set<Categorie> extraire() throws Exception;
}
