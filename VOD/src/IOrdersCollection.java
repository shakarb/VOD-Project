import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public interface IOrdersCollection {
    public void addOrder(Order order) throws SQLException;

    public ArrayList<Order> getOrders() throws SQLException;

    public ArrayList<Order> getUserOrders(String userId) throws SQLException;

    public Iterator<Order> getIterator();

}
