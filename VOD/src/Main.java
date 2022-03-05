import java.util.ArrayList;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        IDbUtils dbUtils = new DbUtils();
        User user = null;
        try {
            // Register User
            String userId = "user1";
            String password = "123456";
            String name = "Shaked";
            String email = "Shaked@gmail.com";
            String phoneNumber = "0544285182";
            String[] favoritesMovies = new String[] {};
            dbUtils.register(userId, password, name, email,phoneNumber, favoritesMovies);

            // login Registered user
            user = dbUtils.login(userId, password);

            if (user == null) {
                System.out.println("Login Failed");
                exit(-1);
            }
        } catch(Exception e) {
            String error = e.getMessage();
            System.out.println(error);
            exit(1);
        }

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
                    System.out.println("There are no movies with higher popularity");
                }
            } catch (Exception ex) {
                System.out.println("Fetching movie data failed : " + ex.getMessage());
            }

            if (user instanceof Admin) {
                // actions that admin can do
            }

            if (user instanceof RegisteredUser) {
                // actions that registered user can do
            }

            try {
                user.logout();
            } catch (Exception ex) {
                System.out.println("Logout failed " + ex.getMessage());
                exit(1);
            }
            System.out.println("Successful logout");


        }
    }

    //order movies
    public void main_a(IDbUtils db, User user) throws Exception{
        MoviesCollection mc = (MoviesCollection)user.getMoviesCollection();
        RegisteredUser user1 = (RegisteredUser)user;
        ArrayList<Movie> availableMovies = mc.getAvailableMovies();
        if (!availableMovies.isEmpty()){
            for (Movie m: availableMovies) {
                System.out.println("Original popularity: " + m.getPopularity());
                user1.orderMovie(m);
                System.out.println("New popularity: " + m.getPopularity());
            }
        }
    }

    //add movie to wishlist
    public void main_b(IDbUtils db, User user) throws Exception {
        MoviesCollection mc = (MoviesCollection) user.getMoviesCollection();
        RegisteredUser user1 = (RegisteredUser) user;
        ArrayList<Movie> availableMovies = mc.getAvailableMovies();
        System.out.println("Before adding new movie to the wishlist");
        user1.displayWishlist();
        if (!availableMovies.isEmpty()) {
            user1.addMovieToWishlist(availableMovies.get(0));
        }
        System.out.println("After adding new movie to the wishlist");
        user1.displayWishlist();
    }

    //observer pattern demonstration
    public void main_c(IDbUtils db, User user) throws Exception {
        //Registered user register to movie.

        MoviesCollection mc = (MoviesCollection) user.getMoviesCollection();
        Movie movie = null; // movie to listen on.
        for (Movie m :mc.getAllMovies()){
            if (!m.isAvailable()){
                m.addToAvailables();
                movie = m;
                break;
            }
        }
        user.logout();

        //admin set movie as available
        User admin = (Admin)db.login("123456789", "123456");
        if (movie != null){
            movie.addToAvailables();
        } else{
            exit(-1);
        }
        admin.logout();

        //user get his update
        String userId = "1234";
        String password = "5678";
        db.login(userId, password);
        //should display notification at this point...
    }

    //order movie & show the difference in user's orders list.
    public void main_d(IDbUtils db, User user) throws Exception {
        if (user instanceof RegisteredUser)  {
            ((RegisteredUser) user).displayAvailableOrders();
            ArrayList<Movie> availableMovies = user.getMoviesCollection().getAvailableMovies();
            if (!availableMovies.isEmpty()){
                ((RegisteredUser) user).orderMovie(availableMovies.get(0));
            }
            //we should see some difference now
            ((RegisteredUser) user).displayAvailableOrders();
        }
        else {
            RegisteredUser regUser = (RegisteredUser)db.login("1234", "5678");
            main_d(db, regUser);
        }

    }
}
