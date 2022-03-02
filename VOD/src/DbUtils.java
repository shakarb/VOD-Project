import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbUtils implements IDbUtils {
    PostgreSqlDB vodDb;

    DbUtils() {
        this.vodDb = PostgreSqlDB.getVodInstance();
    }

    public String login(int user_id, String password) throws SQLException {
        String query = "SELECT * FROM users where user_id = ?";
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

    public void addMovie(String title, String category, int year, String[] actors,
                         boolean isAvailable, double popularity) throws SQLException {

        //vodDb.fetch(query);
        String query = "INSERT INTO movies (title, category, year, actors, is_available, popularity) " +
                "VALUES (?,?,?,?,?,?)";
        vodDb.fetch(query, title, category, year, actors, isAvailable, popularity);
    }

    public void addMovie(Movie movie){
        //TODO: parse with get methods.
    }


}
