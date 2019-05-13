package zad4;

public class MyNumberException extends Exception {
	public String alert;
	public MyNumberException(String a) {
		alert=a;
	}
	private static final long serialVersionUID = 1L;
}
