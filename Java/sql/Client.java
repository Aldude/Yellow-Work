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
    private Console console = System.console();

    //private String exampleDbUrl = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
    private String dbURL = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";

    private Connection dbConn;
    public Statement statement;
    public PreparedStatement preparedStatement;

    public int ConnectToDatabase() {
    	String user;
    	String pass;
    	
    	if(console == null) {
            System.out.println("Enter username:");
            user = scan.nextLine();
            System.out.println("Enter password:");
            pass = scan.nextLine();

            // Bunch of newlines to hide password after entering. Won't need if we use Console.readPassword().
            for (int i = 0; i < 50; i++)
                System.out.println("");
        }
        else {
            console.printf("Enter username: ");
            console.flush();
            user = console.readLine();
            console.printf("Enter password: ");
            console.flush();
            pass = console.readPassword().toString();
        }

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
