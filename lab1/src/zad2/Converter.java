package zad2;
import java.util.Scanner;
public class Converter {
	int liczba;
	String choosedOption;
	Scanner reader;
	public Converter() {
		liczba=0b1101_1000;
		reader = new Scanner(System.in);
	}
	
	
	public void chooseTypeOfConvert() {
		boolean toContinue=true;
		while(toContinue) {
			System.out.println("wybierz\n");
			getConvertOption();
			switch(choosedOption) {
				case "dziesiêæ":{
					System.out.println(Integer.toString(liczba, 10));
					break;
				}
				case "trzy":{
					System.out.println(Integer.toString(liczba, 3));
					break;			
				}
				case "szesnaœcie":{
					System.out.println(Integer.toString(liczba, 16));
					break;	
				}
				case "exit":{
					toContinue=false;
					reader.close();
					break;
				}
					
				default:
					System.out.println("Nie ma takiego wyboru\n");
			}	
		}
		
		
	}
	
	
	
	private void getConvertOption() {
		choosedOption=reader.nextLine();
	}
	
}
