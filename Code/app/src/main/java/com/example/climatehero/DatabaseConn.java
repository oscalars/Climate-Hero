package com.example.climatehero;

import java.sql.*;

public class DatabaseConn {
    //Singleton
    private static DatabaseConn instance;
    private Connection c;


    /* These functions ensures singleton principle. */
    private DatabaseConn() {
        try {
            String url = "jdbc:postgresql://ec2-34-242-84-130.eu-west-1.compute.amazonaws.com/d7eaj1ale21ggd";
            String username = "awqmxaffunzrcw";
            String password = "0a7bdd60f2d0269a6389d84c91f4f0fb0f0a6f132be701eb238f6629235076e9";
            String url2;
            this.c = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            System.out.println("Error establishing database connection.");
        }
    }

    private static DatabaseConn getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConn();
        }
        return instance;
    }

    public static String getBin(String label) {
        try {
            PreparedStatement ps = getInstance().c.prepareStatement("SELECT category FROM Classification WHERE keyword = ?");
            ps.setString(1, label.toLowerCase());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String result = new String(rs.getBytes("category"));
            return result;

        } catch (SQLException e) {
            //System.out.println("Error getting result from database.");
            return "No result, throw in residual bin";
        }
    }
}
