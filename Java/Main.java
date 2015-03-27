import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import sql.Client;
import sql.Searches;
import sql.Updates;
import cli.AddDriverState;
import cli.AutoTransactionState;
import cli.ChoiceState;
import cli.GetChoiceState;
import cli.LicenceRegistrationState;
import cli.NewVehicleState;
import cli.SearchState;
import cli.State;
import cli.UserSelection;
import cli.ViolationRecordState;

/**
 * Created by Blake on 2015-03-09.
 */
public class Main {
	public static final boolean CONNECT = true;	// make this false when testing at home
											// so we don't try to connect when we can't

    public static void main(String[] args) {
        LicenceRegistrationState lcs = new LicenceRegistrationState();
        try
		{
			byte[] array = lcs.extractBytes("greensquare.jpg");
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
        
        Client client = new Client();
        
        if(CONNECT && client.ConnectToDatabase() == 0) {
            client.InitializeDatabase();
            client.PopulateDatabase();
        }
        
        ChoiceState cs = new ChoiceState("Main Menu", true);
        State s1 = new NewVehicleState();
        State s2 = new AutoTransactionState();
        State s3 = new LicenceRegistrationState();
        State s4 = new ViolationRecordState();
        State s5 = new SearchState();
        cs.setChoice(1, s1);
        cs.setChoice(2, s2);
        cs.setChoice(3, s3);
        cs.setChoice(4, s4);
        cs.setChoice(5, s5);
        
        State autoRegistration = new State("New Vehicle Registration")
        {
			@Override
			public void run(Client client)
			{
				Scanner in = new Scanner(System.in);
				System.out.println("Please enter the vehicle's information:");
				System.out.print("Maker: ");
				String maker = in.next();
				System.out.print("Model: ");
				String model = in.next();
				System.out.print("Year: ");
				int year = in.nextInt();
				System.out.print("Color: ");
				String color = in.next();
			}
        	
        };

        cs.setChoice(6, autoRegistration);
        cs.run(client);
        
        Searches s = new Searches();
        Updates u = new Updates();
        
        ResultSet r = s.DriversWithLicenceBySimilarName(client, "ish");
        
        
        System.out.println("DriversBySimilarName:");
        try
		{
			while(r.next())
			{
			    System.out.println("Licence no: " + r.getString(2) + " Name: " + r.getString(1) + " BDay: " + r.getString(4));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
        
        r = s.DriversWithLicenceBySimilarName(client, "ish");
        
        int[] cols = {1,4};
        int selection = UserSelection.getChoice(r, "Select a driver", cols);
        
        try
		{
            r.absolute(selection);
			System.out.println("Selection: " + selection + " - " + r.getString(1));
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*
        r = s.DriversByName(client, "Sandra Fisher");
        
        System.out.println("DriversByName:");
        try
		{
			while(r.next())
			{
			    System.out.println("Licence no: " + r.getNString(2) + " Name: " + r.getNString(1));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
        
        r = s.DriversByLicenceNo(client, "97168-2113");
        System.out.println("DriversByLicenceNo:");
        try
		{
			while(r.next())
			{
			    System.out.println("Licence no: " + r.getNString(2) + " Name: " + r.getNString(1));
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
			    System.out.println("Licence no: " + r.getNString(2) + " Name: " + r.getNString(1));
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
        */
        if(CONNECT)
        	client.Terminate();
    }
}
