package db;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataSource {

        static Connection getConnection() throws Exception {

            String url = "jdbc:mysql://localhost:3306/chatdb";
            String username = "root";
            String password = "admin";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        }
    }
