import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDbUtils {
    String login(int userid, String password) throws SQLException;
    IMoviesCollection getAllMovies() throws SQLException;
    IMoviesCollection getAvailableMovies() throws SQLException;
    void addMovie(Movie movie) throws SQLException;
    IOrdersCollection getAllOrders() throws SQLException;
    IOrdersCollection getUserOrders(String userId) throws SQLException;
    void addOrder(Order order) throws SQLException;
}
