import java.util.ArrayList;

public class Movie {
    private String title;
    private String category;
    private int year;
    private String[] actors;
    private boolean isAvailable;
    private double popularity; // orders counter
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
    public double getPopularity() {
        return this.popularity;
    }
    public String getCategory() {
        return this.category;
    }
    public int getYear() {
        return this.year;
    }
    public String[] getActors() {
        return this.actors;
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