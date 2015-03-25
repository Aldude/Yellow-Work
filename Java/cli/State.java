package cli;


public abstract class State
{	
	private String description;
	
	public State(String d)
	{
		description = d;
	}
	
	public abstract void run();
	public String getDescription() {return description;}
}
