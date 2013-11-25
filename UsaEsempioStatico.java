public class UsaEsempioStatico
{
	public static void main(String[] argv)
	{
		int i = 0;
		if(argv.length > 0)
		{
			i = Integer.parseInt(argv[0]);
		}

		switch(i)
		{
			case 0:
				System.out.println("Eseguo metodo statico");
				EsempioStatico.metodoStatico();
				break;
			case 1:
				System.out.println("Istanzio un oggetto");
				new EsempioStatico();
				break;
			case 2:
				System.out.println("Ne istanzio 2");
				new EsempioStatico();
				new EsempioStatico();
				break;
			default:
				System.out.println("Non faccio nulla");
		}
	}
}
