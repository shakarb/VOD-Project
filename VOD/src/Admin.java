import java.sql.SQLException;
import java.util.List;

public class Admin extends User{

    public Admin(String name, String id, String password){
        super(name, id, password);
    }

    public void addNewMovie() {
        /*
        Movie newMovie = Movie.startBuild().
                movieTitle("title").
                category("action").
                year(2022).
                actors(new String[]{"first actor", "second actor", "third actor"}).
                isAvailable(true).
                popularity(5).
                build();*/
        Movie newMovie = Movie.startBuild().
                movieTitle("title").
                category(new String[]{"first category", "second category"}).
                year(2022).
                actors(new String[]{"first actor", "second actor", "third actor"}).
                isAvailable(true).
                popularity(5).
                runningTime(125).
                price(25).
                build();
        try{
            this.getDb().addMovie(newMovie);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }




    public void addMovieToAvailables(Movie movie){
        if (!movie.isAvailable()){
            try{
                movie.addToAvailables();
                getMoviesCollection().setIsAvailableStatus(movie);
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                System.exit(-1);
            }
            GeneralReport.visit(movie,1);

        }

    }

    public void removeMovieFromAvailables(Movie movie) {
        if (movie.isAvailable()){
            GeneralReport.visit(movie,-1);
            movie.removeFromAvailables();
            try{
                getMoviesCollection().setIsAvailableStatus(movie);
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                System.exit(-1);
            }
        }
    }


    public void viewStatistics() {
        try{
            /*
            SortingStrategy s = new sortByPopularity();
            s.sort(getDb().getAllMovies());
            */
            // demonstrate use of sorting strategy pattern to display movies by 3 different sorting criteria
            DisplayMovies dlm = new DisplayMovies(getMoviesCollection().getAllMovies(),new sortByPopularity());
            List<Movie> myList = dlm.getSortedList();
            System.out.println("Display movies sorted by popularity");
            for (Movie m : myList) {
                System.out.println(m.getTitle() + ": " + m.getPopularity());
            }
            // now change the sorting strategy to sort by name
            dlm.setSortingStrategy(new sortByName());
            myList = dlm.getSortedList();
            System.out.println("Display movies sorted by titles");
            for (Movie m : myList) {
                System.out.println(m.getTitle());
            }
            // now change the sorting strategy to sort by year
            dlm.setSortingStrategy(new sortByYear());
            myList = dlm.getSortedList();
            System.out.println("Display movies sorted by years");
            for (Movie m : myList) {
                System.out.println(m.getTitle() + ": " + m.getYear());
            }
            // demonstrate use of visitor pattern. the data collected by the visitor calls when making  a new order,
            // adding a new movie and when a user is login to the system.
            // this data is kept in the GeneralReport class and displayed by displayResults method of this class.
            GeneralReport.displayResults();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    /*
    public void saveStatistics() {
        try{

            SortingStrategy s = new sortByPopularity();
            s.sort(getDb().getAllMovies());

            // working with DisplayMovies

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    */
}
