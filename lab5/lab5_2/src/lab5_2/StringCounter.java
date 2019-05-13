/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5_2;

import java.util.HashMap;
import java.util.Map;

public class StringCounter {

    public static Map<String, Integer> counts;

    public static Integer containsKey(String key) {
        if (counts.containsKey(key)) {
            return counts.put(key, counts.get(key) + 1) + 1;
        }
        return null;
    }

    public static Integer get(String key) throws NullPointerException {
        if (counts.get(key) != null) {
            return counts.put(key, counts.get(key) + 1) + 1;
        }
        return null;

    }

    public static Integer getOrDefault(String key) throws NoSuchFieldException {
        if (counts.getOrDefault(key, -1) != -1) {
            return counts.put(key, counts.get(key) + 1) + 1;
        }
        return null;

    }

    public static Integer putIfAbsent(String key) {
        if (counts.putIfAbsent(key, 1) != null) {
            return counts.put(key, counts.get(key) + 1) + 1;
        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchFieldException {
        counts = new HashMap<String, Integer>();

        String word = "słowo";
        counts.merge(word, 1, Integer::sum);
        containsKey(word);
        get(word);
        getOrDefault(word);
        putIfAbsent(word);

        System.out.println(counts);

    }

}
