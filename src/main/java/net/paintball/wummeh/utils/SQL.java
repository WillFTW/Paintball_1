package net.paintball.wummeh.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Will on 10/3/2016.
 */
public class SQL {
    public static Connection conn;

    public static void init() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/YOURSQLDATABASE?autoReconnect=true", "USER", "PASSWORD");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
