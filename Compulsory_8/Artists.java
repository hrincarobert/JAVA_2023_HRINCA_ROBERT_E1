import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Artists {
    private final Connection connection;

    public Artists(Connection connection) {
        this.connection = connection;
    }

    public List<Artist> getAllArtists() throws SQLException {
        List<Artist> artists = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM artists")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Artist artist = new Artist(id, name);
                artists.add(artist);
            }
        }
        return artists;
    }

    public void addArtist(Artist artist) throws SQLException {
        String sql = "INSERT INTO artists (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, artist.getName());
            statement.executeUpdate();
        }
    }
}
