package cli;


import sql.Client;

public class DoNothingState extends State
{
	public DoNothingState(String d)
	{
		super(d);
	}

	@Override
	public void run(Client client)
	{}

}
