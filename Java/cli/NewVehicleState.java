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
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		
		out.println("Enter the vehicle's information:");
		out.print("Serial No: ");
		String serialNo = in.nextLine();
		out.print("Maker: ");
		String maker = in.nextLine();
		out.print("Model: ");
		String model = in.nextLine();
		out.print("Year: ");
		int year = in.nextInt();
		out.print("Color: ");
		String color = in.nextLine();
		
		int[] columns = {2};
		int type = UserSelection.getChoice(Searches.VehicleTypes(client), "Vehicle Type", columns);
		
		/* Would be nice to make this use a ChoiceState or something similar */
		out.println("Primary Driver: ");
		GetDriverState g = new GetDriverState(true, "Get Driver SIN");
		String primarySin = g.run(client);
		System.out.println(primarySin);
		
		out.println("Secondary Drivers: ");
		String tempSecondarySin = null;
		ArrayList<String> secondarySins = new ArrayList<String>();
		tempSecondarySin = g.run(client);
		
		while(tempSecondarySin != null) {
			secondarySins.add(tempSecondarySin);
			tempSecondarySin = g.run(client);
		}
		
		Updates.RegisterVehicle(client, serialNo, maker, model, year, color, type, primarySin, secondarySins);
		
	}

}
