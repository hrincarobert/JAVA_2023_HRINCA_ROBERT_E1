package org.example.compulsory;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManagerFactorySingleton entityManagerFactorySingleton = null;
    private EntityManagerFactorySingleton() { }

    public static EntityManagerFactorySingleton getInstance() {
        if(entityManagerFactorySingleton == null) {
            entityManagerFactorySingleton = new EntityManagerFactorySingleton();
            entityManagerFactory = Persistence.createEntityManagerFactory("ExamplePU");
        }
        return entityManagerFactorySingleton;
    }

    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    public static void closeEntity() {
        entityManagerFactory.close();
    }
}


//
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class EntityManagerFactorySingleton {
//
//    private static EntityManagerFactory entityManagerFactory;
//
//    private EntityManagerFactorySingleton() {
//        entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
//    }
//
//    public static EntityManagerFactory getEntityManagerFactory() {
//        if(entityManagerFactory == null) {
//            synchronized (EntityManagerFactorySingleton.class) {
//                if(entityManagerFactory == null) {
//                    new EntityManagerFactorySingleton();
//                }
//            }
//        }
//        return entityManagerFactory;
//    }
//
//    public static void closeEntityManagerFactory() {
//        entityManagerFactory.close();
//    }
//}
