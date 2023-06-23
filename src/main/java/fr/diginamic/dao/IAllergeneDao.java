package fr.diginamic.dao;


import fr.diginamic.entites.Allergene;
import java.util.Set;

public interface IAllergeneDao {
    Set<Allergene> extraire() throws Exception;
}
