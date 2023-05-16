package org.example.bonus.DAO;


import org.example.bonus.tables.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        statement.close();
        resultSet.close();

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

        statement.close();
    }

    public void delete(Album album) throws SQLException {
        String query = "DELETE FROM albums WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, album.getId());
        statement.executeUpdate();

        statement.close();
    }

    public int getAlbumId(String albumName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM artists WHERE name = ?");
        statement.setString(1, albumName);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            statement.close();
            resultSet.close();
            return resultSet.getInt("id");
        } else {
            statement.close();
            resultSet.close();
            return -1; // artist not found
        }
    }

    public int getMaxAlbumId() throws SQLException {
        String query = "SELECT MAX(id) AS max_id FROM albums";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            statement.close();
            resultSet.close();
            return resultSet.getInt("max_id");
        }

        statement.close();
        resultSet.close();
        return 0;
    }
}
