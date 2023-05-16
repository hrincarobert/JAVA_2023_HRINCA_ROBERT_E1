package org.example.compulsory;

import org.example.compulsory.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private Connection connection;

    public GenreDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Genre> select() throws SQLException {
        String query = "SELECT * FROM genres";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<Genre> genres = new ArrayList<>();
        while (resultSet.next()) {
            Genre genre = new Genre();
            genre.setId(resultSet.getInt("id"));
            genre.setName(resultSet.getString("name"));
            genres.add(genre);
        }

        return genres;
    }


    public void insert(Genre genre) throws SQLException {
        String query = "INSERT INTO genres (id, name) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, genre.getId());
        statement.setString(2, genre.getName());
        statement.executeUpdate();
    }

    public void update(Genre genre) throws SQLException {
        String query = "UPDATE genres SET name = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, genre.getName());
        statement.setInt(2, genre.getId());
        statement.executeUpdate();
    }

    public void delete(Genre genre) throws SQLException {
        String query = "DELETE FROM genres WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, genre.getId());
        statement.executeUpdate();
    }
}
