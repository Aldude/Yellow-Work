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
	
	public DataCollector() {}
	
	public DataCollector(String prompt) {
		out.println(prompt);
	}
	
	public String getString(String prompt) {
		out.print(prompt + ": ");
		return in.nextLine();
	}
	
	public char getGender() {
		out.print("Gender (m/f): ");
		while(true) {
			String input = in.nextLine();
			char first = input.charAt(0);
			if(first == 'm' || first == 'M')
				return 'm';
			else if(first == 'f' || first == 'F')
				return 'f';
			out.print("Please enter one of 'm' or 'f'");
		}
	}
	
	public boolean getBool(String prompt) {
		out.print(prompt + " (y/n): ");
		while(true) {
			String input = in.nextLine();
			char first = input.charAt(0);
			if(first == 'y' || first == 'Y')
				return true;
			else
				return false;
		}
	}
	
	public int getInt(String prompt) {
		out.print(prompt + ": ");
		while(true) {
			String input = in.nextLine();
			try {
				int result = Integer.parseInt(input);
				return result;
			} catch (NumberFormatException e) {
				out.println("Please enter a valid integer");
			}
		}
	}
	
	public double getDouble(String prompt) {
		out.print(prompt + ": ");
		while(true) {
			String input = in.nextLine();
			try {
				double result = Double.parseDouble(input);
				return result;
			} catch (NumberFormatException e) {
				out.println("Please enter a valid floating-point number");
			}
		}
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
