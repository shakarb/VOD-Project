import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Order {

    // fields
    private String userId;
    private Movie movie;
    private int totalPayment;
    private String timeOrderMade;
    //default constructor, used in the proxy pattern
    public Order(){}

    //parameters constructor, used when getting an Order from the DB
    public Order(String userId, Movie movie, int totalPayment, String timeOrderMade){
        this.userId = userId;
        this.movie = movie;
        this.totalPayment = totalPayment;
        this.timeOrderMade = timeOrderMade;

    }

    public Movie getMovie() {
        return this.movie;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getTimeOrderMade() {
        return this.timeOrderMade;
    }

    public int getTotalPayment() {
        return this.totalPayment;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setMovie(Movie movie){
        this.movie = movie;
    }

    public void setTimeOrderMade(LocalDateTime time) {
        this.timeOrderMade = time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public String getTimeLeft(){
        LocalDateTime now = LocalDateTime.now();
        String currentTimeAsString = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            Date startingTime = format.parse(this.timeOrderMade);
            Date currentTime = format.parse(currentTimeAsString);

            //diff in milliseconds
            long diff = currentTime.getTime() - startingTime.getTime();

            long diffInMinutes = diff / (60 * 1000) % 60;
            long diffInHours = diff / (60 * 60 * 1000) % 24;

            if (diffInHours >= 24){
                System.out.println("This order is no longer available.");
            }
            else {
                System.out.println(diffInHours + " hours and "+ diffInMinutes + " minutes left to this order.");
            }

            return "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
