import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Admin extends User{

    public void addNewMovie() {
        Movie newMovie = Movie.startBuild().
                movieTitle("title").
                category("action").
                year(2022).
                actors(new String[]{"first actor", "second actor", "third actor"}).
                isAvailable(true).
                popularity(5).
                build();
        this.getDb().addMovie(newMovie);
    }

    public void addMovieToAvailables(Movie movie){
        movie.addToAvailables();
    }

    public void removeMovieFromAvailables(Movie movie) {
        movie.removeFromAvailables();
    }

    public void editMovie() {
        //what can the admin edit here?
    }

    public void viewStatistics() {
        try{
            SortingStrategy s = new sortByPopularity();
            s.sort(getDb().getAllMovies());
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
