package cli;

import java.io.PrintStream;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import sql.Client;
import sql.Updates;


public class AddDriverState extends ReturningState<String>
{

	public AddDriverState()
	{

		super("Add a new driver");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String run(Client client)
	{
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		
		out.println("Enter the driver's information:");
		out.print("Sin: ");
		String sin = in.nextLine();
		out.print("Name: ");
		String name = in.nextLine();
		out.print("Height: ");
		double height = in.nextDouble();
		out.print("Weight: ");
		int weight = in.nextInt();
		out.print("Eye Color: ");
		in.nextLine(); // throw away extra newline
		String eyeColor = in.nextLine();
		out.print("Hair Color: ");
		String hairColor = in.nextLine();
		out.print("Address: ");
		String address = in.nextLine();
		out.print("Gender: ");
		String gender = in.nextLine();
		out.print("Birthday (format yyyy-mm-dd):");
		String bdayString = in.nextLine();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date birthday = new Date();
		try
		{
			birthday = format.parse(bdayString);
		} catch (ParseException e)
		{
			System.out.println("Error parsing date");
			System.out.println(e.getMessage());
		}
		
		Updates.AddPerson(client, sin, name, height, weight, eyeColor, hairColor, address, gender.charAt(0), birthday);
		
		return sin;
	}

}
