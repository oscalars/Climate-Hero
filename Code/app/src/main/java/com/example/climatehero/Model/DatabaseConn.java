package com.example.climatehero.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConn {
    private Connection connection;
    private final String host = "ec2-176-34-234-47.eu-west-1.compute.amazonaws.com";
    private final String database = "de8p33ruqinm0a";
    private final int port = 5432;
    private final String user = "xcjrhryhuptyqz";
    private final String pass = "af46226d608e82d4c31344416df93de3b2c2e7695d3f53fb395f7000f054751f";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public DatabaseConn() {
                this.url = String.format(this.url, this.host, this.port, this.database);
                connect();
            }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;

                } catch (Exception e) {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();

        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);

        } catch(Exception e) {
            e.printStackTrace();
        } return c;
    }

    //This could be used for direct query of cloud database
    public String getBin (String label) throws SQLException{
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT category FROM Classification WHERE keyword = ?");
            ps.setString(1, label);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new String(rs.getBytes("category"));

        } catch (SQLException e) {
               return "Error getting result from database.";
        }
    }

    public ArrayList<String> getWholeDb() throws SQLException{
        try {
            ArrayList<String> db = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT keyword, category FROM Classification");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                db.add(new String(rs.getBytes("keyword")));
                db.add(new String(rs.getBytes("category")));
            }
            return db;

        } catch (SQLException e) {
            ArrayList<String> errorDB = new ArrayList<>();
            errorDB.add("Bad_request");
            return errorDB;
        }
    }

        public int getDatabaseVersion() throws SQLException{
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT db_version FROM Classification");
                ResultSet rs = ps.executeQuery();
                rs.next();

                return rs.getInt("db_version");

            } catch (SQLException e) {
                System.out.println("Error in getDatabaseVersion");
                return -1;
            }
        }
}




