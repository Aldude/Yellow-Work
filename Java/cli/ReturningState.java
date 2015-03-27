package cli;


import sql.Client;

public abstract class ReturningState<T>
{	
	protected String description;
	
	public ReturningState(String d)
	{
		description = d;
	}
	
	public abstract T run(Client client);
	public String getDescription() {return description;}
	public void setDescription(String d) {description = d;}
}
