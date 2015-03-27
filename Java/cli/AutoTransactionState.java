package cli;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
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
		DataCollector dc = new DataCollector("Enter Transaction Information");
		
		String vehicleSerialNo = dc.getString("Vehicle Serial No");
		int price = dc.getInt("Price");
		Date today = dc.getDate("Transaction Date");
		
		GetDriverState gds = new GetDriverState(true, "Buyer SIN");
		String buyerSin = gds.run(client);
		gds.setDescription("Seller SIN");
		String sellerSin = gds.run(client);
		
		Updates.DoTransaction(client, sellerSin, buyerSin, vehicleSerialNo, today, price);
	}

}
