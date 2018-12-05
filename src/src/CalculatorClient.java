package src;

/*
 * @class CalculatorClient
 * 
 * Calculator Client class handles the presentation of the JFrame to the client. 
 * The user can input 2 operands and 1 operator. The chosen operator is stored,
 * and by means of RMI, calls the methods from the server to execute the calculations.
 * 
 * @author David Larkin - 20070186
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorClient 
{
	private double result = 0;
	private JFrame frame;
	private double a;
	private double b;

	private Calculator obj = null; // Setting null object to instantiate later

	public static void main(String args[]) 
	{
		try 
		{
			// Create an instance of the Calculator client to display the calculator GUI,
			// and lookup the registry for a binding.
			CalculatorClient calc = new CalculatorClient();
			calc.obj = (Calculator) Naming.lookup("//" + "192.168.1.129:1111" + "/Calculator");
		} catch (Exception e) 
		{
			System.out.println("CalculatorClient exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Default Contrustor
	public CalculatorClient() 
	{
		setUpFrame();
	}

	public void setA(String a) 
	{
		this.a = Double.parseDouble(a);
	}

	public void setB(String b)
	{
		this.b = Double.parseDouble(b);
	}

	/*
	 * Sets up the JFrame for the Calculator client. Also sets up the Action
	 * Listeners for the buttons. Each button's Action Listener is implemented below
	 * the instantiation of the button. The Equals button is different in that it
	 * splits the input operators/operands by a regex consisting of operators. Then,
	 * I set the operators and operands based on the splits. Then, the calculation
	 * takes place and is displayed on the JTextField at the top of the calculator.
	 */
	private void setUpFrame() 
	{
		JTextField output = new JTextField("", 10);
		JTextField serverMessage = new JTextField("", 10);
		serverMessage.setEditable(false);
		output.setHorizontalAlignment(JTextField.LEFT);
		output.setEditable(false);
		frame = new JFrame("Calculator");
		frame.setResizable(false);
		GridLayout btnLayout = new GridLayout(4, 1, 2, 2);

		JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, add, minus, divide, multiply, power, mod, beq, clear;

		// create operator buttons
		add = new JButton("+");
		add.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "+");
			}
		});

		minus = new JButton("-");
		minus.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "-");
			}
		});

		divide = new JButton("/");
		divide.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "/");
			}
		});

		multiply = new JButton("*");
		multiply.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				output.setText(output.getText() + "*");
			}
		});

		power = new JButton("^");
		power.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				output.setText(output.getText() + "^");
			}

		});

		mod = new JButton("%");
		mod.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "%");
			}
		});

		b0 = new JButton("0");
		b0.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "0");
			}
		});

		b1 = new JButton("1");
		b1.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "1");
			}
		});

		b2 = new JButton("2");
		b2.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				output.setText(output.getText() + "2");
			}
		});

		b3 = new JButton("3");
		b3.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "3");
			}
		});

		b4 = new JButton("4");
		b4.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "4");
			}
		});

		b5 = new JButton("5");
		b5.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "5");
			}
		});

		b6 = new JButton("6");
		b6.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "6");
			}
		});

		b7 = new JButton("7");
		b7.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "7");
			}
		});

		b8 = new JButton("8");
		b8.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				output.setText(output.getText() + "8");
			}
		});

		b9 = new JButton("9");
		b9.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				output.setText(output.getText() + "9");
			}
		});

		clear = new JButton("C");
		clear.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				output.setText("");
			}
		});

		// equals button
		beq = new JButton("=");
		beq.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String regex = "(?<=[%^+*/-])|(?=[%^+*/-])"; // splits the input by all operators that are possible
				String[] ops = output.getText().split(regex);
				setA(ops[0]); // Setting the first operand
				setB(ops[2]); // Setting the second operand
				String operator = ops[1]; // Setting the operator

				switch (operator) 
				{
				// Add symbol
				case "+":
					try 
					{
						// Add both operands
						result = obj.add(a, b);
						output.setText(Double.toString(result));
						serverMessage.setText("Server calculated sum");
					} catch (RemoteException e1) 
					{
						e1.printStackTrace();
						serverMessage.setText("Server couldn't perform calculation");
					}
					break;

				// Minus symbol
				case "-":
					try 
					{
						// Subtract both operands
						result = obj.sub(a, b);
						output.setText(Double.toString(result));
						serverMessage.setText("Server calculated subtraction");
					} catch (RemoteException e1) 
					{
						e1.printStackTrace();
						serverMessage.setText("Server couldn't perform calculation");
					}
					break;

				// Multiply symbol
				case "*":
					try 
					{
						// Multiply both operands
						result = obj.mul(a, b);
						output.setText(Double.toString(result));
						serverMessage.setText("Server calculated multiplication");
					} catch (RemoteException e1) 
					{
						e1.printStackTrace();
						serverMessage.setText("Server couldn't perform calculation");
					}
					break;

				// Divide symbol
				case "/":
					try 
					{
						// Divide both operands
						result = obj.div(a, b);
						output.setText(Double.toString(result));
						serverMessage.setText("Server performed division");
					} catch (RemoteException e1) 
					{
						e1.printStackTrace();
						serverMessage.setText("Server couldn't perform calculation");
					}
					break;

				// Power of symbol
				case "^":
					try 
					{
						// Get first operand (a) to the power of the second operand (b)
						result = obj.power(a, b);
						output.setText(Double.toString(result));
						serverMessage.setText("Server performed power operation");
					} catch (RemoteException e1) 
					{
						e1.printStackTrace();
						serverMessage.setText("Server couldn't perform calculation");
					}
					break;

				// Modulo symbol
				case "%":
					try 
					{
						// Get the modulus of the first operand (a) by the second (b)
						result = obj.mod(a, b);
						output.setText(Double.toString(result));
						serverMessage.setText("Server performed modulus");
					} catch (RemoteException e1) 
					{
						e1.printStackTrace();
						serverMessage.setText("Server couldn't perform calculation");
					}
					break;
				default:
					serverMessage.setText("Invalid input or result too big");
					break;
				}
			}
		});

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(btnLayout);

		// add buttons to mainPanel
		mainPanel.add(output);
		btnPanel.add(b1);
		btnPanel.add(b2);
		btnPanel.add(b3);
		btnPanel.add(add);
		btnPanel.add(multiply);
		btnPanel.add(b4);
		btnPanel.add(b5);
		btnPanel.add(b6);
		btnPanel.add(minus);
		btnPanel.add(divide);
		btnPanel.add(b7);
		btnPanel.add(b8);
		btnPanel.add(b9);
		btnPanel.add(power);
		btnPanel.add(beq);
		btnPanel.add(clear);
		btnPanel.add(b0);
		btnPanel.add(mod);

		// Add the btnPanel to the mainPanel, and show the frame
		mainPanel.add(btnPanel);
		mainPanel.add(serverMessage);
		frame.add(mainPanel);
		frame.setSize(240, 230);
		frame.show();
	}

}