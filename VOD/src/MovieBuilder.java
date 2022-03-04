public class MovieBuilder implements MovieTitle, MovieCategory, MovieYear, MovieActors,
        MovieAvailability, MoviePopularity, MovieRunningTime, MoviePrice, BuildMovie{
    private String title;
    private String[] category;
    private int year;
    private String[] actors;
    private boolean isAvailable;
    private int popularity;
    public double runningTime;
    public int price;

    public Movie build(){
        return new Movie(this);
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String[] getCategory() {
        return this.category;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public String[] getActors() {
        return this.actors;
    }

    @Override
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    @Override
    public int getPopularity() {
        return this.popularity;
    }

    public double getRunningTime() { return this.runningTime;}

    public int getPrice() { return this.price;}

    @Override
    public MovieCategory movieTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public MovieYear category(String[] category) {
        this.category = category;
        return this;
    }

    @Override
    public MovieActors year(int year) {
        this.year = year;
        return this;
    }
    @Override

    public MovieAvailability actors(String[] actors) {
        this.actors = actors;
        return this;
    }

    @Override
    public MoviePopularity isAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    @Override
    public MovieRunningTime popularity(int popularity) {
        this.popularity = popularity;
        return this;
    }

    @Override
    public MoviePrice runningTime(double runningTime) {
        this.runningTime = runningTime;
        return this;
    }

    @Override
    public BuildMovie price(int price) {
        this.price = price;
        return this;
    }

}
