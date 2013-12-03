package net;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMT
{
	ServerSocket ss = null;
	List<Envelope> q = null;

	public ServerMT()
	{
		q = new LinkedList<Envelope>();
	}
	
	public static void main(String[] argv)
	{
		ServerMT smt = new ServerMT();
		smt.exec(argv);
	}

	void exec(String[] argv)
	{
		int numServ = 3;
		if(argv.length > 0)
		{
			numServ = Integer.parseInt(argv[0]);
		}
		for(int i=0;i<numServ;i++)
		{
			(new Thread(new Servant(q))).start();
		}
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

				Listener l = new Listener(s, q);
				(new Thread(l)).start();
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

	class Listener
	implements Runnable
	{
		Socket s;
		List<Envelope> q;

		Listener(Socket theS, List<Envelope> theQ)
		{
			s = theS;
			q = theQ;
		}

		public void run()
		{
				try
				{
					System.out.println("con " + s.getInetAddress().toString() + " sulla porta " + s.getPort());
	
					InputStream is = s.getInputStream();
					OutputStream os = s.getOutputStream();

					ObjectInputStream ois = new ObjectInputStream(is);
					ObjectOutputStream oos = new ObjectOutputStream(os);

					try
					{
						while(true)
						{
							Request req = (Request)ois.readObject();
							synchronized(q)
							{
								q.add(new Envelope(req, oos));
								System.out.println("Ci sono " + q.size() + " richieste in attesa");
								q.notify();
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
					finally
					{
						ois.close();
						oos.close();
						s.close();
						return;
					}
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

	class Servant
	implements Runnable
	{
		List<Envelope> q;

		Servant(List<Envelope> theQ)
		{
			q = theQ;
		}

		public void run()
		{
			while(true)
			{
				Envelope e = null;
				synchronized(q)
				{
					while(q.isEmpty())
					{
						try
						{
							q.wait();
						}
						catch(InterruptedException ie)
						{
							ie.printStackTrace();
						}
					}
					e = q.remove(0);
				}

				System.out.println(Thread.currentThread().getName() + " Ho preso in carico la richiesta <" + e.r.toString() + ">");

				//esegue qualunque cosa ci sia da fare
				e.r.execute();

				//restituisce la risposta
				synchronized(e.oos)
				{
					try
					{
						e.oos.writeObject(e.r);
						e.oos.flush();
					}
					catch(IOException ioe)
					{
						ioe.printStackTrace();
					}
				}
			}
		}
	}

	class Envelope
	{
		Request r;
		ObjectOutputStream oos;

		Envelope(Request theR, ObjectOutputStream theOOS)
		{
			r = theR;
			oos = theOOS;
		}
	}
}
