package src;
/*
 * @interface Calculator
 * 
 * Calculator is the Interface, extending Remote, that provides the methds to be implemented
 * in the CalculatorServer class
 * 
 * @author David Larkin - 20070186
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote
{
	public double add(double a, double b) throws RemoteException; 
	 
	public double sub(double a, double b) throws RemoteException; 
	 
	public double mul(double a, double b) throws RemoteException; 
	 
	public double div(double a, double b) throws RemoteException; 
	
	public double power(double a, double b) throws RemoteException;
	
	public double mod(double a, double b) throws RemoteException;
}
