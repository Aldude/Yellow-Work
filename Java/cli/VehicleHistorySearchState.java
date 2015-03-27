package cli;

import sql.Client;
import sql.Searches;

import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Serves as an option in the search menu to allow users to lookup the history of a vehicle by its serial no.
 */
public class VehicleHistorySearchState extends State {

    private Scanner scan = new Scanner(System.in);
    /* The SQL headers to display with the results */
    private String[] headers = {"Serial No", "Sales", "Price", "Violations"};

    /* Default constructor */
    public VehicleHistorySearchState() {
        super("Vehicle History Search by Serial No");
    }

    @Override
    /* Executes the search and prints the results to the console */
    public void run(Client client) {
        System.out.println("Vehicle serial to search for:");
        String serial = scan.nextLine();
        /* TODO: Do something useful with the result */
        ResultSet r = Searches.VehicleHistory(client, serial);
        int[] columns = {1,2,3};
        UserSelection.printResults(r,columns, headers);
    }
}
