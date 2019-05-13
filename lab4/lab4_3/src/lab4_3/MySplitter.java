package lab4_3;


import com.google.common.base.Splitter;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MySplitter {

    public static void main(String [] args){
        try {
            System.out.println(MySplitter.cutter("Ala ma kota", 3));
            List<String> result1 = MySplitter.cutter("Ala ma kota", 3);
            System.out.println(MySplitter.cutter("abcd", 2));
            System.out.println(MySplitter.cutter(null, 3));
        } catch(IllegalArgumentException e){
            System.out.println("Error");
        }
        try {
            System.out.println(Splitter.fixedLength(3).split("Ala ma kota"));
            List<String> result2 = (List<String>) Splitter.fixedLength(3).splitToList("Ala ma kota");
            System.out.println(Splitter.fixedLength(2).split("abcd"));
            System.out.println(Splitter.fixedLength(3).split(null));
        } catch(NullPointerException e){
            System.out.println("Error");
        }  
    }
    public static List<String> cutter(@NotNull String s, int length) throws IllegalArgumentException{
        if(s==null || length<=0) throw new IllegalArgumentException();
        List<String> wynik = new ArrayList<String>();
        boolean ifStop = false;
        int first = 0;
        int data = length;

        while(!ifStop){
            if(data <=s.length()){
                wynik.add(s.substring(first,data));
            } else if(first<s.length()){
                wynik.add(s.substring(first,s.length()));
                ifStop = true;
            }  else{
                ifStop = true;
            }
            first = data++;
            data = first+length;
        }
        return wynik;
    }
}