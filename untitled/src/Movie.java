import java.sql.Connection;
import java.util.Scanner;
import java.sql.*;

public class Movie {
    static String url = "jdbc:mysql://localhost:3306/moviehub";
    static String user = "danilka";
    static String password = "xxxx";
    static String showStockQuery = "SELECT * FROM stock";

    static Scanner scanner = new Scanner(System.in);

    public void addStock() throws ClassNotFoundException {               //This method is to add a new stock. That means we are entering the name,quantity.

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();


            System.out.println("Enter the Movie name : ");
            String addedMovie = scanner.nextLine().toLowerCase();

            String selectMovieQuery = "SELECT * FROM stock WHERE movie_name ='"+ addedMovie+"'";
            ResultSet result = statement.executeQuery(selectMovieQuery);

            if (!result.next()){
                System.out.println("Enter the quantity : ");
                int addedQuantity = Integer.parseInt(scanner.nextLine());

                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO stock(movie_name, available_quantity) VALUE (?,?)");
                pstmt.setString(1,addedMovie);
                pstmt.setInt(2,addedQuantity);
                pstmt.executeUpdate();


                System.out.println("Stock added successfully.");
                System.out.println("");
            }else {
                System.out.println("Movie is already exist in the stock.");
                System.out.println("");
            }


            connection.close();
        }catch (SQLException e){
            e.printStackTrace();

        }

    }

    public void deleteStock() throws ClassNotFoundException{                //By this method, we can completely remove an existing stock.

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();


            showStock();
            System.out.println("Enter the movie name to remove from stock.");
            String removingMovie = scanner.nextLine().toLowerCase();

            String selectMovieQuery = "SELECT * FROM stock WHERE movie_name ='"+removingMovie+"'";
            ResultSet result = statement.executeQuery(selectMovieQuery);

            if (result.next()){
                PreparedStatement pstmt = connection.prepareStatement("DELETE FROM stock where movie_name = ?");
                pstmt.setString(1,removingMovie);
                pstmt.executeUpdate();

                System.out.println("Stock removed successfully.");
                System.out.println("");

            }else {
                System.out.println("That movie is not available in the stock.");
                System.out.println("");

            }


            connection.close();


        }catch (SQLException e){
            e.printStackTrace();

        }

    }

    public void addAMovieItem() throws ClassNotFoundException {                      //By this method, we can update(add) our stock related to a selected movie.
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();



            showStock();

            System.out.println("Enter movie name : ");
            String updatingMovie = scanner.nextLine().toLowerCase();


            String selectMovieQuery = "SELECT * FROM stock WHERE movie_name ='"+ updatingMovie+"'";
            ResultSet result = statement.executeQuery(selectMovieQuery);
            if(result.next()){
                System.out.println("Enter quantity : ");
                int updateMovieQuantity = scanner.nextInt();

                PreparedStatement pstmt = connection.prepareStatement("UPDATE stock SET available_quantity=available_quantity+? WHERE movie_name=?");
                pstmt.setInt(1,updateMovieQuantity);
                pstmt.setString(2,updatingMovie);
                pstmt.executeUpdate();

                System.out.println("Stock updated successfully.");
                System.out.println("");

            }else {
                System.out.println("Movie is not in the stock.");
                System.out.println("");

            }

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public void removeAMovieItem() throws ClassNotFoundException{                  //By this method, we can update(remove) our stock related to a selected movie.
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();

            showStock();

            System.out.println("Enter movie name : ");
            String updatingMovie = scanner.nextLine().toLowerCase();


            String selectMovieQuery = "SELECT * FROM stock WHERE movie_name ='"+ updatingMovie+"'";
            ResultSet result = statement.executeQuery(selectMovieQuery);
            if(result.next()){

                System.out.println("Enter quantity : ");
                int updateMovieQuantity = scanner.nextInt();
                PreparedStatement pstmt = connection.prepareStatement("UPDATE stock SET available_quantity=available_quantity-? WHERE movie_name=?");
                pstmt.setInt(1,updateMovieQuantity);
                pstmt.setString(2,updatingMovie);
                pstmt.executeUpdate();

                System.out.println("Stock updated successfully.");


            }else {
                System.out.println("Movie is not in the stock.");

            }
            System.out.println("");

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }



    public void showStock() throws SQLException, ClassNotFoundException {              //By this, We can see our entire stock.

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(showStockQuery);


            int movie_id;
            String movie_name;
            int quantity;

            while(result.next()){
                movie_id = result.getInt(1);
                movie_name = result.getString(2);
                quantity = result.getInt(3);
                System.out.println(movie_id+" : "+movie_name+" - "+quantity);

            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
