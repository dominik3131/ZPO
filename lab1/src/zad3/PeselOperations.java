package zad3;

public class PeselOperations  {
	public Pesel getDateAndSexFromPesel(String peselNumber) throws PeselException {
		if(checkPeselString(peselNumber)) {
			Pesel p = new Pesel();
			if(Integer.valueOf(peselNumber.substring(9, 10))%2==0) 
				p.sex='f';
			else
				p.sex='m';
			p.day=peselNumber.substring(4,6);
			if(Integer.valueOf(peselNumber.substring(2,4))>80) {
				p.month=Integer.toString((Integer.valueOf(peselNumber.substring(2,4))-80));
				p.year="18";
				p.year+=peselNumber.substring(0,2);
			}
			else if(Integer.valueOf(peselNumber.substring(2,4))>60) {
				p.month=Integer.toString((Integer.valueOf(peselNumber.substring(2,4))-60));
				p.year="22";
				p.year+=peselNumber.substring(0,2);
			}
			else if(Integer.valueOf(peselNumber.substring(2,4))>40) {
				p.month=Integer.toString((Integer.valueOf(peselNumber.substring(2,4))-40));
				p.year="21";
				p.year+=peselNumber.substring(0,2);
			}
			else if(Integer.valueOf(peselNumber.substring(2,4))>20) {
				p.month=Integer.toString((Integer.valueOf(peselNumber.substring(2,4))-20));
				p.year="20";
				p.year+=peselNumber.substring(0,2);
			}
			else {
				p.month=peselNumber.substring(2,4);
				p.year="19";
				p.year+=peselNumber.substring(0,2);
			}
			return p;
			
		}
		else throw new PeselException("operacja nie uda³a siê");
		
		
	}
	private boolean checkPeselString(String peselNumber) throws PeselException {
		if(peselNumber.length()!=11){
			throw new PeselException("podany ci¹g znaków ma z³a d³ugoœæ, powinien zawieraæ 11 znaków");
		}
		long pesel;
		try {
			pesel = Long.valueOf(peselNumber);
		}
		catch(NumberFormatException n) {
			throw new PeselException("podany ci¹g znaków nie jest prawid³owym numerem");
		}
		
		long controlSum		= 0;
		long temp = pesel;
		temp=temp/10;
		byte[] controlSumFactors= {9,7,3,1,9,7,3,1,9,7};
		for(int i = 0;i<10;i++) {
			controlSum+=controlSumFactors[9-i]*(temp%10);		
			temp=temp/10;
		}
		if(pesel%10==controlSum%10) return true;
		else throw new PeselException("podany numer jest nieprawid³owym numerem PESEL");
	}
	

		
}
