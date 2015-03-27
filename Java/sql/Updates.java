package sql;
import java.sql.*;
import java.util.List;
import java.util.Date;

public class Updates {
	private int nextTransactionId = 22;
	private int nextTicketNo = 10;
	 
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
			String primaryOwnerSin,
			List<String> secondaryOwnerSins )
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
				"VALUES( '" + primaryOwnerSin + "', '" + serialNo + "', 'y')";
		try {
			client.statement.executeUpdate(update);
		} catch(SQLException e) {
			System.out.println("RegisterVehicle :: INSERT into owner (primary) failed");
			System.out.println(update);
			System.out.println(e.getMessage());
			return;
		}
		 
		for(String secondaryOwnerSin : secondaryOwnerSins)
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
		String update = "DELETE " +
				"FROM owner " +
				"WHERE vehicle_id = '" + vehicleSerialNo + "'";
		try {
			client.statement.executeUpdate(update);
			 
		} catch(SQLException e) {
			System.out.println("DoTransaction :: DELETE from owner failed");
			System.out.println(e.getMessage());
			System.out.println(update);
			return;
		}
		update = "INSERT into owner " +
				"VALUES( '" + buyerSin + "', '" + vehicleSerialNo + "', 'y')";
		try {
			client.statement.executeUpdate(update);
			 
		} catch(SQLException e) {
			System.out.println("DoTransaction :: INSERT into owner failed");
			System.out.println(e.getMessage());
			System.out.println(update);
			return;
		}
		update = "INSERT into auto_sale " +
				"VALUES( " + nextTransactionId++ + ", '" + sellerSin + "', '" + buyerSin + "', '" + vehicleSerialNo +
				"', " + getSqlDateString(saleDate) + ", " + price + ")";
		try {
			client.statement.executeUpdate(update);
		} catch(SQLException e) {
			System.out.println("DoTransaction :: INSERT into auto_sale failed");
			System.out.println(e.getMessage());
			System.out.println(update);
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
		String update = "INSERT into drive_license " +
				"VALUES( '" + licenseNo + "', '" + driverSin + "', '" + licenseClass + "', ?, " +
				getSqlDateString(issuingDate) + ", " + getSqlDateString(expiringDate) + ")";
		
		try {
			client.PrepareStatement(update);
			Blob blob = new javax.sql.rowset.serial.SerialBlob(photo);
			client.preparedStatement.setBlob(1, blob);
		} catch(SQLException e) {
			System.out.println("RegisterLicense :: INSERT into drive_license failed");
			System.out.println(e.getMessage());
			System.out.println(update);
			return;
		}
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
		String update = "INSERT into ticket " +
				"VALUES( " + nextTicketNo++ + ", '" + violatorSin + "', '" + vehicleSerialNo + "', '" + officerSin +
				"', '" + type + "', " + getSqlDateString(date) + ", '" + location + "', '" + description + "')";
		try {
			client.statement.executeUpdate(update);
		} catch(SQLException e) {
			System.out.println("RegisterViolation :: INSERT into ticket failed");
			System.out.println(e.getMessage());
			System.out.println(update);
			return;
		}
	}
			
	 
	 static String getSqlDateString(Date d)
	 {
		 java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		 return "TO_DATE('" + sqlDate.toString() + "', 'yyyy-mm-dd')";
	 }
			 
			 
	 
	 
}
