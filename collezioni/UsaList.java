package collezioni;

import java.util.*;

public class UsaList
{
	public static void main(String[] argv)
	{
		List<Integer> l = new LinkedList<Integer>();
		//List<Integer> l = new ArrayList<Integer>();
		l.add(null);
		for(int i =0;i<argv.length;i++)
		{
			//l.add( ((Integer)argv[i]) );
			try
			{
				int k = Integer.parseInt(argv[i]);
				l.add(k);
			}
			catch(NumberFormatException nfe)
			{
				nfe.printStackTrace();
				continue;
			}
		}

		System.out.println(l);

		//Iterator<Integer> iter = l.iterator();
		ListIterator<Integer> iter = l.listIterator();
		System.out.println(iter.getClass().getName());
		while(iter.hasNext())
		{
			int j = iter.next() + iter.next();	
			iter.add(j);
			System.out.println(j);
		}
		System.out.println(l);

	}
}
