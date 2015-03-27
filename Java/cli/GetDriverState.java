package cli;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import sql.Client;
import sql.Searches;


public class GetDriverState extends ReturningState<String>
{
	private boolean addingAllowed;

	public GetDriverState(boolean a, String desc)
	{
		super(desc);
		// TODO Auto-generated constructor stub
		addingAllowed = a;
	}

	@Override
	public String run(Client client)
	{
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int choice;
		
		int search, manual;
		int registerNew = -2;
		
		GetChoiceState gcs = new GetChoiceState(description);
		search = gcs.addChoice("Search for an existing driver");
		manual = gcs.addChoice("Manually enter SIN");
			
		if(addingAllowed)
			registerNew = gcs.addChoice("Register a new driver");
			
			choice = gcs.run(client);
		if(choice == -1) {
			return null;
		} else if (choice == search) {
			System.out.print("Name to search for: ");
			String name = in.next();
			int[] cols = {1,2};
			Integer selection;
			ResultSet drivers = Searches.DriversBySimilarName(client,name);
			selection = UserSelection.getChoice(drivers,
					"Choose a name",
					cols);
			System.out.println("Selection: " + selection);
			try
			{
				drivers.absolute(selection.intValue());
				return drivers.getString(2);
			} catch (SQLException e)
			{
				System.out.println("Failed searching for drivers");
				System.out.println("Selection: " + selection);
				System.out.println(e.getMessage());
			}
		} else if (choice == manual) {
			DataCollector dc = new DataCollector();
			String sin = dc.getString("Enter SIN (leave empty to exit)");
			if(sin.equals("")) {
				System.out.println("Returning to: " + description);
				run(client);
			}
			ResultSet r = Searches.PersonWithSin(client, sin);
			try
			{
				while(!r.isBeforeFirst()) {
					System.out.println("No matches!");
					boolean create = dc.getBool("Create this person?");
					if(create) {
						AddDriverState a = new AddDriverState(sin);
						return a.run(client);
					} else {
						sin = dc.getString("Enter SIN (leave empty to exit)");
						if(sin.equals("")) {
							System.out.println("Returning to: " + description);
							run(client);
						}
						r = Searches.PersonWithSin(client, sin);
					}
				}
				return sin;
				
			} catch (SQLException e)
			{
				System.out.println("Exception during query");
				System.out.println(e.getMessage());
				run(client);
			}
			
		} else if (choice == registerNew) {
			AddDriverState a = new AddDriverState();
			return a.run(client);
		}
		return null;
	}

}
