import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MoviesCollection implements IMoviesCollection, SearchAlgorithms {
    private IDbUtils db;
    ArrayList<Movie> movies = new ArrayList<Movie>();
    ArrayList<Movie> availableMovies = new ArrayList<Movie>();

    public MoviesCollection() {
        this.db = new DbUtils();
    }

    public void addMovie(Movie movie) throws SQLException{
        db.addMovie(movie);
    }

    public ArrayList<Movie> getAllMovies() throws SQLException {
        movies = db.getAllMovies();
        return movies;
    }

    public ArrayList<Movie> getAvailableMovies() throws SQLException {
        availableMovies = db.getAvailableMovies();
        return availableMovies;
    }

    public Iterator<Movie> getIteratorAllMovies() throws SQLException {
        if (movies.isEmpty()) {
            getAllMovies();
        }
        Iterator<Movie> it = movies.iterator();
        return it;
    }

    public Iterator<Movie> getIteratorAvailableMovies() throws SQLException{
        if (availableMovies.isEmpty()) {
            getAvailableMovies();
        }
        Iterator<Movie> it = availableMovies.iterator();
        return it;
    }

    public ArrayList<Movie> searchMovieByName(String movieName) throws SQLException {
        ArrayList<Movie> moviesResults = new ArrayList<Movie>();;
        getAvailableMovies();
        Iterator<Movie> it = getIteratorAvailableMovies();
        Movie movie;
        while (it.hasNext()) {
            movie = it.next();
            if (movie.getTitle().equals(movieName)) {
                moviesResults.add(movie);
                break;
            }
        }
        return moviesResults;
    }

    public ArrayList<Movie> searchMovieByCategory(String catgegory) throws SQLException {
        ArrayList<Movie> moviesResults = new ArrayList<Movie>();;
        Iterator<Movie> it = getIteratorAvailableMovies();
        Movie movie;
        while (it.hasNext()) {
            movie = it.next();
            List categoriesList = Arrays.asList(movie.getCategory());
            if (categoriesList.contains(catgegory)) {
                moviesResults.add(movie);
            }
        }
        return moviesResults;
    }

    public ArrayList<Movie> searchMovieByPopularity(double popularity) throws SQLException {
        ArrayList<Movie> moviesResults = new ArrayList<Movie>();;
        Iterator<Movie> it = getIteratorAvailableMovies();
        Movie movie;
        while (it.hasNext()) {
            movie = it.next();
            if (movie.getPopularity() >= popularity) {
                moviesResults.add(movie);
            }
        }
        return moviesResults;
    }
}
