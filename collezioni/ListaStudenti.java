package collezioni;

import java.util.*;

public class ListaStudenti
{
	public static void main(String[] argv)
	{
		List<Studente> l1 = new LinkedList<Studente>();
		List<StudenteEsteso> l2 = new LinkedList<StudenteEsteso>();

		for(int i = 0;i<argv.length;i+=2)
		{
			Studente s1 = new Studente(argv[i]);
			StudenteEsteso s2 = new StudenteEsteso(argv[i], Integer.parseInt(argv[i+1]));
			l1.add(s1);
			l2.add(s2);
		}
		System.out.println(l1 + "\n" + l2);

		//StudenteEsteso s4 = l1.get(0); non compila
		Studente s3 = l2.get(0);
		//System.out.println(l1.get(0).matricola); non compila
		System.out.println(l2.get(0).matricola);
		
	}
}
