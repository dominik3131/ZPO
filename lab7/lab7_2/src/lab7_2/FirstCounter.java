/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7_2;

//implementuje Runnable zeby mozna bylo odpalic w Thread
public class FirstCounter implements Runnable {

    long a, b, n;

    public FirstCounter(long x, long y) {
        a = x;
        b = y;
    }

    //sprawdzanie wszystkich liczb w przedziale
    public void findFirstCount() {
        n = 0;
        for (; a <= b; a++) {
            if (isFirst(a)) {
                n++;
            }
        }
    }
    
    //zwraca ilosc liczb pierwszych
    public long getN() {
        return n;
    }
    
    //sprawdzanie czy pierwsza
    private boolean isFirst(long x) {
        if (x == 1) {
            return false;
        } else if (x == 2 || x == 3) {
            return true;
        } else {

            if (x % 2 == 0) {
                return false;
            } else {
                for (int i = 3; i <= Math.sqrt(x); i = i + 2) {
                    if (x % i == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    //metoda dla Thread
    @Override
    public void run() {
        try {
            findFirstCount();

        } catch (Exception e) {

            System.out.println("Exception is caught");
        }
    }
}
