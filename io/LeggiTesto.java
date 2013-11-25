package io;

import java.io.*;

public class LeggiTesto
{
	public static void main(String[] argv)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("pippo.txt"));

			//while(dis.available() > 0)
			String line = null;
			while( (line = br.readLine()) != null)
			{
				//System.out.println(dis.readLong());
				System.out.println(line);
			}

			br.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
