import java.util.Iterator;

public interface IMoviesCollection {
    public void addMovie(Movie movie);

    public void removeMovie(Movie movie);

    public Iterator<Movie> getIterator();
}
