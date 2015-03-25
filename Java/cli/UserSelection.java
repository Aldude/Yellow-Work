package cli;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;


/*
 * Class used to get the user's selection for an option that references a table of codes.
 * 
 * For example, vehicle type. Rather than have a user type a number, this class provides
 * a prompt of the following form:
 * 
 * Select a vehicle type:
 * 1) SUV
 * 2) Minivan
 * 3) Sedan
 * ...
 * 
 */
public class UserSelection
{
	private ResultSet result;
	private String prompt;
	private int cColumn;
	private int dColumn;
	
	public UserSelection(ResultSet r, String p, int cCol, int dCol)
	{
		result = r;
		prompt = p;
		cColumn = cCol;
		dColumn = dCol;
	}
	
	public int getChoice()
	{
		HashMap<Integer, String> options = new HashMap<Integer, String>();
		int num;
		Scanner in = new Scanner(System.in);
		try {
			result.first();
			
			do {
				Integer code = result.getInt(cColumn);
				String desc = result.getString(dColumn);
				options.put(code, desc);
			} while(result.next());
			
			System.out.println(prompt + " (-1 to exit)");
			for(Integer code : options.keySet())
			{
				System.out.println(code + " ) " + options.get(code));
			}
			
			do {
				num = in.nextInt();
			}
			while(!options.containsKey(num) && num != -1);
			in.close();
			return num;
			
		} catch (SQLException e) {
			System.out.println("getChoice :: Failes");
			System.out.println(e.getMessage());
		}
		
		in.close();
		return -2;
	}
	
	
}
