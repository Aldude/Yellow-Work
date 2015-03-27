package cli;
import sql.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class ChoiceState extends State
{
	private Map<Integer, State> choices;
	private boolean loop;
	
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
                /* TODO: This doesn't work when you input a non-numeric */
				num = in.nextInt();
			}
			while(!choices.containsKey(num) && num != -1);
			if(num == -1)
				return;
			
			choices.get(num).run(client);
		} while(loop);
		
	}
	
	public void setChoice(int n, State c)
	{
		choices.put(n, c);
	}

}
