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

}
