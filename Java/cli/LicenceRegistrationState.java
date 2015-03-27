package cli;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;

import sql.Client;
import sql.Updates;

public class LicenceRegistrationState extends State {

	public LicenceRegistrationState() {
		super("Licence Registration");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(Client client) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		
		DataCollector dc = new DataCollector("Enter licence information");
		String licenceNo = dc.getString("Licence Number");
		
		GetDriverState gds = new GetDriverState(true, "Driver SIN");
		String driverSin = gds.run(client);
		String licenceClass = dc.getString("Class");
		byte[] image = {};
		try
		{
			image = extractBytes(dc.getString("Image Path"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Date issueDate = dc.getDate("Issuing Date");
		Date expDate = dc.getDate("Expiry Date");
		
		if(Updates.RegisterLicence(client, licenceNo, driverSin, licenceClass, image, issueDate, expDate))
			out.println("\n\nRegistration Successful!\n");
	}

	
	/*
	 * Taken from: http://stackoverflow.com/questions/3211156/how-to-convert-image-to-byte-array-in-java
	 * licenced under CC BY-SA 3.0
	 * 
	 * Solution by: http://stackoverflow.com/users/230188/wajdy-essam
	 */
	public byte[] extractBytes (String ImageName) throws IOException {
		 // open image
		 File imgPath = new File(ImageName);
		 BufferedImage bufferedImage = ImageIO.read(imgPath);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		 return ( data.getData() );
	}
}
