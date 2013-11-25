public class EsempioStatico
{
	public EsempioStatico()
	{
		System.out.println("Io sono il costruttore di default");
	}

	public static void metodoStatico()
	{
		System.out.println("Io sono un metodo statico");
	}

	static
	{
		System.out.println("Io sono l'inizializzatore statico");
		NonParte.main();
	}
}
