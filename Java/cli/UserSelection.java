package cli;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;


/*
 * Class used to get the user's selection of a row in a table.
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
	
	public static int getChoice(ResultSet result, String prompt, int[] columns)
	{
		int row, choice;
		Scanner in = new Scanner(System.in);
		
		try {
			result.first();
			row = 0;

			System.out.println(prompt + " (-1 to exit)");
			do {
				row++;
				System.out.print(row + " ) ");
				for(int col : columns)
					System.out.print(result.getString(col) + " ");
				System.out.println("");
			} while(result.next());
			
			do {
				choice = in.nextInt();
			} while(choice > row || choice == 0 || choice < -1);
			
			result.first();
			return choice;
			
		} catch (SQLException e) {
			System.out.println("getChoice :: Failed");
			System.out.println(e.getMessage());
		}
		
		return -2;
	}
	
	
}
