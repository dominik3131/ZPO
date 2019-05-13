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
public class Lock {
    public volatile int flag=0;
    private Thread[] threads;
    public void setThreads(Thread[] t){
        threads=t;
    }
    public void next(){
        flag++;
        if(flag>=threads.length){
            flag=0;
        }
        if(!threads[flag].isAlive()) next(); 
    }
}
