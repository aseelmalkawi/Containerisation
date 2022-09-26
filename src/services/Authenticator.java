package services;
import java.sql.*;
import java.util.Scanner;

public class Authenticator {
    String username;
    String password;
    Scanner input = new Scanner(System.in);
    Connection connection;
    PreparedStatement st;
    ResultSet rs;

    public Authenticator(){
        System.out.println("Please enter your username: ");
        username = input.next();
        System.out.println("Password: ");
        password = input.next();
    }
    public boolean authenticate() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root", "aseel2000");

        st = connection.prepareStatement("Select username, password from Admin where username=? and password=?");

        st.setString(1, username);
        st.setString(2, password);
        rs = st.executeQuery();
        return rs.next();
    }
}
