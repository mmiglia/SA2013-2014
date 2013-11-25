package it.unige.dibris.sa;

public abstract class Madre
extends Nonna
{
	//public abstract void sferruzza();
	public void nutre()
	{
		System.out.println("Vi do pure da mangiare");
	}

	public String toString()
	{
		return super.toString() + " e anche di tipo Madre";
	}
}