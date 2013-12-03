package net;

public class Request
implements java.io.Serializable
{
	static int count = 0;
	int num;
	boolean complete;

	public Request()
	{
		synchronized(Request.class)
		{
			num = count++;
		}
		complete = false;
	}

	public Request(Request r)
	{
		num = r.num;
		complete = r.complete;
	}

	public void execute()
	{
		System.out.println("Richiesta #"+num+" eseguita");
		complete = true;
	}

	public String toString()
	{
		if(complete)
		{
			return "Request #" + num + " is complete";
		}
		else
		{
			return "Request #" + num + " is incomplete";
		}
	}
}
