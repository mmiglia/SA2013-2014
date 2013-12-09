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
	Map<String, RequestBox> mappaRisposte;

	public static void main(String[] argv)
	{
		new ClientObject3();
	}

	public ClientObject3()
	{
		mappaRisposte = new HashMap<String, RequestBox>();
		exec((String[])null);
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

	public Request pleaseServeMe(Request r) throws IOException
	{
		r.id = Thread.currentThread().getName();

		RequestBox rb = new RequestBox();

		mappaRisposte.put(r.id, rb);
		synchronized(oos)
		{
			oos.writeObject(r);
			oos.flush();
		}

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
			Request req = null;
			try
			{
				req = (Request)ois.readObject();
			}
			catch(ClassNotFoundException cnfe)
			{
				cnfe.printStackTrace();
				continue;
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
				continue;
			}
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
