package rmi;

import java.rmi.*;
import java.rmi.server.*;

public class CalcImpl
extends UnicastRemoteObject
implements Calc
{
	double accumulator = 0;

	public CalcImpl() throws RemoteException
	{
		super();
	}

	public double sum(double d1, double d2) throws RemoteException
	{
		System.out.println("La somma di " + d1 + " con " + d2 + " vale " + (d1+d2));
		return d1 + d2;
	}

	public double sub(double d1, double d2) throws RemoteException
	{
		System.out.println("La sottrazione di " + d2 + " da " + d1 + " vale " + (d1-d2));
		return d1 - d2;
	}

	public void accumulate(double d) throws RemoteException
	{
		double tmp = accumulator;
		tmp = tmp + d;
		accumulator = tmp;
	}

	public double accumulated() throws RemoteException
	{
		return accumulator;
	}

	public static void main(String[] argv)
	{
		CalcImpl ci = null;
		try
		{
			ci = new CalcImpl();
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
			System.exit(1);
		}

		String name = "pippo";
		if(argv.length > 0)
		{
			name = argv[0];
		}
		try
		{
			java.rmi.Naming.bind(name, ci);
		}
		catch(AlreadyBoundException abe)
		{
			abe.printStackTrace();
		}
		catch(java.net.MalformedURLException mue)
		{
			mue.printStackTrace();
		}
		catch(RemoteException re)
		{
			re.printStackTrace();
		}

		System.out.println("Il thread " + Thread.currentThread().getName() + " termina");
	}
}
