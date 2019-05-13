package watki;

/**
 *
 * @author Dominik
 */


public class Watki {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kolejka k = new Kolejka();
        Thread a=new Thread(new A(k));
        Thread b=new Thread(new B(k));
        a.start();
        b.start();
    }
    
}
