package io;

import java.util.*;
import java.io.*;

public class LeggiScanner
{
	public static void main(String[] argv) throws FileNotFoundException
	{
		Scanner s = new Scanner(new FileInputStream(argv[0]));
		System.out.println(s.delimiter());
		s.useDelimiter("a");
		System.out.println(s.delimiter());

		while(s.hasNext())
		{
			String line = s.next();
			System.out.println(line);
		}
	}
}
