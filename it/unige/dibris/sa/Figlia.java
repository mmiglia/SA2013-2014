package it.unige.dibris.sa;

public class Figlia
extends Madre
{
	public void siFaCoccolare()
	{
		System.out.println("Purr");
	}

	public static void main(String[] argv)
	{
		Figlia f = new Figlia();
		f.sferruzza();
		System.out.println(f);
		Nonna n = new Figlia();
		System.out.println(n);
	
	}

	public void sferruzza()
	{
		System.out.println("Mi rifiuto di imparare a sferruzzare!");
	}

	public String toString()
	{
		return super.toString() + " e anche di tipo Figlia";
	}
}