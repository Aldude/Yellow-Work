package cli;


import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import sql.Client;
import sql.Searches;
import sql.Updates;

public class AutoTransactionState extends State {

	public AutoTransactionState() {
		super("Auto Transaction");
	}

	@Override
	public void run(Client client) {
		DataCollector dc = new DataCollector("Enter Transaction Information");
		
		String vehicleSerialNo = dc.getString("Vehicle Serial No");
		int price = dc.getInt("Price");
		Date today = dc.getDate("Transaction Date");
		
		GetDriverState gds = new GetDriverState(true, "Buyer SIN");
		String buyerSin = gds.run(client);
		gds = new GetDriverState(false, "Seller SIN");
		String sellerSin = gds.run(client);

        ResultSet r = Searches.VehicleWithOwner(client, sellerSin, vehicleSerialNo);
        Boolean cont = false;

        try {
            cont = r.isBeforeFirst();
        } catch (SQLException e) {
            System.out.println("Error with isBeforeFirst call");
            System.out.println(e.getSQLState());
            return;
        }

        if(!cont) {
            cont = dc.getBool("Seller does not own this vehicle. Try again?");

            if(!cont) {
                return;
            }
        }

		if(Updates.DoTransaction(client, sellerSin, buyerSin, vehicleSerialNo, today, price))
			System.out.println("\n\nTransaction successful!\n");
	}

}
