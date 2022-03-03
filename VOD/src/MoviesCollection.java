import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MoviesCollection implements IMoviesCollection, SearchAlgorithms {
    private ArrayList<Movie> movies;

    public MoviesCollection() {
        this.movies = new ArrayList<Movie>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
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
                return moviesResults;
            }
        }
        return null;
    }

    public ArrayList<Movie> searchMovieByCategory(String catgegory) {
        ArrayList<Movie> moviesResults = new ArrayList<Movie>();;
        Iterator<Movie> it = getIterator();
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
