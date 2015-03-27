package cli;

import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

import sql.Client;
import sql.Searches;
import sql.Updates;

public class ViolationRecordState extends State {

	public ViolationRecordState() {
		super("Violation Record");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(Client client) {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		
		DataCollector dc = new DataCollector("Enter Violation Information");
		
		GetDriverState gds = new GetDriverState(false, "Violator SIN");
		boolean ignoreViolatorSin = !dc.getBool("Enter a violator sin?");
		String violatorSin;
		if(ignoreViolatorSin) 
			violatorSin = "NULL";
		else
			violatorSin = gds.run(client);
		gds.setDescription("Officer SIN");
		String officerSin = gds.run(client);

		String vehicleSerialNo = dc.getString("Vehicle Serial No");
		ResultSet r = Searches.VehicleBySerialNo(client, vehicleSerialNo);
		try
		{
			while(!r.isBeforeFirst()) {
				out.println("Vehicle not found!");
				vehicleSerialNo = dc.getString("Vehicle Serial No");
				r = Searches.VehicleBySerialNo(client, vehicleSerialNo);
			}
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		r = Searches.TicketTypes(client);
		int[] cols = {1,2};
		int choice = UserSelection.getChoice(r, "Ticket Type", cols);
		String ticketType = null;
		try
		{
			r.absolute(choice);
			ticketType = r.getString(1);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date date = dc.getDate("Date");
		String location = dc.getString("Location");
		String description = dc.getString("Description");
		
		if(Updates.RegisterViolation(client, violatorSin, officerSin, vehicleSerialNo, ticketType, date, location, description))
			out.println("\n\nViolation registered successfully!\n");
	}

}
