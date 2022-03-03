import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public interface IMoviesCollection {
    public void addMovie(Movie movie) throws SQLException;

    public ArrayList<Movie> getAllMovies() throws SQLException;

    public ArrayList<Movie> getAvailableMovies() throws SQLException;

    public Iterator<Movie> getIteratorAllMovies() throws SQLException;

    public Iterator<Movie> getIteratorAvailableMovies() throws SQLException;
}
