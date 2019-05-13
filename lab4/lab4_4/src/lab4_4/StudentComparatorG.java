package lab4_4;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import java.util.Comparator;

public class StudentComparatorG  implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return ComparisonChain.start()
                .compare(s1.dataUrodzenia.getYear(), s2.dataUrodzenia.getYear())
                .compare(s1.nazwisko.charAt(0), s2.nazwisko.charAt(0))
                .compare(s1.wzrost, s2.wzrost, Ordering.natural().reversed())
                .result();
    }
}
