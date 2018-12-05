package src;

/*
 * @class CalculatorServer
 * 
 * Calculator Server class to create the binding for the registry, and implements the Calculator
 * Interface. Is called by CalculatorClient class to perform calculations
 * 
 * @author David Larkin - 20070186
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer extends UnicastRemoteObject implements Calculator 
{

	public static void main(String args[]) 
	{
		try 
		{
			CalculatorServer obj = new CalculatorServer();
			Registry registry = LocateRegistry.createRegistry(1111);
			System.out.println(registry.toString());

			registry.rebind("Calculator", obj);

			System.out.println("Calculator bound in registry");
		} catch (Exception e) 
		{
			System.out.println("CalculatorServer error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public CalculatorServer() throws RemoteException
	{
		super();
	}

	public double add(double a, double b) throws RemoteException 
	{
		return a + b;
	}

	public double sub(double a, double b) throws RemoteException 
	{
		return a - b;
	}

	public double mul(double a, double b) throws RemoteException
	{
		return a * b;
	}

	public double div(double a, double b) throws RemoteException 
	{
		return a / b;
	}

	public double power(double a, double b) throws RemoteException 
	{
		return Math.pow(a, b);
	}

	public double mod(double a, double b) throws RemoteException 
	{
		return a % b;
	}
}