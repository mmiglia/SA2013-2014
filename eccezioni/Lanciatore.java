package eccezioni;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Lanciatore
{
	public static void main(String[] argv) throws IOException
	{
		try
		{
			(new Lanciatore()).exec(argv);
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
	}

	public void exec(String[] argv) throws FileNotFoundException, IOException
	{
		if(argv.length == 0)
		{
			throw new IllegalArgumentException("Non posso funzionare senza parametri");
		}

		FileReader fr = new FileReader(argv[0]);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		while( (line = br.readLine()) != null)
		{
			System.out.println(line);
		}

		br.close();
	}
}