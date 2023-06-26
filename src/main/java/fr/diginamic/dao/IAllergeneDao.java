package fr.diginamic.dao;


import fr.diginamic.entites.Allergene;
import java.util.Set;

public interface IAllergeneDao {
    static void insert(Allergene allergene) throws Exception{

    }
    Set<Allergene> extraire() throws Exception;
}
