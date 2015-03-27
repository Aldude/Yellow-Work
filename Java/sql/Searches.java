package sql;
import java.sql.*;
import java.util.Scanner;

/**
 * Container class that executes the searches.
 */
public final class Searches {
    private static Scanner scan = new Scanner(System.in);
    /* Varable for testing/debugging */
    private static final boolean VERBOSE = false;
    
    private Searches() {}

    /* takes in the SqL query string and executes it */
    public static ResultSet doQuery(Client client, String query) {
    	ResultSet rv = null;

    	try {
            rv = client.statement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Search failed.");
            if(!VERBOSE)	// so we don't print it twice
            	System.out.println(query);
            System.out.println(e.getMessage());
        }

    	if(VERBOSE)
    		System.out.println(query);

        return rv;
    }
    
    public static ResultSet DriversBySimilarName(Client client, String name) {
        String query = "SELECT name, sin " +
        		"FROM people " +
        		"WHERE LOWER(name) LIKE '%' || LOWER('" + name + "') || '%'";
        
        return doQuery(client, query);
    }
    
    public static ResultSet DriversWithLicenceBySimilarName(Client client, String name) {
        String query = "SELECT p.name, p.sin, l.licence_no, p.addr, p.birthday, l.class, c.description, l.expiring_date " +
                "FROM people p, drive_licence l, restriction r, driving_condition c " +
                "WHERE p.sin = l.sin AND " +
                "r.licence_no = l.licence_no AND " +
                "r.r_id = c.c_id AND " +
                "LOWER(p.name) LIKE '%' || LOWER('" + name + "') || '%'";
        
        return doQuery(client, query);
    }

    public static ResultSet DriversWithLicenceByName(Client client, String name) {
        String query = "SELECT p.name, p.sin, l.licence_no, p.addr, p.birthday, l.class, c.description, l.expiring_date " +
                "FROM people p, drive_licence l, restriction r, driving_condition c " +
                "WHERE p.sin = l.sin AND " +
                "r.licence_no = l.licence_no AND " +
                "r.r_id = c.c_id AND " +
                "p.name = '" + name + "'";
        
        return doQuery(client, query);
    }

    public static ResultSet DriversByLicenceNo(Client client, String licenceNo) {
        String query = "SELECT p.name, p.sin, l.licence_no, p.addr, p.birthday, l.class, c.description, l.expiring_date " +
                "FROM people p, drive_licence l, restriction r, driving_condition c " +
                "WHERE p.sin = l.sin AND " +
                "r.licence_no = l.licence_no AND " +
                "r.r_id = c.c_id AND " +
                "l.licence_no = '" + licenceNo + "'";
        
        return doQuery(client, query);
    }

    public static ResultSet TicketsBySIN(Client client, String sin) {
        String query = "SELECT ticket_no " +
                "FROM ticket " +
                "WHERE violator_no = '" + sin + "'";

        return doQuery(client, query);
    }

    public static ResultSet TicketsByLicenceNo(Client client, String licenceNo) {
        String query = "SELECT t.ticket_no, t.vtype, p.name, p.sin, tt.fine " +
                "FROM ticket t, people p, drive_licence l, ticket_type tt " +
                "WHERE t.violator_no = p.sin AND " +
                "t.vtype = tt.vtype AND " +
                "p.sin = l.sin AND " +
                "l.licence_no	= '" + licenceNo + "'";

        return doQuery(client, query);
    }

    public static ResultSet VehicleHistory(Client client, String serialNo) {
        String query = "SELECT v.serial_no, COUNT(s.transaction_id) AS \"Sales\", AVG(s.price) AS \"Price\", COUNT(t.ticket_no) AS \"Violations\" " +
                "FROM auto_sale s, vehicle v, ticket t " +
                "WHERE s.vehicle_id = v.serial_no AND " +
                "v.serial_no = t.vehicle_id	AND " +
                "v.serial_no = '" + serialNo + "' " +
                "GROUP BY v.serial_no";

        return doQuery(client, query);
    }
    
    public static ResultSet VehicleTypes(Client client) {
    	String query = "SELECT * FROM vehicle_type";
    	
        return doQuery(client, query);
    }
    
    public static ResultSet TicketTypes(Client client) {
    	String query = "SELECT * FROM ticket_type";
    	
    	return doQuery(client, query);
    }
    
    public static ResultSet PersonWithSin(Client client, String sin) {
    	String query = "SELECT * FROM people WHERE sin = '" + sin + "'";
    	
    	return doQuery(client, query);
    }
    
    public static ResultSet VehicleBySerialNo(Client client, String serial_no) {
    	String query = "SELECT * FROM vehicle WHERE serial_no = '" + serial_no + "'";
    	
    	return doQuery(client, query);
    }
}