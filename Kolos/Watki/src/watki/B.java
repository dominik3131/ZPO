package watki;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominik
 */
public class B implements Runnable {

    Kolejka k;

    public B(Kolejka k) {
        this.k = k;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (k) {
                if (k.checkIfUpdated()) {
                    int i = 0;
                    for (String s : k.getList()) {
                        if (s.length() != 0) {
                            if (Character.isUpperCase(s.charAt(0))) {
                                i++;
                            }
                        }
                    }
                    System.out.println(i);
                }

            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                Logger.getLogger(B.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
