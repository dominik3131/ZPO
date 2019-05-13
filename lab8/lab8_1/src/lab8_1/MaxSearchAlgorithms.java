/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominik
 */
public class MaxSearchAlgorithms {

    int test(int i) {
        return i;
    }
    void test1(){}
    int[] leftToRightScan(int[] tab) {
        List<Integer> result = new ArrayList<>();
        if (tab.length == 0) {
            return result.stream().mapToInt(i -> i).toArray();
        } else if (tab.length == 1) {
            result.add(tab[0]);
            return result.stream().mapToInt(i -> i).toArray();
        } else {
            result.add(tab[0]);
            for (int i = 1; i < tab.length; i++) {
                if (tab[i] > result.get(result.size() - 1)) {
                    result.add(tab[i]);
                }
            }
            return result.stream().mapToInt(i -> i).toArray();
        }

    }

    int[] rightToLeftScan(int[] tab) {
        List<Integer> result = new ArrayList<>();
        if (tab.length == 0) {
            return result.stream().mapToInt(i -> i).toArray();
        } else if (tab.length == 1) {
            result.add(tab[0]);
            return result.stream().mapToInt(i -> i).toArray();
        } else {
            result.add(tab[tab.length - 1]);

            for (int i = tab.length - 2; i >= 0; i--) {
                if (tab[i] > result.get(result.size() - 1)) {
                    result.add(tab[i]);
                }
            }
            return result.stream().mapToInt(i -> i).toArray();
        }

    }

    int[] evenAndOddScan(int[] tab) {
        List<Integer> result = new ArrayList<>();
        if (tab.length == 0) {
            return result.stream().mapToInt(i -> i).toArray();
        } else if (tab.length == 1) {
            result.add(tab[0]);
            return result.stream().mapToInt(i -> i).toArray();
        } else {
            result.add(tab[1]);

            for (int i = 1; i < tab.length; i = i + 2) {
                if (tab[i] > result.get(result.size() - 1)) {
                    result.add(tab[i]);
                }
            }
            for (int i = 0; i < tab.length; i = i + 2) {
                if (tab[i] > result.get(result.size() - 1)) {
                    result.add(tab[i]);
                }
            }
            return result.stream().mapToInt(i -> i).toArray();
        }
    }
}
