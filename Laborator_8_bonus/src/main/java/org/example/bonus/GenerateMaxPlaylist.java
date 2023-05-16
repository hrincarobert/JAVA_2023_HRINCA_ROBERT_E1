package org.example.bonus;

import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.example.bonus.tables.Album;

public class GenerateMaxPlaylist {
    private Connection connection;
    private int maxAlbums;
    private int album_counter;
    private List<Integer> albumsUsedIds = new ArrayList<>();

    public GenerateMaxPlaylist(Connection connection, int maxAlbums) throws SQLException {
        this.connection = connection;
        this.maxAlbums = maxAlbums;
    }


    public List<List<Album>> generatePlaylist() throws SQLException {
        this.album_counter = 1;

        List<List<Album>> albumsByArtist = new ArrayList<>();

        List<Album> albumsOfArtist = new ArrayList<>();
        albumsOfArtist = getUnrelatedAlbums(4);
        albumsByArtist.add(albumsOfArtist);

        while (album_counter < maxAlbums) {
            String notInClause = albumsUsedIds.stream()
                    .map(id -> id.toString())
                    .collect(Collectors.joining(", ", "(", ")"));
            String query = "SELECT * FROM albums WHERE id NOT IN " + notInClause + " AND ROWNUM <= 1 ORDER BY id ASC";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            Album album = null;

            if (resultSet.next()) {
                album = new Album();
                album.setId(resultSet.getInt("id"));
                album.setReleaseYear(resultSet.getInt("release_year"));
                album.setTitle(resultSet.getString("title"));
                album.setArtistID(resultSet.getInt("artist_id"));
            }

            if(album == null)
                break;

            List<Album> newAlbumsOfArtist = getUnrelatedAlbums(album.getId());

            albumsByArtist.add(newAlbumsOfArtist);



            statement.close();
            resultSet.close();
        }

        return albumsByArtist;

    }


    private List<Album> getUnrelatedAlbums(int album_id) throws SQLException {
        List<Album> albumsOfArtist = new ArrayList<>();

        // create object ALBUM

        String query1 = "SELECT * FROM albums where id=" + album_id;
        PreparedStatement statement1 = connection.prepareStatement(query1);
        ResultSet resultSet1 = statement1.executeQuery();

        Album principalAlbum = null; // declare the principalAlbum object outside of the if statement

        if (resultSet1.next()) {
            principalAlbum = new Album();
            principalAlbum.setId(resultSet1.getInt("id"));
            principalAlbum.setReleaseYear(resultSet1.getInt("release_year"));
            principalAlbum.setTitle(resultSet1.getString("title"));
            principalAlbum.setArtistID(resultSet1.getInt("artist_id"));
        }

        albumsUsedIds.add(principalAlbum.getId());

        statement1.close();
        resultSet1.close();

        // all the albums

        String notInClause = albumsUsedIds.stream()
                .map(id -> id.toString())
                .collect(Collectors.joining(", ", "(", ")"));
        String query = "SELECT * FROM albums WHERE id NOT IN " + notInClause;
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<Album> albums = new ArrayList<>();
        while (resultSet.next()) {
            Album album = new Album();
            album.setId(resultSet.getInt("id"));
            album.setReleaseYear(resultSet.getInt(("release_year")));
            album.setTitle(resultSet.getString("title"));
            album.setArtistID(resultSet.getInt("artist_id"));
            albums.add(album);
        }

        statement.close();
        resultSet.close();

        albumsOfArtist.add(principalAlbum);

        List<Album> albumsOfArtistCopy = new ArrayList<>(albumsOfArtist);

        for (int i = 0; i < albumsOfArtistCopy.size(); i++) {
            Album album1 = albumsOfArtistCopy.get(i);
            for (Album album2 : albums) {
                if (areRelated(album1, album2) && !albumsOfArtist.contains(album2) ) {
                    albumsOfArtist.add(album2);
                    this.album_counter++;
                    albumsUsedIds.add(album2.getId());
                }
            }
        }

        return albumsOfArtist;
    }


    private boolean areRelated(Album album1, Album album2) throws SQLException {
         // Două albume sunt legate dacă îndeplinesc cel puțin una dintre următoarele condiții: sunt compuse de 
        // același artist sau au fost lansate în același an sau au cel puțin un gen comun.
        
        if(album1.getArtistID() == album2.getArtistID()) {
            System.out.println("Albumul " + album1.getId() + " si albumul " + album2.getId() + " au acelasi artist.");
            return false;
        }
        else if (album1.getReleaseYear() == album2.getReleaseYear()) {
            System.out.println("Albumul " + album1.getId() + " si albumul " + album2.getId() + " au acelasi an de lansare.");
            return false;
        } else {
            // Check if the two albums have at least one common genre
            String commonGenresQuery = "SELECT COUNT(*) FROM Album_genres WHERE album_id = ? AND genre_id IN (SELECT genre_id FROM Album_genres WHERE album_id = ?)";
            PreparedStatement commonGenresStatement = connection.prepareStatement(commonGenresQuery);
            commonGenresStatement.setInt(1, album1.getId());
            commonGenresStatement.setInt(2, album2.getId());
            ResultSet commonGenresResult = commonGenresStatement.executeQuery();
            if (commonGenresResult.next()) {
                int count = commonGenresResult.getInt(1);
                if (count > 0) {
                    commonGenresStatement.close();
                    commonGenresResult.close();
                    System.out.println("Albumul " + album1.getId() + " si albumul " + album2.getId() + " au genuri muzicale in comun.");
                    return false;
                }
            }

            commonGenresStatement.close();
            commonGenresResult.close();

            return true;
        }
    }
}
