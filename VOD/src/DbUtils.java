import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbUtils implements IDbUtils {
    MySqlDB vodDb;

    DbUtils() {
        this.vodDb = MySqlDB.getVodInstance();
    }

    public String login(String username, String password) throws SQLException {
        String query = "SELECT * FROM accounts where userId = ?";
        ResultSet result = vodDb.fetch(query, username);

        while(result.next()) {
            String retrievedPassword = result.getString("password");
            if (retrievedPassword.equals(password)) {
                return "successful";
            }
        }
        return null;
    }

    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<Movie>();
        String query = "SELECT * FROM movies";
        ResultSet result = vodDb.fetch(query);
        while(result.next()) {
            String movieTitle = result.getString("title");
            int year = result.getInt("year");
            Array actorsArr = result.getArray("actors");
            String[] actors = (String[]) actorsArr.getArray();
            boolean isAvailable = result.getBoolean("isAvailable");
            Movie movie = new Movie(movieTitle, year, actors, isAvailable);
            movies.add(movie);
        }
        return movies;
    }

    public List<Movie> getAvailableMovies() throws SQLException {
        List<Movie> movies = new ArrayList<Movie>();
        String query = "SELECT * FROM movies where isAvailable = 1";
        ResultSet result = vodDb.fetch(query);
        while(result.next()) {
            String movieTitle = result.getString("title");
            int year = result.getInt("year");
            Array actorsArr = result.getArray("actors");
            String[] actors = (String[]) actorsArr.getArray();
            boolean isAvailable = result.getBoolean("isAvailable");
            Movie movie = new Movie(movieTitle, year, actors, isAvailable);
            movies.add(movie);
        }
        return movies;
    }

    public void addMovie(String title, int year, String[] actors, boolean isAvailable) throws SQLException {
        //String query = "INSERT INTO movies (title, year, actors, isAvailable) VALUES ('ds',32,'[\"shaked\"]',true)";
        String query = "INSERT INTO movies (title, year, actors, isAvailable) VALUES ('ds',32,'\"shaked\",\"ds\"',true)";
        vodDb.fetch(query);
        //String query = "INSERT INTO movies (title, year, actors, isAvailable) VALUES (?,?,?,?)";
        //vodDb.fetch(query, title, year, actors, isAvailable);

    }


}
