package cli;


public abstract class State
{	
	protected String description;
	
	public State(String d)
	{
		description = d;
	}
	
	public abstract void run();
	public String getDescription() {return description;}
}
