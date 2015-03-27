package cli;

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
		Searches s = new Searches();
		int choice;
		
		if(addingAllowed) {
			GetChoiceState gcs = new GetChoiceState("Find a Driver");
			gcs.addChoice(1, "Search for an existing driver");
			gcs.addChoice(2, "Register a new driver");
			
			choice = gcs.run(client);
		} else {
			choice = 1;
		}
		switch(choice) {
			case -1:
				return null;
			case 1:
				System.out.print("Name to search for: ");
				String name = in.next();
				int[] cols = {1,2};
				Integer selection;
				ResultSet drivers = s.DriversBySimilarName(client,name);
				UserSelection.printResults(drivers, cols);
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
				break;
			case 2:
				AddDriverState a = new AddDriverState();
				return a.run(client);
		}
		return null;
	}

}
