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
		
		out.println(getDescription());
		GetChoiceState gcs = new GetChoiceState("Find a Driver");
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
			DataCollector dc = new DataCollector("Enter SIN");
			return dc.getString("");
		} else if (choice == registerNew) {
			AddDriverState a = new AddDriverState();
			return a.run(client);
		}
		return null;
	}

}
