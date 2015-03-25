package sql;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by Blake on 2015-03-09.
 */
public class Searches {
    private Scanner scan = new Scanner(System.in);
    
    public ResultSet DriversBySimilarName(Client client, String name) {
        ResultSet rv = null;
        String query = "SELECT p.name, l.licence_no, p.addr, p.birthday, l.class, c.description, l.expiring_date " +
                "FROM people p, drive_licence l, restriction r, driving_condition c " +
                "WHERE p.sin = l.sin AND " +
                "r.licence_no = l.licence_no AND " +
                "r.r_id = c.c_id AND " +
                "LOWER(p.name) LIKE '%' || LOWER('" + name + "') || '%'";

        try {
            rv = client.statement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Search failed.");
            System.out.println(query);
            System.out.println(e.getMessage());
        }

        return rv;
    }

    public ResultSet DriversByName(Client client, String name) {
        ResultSet rv = null;
        String query = "SELECT p.name, l.licence_no, p.addr, p.birthday, l.class, c.description, l.expiring_date " +
                "FROM people p, drive_licence l, restriction r, driving_condition c " +
                "WHERE p.sin = l.sin AND " +
                "r.licence_no = l.licence_no AND " +
                "r.r_id = c.c_id AND " +
                "p.name = '" + name + "'";

        try {
            rv = client.statement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Search failed.");
            System.out.println(query);
            System.out.println(e.getMessage());
        }

        return rv;
    }

    public ResultSet DriversByLicenceNo(Client client, String licenceNo) {
        ResultSet rv = null;
        String query = "SELECT p.name, l.licence_no, p.addr, p.birthday, l.class, c.description, l.expiring_date " +
                "FROM people p, drive_licence l, restriction r, driving_condition c " +
                "WHERE p.sin = l.sin AND " +
                "r.licence_no = l.licence_no AND " +
                "r.r_id = c.c_id AND " +
                "l.licence_no = '" + licenceNo + "'";

        try {
            rv = client.statement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Search failed.");
            System.out.println(query);
            System.out.println(e.getMessage());
        }

        return rv;
    }

    public ResultSet TicketsBySIN(Client client, String sin) {
        ResultSet rv = null;
        String query = "SELECT ticket_no " +
                "FROM ticket " +
                "WHERE violator_no = '" + sin + "'";

        try {
            rv = client.statement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Search failed.");
            System.out.println(query);
            System.out.println(e.getMessage());
        }

        return rv;
    }

    public ResultSet TicketsByLicenceNo(Client client, String licenceNo) {
        ResultSet rv = null;
        String query = "SELECT t.ticket_no " +
                "FROM ticket t, people p, drive_licence l " +
                "WHERE t.violator_no = p.sin AND " +
                "p.sin = l.sin AND " +
                "l.licence_no	= '" + licenceNo + "'";

        try {
            rv = client.statement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Search failed.");
            System.out.println(query);
            System.out.println(e.getMessage());
        }

        return rv;
    }

    public ResultSet VehicleHistory(Client client, String serialNo) {
        ResultSet rv = null;
        String query = "SELECT v.serial_no, COUNT(s.transaction_id) AS \"Sales\", AVG(s.price) AS \"Price\", COUNT(t.ticket_no) AS \"Violations\" " +
                "FROM auto_sale s, vehicle v, ticket t " +
                "WHERE s.vehicle_id = v.serial_no AND " +
                "v.serial_no = t.vehicle_id	AND " +
                "v.serial_no = '" + serialNo + "' " +
                "GROUP BY v.serial_no";

        try {
            rv = client.statement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Search failed.");
            System.out.println(query);
            System.out.println(e.getMessage());
        }

        return rv;
    }
}