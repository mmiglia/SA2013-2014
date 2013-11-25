package threads;

import java.util.*;

public class TestTreeSet
{
	TreeSet<String> s = new TreeSet<String>();

	public static void main(String[] argv)
	{
		(new TestTreeSet()).exec(argv);
	}

	void exec(String[] argv)
	{
		int i;
		for(i=0;i<argv.length;i++)
		{
			s.add(argv[i]);
			System.out.println(s);
		}

		while(i-- > 0 && !s.isEmpty())
		{
			System.out.println("***\n" + s.toString());
			System.out.println(s.last());
			System.out.println(s.toString() + "***\n");
		}
	}
}
