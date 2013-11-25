package threads;

import java.util.*;

public class IntHolder
implements Comparable
{
	int value;
	
	public IntHolder(int val)
	{
		value = val;
	}

	public IntHolder()
	{
		this(0);
	}

	public static void main(String[] argv)
	{
		PriorityQueue<IntHolder> pq = new PriorityQueue<IntHolder>();	

		IntHolder[] ih = new IntHolder[argv.length];
		for(int i=0;i<argv.length;i++)
		{
			IntHolder intH = new IntHolder(Integer.parseInt(argv[i]));
			ih[i] = intH;
			pq.add(intH);
		}

		System.out.println(pq);
		for(int i=0;i<ih.length;i++)
		{
			ih[i].value = -3;
		}
		System.out.println(pq);
		ih[0].value = 9999999;
		System.out.println(pq);
	}

	public String toString()
	{
		return String.valueOf(value);
	}

	public int compareTo(Object o)
	{
		return value - ((IntHolder)o).value;
	}
}
