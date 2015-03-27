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
        ChoiceState cs = new ChoiceState("Main Menu", true);
        State s1 = new DriverNameSearchState();
        State s2 = new DriverLicenceNoSearchState();
        State s3 = new TicketLicenceNoSearchState();
        cs.setChoice(1, s1);
        cs.setChoice(2, s2);
        cs.setChoice(3, s3);

        cs.run(client);
	}

}
