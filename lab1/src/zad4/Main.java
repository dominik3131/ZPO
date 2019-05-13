package zad4;

import java.util.HashMap;

import javax.swing.*;

public class Main
{
  public static void main(String[] args)
  {
	HashMap<String, String> hash = new HashMap<String, String>();
	hash.put("1", "jeden");
	hash.put("2", "dwa");
	hash.put("3", "trzy");
	hash.put("4", "cztery");
	hash.put("5", "piec");
	hash.put("6", "szesc");
	hash.put("7", "siedem");
	hash.put("8", "osiem");
	hash.put("9", "dziewiec");
	hash.put("0", "zero");
	hash.put("-", "minus");
	
    JFrame frame = new JFrame("InputDialog");

    String number = JOptionPane.showInputDialog(frame, "What's your number?");
    try {
    	NumberChecker checker = new NumberChecker();
    	checker.checkNumber(number);
    	String numberPhrase=new String();
    	for(int i=0;i<number.length();i++) {
    	    numberPhrase+=hash.get(number.substring(i, i+1))+" ";
    	}
    	JFrame frame2 = new JFrame("OutputDialog");

    	JOptionPane.showMessageDialog(frame2,numberPhrase);
    }
    catch(MyNumberException n) {
    	System.out.println(n.alert);
    }
   
  
  
   
    System.exit(0);
  }
}
