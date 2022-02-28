import java.util.ArrayList;

public interface SearchAlgorithms {
    public ArrayList<Movie> searchMovieByName(String movieName);

    public ArrayList<Movie> searchMovieByCategory(String category);

    public ArrayList<Movie> searchMovieByPopularity(double popularity);
}
