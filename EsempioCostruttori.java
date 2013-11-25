public class EsempioCostruttori
extends Object
{
	int altezza = 10;
	int larghezza = 10;

	private static int contaIstanze = 0;

	/**
	* Costruttore con parametri. Inizializza altezza ad a e larghezza a l.
	*/
	public EsempioCostruttori(int a, int l)
	{
		altezza = a;
		larghezza = l;
		contaIstanze++;
	}

	public EsempioCostruttori()
	{
		//this = new EsempioCostruttori(0, 0);
		this(0, 0);
	}

	/*
	public String toString()
	{
		return "io sono alto " + altezza + " e largo " + larghezza + " e sono uno di " + contaIstanze;
	}
	*/

	public static void main(String[] argv)
	{
		EsempioCostruttori ec = new EsempioCostruttori(Integer.parseInt(argv[0]), Integer.parseInt(argv[1]));
		EsempioCostruttori ec2 = new EsempioCostruttori(Integer.parseInt(argv[0]), Integer.parseInt(argv[1]));
		System.out.println(ec);
		System.out.println(ec2);
		//if(ec.equals(ec2))
		if(ec == ec2)
		{
			System.out.println("Sono uguali");
		}
		else
		{
			System.out.println("Sono diversi");
		}
		ec = new EsempioCostruttori();
		System.out.println(ec);

		if(ec.equals("PIPPO"))
		{
			System.out.println("Sono uguali");
		}
		else
		{
			System.out.println("Sono diversi");
		}
	}

	public boolean equals(Object o)
	{
		assert (o instanceof EsempioCostruttori);

		if(! (o instanceof EsempioCostruttori))
		{
			return false;
		}

		return larghezza == ((EsempioCostruttori)o).larghezza && altezza == ((EsempioCostruttori)o).altezza;
	}
}
