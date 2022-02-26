import java.util.ArrayList;
import java.util.List;

public class Movie {
    String title;
    String category;
    int year;
    String[] actors;
    boolean isAvailable;
    double popularity;

    public Movie(String movieTitle, String category, int year, String[] actors,
                 boolean isAvailable,  double inc_popularity) {
        this.title = movieTitle;
        this.category = category;
        this.year = year;
        this.actors = actors;
        this.isAvailable = isAvailable;
        this.popularity = inc_popularity;
    }

    public Movie(MovieBuilder builder){
        this.title = builder.getTitle();
        this.category = builder.getCategory();
        this.year = builder.getYear();
        this.actors = builder.getActors();
        this.isAvailable = builder.getIsAvailable();
        this.popularity = builder.getPopularity();
    }

    public static MovieTitle startBuild(){
        return new MovieBuilder();
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