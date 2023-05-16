package org.example.bonus.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumGenresDAO {
    private Connection connection;

    public AlbumGenresDAO(Connection connection) {
        this.connection = connection;
    }
//
//    public List<Album> select() throws SQLException {
//        String query = "SELECT * FROM albums";
//        PreparedStatement statement = connection.prepareStatement(query);
//        ResultSet resultSet = statement.executeQuery();
//
//        List<Album> albums = new ArrayList<>();
//        while (resultSet.next()) {
//            Album album = new Album();
//            album.setId(resultSet.getInt("id"));
//            album.setReleaseYear(resultSet.getInt(("release_year")));
//            album.setTitle(resultSet.getString("title"));
//            album.setArtistID(resultSet.getInt("artist_id"));
//            albums.add(album);
//        }
//
//        return albums;
//    }


    public void insert(int albumID, int genreID) throws SQLException {
        String query = "INSERT INTO album_genres (album_id, genre_id) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, albumID);
        statement.setInt(2, genreID);
        statement.executeUpdate();

        statement.close();
    }

//    public void delete(Album album) throws SQLException {
//        String query = "DELETE FROM albums WHERE id = ?";
//        PreparedStatement statement = connection.prepareStatement(query);
//        statement.setInt(1, album.getId());
//        statement.executeUpdate();
//    }

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
