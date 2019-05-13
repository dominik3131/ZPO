package lab7_1;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.google.common.base.Function;

import java.util.Collections;

public class Lab7_1 {

    public static void main(String[] args) {
        findZero(-8.0,-4.0,100000);
    }

    public static double funX(double x) {
        return 0.8 * Math.pow(x, 3) + 4 * Math.pow(x, 2) + x - 2;
        //return x*x-2.0;
    }
    /**
     * 
     * @param a poczatek    
     * @param b koniec
     * @param n ilosc punktow w przedziale
     */
    public static void findZero(Double a, Double b, int n) {
        if (a.doubleValue() == b.doubleValue()) {
            if (funX(a) == 0) {
                System.out.print("miejsce zerowe w " + a);
            } else {
                System.out.print("brak miejsca zerowego ");
            }
        } else {
            //tworzenie listy x
            List<Double> xList = Lists.newArrayList();
            for (Double i = a; i <= b; i = i + (b-a)/(double)n) {    
                xList.add(i);
            }

            //tworzenie leniwej listy y. Wartosci beda odczytane tylko wtedy gdy Collections.binarySearch() bedzie chcial je odczytac
            List<Double> yList = Lists.transform(xList, new Function< Double, Double>() {
                @Override
                public Double apply(Double input) {
                    return funX(input);         //dla kazdego elemntu przy odczycie ta funkcja zostanie wykonana
                }
            });
            int index;
            if (funX(a) <= funX(b)) {
                index = Collections.binarySearch(yList, 0.0);
            } else {
                index = Collections.binarySearch(yList, 0.0, Collections.reverseOrder());
            }
            /* index to pozycja zera na liscie
                moze byc ujemne jesli zera na niej nie ma i wtedy wskazuje, na ktorym miejcu by byl ale jako liczba ujemna dlatego Math.abs(index)
            */
            System.out.println("miejsce zerowe w "+ (a+Math.abs(index)*(b-a)/n));

        }

    }
}
