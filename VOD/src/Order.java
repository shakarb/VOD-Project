import java.sql.Time;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Order {

    // fields
    private String orderId;
    private Movie movie;
    private String userId;
    private LocalDateTime time;
    private boolean isActive;

    public boolean makePay(){
        //set payment?
        this.time = LocalDateTime.now();
        return true;
    }

    public boolean clearOrder(){ // method meaning? are we talking about new(==like a "cart" in sites) or existing order?
        return true;
    }

    public void setMovie(Movie movie){
        this.movie = movie;
    }

    public String getTimeLeft(){
        LocalDateTime now = LocalDateTime.now();
        long timeFromOrder = ChronoUnit.MINUTES.between(this.time, now);
        int HOURS_OF_AVAILABILITY = 24;
        int MINUTES = 60;
        if (timeFromOrder < HOURS_OF_AVAILABILITY * MINUTES){ //24H count
            int hoursLeft = (int) (timeFromOrder / MINUTES); //since both are ints, you get an int
            int minutesLeft = (int) (timeFromOrder % MINUTES);
            return String.format("Order have %d:%02d left", hoursLeft, minutesLeft);
        }
        else {
            return "Order is no longer available.";
        }
    }


}
