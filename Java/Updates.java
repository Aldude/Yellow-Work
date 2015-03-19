import java.sql.*;

public class Updates {
	private int nextTransactionId = 1;
	private int nextTicketNo = 1;
	 
	public void AddPerson(Client client,
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
		
		try {
			client.statement.executeUpdate(update);
		} catch(SQLException e) {
			System.out.println("AddPerson :: INSERT into people failed");
			System.out.println(update);
			System.out.println(e.getMessage());
			return;
		}
	}
	 
	public void RegisterVehicle(Client client,
			String serialNo,
			String maker,
			String model,
			int year,
			String color,
			int typeId,
			int primaryOwnerSin,
			int[] secondaryOwnerSins )
	{
		String update = "INSERT into vehicle " +
				"VALUES( '" + serialNo + "', '" + maker + "', '" + model + "', " + year + ", '" + color + "', " + typeId + ")";
		try {
			client.statement.executeUpdate(update);
		} catch(SQLException e) {
			System.out.println("RegisterVehicle :: INSERT into vehicle failed");
			System.out.println(update);
			System.out.println(e.getMessage());
			return;
		}
		
		update = "INSERT into owner " +
				"VALUES( '" + primaryOwnerSin + "', '" + serialNo + "', 'y');";
		try {
			client.statement.executeUpdate(update);
		} catch(SQLException e) {
			System.out.println("RegisterVehicle :: INSERT into owner (primary) failed");
			System.out.println(update);
			System.out.println(e.getMessage());
			return;
		}
		 
		for(int secondaryOwnerSin : secondaryOwnerSins)
		{
			update = "INSERT into owner " +
					"VALUES( '" + secondaryOwnerSin + "', '" + serialNo + "', 'n')";
			try {
				client.statement.executeUpdate(update);
			} catch(SQLException e) {
				System.out.println("RegisterVehicle :: INSERT into owner (secondary) failed");
				System.out.println(update);
				System.out.println(e.getMessage());
				return;
			}
		}
	}
	 
	public void DoTransaction(Client client,
			String sellerSin,
			String buyerSin,
			String vehicleSerialNo,
			Date saleDate,
			int price)
	{
		try {
			client.statement.executeUpdate("DELETE " +
					"FROM owner " +
					"WHERE vehicle_id = '" + vehicleSerialNo + "'"
					);
			 
		} catch(SQLException e) {
			System.out.println("DoTransaction :: DELETE from owner failed");
			System.out.println(e.getMessage());
			return;
		}
		 
		try {
			client.statement.executeUpdate("INSERT into owner" +
					"VALUES( '" + buyerSin + "', '" + vehicleSerialNo + "', 'y')" );
			 
		} catch(SQLException e) {
			System.out.println("DoTransaction :: INSERT into owner failed");
			System.out.println(e.getMessage());
			return;
		}
		 
		try {
			client.statement.executeUpdate("INSERT into auto_sale" +
					"VALUES( " + nextTransactionId++ + ", '" + sellerSin + "', '" + buyerSin + "', '" + vehicleSerialNo +
					"', " + getSqlDateString(saleDate) + ", " + price + ")" );
		} catch(SQLException e) {
			System.out.println("DoTransaction :: INSERT into auto_sale failed");
			System.out.println(e.getMessage());
			return;
		}
		 
	 }
	
	public void RegisterLicense(Client client,
			String licenseNo,
			String driverSin,
			String licenseClass,
			byte[] photo,
			Date issuingDate,
			Date expiringDate)
	{
		/*
		 * TODO - get photo blob into INSERT statement. Might need PreparedStatement instead of Statement.
		 * 
		 * 
		try {
			client.statement.set
			
			client.statement.executeUpdate("INSERT into drive_license" +
					"VALUES( '" + licenseNo + "', '" + driverSin + "', '" + licenseClass + "', '");
			 
		} catch(SQLException e) {
			System.out.println("RegisterLicense :: INSERT into drive_license failed");
			System.out.println(e.getSQLState());
			return;
		}*/
	}
	
	public void RegisterViolation(Client client,
			String violatorSin,
			String officerSin,
			String vehicleSerialNo,
			String type,
			Date date,
			String location,
			String description)
	{
		try {
			client.statement.executeUpdate("INSERT into ticket" +
					"VALUES( " + nextTicketNo++ + ", '" + violatorSin + "', '" + vehicleSerialNo + "', '" + officerSin +
					"', '" + type + "', " + getSqlDateString(date) + ", '" + location + "', '" + description + "')" );
		} catch(SQLException e) {
			System.out.println("RegisterViolation :: INSERT into ticket failed");
			System.out.println(e.getMessage());
			return;
		}
	}
			
	 
	 static String getSqlDateString(Date d)
	 {
		 return "TO_DATE('" + d.toString() + "', 'yyyy-mm-dd')";
	 }
			 
			 
	 
	 
}
