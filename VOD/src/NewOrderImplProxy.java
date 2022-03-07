import java.sql.SQLException;

public class NewOrderImplProxy implements NewOrder{


    public NewOrderImplProxy(){}
    @Override
    public Order makeOrder(RegisteredUser user, Movie movie) {
        NewOrder order = new NewOrderImpl();
        Order orderToReturn;
        String givenPassword = "1234"; //in real time - the app should get user password as input

        boolean isPasswordCorrect = false;
        try{
            isPasswordCorrect = user.getDb().checkUserPassword(user, givenPassword);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
        if (isPasswordCorrect){
            orderToReturn = order.makeOrder(user, movie);
        } else{
            System.out.println("Wrong password");
            orderToReturn = null;
        }
        return orderToReturn;
    }
}
