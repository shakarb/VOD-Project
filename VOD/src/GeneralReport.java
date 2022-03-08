import java.sql.SQLException;

import static java.lang.System.exit;

public class GeneralReport implements IVisitor{
    private static int loginNo;
    private static int ordersNo;
    private static int movieNo;

    public static void visit(User user, int count){
        loginNo +=count;
    }
    public static void visit(Order order, int count){
        ordersNo+=count;
    }
    public static void visit(Movie movie, int count){
        movieNo+=count;
    }

    public static void getStatistics(IDbUtils db) {
        try {
            int[] statistics = db.getStatistics();
            loginNo = statistics[0];
            movieNo = statistics[1];
            ordersNo = statistics[2];
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            exit(-1);
        }
    }

    public static void updateStatistics(IDbUtils db) {
        try {
            db.updateStatistics(loginNo, movieNo, ordersNo);
        } catch (Exception ex) {
        System.out.println(ex.getMessage());
        exit(-1);
        }
    }

    public static void displayResults()
    {
        System.out.println("Below results of statistics collected using the visitor pattern");
        System.out.println("Nr of logins:   " + loginNo);
        System.out.println("Nr of orders:   " + ordersNo);
        System.out.println("Nr of movies:   " + movieNo);
    }
}
