package zad3;

public class PeselException extends Exception {

	public String alert;
	public PeselException(String a) {
		alert=a;
	}
	private static final long serialVersionUID = 1L;
	
}
