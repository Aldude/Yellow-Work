package cli;

import java.sql.ResultSet;
import java.util.Scanner;

import sql.Client;
import sql.Searches;

/**
 * Serves as an option in the search engine menu to search for drivers by their Licence No.
 */
public class DriverLicenceNoSearchState extends State {

    private Scanner scan = new Scanner(System.in);
    /* The SQL headers to display with the results */
    String[] headers = {"Name", "SIN", "Licence No", "Address", "Birthday", "Licence Class", "Conditions", "Expires"};

    /* Default constructor */
    public DriverLicenceNoSearchState() { super("Driver Search by Licence No"); }

    @Override
    /* Executes the search and prints the results to the console */
    public void run(Client client) {
        System.out.println("Licence number to search for:");
        String licence = scan.nextLine();
        ResultSet r = Searches.DriversByLicenceNo(client, licence);
        int[] columns = {1,2};
        UserSelection.printResults(r, columns, headers);
    }
}
