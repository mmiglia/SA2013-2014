package threads;

import java.util.*;

/**
* Buffer3 di elementi generici. ha una dimensione massima e non puo' contenere null.
*/
public class Buffer3<E>
{
	List<E> storage = new LinkedList<E>();
	private int maxSize;
	private List<Thread> t = new LinkedList<Thread>();

	/**
	* Costruisco il buffer dichiarando la dimensione massima.
	*/
	public Buffer3(int ms)
	{
		maxSize = ms;
	}

	/**
	* Aggiungo un elemento in fondo, ritorna true se c'e' spazio e lo fa, false altrimenti.
	*/
	public boolean add(E s)
	{
		Thread tmp = Thread.currentThread();
		while(true)
		{
			synchronized(tmp)
			{
				synchronized(this)
				{
					if(storage.size() == maxSize)
					{
						if(!t.contains(tmp))
						{
							t.add(tmp);
						}
					}
					else
					{
						storage.add(s);
						return true;
					}
				}

				try
				{
					tmp.wait();
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	* Rimuovo il primo elemento. Ritorna null se e' vuoto.
	*/
	public synchronized E remove()
	{
		if(storage.isEmpty())
		{
			return null;
		}
		if(storage.size() == maxSize)
		{
			//sveglia un dormiente
			if(!t.isEmpty())
			{
				Thread tmp = t.remove(0);
				//invece che le eccezioni uso il metodo ad hoc -> notify
				//tmp.interrupt();
				synchronized(tmp)
				{
					tmp.notify();
				}
			}
		}
		return storage.remove(0);
	}

	/**
	* Ritorno il numero di elementi presenti.
	*/
	public synchronized int size()
	{
		return storage.size();
	}

	/**
	* Ritorno true se contiene zero elementi, false negli altri casi.
	*/
	public synchronized boolean isEmpty()
	{
		return storage.isEmpty();
	}

	public synchronized String toString()
	{
		return storage.toString();
	}
}
