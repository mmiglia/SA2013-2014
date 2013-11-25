package net;

import java.io.*;
import java.net.*;

public class Client
{
	public static void main(String[] argv)
	{
		String hostname = "localhost";
		int port = 5678;

		if(argv.length > 0)
		{
			hostname = argv[0];
		}
		if(argv.length > 1)
		{
			port = Integer.parseInt(argv[1]);
		}

		InetAddress ia = null;
		try
		{
			ia = InetAddress.getByName(hostname);
		}
		catch(UnknownHostException uhe)
		{
			uhe.printStackTrace();
			System.exit(1);
		}

		Socket s = null;
		try
		{
			s = new Socket(ia, port); //bind & connect

			System.out.println("Local address is: " + s.getLocalAddress());
			System.out.println("Local port is: " + s.getLocalPort());
	
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
	
			PrintWriter pw = new PrintWriter(os);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
	
			String line = null;
			while( (line = br.readLine()) != null)
			{
				Thread.sleep(10000);
				pw.println("ok");
				pw.flush();
				System.out.println("Il Server mi ha detto <" + line + ">");
			}
	
			pw.close();
			br.close();
			s.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}
		catch(InterruptedException ie)
		{
		}
	}
}
