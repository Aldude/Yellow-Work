package cli;

import java.sql.ResultSet;

import sql.Client;
import sql.Searches;

/**
 * Created by Blake on 2015-03-26.
 */
public class DriverNameSearchState extends SearchState {

    public DriverNameSearchState() {
        super("Driver Search by Name");
    }

    @Override
    public void run(Client client) {
        System.out.println("Driver name to search for:");
        String name = scan.nextLine();
        /* TODO: Do something useful with the result */
        ResultSet r = Searches.DriversWithLicenceByName(client, name);
        int[] columns = {1,2,3};
        UserSelection.printResults(r,columns);
    }
}
