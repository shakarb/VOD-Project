import java.util.ArrayList;
import java.util.Iterator;

public class OrdersCollection implements IOrdersCollection {
    private ArrayList<Order> orders;

    public OrdersCollection() {
        this.orders = new ArrayList<Order>();;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }


    public ArrayList<Order> getOrders() {
        return orders;
    }

    public Iterator<Order> getIterator() {
        Iterator<Order> it = orders.iterator();
        return it;
    }
}
