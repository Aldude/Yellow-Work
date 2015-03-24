

public class DoNothingState implements State
{
	String description;
	public DoNothingState(String d)
	{
		description = d;
	}

	@Override
	public void run()
	{}

	@Override
	public String getDescription() { return description; }

}
