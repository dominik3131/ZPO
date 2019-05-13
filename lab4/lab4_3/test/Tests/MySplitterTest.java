/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysplitter;

import com.google.common.base.Splitter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kuba
 */
public class MySplitterTest {
    
    public MySplitterTest() {
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

    
    @Test
    public void test() 
    {
        List<String> result2 =  Splitter.fixedLength(3).splitToList("Ala ma kota");
        List<String> result1 = MySplitter.cutter("Ala ma kota", 3);
        assertEquals(result2,result1);
    }
    
}
