package threads;

public class UsaBufferLock2
implements Runnable
{
	boolean reader;
	BufferLock2<String> bl2;
	java.util.Random rand;

	public UsaBufferLock2(boolean r)
	{
		reader = r;
		rand = new java.util.Random(System.currentTimeMillis());
	}

	public static void main(String[] argv)
	{
		BufferLock2<String> b = new BufferLock2<String>(10);

		for(int i = 0;i<argv.length;i++)
		{
			UsaBufferLock2 ubl2;
			if(argv[i].equals("reader"))
			{
				ubl2 = new UsaBufferLock2(true);
			}
			else
			{
				ubl2 = new UsaBufferLock2(false);
			}
			ubl2.bl2 = b;
			(new Thread(ubl2)).start();
		}
	}

	public void run()
	{
		if(reader)
		{
			while(true)
			{
				try
				{
					Thread.sleep(rand.nextInt(5000));
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}

				String s = bl2.remove();
				System.out.println(Thread.currentThread().getName() + " ho letto: " + s);
			}
		}
		else
		{
			while(true)
			{
				int i = rand.nextInt(5000);
				bl2.add(new String(Thread.currentThread().getName() + " inserisco " + String.valueOf(i)));
				try
				{
					Thread.sleep((long)i);
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
		}
	}
}
