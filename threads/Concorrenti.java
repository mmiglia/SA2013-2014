package threads;

public class Concorrenti
implements Runnable
{
	IntHolder nextToServe = new IntHolder(0);

	public static void main(String[] argv)
	{
		int numThread = 1;
		if(argv.length>0)
		{
			numThread = Integer.parseInt(argv[0]);
		}

		Concorrenti c = new Concorrenti();
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
