package fr.diginamic.dao;

import fr.diginamic.entites.Ingredient;

import java.util.Set;

public interface IIngredientDao {
    Set<Ingredient> extraire() throws Exception;
}
