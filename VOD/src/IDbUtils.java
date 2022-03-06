import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IDbUtils {
    User login(String userid, String password) throws SQLException;
    void logout(User user) throws SQLException;
    void register(String userId, String password, String name, String email, String phoneNumber) throws SQLException;
    ArrayList<Movie> getAllMovies() throws SQLException;
    ArrayList<Movie> getAvailableMovies() throws SQLException;
    void addMovie(Movie movie) throws SQLException;
    ArrayList<Order> getAllOrders() throws SQLException;
    ArrayList<Order> getUserOrders(String userId) throws SQLException;
    void addOrder(Order order) throws SQLException;
}
