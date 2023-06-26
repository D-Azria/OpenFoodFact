package fr.diginamic.dao;

import fr.diginamic.entites.Marque;

import java.util.Set;

public interface IMarqueDao {

    Set<Marque> extraire() throws Exception;

    static void insert(Marque marque) throws Exception{

    }
}
