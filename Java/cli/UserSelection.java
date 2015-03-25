package cli;
import java.sql.ResultSet;



public class UserSelection
{
	private ResultSet result;
	private int column;
	
	public UserSelection(ResultSet r, int col)
	{
		result = r;
		column = col;
	}
	
	
}
