package watki;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Dominik
 */
public class A implements Runnable {

    Kolejka k;
    private String s= "Wątek A, w nieskończonej pętli losuje słowa z tego akapitu (zaznacz, wklej do swojej klasy jako string), każde losowanie poprzedzone pauzą o czasie od 0.5s do 1s (wybranym losowo z dokładnością do 0.01s), i wrzuca je do kolejki o maks. rozmiarze 3 elementów. Wątek B co 0.7s sprawdza czy kolejka została uaktualniona (od poprzedniego sprawdzenia), i jeśli tak, to wypisuje liczbę wielkich liter w kolejce. Do realizacji kolejki użyj kolekcji LinkedList (jeśli po dodaniu elementu na końcu listy liczba elementów w kolejce wynosiłaby 4, to usuwamy element z początku listy).";
    private String[] words;
    private Random r;
    public A(Kolejka k) {
        r=new Random();
        words=s.split(" ");
        this.k = k;
    }

    @Override
    public void run() {
        int timeToSleep;
        while (true) {
            synchronized (k) {
                int wordToAddIndex=r.nextInt(words.length);
                k.add(words[wordToAddIndex]);
            }
            timeToSleep=500+r.nextInt(51)*10;
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(A.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
