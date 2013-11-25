package threads;

import java.util.*;

/**
* Buffer di elementi generici. ha una dimensione massima e non puo' contenere null.
*/
public class Buffer<E>
{
	List<E> storage = new LinkedList<E>();
	private int maxSize;

	/**
	* Costruisco il buffer dichiarando la dimensione massima.
	*/
	public Buffer(int ms)
	{
		maxSize = ms;
	}

	/**
	* Aggiungo un elemento in fondo, ritorna true se c'e' spazio e lo fa, false altrimenti.
	*/
	public synchronized boolean add(E s)
	{
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
