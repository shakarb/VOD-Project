import java.sql.SQLException;
import java.util.List;

public class Admin extends User{

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
        GeneralReport.visit(movie,1);
        movie.addToAvailables();

    }

    public void removeMovieFromAvailables(Movie movie) {
        GeneralReport.visit(movie,-1);
        movie.removeFromAvailables();
    }

    public void editMovie() {
        //what can the admin edit here?
    }

    public void viewStatistics() {
        try{
            /*
            SortingStrategy s = new sortByPopularity();
            s.sort(getDb().getAllMovies());
            */
            // working with DisplayMovies
            DisplayMovies Dlm = new DisplayMovies(getMoviesCollection().getAllMovies(),new sortByPopularity());
            List<Movie> myList = Dlm.getSortedList();
            for (Movie m : myList) {
                System.out.println(m.getTitle() + ": " + m.getPopularity());
            }
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
