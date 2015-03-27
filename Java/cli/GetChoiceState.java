package cli;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import sql.Client;


public class GetChoiceState extends ReturningState<Integer>
{
	private HashMap<Integer, String> choices = new HashMap<Integer,String>();
	private int nextCode = 1;

	public GetChoiceState(String d)
	{
		super(d);
	}
	
	public int addChoice(String description)
	{
		choices.put(nextCode, description);
		return nextCode++;
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
			try {
				num = in.nextInt();
			} catch (InputMismatchException e) {
				in.next();
				num = -2;
			}
		}
		while(!choices.containsKey(num) && num != -1);
		return num;
	}

}
