package cli;

import sql.Client;
import sql.Searches;

import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by Blake on 2015-03-27.
 */
public class VehicleHistorySearchState extends State {

    private Scanner scan = new Scanner(System.in);

    public VehicleHistorySearchState() {
        super("Vehicle History Search by Serial No");
    }

    @Override
    public void run(Client client) {
        System.out.println("Vehicle serial to search for:");
        String serial = scan.nextLine();
        /* TODO: Do something useful with the result */
        ResultSet r = Searches.VehicleHistory(client, serial);
        int[] columns = {1,2,3};
        UserSelection.printResults(r,columns);
    }
}
