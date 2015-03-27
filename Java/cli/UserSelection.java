package cli;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
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
public final class UserSelection
{
	private static final int EXHAUSTED_RESULTSET = 17011;
	
	private UserSelection() {}
	
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
				try {
					choice = in.nextInt();
				} catch (InputMismatchException e) {
					in.next();
					choice = -2;
				}
			} while(choice > row || choice == 0 || choice < -1);
			
			result.first();
			return choice;
			
		} catch (SQLException e) {
			System.out.println("getChoice :: Failed");
			System.out.println(e.getMessage());
		}
		
		return -2;
	}
	
	public static void printResults(ResultSet result, int[] columns, String[] headers)
	{
        for(int col : columns)
            System.out.printf("%-9s ", headers[col-1]);
        System.out.println();

		try {
			result.first();
			do {
				for(int col : columns)
					System.out.printf("%-9s ", result.getString(col));
				System.out.println();
			} while(result.next());
		} catch (SQLException e) {
			if(e.getErrorCode() == EXHAUSTED_RESULTSET)
				System.out.println("No Results Found!");
			else {
				System.out.println("printResults :: Failed");
				System.out.println(e.getMessage());
			}
		}
	}
	
}
