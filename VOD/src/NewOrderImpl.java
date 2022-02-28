import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewOrderImpl implements NewOrder{
    @Override
    public Order makeOrder(RegisteredUser user, Movie movie) {
        Order order = new Order();
        order.setUserId(user.getId());
        order.setMovie(movie);
        order.setTimeOrderMade(LocalDateTime.now());
        order.setOrderId("some unique order id");
        GeneralReport.visit(order,1);
        return order;
    }
}
