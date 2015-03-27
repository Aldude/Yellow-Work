package cli;

import sql.Client;

import java.util.Scanner;

public class SearchState extends State {
    protected Scanner scan = new Scanner(System.in);

	public SearchState() {
		super("Search");
		// TODO Auto-generated constructor stub
	}

    public SearchState(String d) { super(d); }

	@Override
	public void run(Client client) {
		// TODO Auto-generated method stub
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
