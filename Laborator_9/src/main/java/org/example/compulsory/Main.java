package org.example.compulsory;


import org.example.compulsory.repository.*;
import org.example.compulsory.tables.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        GenreRepository genreRepository = new GenreRepository();
        Genre genre = new Genre("rock");
        genre.setId(genreRepository.findId());
        for(Genre gen : genreRepository.findByName("pop")) {
            System.out.println(gen);
        }
        System.out.println("Genul cu id-ul 7: " + genreRepository.findById(7l));
        genreRepository.create((jakarta.persistence.EntityManager) entityManager, genre);

        ArtistRepository artistRepository = new ArtistRepository();
        Artist artist = new Artist("The beatles");
        artist.setId(artistRepository.findId());
        for(Artist art : artistRepository.findByName("The beatles")) {
            System.out.println(art);
        }
        artistRepository.create((jakarta.persistence.EntityManager) entityManager, artist);



        entityManager.close();
        entityManagerFactory.close();

//        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Interact with the database using the entity manager

        // Interogarea bazei de date pentru a obține toți artiștii
//        List<Artist> artists = entityManager.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
//
//        // Afisarea rezultatelor
//        for (Artist artist : artists) {
//            System.out.println(artist);
//        }
//
//        entityManager.close();
//        EntityManagerFactorySingleton.closeEntityManagerFactory();


//
//        // Crearea obiectului EntityManagerFactory
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyApplicationPU");
//
//        // Crearea obiectului EntityManager
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        // Interogarea bazei de date pentru a obține toți artiștii
//        List<Artist> artists = entityManager.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
//
//        // Afisarea rezultatelor
//        for (Artist artist : artists) {
//            System.out.println(artist);
//        }
//
//        // Inchiderea EntityManager
//        entityManager.close();
//
//        // Inchiderea EntityManagerFactory
//        entityManagerFactory.close();
    }
}
