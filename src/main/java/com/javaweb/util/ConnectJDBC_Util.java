package com.javaweb.util;

import java.sql.*;

public class ConnectJDBC_Util {
    static final String DB_URL = "jdbc:mysql://localhost:3306/real_estate1";
    static final String USER = "root";
    static final String PASS = "Binbin29042004";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
