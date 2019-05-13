package lab9_3;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author 209443
 */
public class Person implements Callable<Boolean>{
    private static double propability =0.05;
    
       
    public Boolean call() throws Exception {
         Random r =new Random();
        long seconds = r.nextInt(4)+1;
        try{
            Thread.sleep(seconds*1000);
            double p=r.nextDouble();
            if(p<propability){
                return true;
            }
            return false;
        }
        catch(Exception e){
            return null;
        }
    }
    
    
    
}
