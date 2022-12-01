package calculator;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
	JFrame f;
	JTextField t;
	String[] buttonLabels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", ".", "0", "=", "+", "Delete", "Clear"};
	
	static double a=0,b=0,result=0;
	static int operator=0;

	Calculator() {
		f=new JFrame("Calculator");
		f.setLayout(null);
		
		t=new JTextField();
		t.setEditable(false);
		t.setFont(new Font(Font.SERIF,Font.BOLD,30));
		t.setBounds(10,10,220,40);
		f.add(t);
		
		// Initialize buttons
		for(int i=0; i<buttonLabels.length; i++) {
			JButton button = new JButton(buttonLabels[i]);
			button.setFont(new Font(Font.SERIF,Font.BOLD,20));
			
			// Set Background colors for the buttons
			if(buttonLabels[i].equals("+") || buttonLabels[i].equals("-") || buttonLabels[i].equals("*") || buttonLabels[i].equals("/") || buttonLabels[i].equals("=")) {
				button.setBackground(Color.GRAY);
			} else {
				button.setBackground(Color.WHITE);
			}
			
			// Add to frame
			f.add(button);
			
			// Set bounds
			if(buttonLabels[i].equals("Delete") || buttonLabels[i].equals("Clear")) {
				button.setBounds(10+((i%2)*120),100+((i/4)*60),100,50);
			} else {
				button.setBounds(10+((i%4)*60),100+((i/4)*60),50,50);
			}
			
			// Add action listeners
			button.addActionListener(this);
		}
		
		
		f.setVisible(true);
		f.setSize(260,435);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setResizable(false);
	    f.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		String btnText = ((JButton) e.getSource()).getText();
		
		if(btnText.equals("+")) {
			a=Double.parseDouble(t.getText());
	        operator=1;
	        t.setText("");
		} else if(btnText.equals("-")) {
			a=Double.parseDouble(t.getText());
	        operator=2;
	        t.setText("");
		} else if(btnText.equals("*")) {
			a=Double.parseDouble(t.getText());
	        operator=3;
	        t.setText("");
		} else if(btnText.equals("/")) {
			a=Double.parseDouble(t.getText());
	        operator=4;
	        t.setText("");
		} else if(btnText.equals("Delete")) {
			String s = t.getText();
	        t.setText("");
	        for(int i=0;i<s.length()-1;i++) {
	        	t.setText(t.getText()+s.charAt(i));
	        }
		} else if(btnText.equals("Clear")) {
	        t.setText("");
		} else if(btnText.equals("=")) {
			b=Double.parseDouble(t.getText());

	        switch(operator) {
	            case 1: result=a+b;
	            	break;

	            case 2: result=a-b;
	                break;

	            case 3: result=a*b;
	                break;

	            case 4: result=a/b;
	                break;

	            default: result=0;
	          }
	          t.setText(""+result);
		} else {
			t.setText(t.getText().concat(btnText));
		}
	}
	
	public static void main(String[] args)
	{
	    new Calculator();
	}
}