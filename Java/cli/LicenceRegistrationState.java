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
		
		out.println("Enter licence information:");
		out.print("licence number: ");
		String licenceNo = in.nextLine();
		GetDriverState gds = new GetDriverState(true, "Driver SIN");
		String driverSin = gds.run(client);
		out.print("Class: ");
		String licenceClass = in.nextLine();
		out.print("Image path: ");
		byte[] image = {};
		try
		{
			image = extractBytes(in.nextLine());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		java.util.Date today = Calendar.getInstance().getTime();
		java.util.Date expDate = (Date) today.clone();
		expDate.setYear(today.getYear() + 4);
		
		if(Updates.RegisterLicence(client, licenceNo, driverSin, licenceClass, image, today, expDate))
			out.println("Registration Successful!");
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
