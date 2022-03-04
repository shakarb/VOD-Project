import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User{
    private String name;
    private String id;
    private String password;
    private DbUtils Db;
    private IMoviesCollection moviesCollection;

    public String getId() {
        return id;
    }

    public DbUtils getDb(){
        return Db;
    }

    public String getName() {
        return name;
    }

    public IMoviesCollection getMoviesCollection() {
        return moviesCollection;
    }


    public void login(String user, String password) {
        GeneralReport.visit(this,1);

    }

    public void logout() throws SQLException {
        // temporary - should not hold dbutils instance
        Db = new DbUtils();
        Db.logout(this);
    }

    public void viewMovies() {
        SortingStrategy s = new sortByName(); //lexicographic order as default view.
        if (this instanceof RegisteredUser){
            try{
                ArrayList<Movie> movies = moviesCollection.getAvailableMovies();
                s.sort(movies);
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        } else if (this instanceof Admin){
            try{
                ArrayList<Movie> movies = moviesCollection.getAllMovies();
                s.sort(movies);
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}