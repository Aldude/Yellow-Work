import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;



public class SqlFileScanner
{
	private Scanner fileScanner;
	
	public SqlFileScanner(File file) throws FileNotFoundException
	{
		fileScanner = new Scanner(file);
		fileScanner.useDelimiter(Pattern.compile(";"));
	}
	
	public SqlFileScanner(String fileName) throws FileNotFoundException
	{
		this(new File(fileName));
	}
	
	public boolean hasNextUpdate()
	{
		return fileScanner.hasNext();
	}
	
	public String nextUpdate()
	{
		return fileScanner.next();
	}
	
}
