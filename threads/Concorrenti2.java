package threads;

import java.util.*;

public class Concorrenti2
implements Runnable
{
	IntHolder nextToServe = new IntHolder(0);
	List<String> l = new LinkedList<String>();

	public static void main(String[] argv)
	{
		int numThread = 1;
		Concorrenti2 c = new Concorrenti2();
		if(argv.length>0)
		{
			numThread = Integer.parseInt(argv[0]);
		}
		for(int j=0;j<argv.length;j++)
		{
			c.l.add(argv[j]);
		}

		for(int i = 0;i<numThread;i++)
		{
			(new Thread(c)).start();
		}
	}

	public void run()
	{
		int locale;
		while(true)
		{
			/*
			synchronized(nextToServe)
			{
				int locale = nextToServe.getVal();
				System.out.println("Adesso servo il numero " + locale);
				locale = locale + 1;
				nextToServe.setVal(locale);
			}
			*/

			System.out.println("Adesso servo il numero " + (locale = nextToServe.getAndIncrement()));
			//qualunque cosa da fare per servire il numero dato
			try
			{
				/*
				synchronized(l)
				{
					//System.out.println("Ho servito " + l.remove(locale));
				}
				*/
				System.out.println("Ho servito " + l.get(locale));
				Thread.sleep(1000);
			}
			catch(InterruptedException ie)
			{
			}
		}
	}

	class IntHolder
	{
		int val;

		IntHolder(int x)
		{
			val = x;
		}

		synchronized int getVal()
		{
			return val;
		}

		synchronized void setVal(int x)
		{
			val = x;
		}

		synchronized int getAndIncrement()
		{
			int retval = val;
			val++;
			return retval;
		}
	}
}
