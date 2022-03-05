

import java.sql.SQLException;
import java.util.Scanner;


public class VodApp {
    //constants
    private final int EXIT_SUCCESS = 0;
    private final int EXIT_FAILURE = -1;
    private final String SEPARATOR = "--------------------------------------------------------------------------------";

    //field and methods
    private static boolean isSystemOn = false;
    private IDbUtils db;
    User user;
    public VodApp(IDbUtils db) {
        this.db = db;
    }

    public void runApp(){
        welcome();
    }

    private void isExitPressed(String command){
        if (command.toLowerCase().equals("exit")){
            System.exit(EXIT_SUCCESS);
        }
    }


    private void setSystemOn(){
        //for first time welcome screen
        isSystemOn = true;
    }

    private void welcome(){

        if (!isSystemOn){
            System.out.println("Welcome to our VOD app! if you wish to close the window, enter exit at any point");
            setSystemOn();
        }
        System.out.print("ID: ");
        Scanner scanner = new Scanner(System.in);
        String userId = "";
        String password = "";
        try {
            userId = scanner.nextLine();
            System.out.print("Password: ");
            password = scanner.nextLine();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            System.exit(EXIT_FAILURE);
        }

        //user id & password validation. assume its validated
        try {
            User user = db.login(userId, password);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.exit(EXIT_FAILURE);
        }
        userMenu();

    }

    private void userMenu(){
        System.out.println(SEPARATOR);
        //displaying the all user menu: available movies & sorting options, search bar, orders
    }

    private void displayMovie(Movie movie){}

    private void placeOrder(Movie movie, User user){}
}
