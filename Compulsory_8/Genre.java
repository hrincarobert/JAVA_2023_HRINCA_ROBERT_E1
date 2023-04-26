package org.example;
package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Genre {
    private final Connection connection;

    public Genre(Connection connection) {
        this.connection = connection;
    }

    public List<Genre> getAllGenres() throws SQLException {
        String query = "SELECT * FROM genres";
        List<Genre> genres = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt("id"));
                genre.setName(resultSet.getString("name"));
                genres.add(genre);
            }
        }

        return genres;
    }
}

public class AlbumGenre {
    private final Connection connection;

    public AlbumGenre(Connection connection) {
        this.connection = connection;
    }

    public void addGenresToAlbum(int albumId, List<Genre> genres) throws SQLException {
        String query = "INSERT INTO album_genres (album_id, genre_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Genre genre : genres) {
                statement.setInt(1, albumId);
                statement.setInt(2, genre.getId());
                statement.executeUpdate();
            }
        }
    }
}
