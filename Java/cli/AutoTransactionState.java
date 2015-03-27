package cli;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Scanner;

import sql.Client;
import sql.Updates;

public class AutoTransactionState extends State {

	public AutoTransactionState() {
		super("Auto Transaction");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(Client client) {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		
		out.println("Enter Transaction Information");
		out.print("Vehicle Serial No: ");
		String vehicleSerialNo = in.nextLine();
		GetDriverState gds = new GetDriverState(false, "Buyer SIN");
		String buyerSin = gds.run(client);
		gds.setDescription("Seller SIN");
		String sellerSin = gds.run(client);
		java.util.Date today = Calendar.getInstance().getTime();
		out.print("Price: ");
		int price = in.nextInt();
		
		Updates u = new Updates();
		u.DoTransaction(client, sellerSin, buyerSin, vehicleSerialNo, today, price);
	}

}
