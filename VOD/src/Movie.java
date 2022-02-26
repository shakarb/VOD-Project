import java.util.ArrayList;
import java.util.List;

public class Movie {
    String title;
    String category;
    int year;
    String[] actors;
    boolean isAvailable;
    double popularity; // orders counter
    private ArrayList<RegisteredUser> listeningUsers;
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
        this.listeningUsers = new ArrayList<>();
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

    private void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void addToAvailables() {
        if (!isAvailable()){
            notifyMovieIsAvailable();
            setIsAvailable(true);
        }
    }

    public void removeFromAvailables() {
        setIsAvailable(false);
    }

    public void subscribe(RegisteredUser user){
        listeningUsers.add(user);
    }

    public void unSubscribe(RegisteredUser user){
        listeningUsers.remove(user);
    }

    public void notifyMovieIsAvailable(){
        for (RegisteredUser listeningUser : listeningUsers){
            listeningUser.update(this.title);
        }
    }
}