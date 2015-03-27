package cli;

import java.sql.ResultSet;
import java.util.Scanner;

import sql.Client;
import sql.Searches;

/**
 * Created by Blake on 2015-03-26.
 */
public class DriverLicenceNoSearchState extends State {

    private Scanner scan = new Scanner(System.in);

    public DriverLicenceNoSearchState() { super("Driver Search by Licence No"); }

    @Override
    public void run(Client client) {
        System.out.println("Licence number to search for:");
        String licence = scan.nextLine();
        /* TODO: Do something useful with the result */
        ResultSet r = Searches.DriversByLicenceNo(client, licence);
        int[] columns = {1,2};
        UserSelection.printResults(r, columns);
    }
}
