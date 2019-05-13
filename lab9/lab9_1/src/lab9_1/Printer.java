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
public class Printer implements Runnable {
    private String toPrint;
    private int turn;
    private Lock lock;

    public Printer(String toPrint, int turn, Lock lock) {
        this.toPrint = toPrint;
        this.turn = turn;
        this.lock = lock;
    }
    
    @Override
    public void run() {
        try{
            synchronized(lock){
                for(int index=0;index<toPrint.length();index++){
                    while(lock.flag!=turn){
                        lock.wait();
                    }
                    System.out.print(toPrint.charAt(index));
                    lock.next();
                    lock.notifyAll();
                }
            }
        }
        catch(Exception e){
        }
    }

    

}
