package com.example.climatehero.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DatabaseConn {
    private Connection connection;
    private final String host = "ec2-52-31-77-218.eu-west-1.compute.amazonaws.com";
    private final String database = "d19gf5000719oa";
    private final int port = 5432;
    private final String user = "bhijhvrkthxfrr";
    private final String pass = "1e9ebab668ba420a3cb71953bda55e4a0e74efc625bb295b920ca70a45241b16";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    /* These functions ensures singleton principle. */
    public DatabaseConn() {
                this.url = String.format(this.url, this.host, this.port, this.database);
                connect();
                //this.disconnect();
                System.out.println("connection status:" + status);
            }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected:" + status);
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }

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
    }

    /* To collect data from database use the code below*/
/*
   Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        DatabaseConn db = new DatabaseConn();
                        System.out.println((db.getBin("Plastic")));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
 */




