package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Albums {
    private final Connection connection;

    public Albums(Connection connection) {
        this.connection = connection;
    }

    public List<Album> getAllAlbums() throws SQLException {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM albums";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int releaseYear = resultSet.getInt("release_year");
                String title = resultSet.getString("title");
                String artist = resultSet.getString("artist");
                List<String> genres = getGenresForAlbum(id);
                Album album = new Album(id, releaseYear, title, artist, genres);
                albums.add(album);
            }
        }
        return albums;
    }

    public void addAlbum(Album album) throws SQLException {
        String sql = "INSERT INTO albums (release_year, title, artist) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, album.getReleaseYear());
            statement.setString(2, album.getTitle());
            statement.setString(3, album.getArtist());
            statement.executeUpdate();

            int albumId = getAlbumId(album);
            addAlbumGenres(albumId, album.getGenres());
        }
    }

    private int getAlbumId(Album album) throws SQLException {
        String sql = "SELECT id FROM albums WHERE release_year = ? AND title = ? AND artist = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, album.getReleaseYear());
            statement.setString(2, album.getTitle());
            statement.setString(3, album.getArtist());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        throw new SQLException("Could not retrieve album ID.");
    }