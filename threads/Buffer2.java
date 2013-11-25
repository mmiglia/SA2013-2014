package threads;

import java.util.*;

/**
* Buffer2 di elementi generici. ha una dimensione massima e non puo' contenere null.
*/
public class Buffer2<E>
{
	List<E> storage = new LinkedList<E>();
	private int maxSize;
	private List<Thread> t = new LinkedList<Thread>();

	/**
	* Costruisco il buffer dichiarando la dimensione massima.
	*/
	public Buffer2(int ms)
	{
		maxSize = ms;
	}

	/**
	* Aggiungo un elemento in fondo, ritorna true se c'e' spazio e lo fa, false altrimenti.
	*/
	public synchronized boolean add(E s)
	{
		while(storage.size() == maxSize)
		{
			try
			{
				if(!t.contains(Thread.currentThread()))
				{
					t.add(Thread.currentThread());
				}
				this.wait();
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}

		if(storage.size() == maxSize)
		{
			return false;
		}
		storage.add(s);
		return true;
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
				this.notify();
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
