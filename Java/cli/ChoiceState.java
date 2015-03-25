package cli;
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
	public void run()
	{
		int num;
		do
		{
			System.out.println("Please make a selection (-1 to exit):");
			for(Integer i : choices.keySet())
			{
				System.out.println(i + ") " + choices.get(i).getDescription());
			}
			do {
				Scanner in = new Scanner(System.in);
				num = in.nextInt();
			}
			while(!choices.containsKey(num) && num != -1);
			if(num == -1)
				return;
			
			choices.get(num).run();
		} while(loop);
		
	}
	
	public void setChoice(int n, State c)
	{
		choices.put(n, c);
	}

}
