package cli;

import sql.Client;

/**
 * Created by Blake on 2015-03-26.
 */
public class DriverLicenceNoSearchState extends SearchState {

    public DriverLicenceNoSearchState() { super("Driver Search by Licence No"); }

    @Override
    public void run(Client client) {
        System.out.println("Licence number to search for:");
        String licence = scan.nextLine();
        /* TODO: Do something useful with the result */
        search.DriversByLicenceNo(client, licence);
    }
}
