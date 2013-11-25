package io;

import java.io.*;

public class ScriviTesto
{
	public static void main(String[] argv) //throws Exception
	{
		String name = "pippo.txt";

		File f = new File(name);

		try
		{
			PrintWriter pw = new PrintWriter(new FileWriter(f));

			for(int i=0;i<argv.length;i++)
			{
				pw.println(argv[i]);
			}
			pw.close();
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
