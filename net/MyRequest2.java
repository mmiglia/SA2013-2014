package net;

import java.util.*;

public class MyRequest2
extends MyRequest
implements java.io.Serializable
{
	Random rand = new Random(System.currentTimeMillis());

	public MyRequest2()
	{
		super();
	}

	public MyRequest2(MyRequest2 mr)
	{
		super(mr);
		rand = mr.rand;
	}

	public void execute()
	{
		super.execute();

		try
		{
			Thread.sleep(2000 + rand.nextInt(5000));
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
	}
}
