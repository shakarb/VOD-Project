import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        IDbUtils dbUtils = new DbUtils();
        try {
            /*
            String result = dbUtils.login("root", "root");
            System.out.println(result);

            ResultSet moviesResult = dbUtils.getAllMovies();
            System.out.println(moviesResult);

            moviesResult = dbUtils.getAvailableMovies();
            System.out.println(moviesResult);

             */
            //String[] actors = {"Adi"};
            //dbUtils.addMovie("inception", 2019, actors, true);
            List<Movie> moviesResult = dbUtils.getAllMovies();
            System.out.println(moviesResult);

        } catch(Exception e) {

        }

    }

}
