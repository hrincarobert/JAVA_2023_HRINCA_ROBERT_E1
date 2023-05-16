package org.example.compulsory;

import java.util.ArrayList;
import java.util.List;

import org.example.compulsory.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistDAO {
    private Connection connection;

    public ArtistDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Artist> select() throws SQLException {
        String query = "SELECT * FROM artists";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<Artist> artists = new ArrayList<>();
        while (resultSet.next()) {
            Artist artist = new Artist();
            artist.setId(resultSet.getInt("id"));
            artist.setName(resultSet.getString("name"));
            artists.add(artist);
        }

        return artists;
    }


    public void insert(Artist artist) throws SQLException {
        String query = "INSERT INTO artists (id, name) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, artist.getId());
        statement.setString(2, artist.getName());
        statement.executeUpdate();

//        // commit the transaction
//        connection.commit();
    }

    public void update(Artist artist) throws SQLException {
        String query = "UPDATE artists SET name = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, artist.getName());
        statement.setInt(2, artist.getId());
        statement.executeUpdate();
    }

    public void delete(Artist artist) throws SQLException {
        String query = "DELETE FROM artists WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, artist.getId());
        statement.executeUpdate();
    }

//    public Artist findById(int id) throws SQLException {
//        String query = "SELECT * FROM artists WHERE id = ?";
//        PreparedStatement statement = connection.prepareStatement(query);
//        statement.setInt(1, id);
//        ResultSet resultSet = statement.executeQuery();
//
//        if (resultSet.next()) {
//            Artist artist = new Artist();
//            artist.setId(resultSet.getInt("id"));
//            artist.setName(resultSet.getString("name"));
//            return artist;
//        }
//
//        return null;
//    }
}
