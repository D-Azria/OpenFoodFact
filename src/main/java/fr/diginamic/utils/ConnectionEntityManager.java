package fr.diginamic.utils;

import jakarta.persistence.*;

/**
 * Cette classe gère la connexion à l'EntityManager et à l'EntityManagerFactory pour l'accès à la base de données.
 */
public class ConnectionEntityManager {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    /**
     * Renvoie l'EntityManagerFactory pour l'accès à la base de données.
     * Si l'EntityManagerFactory n'a pas été initialisé, il est créé avec le nom de persistence unit spécifié.
     *
     * @return l'EntityManagerFactory pour l'accès à la base de données
     */
    public static EntityManagerFactory getEmf() {
        if (emf != null) {
            return emf;
        } else {
            emf = Persistence.createEntityManagerFactory("open_food_fact");
            return emf;
        }
    }

    /**
     * Renvoie l'EntityManager pour l'accès à la base de données.
     * Si l'EntityManager n'a pas été initialisé, il est créé à partir de l'EntityManagerFactory.
     *
     * @return l'EntityManager pour l'accès à la base de données
     */
    public static EntityManager getEm() {
        if (em != null) {
            return em;
        } else {
            em = getEmf().createEntityManager();
            return em;
        }
    }
    /**
     * Ferme l'EntityManager.
     * Cette méthode doit être appelée pour libérer les ressources de l'EntityManager.
     */
    public static void closeEM(){
        em.close();
    }

    /**
     * Ferme l'EntityManagerFactory et l'EntityManager.
     * Cette méthode doit être appelée pour libérer les ressources de l'EntityManagerFactory et de l'EntityManager.
     */
    public static void closeEMF() {
        em.close();
        emf.close();
    }

    private ConnectionEntityManager() {
    }
}
