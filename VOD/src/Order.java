import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Order {
    //TODO maybe we should add the unique order id to the moment we are creating it?
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

            //if no time left returns null, else returns a string to represent the time left
            if (diffInHours >= 24){
                return null;
            }
            else {
               return diffInHours + " hours and "+ diffInMinutes + " minutes left to this order.";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void displayOrder(){
        String display = "Movie name: " + this.getMovie().getTitle() +
                "\nOrdered at: " + this.getTimeOrderMade() +
                "\nCost: " + this.getTotalPayment() +
                "\n" + getTimeLeft() + "\n\n";
        System.out.println(display);
    }
}
