package fr.diginamic.dao;

import fr.diginamic.entites.Ingredient;

import java.util.Set;

public interface IIngredientDao {
    static void insert(Ingredient ingredient) throws Exception{

    }
    Set<Ingredient> extraire() throws Exception;
}
