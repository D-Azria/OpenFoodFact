package fr.diginamic.dao;

import fr.diginamic.entites.Produit;

import java.util.Set;

public interface IProduitDao {
    Set<Produit> extraire() throws Exception;
}
