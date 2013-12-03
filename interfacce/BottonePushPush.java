package interfacce;

public class BottonePushPush
extends it.unige.dibris.sa.Pippo
implements Bottone
{
	protected boolean stato = false;

	public BottonePushPush(boolean initStato)
	{
		stato = initStato;
	}

	public BottonePushPush()
	{
		this(false);
	}

	/**
	* Ritorna lo stato corrente del bottone, true per schiacciato, false altrimenti.
	*/
	public boolean isPressed()
	{
		return stato;
	}

	/**
	* Cambia lo stato del bottone.
	*/
	public void press()
	{
		stato = !stato;
	}

	public static void main(String[] argv)
	{
		Bottone b = new BottonePushPush();
		//BottonePushPush bpp = ((BottonePushPush)b);
		//bpp.super.main(argv);

		((BottonePushPush)b).exec(argv);
	}

	public void exec(String[] argv)
	{
		//a = 12; non si puo' fare, gli attributi di un'interfaccia sono sempre final implicitamente
		for(int i = 0;i<argv.length;i++)
		{
			System.out.println("Il bottone e' schiacciato? " + isPressed());
			press();
			System.out.println("Il bottone e' schiacciato? " + isPressed());
		}
		super.main(argv);
	}
}
