public class Hello2
{
	int i;
	String s;

	public static void main(String[] argv)
	{
		Hello2 hello2 = new Hello2();
		int intero = hello2.go(argv);
		System.out.println(intero);

		for(intero=0;intero<argv.length;intero++)
		{
			System.out.println("Argv["+intero+"] = " + argv[intero]);
		}

		hello2.stringazione();
		Integer integer = new Integer(5);
		System.out.println(integer);
		integer++;
		System.out.println(integer);
	}

	void stringazione()
	{
		String p = new String("PIPPO");
		String t = new String("TOPOLINO");

		//System.out.println(p.concat(t));
		p = p.concat(t);
		System.out.println(p);
	}

	int go(String[] args)
	{
		//String p = new String("PIPPO");
		String p = "PIPPO";
		int k = 0;
		System.out.println(i);
		System.out.println(s);
		System.out.println(k);
		System.out.println(p);
		return k;
	}
}
