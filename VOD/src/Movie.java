import java.util.ArrayList;
import java.util.List;

public class Movie {
    String title;
    String year;
    List<String> actors = new ArrayList<>();
    boolean isAvailable;


    public Movie(String movieTitle, String year, List<String> actors,
                 boolean isAvailable) {
        this.title = movieTitle;
        this.year = year;
        this.actors = actors;
        this.isAvailable = isAvailable;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void addToAvailables() {
        setIsAvailable(true);
    }

}