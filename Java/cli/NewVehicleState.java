package cli;

import java.io.PrintStream;
import java.util.Scanner;

import sql.Client;
import sql.Searches;

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
		out.print("Maker: ");
		String maker = in.next();
		out.print("Model: ");
		String model = in.next();
		out.print("Year: ");
		int year = in.nextInt();
		out.print("Color: ");
		String color = in.next();
		
		Searches s = new Searches();
		int[] columns = {2};
		int type = UserSelection.getChoice(s.VehicleTypes(client), "Vehicle Type", columns);
		
		/* Would be nice to make this use a ChoiceState or something similar */
		out.println("Primary Driver: ");
		GetDriverState g = new GetDriverState();
		String primarySin = g.run(client);
		System.out.println(primarySin);
		
	}

}
