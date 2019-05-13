package lab9_1;

public class Lock {
    public volatile int flag=0;
    private Thread[] threads;
    
    public void setThreads(Thread[] t){
        threads=t;
    }
    
    public void next(){
        flag++;
        if(flag>=threads.length) flag=0;
        if(!threads[flag].isAlive()) next();
    }
}
