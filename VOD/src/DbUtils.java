import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtils implements IDbUtils {
    PostgreSqlDB vodDb;

    DbUtils() {
        this.vodDb = PostgreSqlDB.getVodInstance();
    }

    public String login(int user_id, String password) throws SQLException {
        String query = "SELECT * FROM accounts where user_id = ?";
        ResultSet result = vodDb.fetch(query, user_id);

        while(result.next()) {
            String retrievedPassword = result.getString("password");
            if (retrievedPassword.equals(password)) {
                return "successful";
            }
        }
        return "failed";
    }

    public IMoviesCollection getAllMovies() throws SQLException {
        IMoviesCollection moviesCollection = new MoviesCollection();
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
            double popularity = result.getDouble("popularity");
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

            moviesCollection.addMovie(newMovie);
        }
        return moviesCollection;
    }

    public IMoviesCollection getAvailableMovies() throws SQLException {
        IMoviesCollection moviesCollection = new MoviesCollection();
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
            double popularity = result.getDouble("popularity");
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

            moviesCollection.addMovie(newMovie);
        }
        return moviesCollection;
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

    public IOrdersCollection getAllOrders() throws SQLException {
        IOrdersCollection ordersCollection = new OrdersCollection();
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
            double popularity = result.getDouble("popularity");
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


            Order newOrder = new Order(userId, newMovie, totalPayment, timeOrderMade);
            ordersCollection.addOrder(newOrder);
        }
        return ordersCollection;
    }

    public IOrdersCollection getUserOrders(String userId) throws SQLException {
        IOrdersCollection ordersCollection = new OrdersCollection();
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
            double popularity = result.getDouble("popularity");
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

            Order newOrder = new Order(userId, newMovie, totalPayment, timeOrderMade);
            ordersCollection.addOrder(newOrder);
        }
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
