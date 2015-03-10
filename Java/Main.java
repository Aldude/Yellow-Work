/**
 * Created by Blake on 2015-03-09.
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client();

        if(client.ConnectToDatabase() == 0) {
            client.InitializeDatabase();
            client.PopulateDatabase();
        }
    }
}
