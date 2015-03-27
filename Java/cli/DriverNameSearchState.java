package cli;

import java.sql.ResultSet;
import java.util.Scanner;

import sql.Client;
import sql.Searches;

/**
 * Serves as an option in the search engine menu to search for drivers by their name.
 */
public class DriverNameSearchState extends State {

    private Scanner scan = new Scanner(System.in);
    /* The SQL headers to display with the results */
    String[] headers = {"Name", "SIN", "Licence No", "Address", "Birthday", "Licence Class", "Conditions", "Expires"};

    /* Default constructor */
    public DriverNameSearchState() {
        super("Driver Search by Name");
    }

    @Override
    /* Executes the search and prints the results to the console */
    public void run(Client client) {
        System.out.println("Driver name to search for:");
        String name = scan.nextLine();
        ResultSet r = Searches.DriversWithLicenceByName(client, name);
        int[] columns = {1,2,3};
        UserSelection.printResults(r, columns, headers);
    }
}
