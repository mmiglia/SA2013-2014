package net;

import java.util.*;
import java.net.*;
import java.io.*;

public class ClientObject3
implements Runnable
{
	Socket s = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public static void main(String[] argv)
	{
		(new ClientObject3()).exec(argv);
	}

	void exec(String[] argv)
	{
		try
		{
			s = new Socket("localhost", 5678);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			(new Thread(this)).start();
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

	public request pleaseServeMe(Request r)
	{
		r.id = Thread.currentThread().getName();
		synchronized(oos)
		{
			oos.writeObject(r);
			oos.flush();
		}

		RequestBox rb = new RequestBox();

		mappaRisposte.put(r.id, rb);

		synchronized(rb)
		{
			while(!rb.complete)
			{
				try
				{
					rb.wait();
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
		}
		return rb.req;
	}

	public void run()
	{
		while(true)
		{
			Request req = (Request)ois.readObject();
			RequestBox rb = mappaRisposte.get(req.id);//non considero il caso che non ci sia un qualcuno in attesa

			synchronized(rb)
			{
				rb.req = req;
				rb.complete = true;
				rb.notify();
			}
		}
	}

	class RequestBox
	{
		boolean complete;
		Request req;
	}
}
