/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countsTest;

import java.util.HashMap;
import static lab5_2.StringCounter.containsKey;
import static lab5_2.StringCounter.counts;
import static lab5_2.StringCounter.get;
import static lab5_2.StringCounter.getOrDefault;
import static lab5_2.StringCounter.putIfAbsent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dominik
 */
public class JUnitTest {
    
    public JUnitTest() throws NoSuchFieldException {
        
    }
    @Test
    public void test() throws NoSuchFieldException{
        System.out.println("main");
   
        String word1 = "wordN1";
        String word2 = "wordN2";
        counts = null;
        counts = new HashMap<>();
        counts.merge(word1, 1, Integer::sum);
        counts.merge(word2, 1, Integer::sum);
        //musza byc dwa rozne slowa bo piwersze wywolanie zwieksza licznik
        assertEquals(counts.merge(word1, 1, Integer::sum),containsKey(word2));
        assertEquals(counts.merge(word1, 1, Integer::sum), get(word2));
        assertEquals(counts.merge(word1, 1, Integer::sum),getOrDefault(word2));
        assertEquals(counts.merge(word1, 1, Integer::sum),putIfAbsent(word2));
        assertEquals(counts.get(word1),counts.get(word2));
    }
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
