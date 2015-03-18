import java.io.FileNotFoundException;

/**
 * Created by Blake on 2015-03-09.
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client();

        SqlFileScanner s = null;
		try
		{
			s = new SqlFileScanner("SQL/p1_setup.sql");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(s != null)
        {
	        System.out.println(s.nextUpdate());
	        System.out.println(s.nextUpdate());
        }
        
        if(client.ConnectToDatabase() == 0) {
            client.InitializeDatabase();
            client.PopulateDatabase();
        }
    }
}
