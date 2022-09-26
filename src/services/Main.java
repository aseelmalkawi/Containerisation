package services;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static Connection con;
    static PreparedStatement st;
    static ShowResults sr;
    static Scanner input = new Scanner(System.in);
    static int grade;
    static int num;
    static String student = "";
    static Authenticator authenticator;

    public static void main(String[] args) throws SQLException {
        authenticator = new Authenticator();
        if (authenticator.authenticate()) {
            System.out.println("Type E to enter data or A to view analytics of saved data:");
            String a = input.next();
            if (a.equals("E")) {
                enterData();
                System.out.println("Do you want to view analytics? Yes or No: ");
                a = input.next();
                if(a.equals("Yes")){
                    viewAnalytics();
                }
            } else {
                viewAnalytics();
            }
        } else {
            System.out.println("Credentials unmatched. Try again.");
        }
    }

    public static void enterData() throws SQLException {
        System.out.println("How many students do you want to enter?");
        num = input.nextInt();
        con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb", "root", "aseel2000");
        while (num-- > 0) {
            System.out.print("student: ");
            student = input.next();
            System.out.print("grade: ");
            grade = input.nextInt();
            st = con.prepareStatement("insert into Class (Student, grade) values(?,?)");
            st.setString(1, student);
            st.setInt(2, grade);
            st.executeUpdate();
            System.out.println();
        }
    }

    public static void viewAnalytics() throws SQLException {
        sr = new ShowResults();
    }
}