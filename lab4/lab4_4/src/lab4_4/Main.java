package lab4_4;


import java.util.ArrayList;
import java.util.Date;


public class Main {

    public static void main(String[] args) {
	ArrayList<Student> lista=new ArrayList<>();
	lista.add(new Student("Dominik","R",new Date(95,7,29), 120));
	lista.add(new Student("Dominik","R",new Date(96,8,29), 120));
	lista.add(new Student("Dominik","S",new Date(96,8,29), 120));
	lista.add(new Student("Dominik","S",new Date(96,8,29), 110));
	lista.add(new Student("Dominik","S",new Date(96,8,29), 100));
	lista.sort(new StudentComparator());
	//lista.sort(new StudentComparatorG());


    for (int i=0; i<lista.size(); i++)
            System.out.println(lista.get(i).toString());

    }
}
