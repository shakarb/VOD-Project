import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User{

    private String name;
    private String id;
    private String password;
    DbUtils Db;


    public void login(String user, String password) {

    }

    public void logout() {

    }

    public void viewMovies() {
        SortingStrategy s = new sortByName(); //lexicographic order as default view.
        if (this instanceof RegisteredUser){
            try{
                ArrayList<Movie> movies = Db.getAvailableMovies();
                s.sort(movies);
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        } else if (this instanceof Admin){
            try{
                ArrayList<Movie> movies = Db.getAllMovies();
                s.sort(movies);
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}