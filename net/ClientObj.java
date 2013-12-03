package net;

import java.util.*;
import java.net.*;
import java.io.*;

public class ClientObj
{
	public static void main(String[] argv)
	{
		(new ClientObj()).exec(argv);
	}

	void exec(String[] argv)
	{
		Socket s = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try
		{
			s = new Socket("localhost", 5678);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
		}
		catch(UnknownHostException uhe)
		{
			uhe.printStackTrace();
			System.exit(1);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}

		Request req = new MyRequest();
		Random random = new Random(System.currentTimeMillis());

		try
		{
			while(true)
			{
				((MyRequest)req).temperatura = 15 + random.nextInt(10);
				System.out.println(req.toString());
				oos.writeObject(req);
				oos.flush();
	
				Request retVal = (Request)ois.readObject();
				System.out.println(retVal.toString());
	
				try
				{
					Thread.sleep(1000+random.nextInt(2000));
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
