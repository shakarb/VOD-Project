import java.util.ArrayList;
import java.util.List;

public class Movie {
    String title;
    String category;
    int year;
    String[] actors;
    boolean isAvailable;
    double popularity;

    public Movie(String movieTitle, int year, String[] actors,
                 boolean isAvailable,  double inc_popularity) {

        this.title = movieTitle;
        this.year = year;
        this.actors = actors;
        this.isAvailable = isAvailable;
        this.popularity = inc_popularity;
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
    public void removeFromAvailables() {
        setIsAvailable(false);
    }

}