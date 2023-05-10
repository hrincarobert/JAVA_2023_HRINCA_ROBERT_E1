package org.example.homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.homework.tables.Album;
import org.example.homework.tables.Genre;
import org.example.homework.tables.Artist;
import org.example.homework.DAO.ArtistDAO;
import org.example.homework.DAO.GenreDAO;
import org.example.homework.DAO.AlbumDAO;
import org.example.homework.DAO.AlbumGenresDAO;

public class AlbumImporter {
    private Connection connection;
    private String csvFile;
    private static final String ARTIST_QUERY = "SELECT id FROM artists WHERE name = ?";
    private static final String INSERT_ARTIST_QUERY = "INSERT INTO artists (name) VALUES (?)";
    private static final String INSERT_ALBUM_QUERY = "INSERT INTO albums (id, release_year, title, artist_id) VALUES (?, ?, ?, ?)";
    private static final String GENRE_QUERY = "SELECT id FROM genres WHERE name = ?";
    private static final String INSERT_GENRE_QUERY = "INSERT INTO genres (name) VALUES (?)";
    private static final String INSERT_ALBUM_GENRE_QUERY = "INSERT INTO album_genres (album_id, genre_id) VALUES (?, ?)";


    public AlbumImporter(Connection connection, String csvFile) {
        this.connection = connection;
        this.csvFile = csvFile;

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            br.readLine(); // skip header line

            while ((line = br.readLine()) != null) {

                // split the line into columns
                String[] columns = line.split(cvsSplitBy);

                ArtistDAO artistDAO = new ArtistDAO(connection);
                AlbumDAO albumDAO = new AlbumDAO(connection);
                GenreDAO genreDAO = new GenreDAO(connection);
                AlbumGenresDAO albumGenresDAO = new AlbumGenresDAO(connection);

                // check if the artist already exists
                int artistId = artistDAO.getArtistId(columns[3].trim());

                int album_id = artistDAO.getMaxArtistId() + 1;
                if (artistId == -1) {
                    // add the artist to the database and get the new id
                    System.out.println("Artistul " + columns[3].trim() + " NU exista in baza de date si este adaugat");
                    Artist newArtist = new Artist(artistDAO.getMaxArtistId() + 1, columns[3].trim());
                    artistDAO.insert(newArtist);
                }
                else
                    System.out.println("Artistul " + columns[3].trim() + " exista in baza de date si este adaugat");

                // check if the album already exists
                int albumID= albumDAO.getAlbumId(columns[2].trim());

                // create a new Album object
                Album album = new Album();

                if (albumID == -1) {
                    // add the album to the database and get the new id

                    album.setId(albumDAO.getMaxAlbumId() + 1);
                    album.setReleaseYear(Integer.parseInt(columns[1].trim()));
                    album.setTitle(columns[2].trim());
                    album.setArtistID(artistDAO.getArtistId(columns[3].trim()));

                    albumDAO.insert(album);
                    System.out.println("Am adaugat albumul cu numele " + album.getTitle() + " si id-ul" + album.getId() +"in baza de date");
                    albumID=album.getId();
                }
                else {
                    System.out.println("Albumul " + columns[2].trim() + " deja exista in baza de date");
                    albumID = album_id;
                }

                for (int i = 5; i < columns.length; i++) {
                    int genre_id = genreDAO.getMaxGenreId() + 1;

                    String genre = columns[i].trim();
                    if (genre.startsWith("\"")) {
                        genre = genre.substring(1);
                    }
                    if (genre.endsWith("\"")) {
                        genre = genre.substring(0, genre.length() - 1);
                    }
                    System.out.println("Coloana " + (i + 1) + ": <" + genre + ">");

                    int genreId = genreDAO.getGenretId(genre);
                    System.out.println("Genul muzical " + genre + " are id-ul " + genreId);
                    if (genreId == -1) {
                        // add the genre to the database and get the new id
                        Genre newGenre = new Genre( genre_id, genre);
                        genreDAO.insert(newGenre);
                        System.out.println("Am adaugat genul muzical  " + genre + " in baza de date");
                        genreId = genre_id;
                    }
                    else
                        System.out.println("Genul muzical " + genre + " deja exista in baza de date");


                    System.out.println("Album id: " + albumID + " genre id: " + genreId);
                    albumGenresDAO.insert(albumID, genreId);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }


}
