import java.util.ArrayList;
import java.util.Iterator;

public class MoviesCollection implements IMoviesCollection, SearchAlgorithms {
    ArrayList<Movie> movies;

    public MoviesCollection() {
        this.movies = new ArrayList<Movie>();;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public Iterator<Movie> getIterator() {
        Iterator<Movie> it = movies.iterator();
        return it;
    }

    public ArrayList<Movie> searchMovieByName(String movieName) {
        ArrayList<Movie> moviesResults = new ArrayList<Movie>();;
        Iterator<Movie> it = getIterator();
        Movie movie;
        while (it.hasNext()) {
            movie = it.next();
            if (movie.getTitle().equals(movieName)) {
                moviesResults.add(movie);
            }
        }
        return moviesResults;
    }

    public ArrayList<Movie> searchMovieByCategory(String category) {
        ArrayList<Movie> moviesResults = new ArrayList<Movie>();;
        Iterator<Movie> it = getIterator();
        Movie movie;
        while (it.hasNext()) {
            movie = it.next();
            if (movie.getCategory().equals(category)) {
                moviesResults.add(movie);
            }
        }
        return moviesResults;
    }

    public ArrayList<Movie> searchMovieByPopularity(double popularity) {
        ArrayList<Movie> moviesResults = new ArrayList<Movie>();;
        Iterator<Movie> it = getIterator();
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
