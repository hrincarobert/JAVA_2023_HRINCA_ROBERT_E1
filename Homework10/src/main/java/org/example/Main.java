package org.example;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        GenreRepository genreRepository = new GenreRepository();
        Genre genre = new Genre("rock");
        genre.setId(genreRepository.findId());
        for(Genre gen : genreRepository.findByName("Country Rock")) {
            System.out.println(gen);
        }
        System.out.println("Genul cu id-ul 7: " + genreRepository.findById(7l));
        genreRepository.create(entityManager, genre);

        ArtistRepository artistRepository = new ArtistRepository();
        Artist artist = new Artist("The beatles");
        artist.setId(artistRepository.findId());
        for(Artist art : artistRepository.findByName("The beatles")) {
            System.out.println(art);
        }
        artistRepository.create(entityManager, artist);

        AlbumRepository albumRepository = new AlbumRepository();

        for (int i = 0; i < 5; i++) {
            entityManager.getTransaction().begin();
            Artist artist1 = new Artist("Artist " + i);
            artist1.setId(artistRepository.findId());

            try {
                entityManager.persist(artist1);
            } catch(EntityExistsException e) {
                System.out.println("Obiectul artist exista deja in baza de date.");
            }
            entityManager.getTransaction().commit();

            System.out.println("Artist saved! " + artist1);

            entityManager.getTransaction().begin();

            Album album = new Album();
            album.setTitle("Album " + i);
            album.setReleaseYear(2021);
            album.setArtistID(artist1.getId());
            album.setId(albumRepository.findId() + 5);

            try {
                entityManager.persist(album);
            } catch(EntityExistsException e) {
                System.out.println("Obiectul album exista deja in baza de date.");
            }
            entityManager.getTransaction().commit();

            System.out.println("Album saved! " + album);
        }

        try {
            entityManager.getTransaction().commit();
        } catch (IllegalStateException e) {
            System.out.println("");
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}