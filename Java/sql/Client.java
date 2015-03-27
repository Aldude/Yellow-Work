package sql;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by Blake on 2015-03-09.
 */
public class Client {
    private Scanner scan = new Scanner(System.in);

    //private String exampleDbUrl = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
    private String dbURL = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";

    private Connection dbConn;
    public Statement statement;
    public PreparedStatement preparedStatement;

    public int ConnectToDatabase() {
    	/*
    	 * If we run this in the console, this should be modified to use Console.readLine() and Console.readPassword().
    	 * However, these don't work while running in IDEs, since System.console() returns null.
    	 */
    	
        System.out.println("Enter username:");
    	String user = scan.nextLine();
    	System.out.println("Enter password:");
        String pass = scan.nextLine();
        
        // Bunch of newlines to hide password after entering. Won't need if we use Console.readPassword().
        for(int i = 0; i < 50; i++)
        	System.out.println("");
        

        try {
            dbConn = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            System.out.println(e.getSQLState());
            return 1;
        }

        try {
            statement = dbConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            System.out.println("Statement creation failed.");
            System.out.println(e.getSQLState());
            return 2;
        }


        try {
            dbConn.setAutoCommit(true);
        } catch(SQLException e) {
            System.out.println("Autocommit not initialized.");
            System.out.println(e.getSQLState());
        }

        return 0;
    }
    
    public void PrepareStatement(String s) {
    	try
		{
			preparedStatement = dbConn.prepareStatement(s, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e)
		{
			System.out.println("PrepareStatement :: Failed!");
			System.out.println(e.getMessage());
		}
    }
    
    public Blob CreateBlob() {
    	Blob b = null;
    	try
		{
			b = dbConn.createBlob();
		} catch (SQLException e)
		{
			System.out.println("CreateBlob :: Failed!");
			System.out.println(e.getMessage());
		}
    	return b;
    }

    public void InitializeDatabase() {
        String update = null;
        SqlFileScanner sqlFile;

        try {
            sqlFile = new SqlFileScanner(new File("SQL/p1_setup.sql"));
        } catch(FileNotFoundException e) {
            System.out.println("Setup file not found. Check local directory for p1_setup.sql.");
            System.out.println(e.toString());
            return;
        }
        
        
        while(sqlFile.hasNextUpdate()) {
            update = sqlFile.nextUpdate();
            try {
    	        statement.execute(update);
    	    } catch(SQLException e) {
    	        System.out.println("InitializeDatabase :: The following update did not execute:");
    	        System.out.println(update);
    	        System.out.println(e.getMessage());
    	        System.out.println(e.getSQLState());
    	    }
        }
    }

    public void PopulateDatabase() {
        String update = null;
        SqlFileScanner sqlFile;

        try {
            sqlFile = new SqlFileScanner(new File("SQL/data.sql"));
        } catch(FileNotFoundException e) {
            System.out.println("Data file not found. Check local directory for data.sql.");
            System.out.println(e.toString());
            return;
        }
        
        while(sqlFile.hasNextUpdate()) {
            update = sqlFile.nextUpdate();
            try {
    	        statement.execute(update);
    	    } catch(SQLException e) {
    	        System.out.println("PopulateDatabase :: The following update did not execute:");
    	        System.out.println(update);
    	        System.out.println(e.getMessage());
    	        System.out.println(e.getSQLState());
    	    }
        }
    }

    public void Terminate() {
        try {
            dbConn.close();
        } catch(SQLException e) {
            System.out.println("Connection closing failed.");
            System.out.println(e.getSQLState());
        }
    }
}
