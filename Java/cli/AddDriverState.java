package cli;

import java.util.Date;
import sql.Client;
import sql.Updates;


public class AddDriverState extends ReturningState<String>
{
	private String autofillSin = null;

	public AddDriverState()
	{

		super("Add a new driver");
		// TODO Auto-generated constructor stub
	}
	
	public AddDriverState(String sin)
	{
		this();
		autofillSin = sin;
	}

	@Override
	public String run(Client client)
	{
		DataCollector dc = new DataCollector("Enter the driver's information");
		String sin;
		if(autofillSin == null)
			sin = dc.getString("SIN");
		else
			sin = autofillSin;
		String name = dc.getString("Name");
		double height = dc.getDouble("Height");
		int weight = dc.getInt("Weight");
		String eyeColor = dc.getString("Eye Color");
		String hairColor = dc.getString("Hair Color");
		String address = dc.getString("Address");
		char gender = dc.getGender();
		Date birthday = dc.getDate("Birthday");
		
		
		boolean success = Updates.AddPerson(client, sin, name, height, weight,
				eyeColor, hairColor, address, gender, birthday);
		
		if(success) {
			return sin;
		} else {
			System.out.println("Error creating driver (0 to quit)");
			int input = dc.getInt("Try again?");
			if(input != 0)
				return run(client);
			else
				return null;
		}
	}

}
