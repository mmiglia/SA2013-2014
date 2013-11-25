package threads;

public class UsaBuffer
implements Runnable
{
	Buffer<String> b = new Buffer<String>();
	String[] argv;

	public static void main(String[] args)
	{
		UsaBuffer ub = new UsaBuffer();
		ub.argv = args;
		for(int i =0;i<5;i++)
		{
			(new Thread(ub)).start();
		}
	}

	public void run()
	{
		for(int i = 0;i<argv.length;i++)
		{
			//synchronized(argv)
			//{
				b.add(argv[i]);
			//}
			//non serve perche': 1) argv e' solo in lettura; 2) ci si accede solo via add che e' in mutua esclusione.
			System.out.println("Ci sono " + b.size() + " elementi");
		}
		System.out.println(Thread.currentThread().getName() + ":" + b);

		while(true)
		{
			String s = null;
			synchronized(b)
			{
				if(!b.isEmpty())
				{
					try
					{
						Thread.sleep(100);
					}
					catch(InterruptedException ie)
					{
					}
					s = b.remove();
				}
				else
				{
					break;
				}
			}
			//faccio quello che devo fare con il mio elemento in s
			System.out.println("Ho estratto " + s + " Ci sono " + b.size() + " elementi");
		}
	}
}
