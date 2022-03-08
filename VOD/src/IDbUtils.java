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
    ArrayList<RegisteredUser> getAllRegisteredUsers() throws SQLException;
    String getWishlistUpdateStatus(User user) throws SQLException;
    void setWishListUpToDate(User user) throws SQLException;
    void updateWishlist(User user) throws SQLException;
    boolean checkUserPassword(User user, String password) throws SQLException;
    void setCreditCard(User user) throws SQLException;
    public void setIsAvailableStatus(Movie movie) throws SQLException;
}
