package it.develhope.jdbc;

import java.sql.*;
import java.util.ArrayList;

public class DriverJDBC {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/newdb";
    private static final String USER = "developer";
    private static final String PASSWORD = "developer1?";

    public static void main(String[] args) {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            System.out.printf("Connected to database %s successfully.\n\n", conn.getCatalog());

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT last_name, first_name FROM students;");

            ArrayList<String> surnames = new ArrayList<>();

            System.out.println("Print all students names:\n");
            while(resultSet.next()){
                System.out.println(resultSet.getString("first_name"));
                surnames.add(resultSet.getString("last_name"));
            }

            System.out.println("\nPrint all students surnames:\n");
            surnames.forEach(System.out::println);

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

    }

}
