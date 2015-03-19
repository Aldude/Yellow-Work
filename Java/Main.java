import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        
        Searches s = new Searches();
        Updates u = new Updates();
        
        ResultSet r = s.DriversBySimilarName(client, "ish");
        try
		{
			while(r.next())
			{
			    System.out.println("Sin: " + r.getNString(2) + " Name: " + r.getNString(1));
			}
		} catch (SQLException e)
		{
			System.out.println("Exception:");
			System.out.println(e.getMessage());
		}
    }
}
