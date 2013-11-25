package net;

import java.io.*;
import java.net.*;

public class Server
{
	public static void main(String[] argv)
	{
		ServerSocket ss = null;

		try
		{
			ss = new ServerSocket(5678);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}

		while(true)
		{
			try
			{
				System.out.print("In attesa di connessione....");
				Socket s = ss.accept();
				System.out.println("Connesso!");
	
				System.out.println("con " + s.getInetAddress().toString() + " sulla porta " + s.getPort());
	
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
	
				PrintWriter pw = new PrintWriter(os);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				pw.println("pippo");
				pw.flush();
				System.out.println("Per il client e' " + br.readLine());
				
				pw.println("pluto");
				pw.flush();
				System.out.println("Per il client e' " + br.readLine());
				
				pw.println("paperino");
				pw.flush();
				System.out.println("Per il client e' " + br.readLine());
	
				pw.close();
				br.close();
				s.close();
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
}
