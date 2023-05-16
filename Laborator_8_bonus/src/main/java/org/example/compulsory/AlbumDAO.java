package org.example.compulsory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.compulsory.Album;

public class AlbumDAO {
    private Connection connection;

    public AlbumDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Album> select() throws SQLException {
        String query = "SELECT * FROM albums";
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

        return albums;
    }


    public void insert(Album album) throws SQLException {
        String query = "INSERT INTO albums (id, release_year, title, artist_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, album.getId());
        statement.setInt(2, album.getReleaseYear());
        statement.setString(3, album.getTitle());
        statement.setInt(4, album.getArtistID());
        statement.executeUpdate();
    }

    public void delete(Album album) throws SQLException {
        String query = "DELETE FROM albums WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, album.getId());
        statement.executeUpdate();
    }
}
