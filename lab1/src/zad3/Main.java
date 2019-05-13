package zad3;

public class Main {

	public static void main(String[] args) {
		PeselOperations check = new PeselOperations();
		Pesel pesel = new Pesel();
		try {
			pesel=check.getDateAndSexFromPesel("96011210512");
			System.out.println(pesel.toString());
		}
		catch(PeselException p) {
			System.out.println(p.alert);
		}


	}
	
	
}
