package io;

import java.io.*;
import java.util.*;

public class ListaStringhe
{
	public static void main(String[] argv) throws Exception
	{
		(new ListaStringhe()).exec(argv);
	}

	public void exec(String[] argv) throws IOException
	{
		List<StringHolder> l = null;
		File f = new File("dati.dat");
		if(f.canRead())
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			try
			{
				l = (List<StringHolder>)ois.readObject();
			}
			catch(ClassNotFoundException cnfe)
			{
				cnfe.printStackTrace();
				System.exit(1);
			}
			ois.close();
		}
		else
		{
			l = new LinkedList<StringHolder>();
		}

		Scanner s = new Scanner(System.in);

		while(true)
		{
			String line = s.nextLine();
			if(line.equals("exit"))
			{
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(l);
				oos.close();
				break;
			}
			l.add(new StringHolder(line));
			System.out.println(l);
		}
	}
}
