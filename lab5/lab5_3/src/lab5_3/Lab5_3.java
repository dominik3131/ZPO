/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5_3;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Lab5_3 {

    public Set<String> allWords = new LinkedHashSet<>();
    public Set<String> forgottenWords = new LinkedHashSet<>();
    Multimap<Integer, String> rememberedWords = HashMultimap.create();
    private final int numberOfWordsToLearn = 2;
    private final int numberOfWordsToForgot = 2;

    private final Random r = new Random();

    public void start() {
        try {
            File wordsFile = new File("1500.txt");
            Scanner in = new Scanner(wordsFile);

            String word;
            while (in.hasNextLine()) {
                word = in.nextLine();
                allWords.add(word);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Odczytano");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lab5_3.class.getName()).log(Level.SEVERE, null, ex);
        }
        learningSymulation(10, 3, 0.5);
    }

    /**
     * @param n days
     * @param k days after words can be forgotten
     * @param p possibility to forget
     */
    private void learningSymulation(int n, int k, double p) {
        for (int i = 1; i <= n; i++) {
            rememberNextWords(i, numberOfWordsToLearn);
            System.out.println("Day " + (i));
            System.out.println("New Words: " + rememberedWords.get(i));
            forgetWords(i, k, numberOfWordsToForgot, p);
            if (forgottenWords.isEmpty()) {
                System.out.println("Forgotten words: ---------");
            } else {
                System.out.print("Forgotten words: ");
                Iterator it = forgottenWords.iterator();
                while (it.hasNext()) {
                    System.out.print(it.next() + " ");
                }
                System.out.println("");
            }
            forgottenWords.clear();
            System.out.println(rememberedWords.values());
            
        }
    }

    private int getRandomNumber(int start, int end) {
        int a = new Random().nextInt(end - start + 1) + start;
        return a;
    }

    private void rememberNextWords(int actualDay, int words) {
        int allWordsSize, randomWordIndex;
        for (int i = 0; i < words; i++) {
            allWordsSize = allWords.size();
            randomWordIndex = r.nextInt(allWordsSize);
            int j = 0;
            for (String obj : allWords) {
                if (j == randomWordIndex) {
                    allWords.remove(obj);
                    rememberedWords.put(actualDay, obj);
                    break;
                }
                j++;
            }
        }
    }

    private void forgetWords(int actualDay, int forgotDays, int wordsToForget, double possibility) {
        if (actualDay - forgotDays > 0) {
            int randNumber;
            for (int i = 0; i < wordsToForget; i++) {
                randNumber = r.nextInt(1000);
                if (possibility * 1000 > randNumber) {
                    forgetOneWord(actualDay, forgotDays);
                }
            }
        }

    }

    private void forgetOneWord(int actualDay, int forgotDays) {
        Integer day = r.nextInt(actualDay - forgotDays)+1;
        String wordToForgot;
        Collection wordsOfThatDay = rememberedWords.get(day);
        if (wordsOfThatDay.isEmpty()) {
            forgetOneWord(actualDay, forgotDays);
        } else {

            int i = r.nextInt(wordsOfThatDay.size());
            wordToForgot = (String) wordsOfThatDay.toArray()[i];
            forgottenWords.add(wordToForgot);
            rememberedWords.remove(day, wordToForgot);
        }
    }

    /*
        int size = allWords.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for (String obj : allWords) {
            if (i == item) {
                firstWord = obj;
                allWords.remove(obj);
                rememberedWords.add(day, obj);
            }
            i++;
        }

        size = allWords.size();
        item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
        i = 0;
        for (String obj : allWords) {
            if (i == item) {
                secondWord = obj;
                allWords.remove(obj);
                rememberedWords.add(obj);
            }
            i++;
        }
     */
}
