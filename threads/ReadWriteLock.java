package threads;

import java.util.*;

public class ReadWriteLock
{
	List<Thread> readers;
	List<Thread> readingReaders;
	List<Thread> writers;
	Thread writingWriter;
	int writing;

	public ReadWriteLock()
	{
		readers = new LinkedList<Thread>();
		readingReaders = new LinkedList<Thread>();
		writers = new LinkedList<Thread>();
		writingWriter = null;
		writing = 0;
	}
	
	public void readLock()
	{
		Thread t = Thread.currentThread();
		synchronized(t)
		{
			while(true)
			{
				synchronized(this)
				{
					if(!readingReaders.contains(t) && (writingWriter != null || !writers.isEmpty()))//c'e' uno scrittore o in scrittura o in attesa
					{
						readers.add(t);
					}
					else
					{
						readingReaders.add(t);
						break;
					}
				}
				try
				{
					t.wait();
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
		}
	}

	public void readUnlock()
	{
		synchronized(this)
		{
			if(!readingReaders.remove(Thread.currentThread()))
			{
				throw new IllegalMonitorStateException("Non hai il lock!!!!");
				//return;
			}

			if(readingReaders.isEmpty())
			{
				if(!writers.isEmpty())
				{
					Thread t = writers.remove(0);
					synchronized(t)
					{
						t.notify();
						writingWriter = t;
					}
				}
			}
		}
	}

	public void writeLock()
	{
		Thread t = Thread.currentThread();

		synchronized(t)
		{
			while(true)
			{
				synchronized(this)
				{
					if(writingWriter == t || writingWriter == null && readingReaders.isEmpty())
					{
						writing++;
						break;
					}
					writers.add(t);
				}

				try
				{
					t.wait();
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}


				/* avendo completato la condizione sopra con l'or, sono certamente nella condizione dell'if qui sotto
				if(!readingReaders.isEmpty() || writingWriter != null)
				{
					//devo aspettare
				}
				else
				{
					//writingWriter == null && readingReaders.isEmpty()
					writingWriter = t;
					writing++;
					break;
				}
				*/

			}
		}
	}

	public void writeUnlock()
	{
		synchronized(this)
		{
			//writing--;
			if(! (writingWriter == Thread.currentThread()))
			{
				throw new IllegalMonitorStateException("Non sei tu lo scrittore corrente!");
			}

			if(--writing == 0)
			{
				if(!writers.isEmpty())
				{
					Thread t = writers.remove(0);
					synchronized(t)
					{
						t.notify();
					}
					writingWriter = t;
				}
				else
				{
					if(!readers.isEmpty())
					{
						Thread t = readers.remove(0);
						synchronized(t)
						{
							t.notify();
						}
					}
				}
			}
		}
	}
}
