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




            System.out.println("\nGENRE");

            GenreDAO genreDAO = new GenreDAO(connection);

            List<Genre> genres = genreDAO.select();

            System.out.println("All genres (BEFORE INSERTION) :");
            for (Genre genre : genres) {
                System.out.println("\t" + genre);
            }

            Genre genre_test = new Genre(10000, "GENRE_TEST");

            genreDAO.insert(genre_test);

            genres = genreDAO.select();

            System.out.println("\nAll genres (AFTER INSERTION) :");
            for (Genre genre : genres) {
                System.out.println("\t" + genre);
            }

            genreDAO.delete(genre_test);

            genres = genreDAO.select();

            System.out.println("\nAll genres (AFTER DELETION) :");
            for (Genre genre : genres) {
                System.out.println("\t" + genre);
            }


            AlbumImporter album_nou = new AlbumImporter(connection, "C:/Users/Robert/IdeaProjects/tema8/albumlist.csv");

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