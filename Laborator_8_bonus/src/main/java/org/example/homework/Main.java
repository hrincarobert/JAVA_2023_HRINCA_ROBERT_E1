package org.example.homework;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.example.homework.DAO.ArtistDAO;
import org.example.homework.tables.Artist;
import org.example.homework.DAO.AlbumDAO;
import org.example.homework.tables.Album;
import org.example.homework.DAO.GenreDAO;
import org.example.homework.tables.Genre;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public class Main {
    public static void main(String[] args) {

        Connection connection = null;

        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass("oracle.jdbc.driver.OracleDriver");
            cpds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
            cpds.setUser("music");
            cpds.setPassword("MUSIC");

            connection = cpds.getConnection();


            // ARTIST EX

//            ArtistDAO artistDAO = new ArtistDAO(connection);
//
//            List<Artist> artists = artistDAO.select();
//
//            System.out.println("All artists (BEFORE INSERTION) :");
//            for (Artist artist : artists) {
//                System.out.println("\t" + artist);
//            }
//
//            Artist artist_test = new Artist(10000, "ARTIST_TEST");
//            artistDAO.insert(artist_test);
//
//            artists = artistDAO.select();
//
//            System.out.println("\nAll artists (AFTER INSERTION) :");
//            for (Artist artist : artists) {
//                System.out.println("\t" + artist);
//            }
//
//            artistDAO.delete(artist_test);
//
//            artists = artistDAO.select();
//
//            System.out.println("\nAll artists (AFTER DELETION) :");
//            for (Artist artist : artists) {
//                System.out.println("\t" + artist);
//            }


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
//            Genre genre_test = new Genre(10000, "GENRE_TEST");
//
//            genreDAO.insert(genre_test);
//
//            genres = genreDAO.select();
//
//            System.out.println("\nAll genres (AFTER INSERTION) :");
//            for (Genre genre : genres) {
//                System.out.println("\t" + genre);
//            }
//
//            genreDAO.delete(genre_test);
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
//            Album album_test = new Album(10000, 1973, "ALBUM_TEST", 4);
//            albumDAO.insert(album_test);
//
//            albums = albumDAO.select();
//
//            System.out.println("\nAll albums (AFTER INSERTION) :");
//            for (Album album : albums) {
//                System.out.println("\t" + album);
//            }
//
//            albumDAO.delete(album_test);
//
//            albums = albumDAO.select();
//
//            System.out.println("\nAll albums (AFTER DELETION) :");
//            for (Album album : albums) {
//                System.out.println("\t" + album);
//            }

            AlbumImporter album_nou = new AlbumImporter(connection, "Laborator_8/albumlist.csv");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}