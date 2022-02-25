import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDbUtils {
    String login(int userid, String password) throws SQLException;
    List<Movie> getAllMovies() throws SQLException;
    List<Movie> getAvailableMovies() throws SQLException;
    void addMovie(String title, String category, int year, String[] actors,
                  boolean isAvailable, double popularity) throws SQLException;
}
