package Test;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;
import lab4_4.Student;
import lab4_4.StudentComparator;
import lab4_4.StudentComparatorG;
public class MainTest extends TestCase {

    public void tworzenieListy(ArrayList<Student> lista) {
       lista.add(new Student("Dominik","R",new Date(95,7,29), 120));
	lista.add(new Student("Dominik","R",new Date(96,8,29), 120));
	lista.add(new Student("Dominik","S",new Date(96,8,29), 120));
	lista.add(new Student("Dominik","S",new Date(96,8,29), 110));
	lista.add(new Student("Dominik","S",new Date(96,8,29), 100));
    }

    public void testMain() {
        ArrayList<Student> lista=new ArrayList<>();
        tworzenieListy(lista);
        lista.sort(new StudentComparator());

        ArrayList<Student> lista2=new ArrayList<>();
        tworzenieListy(lista2);
        lista2.sort(new StudentComparatorG());

        for (int i=0; i<lista.size(); i++)
            assertEquals(lista.get(i).toString(),lista2.get(i).toString());

    }

    }
