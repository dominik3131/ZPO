package lab4_4;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        if(s1.dataUrodzenia.getYear()>s2.dataUrodzenia.getYear())
        {
            return 1;
        }else if (s1.dataUrodzenia.getYear()==s2.dataUrodzenia.getYear())
        {
            if(s1.nazwisko.charAt(0)>s2.nazwisko.charAt(0))
                return 1;
            else if (s1.nazwisko.charAt(0)==s2.nazwisko.charAt(0))
            {
                if (s1.wzrost<s2.wzrost)
                    return 1;
                else if (s1.wzrost==s2.wzrost)
                    return 0;
                else
                    return -1;
            }
            else
                return -1;
        }
        else
            return -1;

    }}
