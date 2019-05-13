

public class DataInfo {
	public String name;
	public String surname;
	public String country;
	public int salary;
	public DataInfo(String[] data) {
		name=data[0];
		surname=data[1];
		country=data[2];
		salary=Integer.parseInt(data[3]);
	}
	
	@Override
	public String toString() {
		return name+" "+surname+" "+country+" "+salary+"\n";
	}
}
