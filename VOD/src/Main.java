import java.util.ArrayList;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        IDbUtils dbUtils = new DbUtils();
        User user = null;
        GeneralReport.getStatistics(dbUtils);

        try {
            // Register User
            String userId = "user";
            String password = "123456";
            String name = "Shaked";
            String email = "Shaked@gmail.com";
            String phoneNumber = "0544285182";
            dbUtils.register(userId, password, name, email, phoneNumber);

            // login Registered user
            user = dbUtils.login(userId, password);
            ((RegisteredUser) user).printWelcomeMessage();
            if (user == null) {
                System.out.println("Login Failed");
                exit(-1);
            }
        } catch (Exception e) {
            String error = e.getMessage();
            System.out.println(error);
            exit(1);
        }

        //user = new Admin("1", "123", "admin");
        if (user instanceof Admin || user instanceof RegisteredUser) {
            // actions that both admin and registered users can do

            // Iterator pattern - Smart Search
            MoviesCollection moviesCollection = new MoviesCollection();
            try {
                // Search movie by name
                String movieName = "Titanic";
                ArrayList<Movie> moviesList = moviesCollection.searchMovieByName(movieName);
                for (Movie movie : moviesList) {
                    movie.printMovieDetails();
                }
                if (moviesList.isEmpty()) {
                    System.out.println("There are no movies named " + movieName);
                }
                // Search movie by category
                moviesList = moviesCollection.searchMovieByCategory("Drama");
                for (Movie movie : moviesList) {
                    movie.printMovieDetails();
                }
                if (moviesList.isEmpty()) {
                    System.out.println("There are no movies in this category");
                }
                // Search movie by year
                moviesList = moviesCollection.searchMovieByPopularity(1);
                for (Movie movie : moviesList) {
                    movie.printMovieDetails();
                }
                if (moviesList.isEmpty()) {
                    System.out.println("There are no movies with higher popularity\n");
                }
            } catch (Exception ex) {
                System.out.println("Fetching movie data failed : " + ex.getMessage());
            }

            if (user instanceof Admin) {
                ((Admin) user).viewStatistics();
            }

            if (user instanceof RegisteredUser) {
                try {
                    main_a(dbUtils);
                    main_c(dbUtils);
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                    exit(-1);
                }
            }

            try {
                user.logout();
            } catch (Exception ex) {
                System.out.println("Logout failed" + ex.getMessage());
                exit(1);
            }
            System.out.println("Successful logout");

            // Update statistics
            GeneralReport.updateStatistics(dbUtils);
        }
    }

    //order movies
    public static void main_a(IDbUtils db) throws Exception {
        RegisteredUser user1 = (RegisteredUser) db.login("user", "123456");
        MoviesCollection mc = (MoviesCollection) user1.getMoviesCollection();
        ArrayList<Movie> availableMovies = mc.getAvailableMovies();
        if (!availableMovies.isEmpty()) {
            Movie m = availableMovies.get(0);
            System.out.println(m.getTitle() + " Original popularity: " + m.getPopularity());
            user1.orderMovie(m);
            System.out.println(m.getTitle() + " New popularity: " + m.getPopularity());
            RegisteredUser user2 = (RegisteredUser) db.login("123456", "123456");
            user2.orderMovie(m);
            user2.logout();
            System.out.println(m.getTitle() + " New popularity: " + m.getPopularity());
        }
        user1.logout();

    }


    //observer pattern demonstration
    public static void main_c(IDbUtils db) throws Exception {

        RegisteredUser user = (RegisteredUser) db.login("user", "123456");
        user.printWelcomeMessage();
        user.displayWishlist();
        MoviesCollection mc = (MoviesCollection) user.getMoviesCollection();
        Movie movie = null; // movie to listen on.
        for (Movie m : mc.getAllMovies()) {
            if (!m.isAvailable()) {
                movie = m;
                break;
            }
        }
        if (movie != null) {
            user.addMovieToWishlist(movie);
        } else {
            System.out.println("There are no available movies");
            user.logout();
            exit(-1);
        }
        user.displayWishlist();
        user.logout();
        //admin set this movie as available
        //Admin admin = new Admin("123", "123", "admin");
        Admin admin = (Admin)db.login("123", "admin");
        if (movie != null) {
            admin.addMovieToAvailables(movie);
        } else {
            System.out.println("There are no available movies");
            admin.logout();
            exit(-1);
        }
        admin.logout();

        //user gets his update
        user = (RegisteredUser) db.login("user", "123456");
        user.printWelcomeMessage();
        user.logout();
        user = (RegisteredUser) db.login("user", "123456");
        user.printWelcomeMessage();
        user.logout();
    }

    //order movie & show the difference in user's orders list.
    public void main_d(IDbUtils db, User user) throws Exception {
        if (user instanceof RegisteredUser) {
            ((RegisteredUser) user).displayAvailableOrders();
            ArrayList<Movie> availableMovies = user.getMoviesCollection().getAvailableMovies();
            if (!availableMovies.isEmpty()) {
                ((RegisteredUser) user).orderMovie(availableMovies.get(0));
            }
            //we should see some difference now
            ((RegisteredUser) user).displayAvailableOrders();
        } else {
            RegisteredUser regUser = (RegisteredUser) db.login("1234", "5678");
            main_d(db, regUser);
        }
    }
}
