import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisteredUser extends User{
    private String email;
    private String phoneNumber;
    private String[] wishlist; // list of the movie titles
    private IOrdersCollection ordersCollection;
    private List<Movie> newWishList;
    private String isWishListUpToDate;
    private String creditCard;


    public RegisteredUser(String name, String id, String password, String email, String phoneNumber,
                          String[] wishlist, String wishlistStatus) {
        super(name, id, password);
        this.ordersCollection = new OrdersCollection();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.wishlist = wishlist;
        this.isWishListUpToDate = wishlistStatus;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
        try {
            this.getDb().setCreditCard(this);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
    }

    public String getCreditCard(){
        return this.creditCard;
    }

    public void addMovieToWishlist(Movie movie) {
        //using array
        //validate - no double insertions
        if ((Arrays.stream(this.wishlist).filter(s -> s.equals(movie.getTitle())).findFirst().orElse(null)) != null) {
            return; // movie is already in the wishlist
        }

        movie.subscribe(this);
        int l = this.wishlist.length;
        String[] newWishlist = new String[l + 1];
        System.arraycopy(this.wishlist, 0, newWishlist, 0, l);
        newWishlist[l] = movie.getTitle();;
        this.wishlist = newWishlist;

        //using ArrayList
        //this.newWishList.add(movie);


        try{
            this.getDb().updateWishlist(this);
            this.getDb().updateListeners(movie);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

    }

    public void removeMovieFromWishlist(Movie movie) {
        //using array
        //validate - no null remove
        if ((Arrays.stream(this.wishlist).filter(s -> s.equals(movie.getTitle())).findFirst().orElse(null)) == null) {
            return; // movie is not in the wishlist
        }
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
        try{
            this.getDb().updateWishlist(this);
            this.getDb().updateListeners(movie);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

    }

    public String[] getWishlist() {
        return this.wishlist;
    }

    public String getWishlistStatus() {
        return this.isWishListUpToDate;
    }

    public void orderMovie(Movie movie) {
        NewOrder order = new NewOrderImplProxy();
        Order newOrder = order.makeOrder(this, movie);
        try{
            this.ordersCollection.addOrder(newOrder);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

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
        try {
            this.isWishListUpToDate = "your wishlist just updated!";
            this.getDb().setWishListUpToDate(this);
            this.getDb().updateWishlist(this);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
    }

    public void printWelcomeMessage(){
        System.out.println("Hi " + super.getName() + ", " + this.isWishListUpToDate);
    }

    public void displayWishlist(){
        //using array
        try {
            for (int i = 0; i < this.wishlist.length; i++){
                for (Movie m: this.getMoviesCollection().getAllMovies()){
                    if (m.getTitle().equals(this.wishlist[i])){
                        m.printMovieDetails();
                    }
                }
            }

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

//        // using ArrayList
//        try {
//            for (Movie m : this.getMoviesCollection().getAllMovies()){
//                if (this.newWishList.contains(m)){
//                    m.printMovieDetails();
//                }
//            }
//        }
//        catch (SQLException ex){
//            System.out.println(ex.getMessage());
//        }

    }

    @Override
    public void logout(){
        //TODO set this user IS UPDATE string to "wishlist is up to date.".
        try{
            this.isWishListUpToDate = "your wishlist is up do date";
            this.getDb().setWishListUpToDate(this);
            super.logout();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}














