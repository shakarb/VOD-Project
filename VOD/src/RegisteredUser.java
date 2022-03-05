import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisteredUser extends User{
//    private HashMap<Movie, LocalDateTime> orders;
    private String email;
    private String phoneNumber;
//    private List<Movie> orderedList = new ArrayList<>();
    private String[] wishlist; //TODO should not be <Movie> ?? also - must be an ArrayList because array is immutable as "ordersList"
    private IOrdersCollection ordersCollection;
    private List<Order> orderList;

    public RegisteredUser(String email, String phoneNumber, String[] wishlist) {
        this.ordersCollection = new OrdersCollection();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.wishlist = wishlist;
        try {
            if (this.getDb().isWishListUpdated(this)){
                this.getDb().setWishListUpToDate(this);
                System.out.println("Hi " + this.getName() + "! Your wishlist just updated\n");
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        //QUERY - IF UPDATE FLAG IS TRUE -> DISPLAY "Wishlist list updated!"
        //      - ELSE -> DO NOTHING
    }
    public void addMovieToWishlist(Movie movie) {
        movie.subscribe(this);
        int l = this.wishlist.length;
        String[] newWishlist = new String[l + 1];
        System.arraycopy(this.wishlist, 0, newWishlist, 0, l);
        newWishlist[l] = movie.getTitle();;
        this.wishlist = newWishlist;
		//TODO add query "addToWishList" in the db?
    }

    public void removeMovieFromWishlist(Movie movie) {
        movie.unSubscribe(this);
		//TODO add query "removeFromWishList" in the db?
    }

    public void orderMovie(Movie movie) {
        NewOrder order = new NewOrderImplProxy();
        Order newOrder = order.makeOrder(this, movie);
        this.orderList.add(newOrder);
    }

    public void displayAvailableOrders(){
        for (Order order : orderList){
            if (order.getTimeLeft() != null){
                order.displayOrder();
            }
        }
    }

    public String getContactDetails() {
        return "eEmail: " + this.email + ", Phone number: " + this.phoneNumber;

    }

    public void update(String title){
        System.out.println(title + " is now available!");

        //SET UPDATE FLAG TO TRUE IN THE DB
    }

    public void displayWishlist(){
        for (String s : this.wishlist){
            System.out.println(s);
        }
    }
}














