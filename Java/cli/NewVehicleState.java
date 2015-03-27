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
		
		int[] columns = {2};
		Searches s = new Searches();
		int type = UserSelection.getChoice(s.VehicleTypes(client), "Vehicle Type", columns);
		out.println(type);
	}

}
