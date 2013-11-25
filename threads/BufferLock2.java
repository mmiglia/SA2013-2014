package threads;

import java.util.concurrent.locks.*;
import java.util.*;

public class BufferLock2<E>
{
	List<E> storage = new LinkedList<E>();
	private int maxSize;

	Lock l;
	Condition pieno, vuoto;

	public BufferLock2(int ms)
	{
		maxSize = ms;
		l = new ReentrantLock();
		pieno = l.newCondition();
		vuoto = l.newCondition();
	}

	public void add(E elem)
	{
		try
		{
			l.lock();
			while(storage.size() == maxSize)//Houston, we got a problem
			{
				pieno.awaitUninterruptibly();
			}
			storage.add(elem);
			if(storage.size() == 1)
			{
				vuoto.signal();
			}
		}
		finally
		{
			l.unlock();
		}
	}

	public E remove()
	{
		E retval = null;
		try
		{
			l.lock();
			while(storage.isEmpty())
			{
				vuoto.awaitUninterruptibly();
			}

			if(storage.size() == maxSize)
			{
				pieno.signal();
			}

			retval = storage.remove(0);
		}
		finally
		{
			l.unlock();
			//l.unlock();
		}
		return retval;
	}
}
