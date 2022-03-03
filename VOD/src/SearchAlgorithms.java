import java.sql.SQLException;
import java.util.ArrayList;

public interface SearchAlgorithms {
    public ArrayList<Movie> searchMovieByName(String movieName) throws SQLException;

    public ArrayList<Movie> searchMovieByCategory(String category) throws SQLException;

    public ArrayList<Movie> searchMovieByPopularity(double popularity) throws SQLException;
}
