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
				num = in.nextInt();
			}
			while(!choices.containsKey(num) && num != -1);
			if(num == -1)
				return;
			
			choices.get(num).run();
		} while(loop);
		in.close();
		
	}
	
	public void setChoice(int n, State c)
	{
		choices.put(n, c);
	}

}
