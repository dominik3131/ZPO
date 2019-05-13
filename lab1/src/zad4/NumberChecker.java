package zad4;

public class NumberChecker {
	public boolean checkNumber(String number) throws MyNumberException {
		if((number.length()==3 && number.charAt(0)!='-')||(number.length()==4 && number.charAt(0)=='-')) {
			
			try {
				int i=Integer.valueOf(number);
				return true;
			}
			catch(NumberFormatException p) {
				throw new MyNumberException("not int");
			}
		}
		else {
			throw new MyNumberException("number of chars is not correct");
		}
		
	}
}
