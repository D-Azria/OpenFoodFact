package fr.diginamic;

import fr.diginamic.dao.*;
import fr.diginamic.entites.*;
import fr.diginamic.ihm.StartInterface;
import fr.diginamic.parse.LectureCsv;
import fr.diginamic.parse.ParseurLigne;
import fr.diginamic.utils.ConnectionEntityManager;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        MainDao.mainDao();
        //StartInterface.startInterface();


    }
}
