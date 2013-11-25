package threads;

public class Studente
{
	int votoMinimo;

	public Studente(String v)
	{
		this(Integer.parseInt(v));
	}

	public Studente(int v)
	{
		votoMinimo = v;
	}

	public String toString()
	{
		return "Il voto piu' basso e' :" + votoMinimo;
	}
}
