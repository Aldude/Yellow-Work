package cli;

import sql.Client;

import java.util.Scanner;

/**
 * Serves as an option in the main menu to allow the user to search the database.
 */
public class SearchState extends State {

    /* Default constructor */
	public SearchState() {
		super("Search");
	}

    /* Default constructor */
    public SearchState(String d) { super(d); }

	@Override
    /* Displays a menu on the console for the user to select search method */
	public void run(Client client) {
        ChoiceState cs = new ChoiceState("Search Engine", true);
        State s1 = new DriverNameSearchState();
        State s2 = new DriverLicenceNoSearchState();
        State s3 = new TicketLicenceNoSearchState();
        State s4 = new VehicleHistorySearchState();
        cs.addChoice(s1);
        cs.addChoice(s2);
        cs.addChoice(s3);
        cs.addChoice(s4);

        cs.run(client);
	}

}
