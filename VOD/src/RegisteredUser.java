import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisteredUser extends User{
    private String email;
    private String phoneNumber;
    private String[] wishlist;
    private IOrdersCollection ordersCollection;
    private List<Order> orderList;
    private List<Movie> newWishList;
    private String isWishListUpToDate;


    public RegisteredUser(String name, String id, String password, String email, String phoneNumber, String[] wishlist) {
        super(name, id, password);
        this.ordersCollection = new OrdersCollection();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.wishlist = wishlist;
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

		//TODO add query "addMovieToWishList"
        //TODO add user as subscriber to the given movie
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
        //TODO add query "removeMovieFromWishList"
        //TODO add remove user from subscriber to the given movie

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
        //TODO set this user IS UPDATE string to "your wish list just updated!".
    }
    public void printWelcomeMessage(){
        System.out.println("Hi " + this.getName() + ", " + this.isWishListUpToDate);
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

    @Override
    public void logout(){
        //TODO set this user IS UPDATE string to "wishlist is up to date.".
        try{
            super.logout();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}














