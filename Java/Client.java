import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
            statement = dbConn.createStatement();
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

    public void InitializeDatabase() {
        String line = null;
        Scanner fileReader;

        try {
            fileReader = new Scanner(new File("SQL/p1_setup.sql"));
        } catch(FileNotFoundException e) {
            System.out.println("Setup file not found. Check local directory for p1_setup.sql.");
            System.out.println(e.toString());
            return;
        }
        
        StringBuffer sb = new StringBuffer();
        
        while(fileReader.hasNextLine()) {
            line = fileReader.nextLine();
            sb.append(line + "\n");
        }
        try {
	        statement.execute(sb.toString());
	    } catch(SQLException e) {
	        System.out.println("Setup did not execute.");
	        System.out.println(e.getSQLState());
	    }
    }

    public void PopulateDatabase() {
        String line = null;
        Scanner fileReader;

        try {
            fileReader = new Scanner(new File("SQL/data.sql"));
        } catch(FileNotFoundException e) {
            System.out.println("Data file not found. Check local directory for data.sql.");
            System.out.println(e.toString());
            return;
        }
        StringBuffer sb = new StringBuffer();
        
        while(fileReader.hasNextLine()) {
            line = fileReader.nextLine();
            sb.append(line + "\n");
        }
        try {
	        statement.execute(sb.toString());
	    } catch(SQLException e) {
	        System.out.println("Populate did not execute.");
	        System.out.println(e.getSQLState());
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
