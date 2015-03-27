package cli;
import sql.Client;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;



public class ChoiceState extends State
{
	private Map<Integer, State> choices;
	private boolean loop;
	private int nextCode = 1;
	
	public ChoiceState(String d, boolean l)
	{
		super(d);
		loop = l;
		choices = new HashMap<Integer, State>();
	}

	@Override
	public void run(Client client)
	{
		int num;
		Scanner in = new Scanner(System.in);
		do
		{
			System.out.println("---------------------\n" + description);
			System.out.println("Please make a selection (-1 to exit):");
			System.out.println("---------------------");
			for(Integer i : choices.keySet())
			{
				System.out.println(i + ") " + choices.get(i).getDescription());
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
			if(num == -1)
				return;
			
			choices.get(num).run(client);
		} while(loop);
		
	}
	
	public void addChoice(State c)
	{
		choices.put(nextCode++, c);
	}

}
