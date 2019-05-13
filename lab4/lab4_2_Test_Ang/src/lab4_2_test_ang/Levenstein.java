/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4_2_test_ang;

/**
 *
 * @author Dominik
 */
public class Levenstein {

    public static void main(String[] args) {
        System.out.println(LevQWERTY("marka", "ariada"));
    }
    
    public static int LevQWERTY(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
       int[][] tab = new int[aLength + 1][bLength + 1];

        for (int i = 0; i <= aLength; i++) {
            tab[i][0] = i;
        }
        for (int i = 1; i <= bLength; i++) {
            tab[0][i] = i;
        }
        int cost = 0;
        for (int i = 1; i <= aLength; i++) {
            for (int j = 1; j <= bLength; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    cost = 0;
                } else {
                    cost = 1;                    
                }
                tab[i][j] = Math.min(tab[i - 1][j - 1], Math.min(tab[i - 1][j], tab[i][j - 1])) + cost;
            }
        }
        return tab[aLength][bLength];
    }
}
