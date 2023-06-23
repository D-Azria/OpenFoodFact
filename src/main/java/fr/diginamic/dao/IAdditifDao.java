package fr.diginamic.dao;

import fr.diginamic.entites.Additif;
import java.util.Set;

public interface IAdditifDao {

    Set<Additif> extraire() throws Exception;
}
