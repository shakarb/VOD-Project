import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisteredUser extends User{
    private String email;
    private String phoneNumber;
    private String[] wishlist; //TODO should not be <Movie>? also - better be an ArrayList because array is immutable...
    private IOrdersCollection ordersCollection;
    private List<Order> orderList;
    private List<Movie> newWishList;

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
        //using array
        movie.subscribe(this);
        int l = this.wishlist.length;
        String[] newWishlist = new String[l + 1];
        System.arraycopy(this.wishlist, 0, newWishlist, 0, l);
        newWishlist[l] = movie.getTitle();;
        this.wishlist = newWishlist;

        //using ArrayList
        this.newWishList.add(movie);
		//TODO add query "addToWishList" in the db?
    }

    public void removeMovieFromWishlist(Movie movie) {
        //using array
        String movieNameToRemove = movie.getTitle();
        int l = this.wishlist.length - 1;
        String[] newStringsWishlist = new String[l + 1];
        int i = 0;
        for (String s : this.wishlist){
            if (!s.equals(movieNameToRemove)){
                newStringsWishlist[i] = s;
                i++;
            }
        }
        this.wishlist = newStringsWishlist;

        //using ArrayList
        this.newWishList.remove(movie);

        movie.unSubscribe(this);
		//TODO add query "removeFromWishList" in the db?
    }

    public void orderMovie(Movie movie) {
        NewOrder order = new NewOrderImplProxy();
        Order newOrder = order.makeOrder(this, movie);
        this.orderList.add(newOrder);
    }

    public void displayAvailableOrders(){
        try {
            ArrayList<Order> allOrders = this.ordersCollection.getUserOrders(this.getId());
            for (Order order : allOrders){
                if (order.getTimeLeft() != null){
                    order.displayOrder();
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    public String getContactDetails() {
        return "eEmail: " + this.email + ", Phone number: " + this.phoneNumber;

    }

    public void update(String title){
        //SET UPDATE FLAG TO TRUE IN THE DB
    }

    public void displayWishlist(){
        //using array
        try {
            for (String title : this.wishlist){
                for (Movie m: this.getMoviesCollection().getAllMovies()){
                    if (m.getTitle().equals(title)){
                        m.printMovieDetails();
                    }
                }
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        // using ArrayList
        try {
            for (Movie m : this.getMoviesCollection().getAllMovies()){
                if (this.newWishList.contains(m)){
                    m.printMovieDetails();
                }
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }
}














