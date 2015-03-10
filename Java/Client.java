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

    private String dbURL = "jdbc:oracle:thin:@ui17.cs.ualberta.ca";
    private String user = "sakaluk@ualberta.ca";

    private Connection dbConn;
    public Statement statement;

    public int ConnectToDatabase() {
        System.out.println("Enter password for " + user);
        String pass = scan.nextLine();

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
            fileReader = new Scanner(new File("./p1_setup.sql"));
        } catch(FileNotFoundException e) {
            System.out.println("Setup file not found. Check local directory for p1_setup.sql.");
            System.out.println(e.toString());
            return;
        }

        while(fileReader.hasNextLine()) {
            line = fileReader.nextLine();

            try {
                statement.execute(line);
            } catch(SQLException e) {
                System.out.println("Command: " + line + " did not execute.");
                System.out.println(e.getSQLState());
            }
        }
    }

    public void PopulateDatabase() {
        String line = null;
        Scanner fileReader;

        try {
            fileReader = new Scanner(new File("./data.sql"));
        } catch(FileNotFoundException e) {
            System.out.println("Data file not found. Check local directory for data.sql.");
            System.out.println(e.toString());
            return;
        }

        while(fileReader.hasNextLine()) {
            line = fileReader.nextLine();

            try {
                statement.execute(line);
            } catch(SQLException e) {
                System.out.println("Command: " + line + " did not execute.");
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
