package lab9_3;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author 209443
 */
public class Viewer implements Callable<Boolean>{
    private static double propability =0.3;
    
       
    public Boolean call() throws Exception {
        Random r =new Random();
        try{
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
