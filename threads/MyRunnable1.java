package threads;

public class MyRunnable1
implements Runnable
{
	String[] args;

	public static void main(String[] argv)
	{
		MyRunnable1 mt1 = new MyRunnable1();
		mt1.args = argv;
		//mt1.run();
		Thread t = new Thread(mt1);
		t.start();
		for(int i = 0;i<argv.length;i++)
		{
			System.out.println(Thread.currentThread().getName() + " " + argv[i]);

			/*
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
			*/
		}
	}

	public void run()
	{
		for(int i = 0;i<args.length;i++)
		{
			System.out.println(Thread.currentThread().getName() + " " + args[i]);

			/*
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
			*/
		}
	}
}
