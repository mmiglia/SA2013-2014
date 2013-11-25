package io;

import java.io.*;

public class Scrivi
{
	public static void main(String[] argv) //throws Exception
	{
		String name = "pippo.txt";

		File f = new File(name);

		try
		{
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));

			for(int i=0;i<argv.length;i++)
			{
				dos.writeInt(Integer.parseInt(argv[i]));
			}
			dos.close();
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
