import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDbUtils {
    String login(int userid, String password) throws SQLException;
    IMoviesCollection getAllMovies() throws SQLException;
    IMoviesCollection getAvailableMovies() throws SQLException;
    void addMovie(String title, String category, int year, String[] actors,
                  boolean isAvailable, double popularity) throws SQLException;
}
