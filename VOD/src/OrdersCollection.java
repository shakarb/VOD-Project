import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class OrdersCollection implements IOrdersCollection {
    private IDbUtils db;
    private ArrayList<Order> orders;

    public OrdersCollection() {
        this.db = new DbUtils();
    }

    public void addOrder(Order order) throws SQLException {
        db.addOrder(order);
    }

    public ArrayList<Order> getOrders() throws SQLException {
        orders = db.getAllOrders();
        return orders;
    }

    public ArrayList<Order> getUserOrders(String userId) throws SQLException {
        return db.getUserOrders(userId);
    }

    public Iterator<Order> getIterator() {
        Iterator<Order> it = orders.iterator();
        return it;
    }


}
