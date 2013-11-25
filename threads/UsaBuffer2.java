package threads;

import java.util.*;

public class UsaBuffer2
implements Runnable
{
	List<Studente> b;
	Studente[] st;

	public static void main(String[] argv)
	{
		(new UsaBuffer2()).exec(argv);
	}

	public void exec(String[] argv)
	{
		int i = 0;
		st = new Studente[argv.length];
		for(i = 0;i<argv.length;i++)
		{
			st[i] = new Studente(argv[i]);
		}

		(new Thread(this)).start();

		b = new LinkedList<Studente>();
		for(i=0;i<argv.length;i++)
		{
			b.add(st[i]);
		}

		System.out.println(b);


		//lock di b, nessuno intervenga sulla lista
		synchronized(b)
		{
			ListIterator<Studente> li = b.listIterator();
			while(li.hasNext())
			{
				Studente s = li.next();
				if(s.votoMinimo < 24)
				{
					li.remove();
				}
			}
			System.out.println(b.size());
			li = b.listIterator();
			while(li.hasNext())
			{
				System.out.println(li.next().hashCode());
			}
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException ie)
			{
			}
			System.out.println(b);
			System.out.println(b.size());
			li = b.listIterator();
			while(li.hasNext())
			{
				System.out.println(li.next().hashCode());
			}
		}
	}

	public void run()
	{
			try
			{
				Thread.sleep(10);
			}
			catch(InterruptedException ie)
			{
			}
			for(int i = 0;i<st.length;i++)
			{
				st[i].votoMinimo = 3;
			}
			//yaz yaz yaz
	}
}
