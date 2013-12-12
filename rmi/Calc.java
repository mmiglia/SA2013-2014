package rmi;

import java.rmi.*;

public interface Calc
extends Remote
{
	double sum(double d1, double d2) throws RemoteException;
	double sub(double d1, double d2) throws RemoteException;
	void accumulate(double d) throws RemoteException;
	double accumulated() throws RemoteException;
}
