package org.example.compulsory;

import org.example.compulsory.Artist;
import org.example.compulsory.ArtistDAO;
import org.example.compulsory.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();

            // ARTIST EX

            ArtistDAO artistDAO = new ArtistDAO(connection);

            List<Artist> artists = artistDAO.select();

            System.out.println("All artists (BEFORE INSERTION) :");
            for (Artist artist : artists) {
                System.out.println("\t" + artist);
            }

            Artist queen = new Artist(4, "Queen");
            artistDAO.insert(queen);

            artists = artistDAO.select();

            System.out.println("\nAll artists (AFTER INSERTION) :");
            for (Artist artist : artists) {
                System.out.println("\t" + artist);
            }

            artistDAO.delete(queen);

            artists = artistDAO.select();

            System.out.println("\nAll artists (AFTER DELETION) :");
            for (Artist artist : artists) {
                System.out.println("\t" + artist);
            }


            // GENRE EX

//            System.out.println("\nGENRE");
//
//            GenreDAO genreDAO = new GenreDAO(connection);
//
//            List<Genre> genres = genreDAO.select();
//
//            System.out.println("All genres (BEFORE INSERTION) :");
//            for (Genre genre : genres) {
//                System.out.println("\t" + genre);
//            }
//
//            Genre trap = new Genre(6, "Trap");
//            genreDAO.insert(trap);
//
//            genres = genreDAO.select();
//
//            System.out.println("\nAll genres (AFTER INSERTION) :");
//            for (Genre genre : genres) {
//                System.out.println("\t" + genre);
//            }
//
//            genreDAO.delete(trap);
//
//            genres = genreDAO.select();
//
//            System.out.println("\nAll genres (AFTER DELETION) :");
//            for (Genre genre : genres) {
//                System.out.println("\t" + genre);
//            }

            // ALBUM EX

//            System.out.println("\nALBUM");
//
//            AlbumDAO albumDAO = new AlbumDAO(connection);
//
//            List<Album> albums = albumDAO.select();
//
//            System.out.println("All albums (BEFORE INSERTION) :");
//            for (Album album : albums) {
//                System.out.println("\t" + album);
//            }
//
//            Album queen = new Album(4, 1973, "Queen", 4);
//            albumDAO.insert(queen);
//
//            albums = albumDAO.select();
//
//            System.out.println("\nAll albums (AFTER INSERTION) :");
//            for (Album album : albums) {
//                System.out.println("\t" + album);
//            }
//
//            albumDAO.delete(queen);
//
//            albums = albumDAO.select();
//
//            System.out.println("\nAll albums (AFTER DELETION) :");
//            for (Album album : albums) {
//                System.out.println("\t" + album);
//            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}