package fr.diginamic.utils;

import jakarta.persistence.*;

public class ConnectionEntityManager {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManagerFactory getEmf() {
        if (emf != null) {
            return emf;
        } else {
            emf = Persistence.createEntityManagerFactory("open_food_fact");
            return emf;
        }
    }

    public static EntityManager getEm() {
        if (em != null) {
            return em;
        } else {
            em = getEmf().createEntityManager();
            return em;
        }
    }

    public static void closeEM(){
        em.close();
    }

    public static void closeEMF() {
        em.close();
        emf.close();
    }

    private ConnectionEntityManager() {
    }
}
