package net;

public class MyRequest
extends Request
implements java.io.Serializable
{
	double temperatura;

	public void execute()
	{
		super.execute();
		System.out.println("La temperatura e': " + temperatura);
	}

	public String toString()
	{
		return super.toString() + " temperatura: " + temperatura;
	}
}
