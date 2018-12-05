package test;
/*
 * Test class for the CalculatorServer class. Tests each method that the Server implements e.g.
 * Add, Multiply, Subtract, Divide, Modulo, Power
 * 
 *  @author David Larkin - 20070186
 */
import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Calculator;
import src.CalculatorServer;

class CalculatorServerTest 
{
	private Calculator calc;
	
	@BeforeAll
	static void setUpCalc()
	{
		try 
		{
			CalculatorServer obj = new CalculatorServer();
			Registry registry = LocateRegistry.createRegistry(1112);
			registry.rebind("Calculator", obj);
		} catch (RemoteException e) 
		{
			e.printStackTrace();
		}
	}
	
	@BeforeEach
	void getBinding()
	{
		try 
		{
			calc = (Calculator) Naming.lookup("//" + "192.168.1.129:1112" + "/Calculator");
		} catch (MalformedURLException | RemoteException | NotBoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddSimple()
	{
		try 
		{
			assertEquals(10, calc.add(4, 6), 0.1);
		} catch (RemoteException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddComplex()
	{
		try
		{
			assertEquals(106.24, calc.add(105.01, 1.23), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testSubtractSimple()
	{
		try
		{
			assertEquals(6, calc.sub(10, 4), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testSubtractMinus()
	{
		try
		{
			assertEquals(-15, calc.sub(5, 20), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testSubtractComplex()
	{
		try
		{
			assertEquals(6709.13, calc.sub(6710, 0.87), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testDivideSimple()
	{
		try
		{
			assertEquals(4, calc.div(16, 4), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testDivideComplex()
	{
		try
		{
			assertEquals(1.18, calc.div(96, 81), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testMultiplySimple()
	{
		try
		{
			assertEquals(60, calc.mul(10, 6), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testMultiplyComplex()
	{
		try
		{
			assertEquals(14062620, calc.mul(9685, 1452), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testPowerOfSimple()
	{
		try
		{
			assertEquals(16, calc.power(2, 4), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testPowerOfComplex()
	{
		try
		{
			assertEquals(1e+134, calc.power(100, 67), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testModulo()
	{
		try
		{
			assertEquals(6, calc.mod(78, 72), 0.1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}

}
