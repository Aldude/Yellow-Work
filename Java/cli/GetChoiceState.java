package cli;

import java.util.HashMap;
import java.util.Scanner;

import sql.Client;


public class GetChoiceState extends ReturningState<Integer>
{
	private HashMap<Integer, String> choices = new HashMap<Integer,String>();

	public GetChoiceState(String d)
	{
		super(d);
	}
	
	public void addChoice(Integer number, String description)
	{
		choices.put(number, description);
	}

	@Override
	public Integer run(Client client)
	{
		Integer num;
		Scanner in = new Scanner(System.in);
		System.out.println("---------------------\n" + description);
		System.out.println("Please make a selection (-1 to exit):");
		System.out.println("---------------------");
		for(Integer i : choices.keySet())
		{
			System.out.println(i + ") " + choices.get(i));
		}
		do {
            /* TODO: This doesn't work when you input a non-numeric */
			num = in.nextInt();
		}
		while(!choices.containsKey(num) && num != -1);
		return num;
	}

}
