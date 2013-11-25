package io;

import java.io.*;

public class Leggi
{
	public static void main(String[] argv)
	{
		try
		{
			DataInputStream dis = new DataInputStream(new FileInputStream(argv[0]));

			while(dis.available() > 0)
			{
				//System.out.println(dis.readLong());
				System.out.println(dis.readInt());
			}

			dis.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
