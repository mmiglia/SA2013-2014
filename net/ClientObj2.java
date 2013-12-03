package net;

import java.util.*;
import java.net.*;
import java.io.*;

public class ClientObj2
implements Runnable
{
	Socket s = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public static void main(String[] argv)
	{
		(new ClientObj2()).exec(argv);
	}

	void exec(String[] argv)
	{
		try
		{
			s = new Socket("localhost", 5678);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			for(int i = 0;i<3;i++)
			{
				(new Thread(this)).start();
			}
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
	}

	public void run()
	{
		MyRequest2 req = new MyRequest2();
		Random random = new Random(System.currentTimeMillis());

		try
		{
			while(true)
			{
				req.temperatura = 15 + random.nextInt(10);
				System.out.println(req.toString());
				synchronized(oos)
				{
					oos.writeObject((new MyRequest2(req)));
					oos.flush();
				}
	
				Request retVal = null;
				synchronized(ois)
				{
					retVal = (Request)ois.readObject();
				}
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
