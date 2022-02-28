public class GeneralReport implements IVisitor{
    private static int loginNo =0;
    private static int ordersNo =0;
    private static int movieNo =0;

    public static void visit(User user, int count){
        loginNo +=count;
    }
    public static void visit(Order order, int count){
        ordersNo+=count;
    }
    public static void visit(Movie movie, int count){
        movieNo+=count;
    }
    public static void displayResults()
    {
        System.out.println("Nr of logins:" + loginNo);
        System.out.println("Nr of orders:   " + ordersNo);
        System.out.println("Nr of movies:   " + movieNo);
    }
}
