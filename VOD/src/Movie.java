import java.util.ArrayList;
import java.util.Arrays;

public class Movie {
    private String title;
    private String[] category;
    private int year;
    private String[] actors;
    private boolean isAvailable;
    private int popularity; // orders counter
    private ArrayList<RegisteredUser> listeningUsers;
    private double runningTime;
    private int price;

    public Movie(String movieTitle, String[] category, int year, String[] actors,
                 boolean isAvailable,  int inc_popularity, double runningTime, int price) {
        this.title = movieTitle;
        this.category = category;
        this.year = year;
        this.actors = actors;
        this.isAvailable = isAvailable;
        this.popularity = inc_popularity;
        this.runningTime = runningTime;
        this.price = price;
    }

    public Movie(MovieBuilder builder){
        this.listeningUsers = new ArrayList<>();
        this.title = builder.getTitle();
        this.category = builder.getCategory();
        this.year = builder.getYear();
        this.actors = builder.getActors();
        this.isAvailable = builder.getIsAvailable();
        this.popularity = builder.getPopularity();
        this.runningTime = builder.runningTime;
        this.price = builder.price;
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
    public String[] getCategory() {
        return this.category;
    }
    public int getYear() {
        return this.year;
    }
    public String[] getActors() {
        return this.actors;
    }

    public double getRunningTime() {
        return this.runningTime;
    }

    public int getPrice() {
        return this.price;
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

    public void printMovieDetails() {
        System.out.format("Movie: %s%nGenre: %s%nYear: %s%nActors: %s%nPopularity: %s%n" +
                        "Running Time: %s%nPrice: %s%n%n" ,
                title, Arrays.toString(category), year, Arrays.toString(actors), popularity, runningTime, price);
    }

    public void increasePopularity(){
        this.popularity += 1;
    }
}