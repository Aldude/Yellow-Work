package cli;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import sql.Client;
import sql.Searches;
import sql.Updates;

public class NewVehicleState extends State {

	public NewVehicleState() {
		super("New Vehicle Registration");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(Client client) {
		PrintStream out = System.out;
		DataCollector dc = new DataCollector("Enter the vehicle's information");
		
		String serialNo = dc.getString("Vehicle Serial No");
		String maker = dc.getString("Maker");
		String model = dc.getString("Model");
		int year = dc.getInt("Year");
		String color = dc.getString("Color");
		
		int[] columns = {2};
		int type = UserSelection.getChoice(Searches.VehicleTypes(client), "Vehicle Type", columns);
		
		/* Would be nice to make this use a ChoiceState or something similar */
		GetDriverState g = new GetDriverState(true, "Primary Driver");
		String primarySin = g.run(client);
		System.out.println(primarySin);
		
		g.setDescription("Secondary Drivers");
		String tempSecondarySin = null;
		ArrayList<String> secondarySins = new ArrayList<String>();
		tempSecondarySin = g.run(client);
		
		while(tempSecondarySin != null) {
			secondarySins.add(tempSecondarySin);
			tempSecondarySin = g.run(client);
		}
		
		if(Updates.RegisterVehicle(client, serialNo, maker, model, year, color, type, primarySin, secondarySins));
			out.println("New vehicle registation successful!");
	}

}
