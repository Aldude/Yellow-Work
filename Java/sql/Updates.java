package sql;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.List;
import java.util.Date;

public final class Updates {
    private static final boolean VERBOSE = false;
    
	private static int nextTransactionId = 22;
	private static int nextTicketNo = 10;
	
	private Updates() {}
	
	public static boolean doUpdate(Client client, String update) {
		try {
			client.statement.executeUpdate(update);
		} catch(SQLException e) {
			System.out.println("DoUpdate :: failed");
			if(!VERBOSE)
				System.out.println(update);
			System.out.println(e.getMessage());
			return false;
		}
		if(VERBOSE)
			System.out.println(update);
		return true;
	}
	 
	public static boolean AddPerson(Client client,
			String sin,
			String name,
			double height,
			int weight,
			String eyecolor,
			String haircolor,
			String addr,
			char gender,
			Date birthday )
	{
		String update = "INSERT into people " +
				"VALUES( '" + sin + "', '" + name + "', " + height + ", " + weight + ", '" + eyecolor + "', '" + haircolor +
				"', '" + addr + "', '" + gender + "', " + getSqlDateString(birthday) + ")";
		
		return doUpdate(client, update);
	}
	 
	public static boolean RegisterVehicle(Client client,
			String serialNo,
			String maker,
			String model,
			int year,
			String color,
			int typeId,
			String primaryOwnerSin,
			List<String> secondaryOwnerSins )
	{
		String update = "INSERT into vehicle " +
				"VALUES( '" + serialNo + "', '" + maker + "', '" + model + "', " + year + ", '" + color + "', " + typeId + ")";
		if(!doUpdate(client, update))
			return false;
		
		update = "INSERT into owner " +
				"VALUES( '" + primaryOwnerSin + "', '" + serialNo + "', 'y')";
		if(!doUpdate(client, update))
			return false;
		 
		for(String secondaryOwnerSin : secondaryOwnerSins)
		{
			update = "INSERT into owner " +
					"VALUES( '" + secondaryOwnerSin + "', '" + serialNo + "', 'n')";
			if(!doUpdate(client, update))
				return false;
		}
		return true;
	}
	 
	public static boolean DoTransaction(Client client,
			String sellerSin,
			String buyerSin,
			String vehicleSerialNo,
			Date saleDate,
			int price)
	{
		String update = "DELETE " +
				"FROM owner " +
				"WHERE vehicle_id = '" + vehicleSerialNo + "'";
		if(!doUpdate(client, update))
			return false;
		
		update = "INSERT into owner " +
				"VALUES( '" + buyerSin + "', '" + vehicleSerialNo + "', 'y')";
		if(!doUpdate(client, update))
			return false;
		
		update = "INSERT into auto_sale " +
				"VALUES( " + nextTransactionId++ + ", '" + sellerSin + "', '" + buyerSin + "', '" + vehicleSerialNo +
				"', " + getSqlDateString(saleDate) + ", " + price + ")";
		return doUpdate(client, update);
		 
	 }
	
	public static boolean RegisterLicence(Client client,
			String licenceNo,
			String driverSin,
			String licenceClass,
			byte[] photo,
			Date issuingDate,
			Date expiringDate)
	{
		String update = "INSERT into drive_licence " +
				"VALUES( '" + licenceNo + "', '" + driverSin + "', '" + licenceClass + "', ?, " +
				getSqlDateString(issuingDate) + ", " + getSqlDateString(expiringDate) + ")";
		if(VERBOSE)
			System.out.println(update);
		
		try {
			client.PrepareStatement(update);
			client.preparedStatement.setBinaryStream(1,new ByteArrayInputStream(photo),photo.length);
		} catch(SQLException e) {
			System.out.println("Registerlicence :: INSERT into drive_licence failed");
			System.out.println(e.getMessage());
			if(!VERBOSE)
				System.out.println(update);
			return false;
		}
		return true;
	}
	
	public static boolean RegisterViolation(Client client,
			String violatorSin,
			String officerSin,
			String vehicleSerialNo,
			String type,
			Date date,
			String location,
			String description)
	{
		String update = "INSERT into ticket " +
				"VALUES( " + nextTicketNo++ + ", '" + violatorSin + "', '" + vehicleSerialNo + "', '" + officerSin +
				"', '" + type + "', " + getSqlDateString(date) + ", '" + location + "', '" + description + "')";
		return doUpdate(client, update);
	}
			
	 
	public static String getSqlDateString(Date d)
	{
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
	 	return "TO_DATE('" + sqlDate.toString() + "', 'yyyy-mm-dd')";
	}
			 
			 
	 
	 
}
