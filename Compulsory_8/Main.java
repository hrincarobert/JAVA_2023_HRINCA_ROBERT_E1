
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Artists Artists = new Artists(connection);

        Artist artist = new Artist("The Beatles");
        Artists.addArtist(artist);

        List<Artist> artists = Artists.getAllArtists();
        for (Artist a : artists) {
            System.out.println(a.getName());
        }

        Genre Genre = new Genre(connection);
        List<Genre> genres = Genre.getAllGenres();
        System.out.println(genres);

        AlbumGenre albumGenre = new AlbumGenre(connection);
        List<Genre> albumGenres = new ArrayList<>();
        albumGenres.add(genres.get(0));
        albumGenres.add(genres.get(1));
        albumGenre.addGenresToAlbum(1, albumGenres);
    }
}
