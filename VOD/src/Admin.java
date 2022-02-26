import java.sql.Array;
import java.util.Arrays;

public class Admin extends User{

    public void addMovie() {
        Movie newMovie = Movie.startBuild().
                movieTitle("title").
                category("action").
                year(2022).
                actors(new String[]{"first actor", "second actor", "third actor"}).
                isAvailable(true).
                popularity(5).
                build();
        Db.addMovie(newMovie);
    }

    public void removeMovie() {
    }

    public void editMovie() {

    }
    public void viewStatistics() {

    }
}
