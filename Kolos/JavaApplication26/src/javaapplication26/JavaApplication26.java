
package javaapplication26;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Dominik
 */


public class JavaApplication26 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] tab = {"ala", null,"aa","","a","al"};
        System.out.println(func(3,2,1));
        int[] a ={1};
      
        
    }
    public static String func(int ... args){
        StringBuffer b = new StringBuffer("");
        for(int i =args.length-1;i>=0;i--){
            b.append(args[i]);
            if(i!=0)b.append(",");
        }
        return b.toString();
    }
    
}
