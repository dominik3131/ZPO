package ruler;

/**
 *
 * @author Dominik
 * @version 1.0
 */
public class Ruler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            drawRuler(3.125, 10);
        } catch (wrongLengthException e) {
            System.out.println(e.alert);
        }
    }

    /**
     * Draws Ruler in console use drawRuler(doble,int)
     *
     * @throws wrongLengthException
     *
     * @param length length of the ruler, should be multiple of 0.125
     * @param nestLevel number of '-' used to draw at the whole unit
     */
    public static void drawRuler(double length, int nestLevel) throws wrongLengthException {
        checkLength(length);
        if (length < 0) {
            throw new wrongLengthException("negative length");
        } else if (length == 0) {
            for (int i = 0; i < nestLevel; i++) {
                System.out.print("-");
            }
            System.out.print(length + "\n");
        } else {
            drawRuler(length - 0.125, nestLevel);
            double numberOfLines = 0;
            if (length % 1 == 0) {
                numberOfLines = nestLevel;
            } else if (length % 0.5 == 0) {
                numberOfLines = (double) nestLevel *3.0/4.0;
            } else if (length % 0.25 == 0 || length % 0.75 == 0) {
                numberOfLines = (double) nestLevel / 2.0;
            } else if (length % 0.125 == 0 || length % 0.375 == 0 || length % 0.625 == 0 || length % 0.875 == 0) {
                numberOfLines = (double) nestLevel / 4.0;
            }

            for (int i = 0; i < numberOfLines; i++) {
                System.out.print("-");
            }

            if (length % 1 == 0) {
                System.out.print(length);
            }
            System.out.print("\n");

        }
    }

    /**
     * checks if length is multiple of 0.125
     *
     * @param length length pased from  drawRuler(double,int)
     * @throws wrongLengthException
     */
    public static void checkLength(double length) throws wrongLengthException {
        if (length % 0.125 != 0) {
            throw new wrongLengthException("bad length, use multiple of 0.125");
        }
    }
}
