package cli;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
		
		out.println("Enter Violation Information:");
		GetDriverState gds = new GetDriverState(false, "Violator SIN");
		String violatorSin = gds.run(client);
		gds.setDescription("Officer SIN");
		String officerSin = gds.run(client);
		out.print("Vehicle SIN: ");
		String vehicleId = in.nextLine();
		
		Searches s = new Searches();
		ResultSet r = s.TicketTypes(client);
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
		out.print("Date (format yyyy-mm-dd):");
		String bdayString = in.nextLine();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = new Date();
		try
		{
			date = format.parse(bdayString);
		} catch (ParseException e)
		{
			System.out.println("Error parsing date");
			System.out.println(e.getMessage());
		}
		
		out.print("Location: ");
		String location = in.nextLine();
		out.print("Description: ");
		String description = in.nextLine();
		
		Updates u = new Updates();
		u.RegisterViolation(client, violatorSin, officerSin, vehicleId, ticketType, date, location, description);
	}

}
