package collezioni;

public class StudenteEsteso
extends Studente
{
	int matricola;

	public StudenteEsteso(String n, int m)
	{
		super(n);
		//nome = n;
		matricola = m;
	}

	public String toString()
	{
		return super.toString() + " matricola: " + matricola;
	}
}
