package interfacce;

public interface Bottone
{
	int a = 666;

	/**
	* Ritorna lo stato corrente del bottone, true per schiacciato, false altrimenti.
	*/
	public boolean isPressed();

	/**
	* Cambia lo stato del bottone.
	*/
	public void press();
}