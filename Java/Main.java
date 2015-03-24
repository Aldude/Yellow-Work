import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Blake on 2015-03-09.
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        
        if(client.ConnectToDatabase() == 0) {
            client.InitializeDatabase();
            client.PopulateDatabase();
        }
        
        Searches s = new Searches();
        Updates u = new Updates();
        
        ResultSet r = s.DriversBySimilarName(client, "ish");
        
        System.out.println("DriversBySimilarName:");
        try
		{
			while(r.next())
			{
			    System.out.println("License no: " + r.getNString(2) + " Name: " + r.getNString(1));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
        r = s.DriversByName(client, "Sandra Fisher");
        
        System.out.println("DriversByName:");
        try
		{
			while(r.next())
			{
			    System.out.println("License no: " + r.getNString(2) + " Name: " + r.getNString(1));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
        
        r = s.DriversByLicenceNo(client, "97168-2113");
        System.out.println("DriversByLicenseNo:");
        try
		{
			while(r.next())
			{
			    System.out.println("License no: " + r.getNString(2) + " Name: " + r.getNString(1));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
        
        r = s.TicketsByLicenceNo(client, "97168-2113");
        System.out.println("TicketsByLicenceNo:");

        try
		{
			while(r.next())
			{
			    System.out.println("Ticket no: " + r.getNString(1));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
		
        r = s.TicketsBySIN(client, "555-666-005");
        System.out.println("TicketsBySIN:");

        try
		{
			while(r.next())
			{
			    System.out.println("Ticket no: " + r.getNString(1));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
        
        r = s.VehicleHistory(client, "0104-826-01665");
        System.out.println("VehicleHistory:");
        
        try
		{
			while(r.next())
			{
			    System.out.println("Serial no: " + r.getNString(1) + "  Sales: " + r.getInt(2) +
			    		"  Price: " + r.getDouble(3) + "  Violations: " + r.getInt(4));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
        

    	u.AddPerson(client, "555-666-011", "Bob Bobson", 1.86, 89, "Blue", "Blonde", "123 Bob Road", 'm', new Date(87, 1, 6));
    	r = s.DriversBySimilarName(client, "Bob");
        
        System.out.println("DriversBySimilarName (Bob Bobson):");
        try
		{
			while(r.next())
			{
			    System.out.println("License no: " + r.getNString(2) + " Name: " + r.getNString(1));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
        
        u.RegisterVehicle(client,"1234-567-89098", "Honda", "Odyssey", 2005, "Grey", 6, "555-666-011", new ArrayList<String>());
        u.RegisterVehicle(client,"0234-567-89098", "Honda", "Odyssey", 2005, "Grey", 6, "555-666-011", new ArrayList<String>());
        u.DoTransaction(client,"555-666-011", "555-666-001", "1234-567-89098", new Date(115, 1, 1), 19000);
        u.RegisterViolation(client, "555-666-011", "555-666-001", "1234-567-89098", "Parking", new Date(115,1,1), "123 road", "afsd");
        
        client.Terminate();
    }
}
