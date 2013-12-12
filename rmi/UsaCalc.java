package rmi;

import java.rmi.*;
import java.io.*;

public class UsaCalc
{
	public static void main(String[] argv)
	{
		try
		{
			Calc c = (Calc)Naming.lookup(argv[0]);
			System.out.println("L'oggetto c e' di tipo: " + c.getClass().getName());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			while( (line = br.readLine()) != null)
			{
				if(line.equals("exit"))
				{
					break;
				}
				else if(line.equals("sum"))
				{
					double d1 = Double.parseDouble(br.readLine());
					double d2 = Double.parseDouble(br.readLine());
					System.out.println(c.sum(d1, d2));
				}
				else if(line.equals("sub"))
				{
					double d1 = Double.parseDouble(br.readLine());
					double d2 = Double.parseDouble(br.readLine());
					System.out.println(c.sub(d1, d2));
				}
				else if(line.equals("accumulate"))
				{
					double d1 = Double.parseDouble(br.readLine());
					/* questo lock e' solo locale, non serve
					synchronized(c)
					{
					*/
						c.accumulate(d1);
					//}
				}
				else if(line.equals("accumulated"))
				{
					System.out.println(c.accumulated());
				}
				else
				{
					System.out.println("Command <" + line + "> not recognized");
				}
			}
		}
		catch(NotBoundException nbe)
		{
			nbe.printStackTrace();
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
