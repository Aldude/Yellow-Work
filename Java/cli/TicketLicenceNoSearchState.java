package cli;

import sql.Client;
import sql.Searches;

import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by Blake on 2015-03-26.
 */
public class TicketLicenceNoSearchState extends State {

    private Scanner scan = new Scanner(System.in);

    public TicketLicenceNoSearchState() { super("Ticket Search by Licence No"); }

    @Override
    public void run(Client client) {
        System.out.println("Licence number to search for:");
        String licence = scan.nextLine();
        /* TODO: Do something useful with the result */
        ResultSet r = Searches.TicketsByLicenceNo(client, licence);
        int[] columns = {1,2,3,5};
        UserSelection.printResults(r, columns);
    }
}
