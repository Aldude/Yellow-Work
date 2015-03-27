package cli;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class DataCollector
{
	private Scanner in = new Scanner(System.in);
	private PrintStream out = System.out;
	
	public DataCollector(String prompt) {
		out.println(prompt);
	}
	
	public String getString(String prompt) {
		out.print(prompt + ": ");
		return in.nextLine();
	}
	
	public int getInt(String prompt) {
		out.print(prompt + ": ");
		while(in.hasNext()) {
			if(in.hasNextInt()) {
				int r = in.nextInt();
				return r;
			} else {
				out.println("Invalid integer - try again");
				in.next();
			}
		}
		
		// Should never get here.
		return -1;
	}
	
	public Date getDate(String prompt) {
		out.println(prompt + " format: (yyyy-mm-dd)");
		while(true) {
			String dateString = in.nextLine();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = new Date();
			try
			{
				date = format.parse(dateString);
				return date;
			} catch (ParseException e)
			{
				System.out.println("Error parsing date. Please try again.");
				//System.out.println(e.getMessage());
			}
		}
	}
}
