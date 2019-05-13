/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9_1;

/**
 *
 * @author 209443
 */
public class Lab9_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         String[] strings = {"aaaa", "bb", "ccccccccccccc", "dddddd"}; 
         Lock lock=new Lock();
         Thread[] threads = new Thread[4];
         for(int i=0;i<strings.length;i++){
             Thread t=new Thread(new Printer(strings[i],i,lock));
             threads[i]=t;
         }
         lock.setThreads(threads);
         for(Thread t: threads){
             t.start();
         }
    }
    
}
