import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisteredUser extends User{
//    private HashMap<Movie, LocalDateTime> orders;
    private String email;
    private String phoneNumber;
    private List<Movie> orderedList = new ArrayList<>();
    private List<Movie> favorites = new ArrayList<>();


    public void addMovieToFavorites(Movie movie) {
        movie.subscribe(this);
    }

    public void removeMovieFromFavorites(Movie movie) {
        movie.unSubscribe(this);
    }

    public void orderMovie(Movie movie) {
//        LocalDateTime orderTime = LocalDateTime.now();
        //TODO order using proxy
//        orders.put(movie, orderTime);
    }

//    public void displayAvailableMoviesToWatch(){
//        for (Movie movie : orders.keySet()){
//            LocalDateTime now = LocalDateTime.now();
//            long diffInMinutes = ChronoUnit.MINUTES.between(now, orders.get(movie));
//            if (diffInMinutes < 24 * 60) { // 24 hours of availability
//                System.out.println(movie.title);
//                /* if we would like to display the time left to the order...
//                long hoursLeft = diffInMinutes / 60;
//                long minutesLeft = diffInMinutes % 60;
//                 */
//            }
//        }
//    }

    public String getContactDetails() {
        return "eEmail: " + this.email + ", Phone numver: " + this.phoneNumber;

    }

    public void update(String title){
        System.out.println(title + " is now available!");
    }

}














