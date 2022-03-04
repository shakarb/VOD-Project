import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbUtils implements IDbUtils {
    PostgreSqlDB vodDb;

    DbUtils() {
        this.vodDb = PostgreSqlDB.getVodInstance();
    }

    public User login(String userId, String password) throws SQLException {
        String query = "SELECT * FROM accounts where user_id = ?";
        ResultSet result = vodDb.fetch(query, userId);

        while(result.next()) {
            String retrievedPassword = result.getString("password");
            boolean isAdmin = result.getBoolean("is_admin");
            if (retrievedPassword.equals(password)) {
                // mark user as active
                query = "UPDATE public.accounts SET is_active=? where user_id = ?";
                vodDb.fetch(query, true, userId);

                if (isAdmin) {
                    return new Admin();
                } else {
                    User registeredUser = getUserDetails(userId);
                    return registeredUser;
                }
            }
        }
        return null;
    }

    public void logout(User user) throws SQLException {
        String userId = user.getId();
        String query = "UPDATE accounts SET is_active = ? where user_id = ?";
        vodDb.fetch(query, false, userId);
    }

    public void register(String userId, String password, String name,String email,
                           String phoneNumber, String[] favoritesMovies) throws SQLException {

        String query = "INSERT INTO accounts VALUES (?,?)";
        vodDb.fetch(query, userId, password);

        query = "INSERT INTO user_details VALUES (?,?,?,?,?)";
        vodDb.fetch(query, userId, name, email, phoneNumber, favoritesMovies);
    }

    public RegisteredUser getUserDetails(String userId) throws SQLException {
        String query = "SELECT * FROM user_details where user_id = ?";
        ResultSet result = vodDb.fetch(query, userId);
        result.next();
        String email = result.getString("email");
        String phoneNumber = result.getString("phone_number");
        Array favoritesArr = result.getArray("favorites_movies");
        String[] favoritesMovies = (String[]) favoritesArr.getArray();
        return new RegisteredUser(email, phoneNumber, favoritesMovies);
    }

    public ArrayList<Movie> getAllMovies() throws SQLException {
        ArrayList<Movie> moviesList = new ArrayList<Movie>();
        String query = "SELECT * FROM movies";
        ResultSet result = vodDb.fetch(query);
        while(result.next()) {
            String movieTitle = result.getString("title");
            Array categoriesArr = result.getArray("category");
            String[] category = (String[]) categoriesArr.getArray();
            int year = result.getInt("year");
            Array actorsArr = result.getArray("actors");
            String[] actors = (String[]) actorsArr.getArray();
            boolean isAvailable = result.getBoolean("is_available");
            int popularity = result.getInt("popularity");
            double runningTime = result.getDouble("running_time");
            int price = result.getInt("price");

            // build movie
            Movie newMovie = Movie.startBuild().
                    movieTitle(movieTitle).
                    category(category).
                    year(year).
                    actors(actors).
                    isAvailable(isAvailable).
                    popularity(popularity).
                    runningTime(runningTime).
                    price(price).
                    build();

            moviesList.add(newMovie);
        }
        return moviesList;
    }

    public ArrayList<Movie> getAvailableMovies() throws SQLException {
        ArrayList<Movie> moviesList = new ArrayList<Movie>();
        String query = "SELECT * FROM movies where is_available = true";
        ResultSet result = vodDb.fetch(query);
        while(result.next()) {
            String movieTitle = result.getString("title");
            Array categoriesArr = result.getArray("category");
            String[] category = (String[]) categoriesArr.getArray();
            int year = result.getInt("year");
            Array actorsArr = result.getArray("actors");
            String[] actors = (String[]) actorsArr.getArray();
            boolean isAvailable = result.getBoolean("is_available");
            int popularity = result.getInt("popularity");
            double runningTime = result.getDouble("running_time");
            int price = result.getInt("price");

            // build movie
            Movie newMovie = Movie.startBuild().
                    movieTitle(movieTitle).
                    category(category).
                    year(year).
                    actors(actors).
                    isAvailable(isAvailable).
                    popularity(popularity).
                    runningTime(runningTime).
                    price(price).
                    build();

            moviesList.add(newMovie);
        }
        return moviesList;
    }

    public void addMovie(Movie movie) throws SQLException {
        String title = movie.getTitle();
        String[] category = movie.getCategory();
        int year = movie.getYear();
        String actors[] = movie.getActors();
        boolean isAvailable = movie.isAvailable();
        double popularity = movie.getPopularity();
        double runningTime = movie.getRunningTime();
        int price = movie.getPrice();

        String query = "INSERT INTO movies (title, category, year, actors, is_available, popularity," +
                "running_time, price) VALUES (?,?,?,?,?,?,?,?)";
        vodDb.fetch(query, title, category, year, actors, isAvailable, popularity);
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        ArrayList<Order> ordersList = new ArrayList<Order>();
        String query = "SELECT * FROM orders";
        ResultSet result = vodDb.fetch(query);
        while(result.next()) {
            String userId = result.getString("user_id");
            int totalPayment = result.getInt("total_payment");
            String timeOrderMade = result.getString("time_order_made");

            String movieTitle = result.getString("title");
            Array categoriesArr = result.getArray("category");
            String[] category = (String[]) categoriesArr.getArray();
            int year = result.getInt("year");
            Array actorsArr = result.getArray("actors");
            String[] actors = (String[]) actorsArr.getArray();
            boolean isAvailable = result.getBoolean("is_available");
            int popularity = result.getInt("popularity");
            double runningTime = result.getDouble("running_time");
            int price = result.getInt("price");

            // build movie
            Movie newMovie = Movie.startBuild().
                    movieTitle(movieTitle).
                    category(category).
                    year(year).
                    actors(actors).
                    isAvailable(isAvailable).
                    popularity(popularity).
                    runningTime(runningTime).
                    price(price).
                    build();


            //Order newOrder = new Order(userId, newMovie, totalPayment, timeOrderMade);
            //ordersList.add(newOrder);
        }
        return ordersList;
    }

    public ArrayList<Order> getUserOrders(String userId) throws SQLException {
        ArrayList<Order> ordersList = new ArrayList<Order>();
        String query = "SELECT * FROM orders INNER JOIN movies ON orders.movie_name = movies.title" +
                "where orders.user_id = ?";
        ResultSet result = vodDb.fetch(query, userId);
        while (result.next()) {
            int totalPayment = result.getInt("total_payment");
            String timeOrderMade = result.getString("time_order_made");

            String movieTitle = result.getString("title");
            Array categoriesArr = result.getArray("category");
            String[] category = (String[]) categoriesArr.getArray();
            int year = result.getInt("year");
            Array actorsArr = result.getArray("actors");
            String[] actors = (String[]) actorsArr.getArray();
            boolean isAvailable = result.getBoolean("is_available");
            int popularity = result.getInt("popularity");
            double runningTime = result.getDouble("running_time");
            int price = result.getInt("price");

            // build movie
            Movie newMovie = Movie.startBuild().
                    movieTitle(movieTitle).
                    category(category).
                    year(year).
                    actors(actors).
                    isAvailable(isAvailable).
                    popularity(popularity).
                    runningTime(runningTime).
                    price(price).
                    build();


            //Order newOrder = new Order(userId, newMovie, totalPayment, timeOrderMade);
            //ordersList.add(newOrder);
        }
        return ordersList;
    }

    public void addOrder(Order order) throws SQLException {
        String userId = order.getUserId();
        String movieName = order.getMovie().getTitle();
        int totalPayment = order.getTotalPayment();
        String timeOrderMade = order.getTimeOrderMade();
        String query = "INSERT INTO orders (user_id, movie_name, total_payment, time_order_made) " +
                "VALUES (?,?,?,?)";
        vodDb.fetch(query, userId, movieName, totalPayment, timeOrderMade);
    }
}
