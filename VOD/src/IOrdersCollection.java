import java.util.ArrayList;
import java.util.Iterator;

public interface IOrdersCollection {
    public void addOrder(Order order);

    public void removeOrder(Order order);

    public Iterator<Order> getIterator();

    public ArrayList<Order> getOrders();
}
