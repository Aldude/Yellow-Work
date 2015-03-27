package cli;

import sql.Client;
import sql.Searches;

import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Serves as an option in the search menu to allow the user to find the tickets associated with a licence no.
 */
public class TicketLicenceNoSearchState extends State {

    private Scanner scan = new Scanner(System.in);
    /* The SQL headers to display with the results */
    private String[] headers = {"Ticket", "Violation", "Name", "SIN", "Fine"};

    /* Default constructor */
    public TicketLicenceNoSearchState() { super("Ticket Search by Licence No"); }

    @Override
    /* Executes the search and prints the results to the console */
    public void run(Client client) {
        System.out.println("Licence number to search for:");
        String licence = scan.nextLine();
        /* TODO: Do something useful with the result */
        ResultSet r = Searches.TicketsByLicenceNo(client, licence);
        int[] columns = {1,2,3,5};
        UserSelection.printResults(r, columns, headers);
    }
}
