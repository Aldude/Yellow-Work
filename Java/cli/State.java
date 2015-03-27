package cli;


import sql.Client;

public abstract class State
{	
	protected String description;
	
	public State(String d)
	{
		description = d;
	}
	
	public abstract void run(Client client);
	public String getDescription() {return description;}
	public void setDescription(String desc) {description = desc;}
}
