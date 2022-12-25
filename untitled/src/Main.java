/*This Stock Management System has 1 table called 'stock'.
* It has 3 columns.1st column = movie_id -> primary key, auto incremented.
*                  2nd column = movie_name
*                  3rd column = available_quantity -> not negative
*
* Functions we have -> Login, Add a new stock, Removing an existing stock, Updating stock(add,remove)*/




import java.sql.SQLException;
import java.util.Scanner;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    
    public static void login() {
        String userName = "user";
        String password = "password";
        System.out.println("Enter user name : ");
        String inputUserName;
        inputUserName = scanner.nextLine();


        while (!userName.equals(inputUserName)) {
            System.out.println("Username incorrect");
            System.out.println("Enter user name : ");
            inputUserName = scanner.nextLine();
        }

        System.out.println("Enter Password : ");
        String inputPassword = scanner.nextLine();
        if (inputPassword.equals(password)) {
            System.out.print("Login Successful.");
            System.out.println("");
        } else {
            while (!inputPassword.equals(password)) {
                System.out.println("Password Incorrect.");
                System.out.println("Enter Password : ");
                inputPassword = scanner.nextLine();
            }

            System.out.println("Login Successful.");
            System.out.println("");

        }

    }


    public static void showOption0() {
        System.out.println("====== MAIN MENU =====");
        System.out.println("__________________________");
        System.out.println("");
        System.out.println("Enter 1 to Update(add) stock.");
        System.out.println("Enter 2 to Update(remove) stock.");
        System.out.println("Enter 3 to See the stock.");
        System.out.println("Enter 4 to Add a new stock.");
        System.out.println("Enter 5 to Delete a stock");
        System.out.println("Enter x for Quit program ");
        System.out.println("__________________________");

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        login();    //Login Method
        showOption0();
        String option = scanner.nextLine();

        while (!option.equals("x")){              //this while loop use to run the program until user enters 'x'
            if (option.equals("3")){
                Movie showStock = new Movie();
                showStock.showStock();
            } else if (option.equals("4")) {
                Movie addStock = new Movie();
                addStock.addStock();
            } else if (option.equals("5")) {
                Movie deleteStock =  new Movie();
                deleteStock.deleteStock();
            } else if (option.equals("1")) {
                Movie addMovieItem = new Movie();
                addMovieItem.addAMovieItem();
            } else if (option.equals("2")) {
                Movie removeMovieItem = new Movie();
                removeMovieItem.removeAMovieItem();
            }else {
                System.out.println("Choose a valid option!!!");
                System.out.println("");
            }
            showOption0();
            option = scanner.nextLine();
        }

    }
}


