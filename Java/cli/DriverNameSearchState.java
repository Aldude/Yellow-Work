package cli;

import sql.Client;

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
        search.DriversWithLicenseByName(client, name);
    }
}
