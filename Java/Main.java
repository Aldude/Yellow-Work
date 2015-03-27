import sql.Client;
import cli.AutoTransactionState;
import cli.ChoiceState;
import cli.LicenceRegistrationState;
import cli.NewVehicleState;
import cli.SearchState;
import cli.State;
import cli.ViolationRecordState;

/**
 * Created by Blake on 2015-03-09.
 */
public class Main {
	public static final boolean CONNECT = true;	// make this false when testing at home
													// so we don't try to connect when we can't

    public static void main(String[] args) {    	
        Client client = new Client();
        
        if(CONNECT && client.ConnectToDatabase() == 0) {
            client.InitializeDatabase();
            client.PopulateDatabase();
        } else {
        	return;
        }
        
        ChoiceState cs = new ChoiceState("Main Menu", true);
        State s1 = new NewVehicleState();
        State s2 = new AutoTransactionState();
        State s3 = new LicenceRegistrationState();
        State s4 = new ViolationRecordState();
        State s5 = new SearchState();
        cs.addChoice(s1);
        cs.addChoice(s2);
        cs.addChoice(s3);
        cs.addChoice(s4);
        cs.addChoice(s5);
        
        cs.run(client);
        
        if(CONNECT)
        	client.Terminate();
    }
}
