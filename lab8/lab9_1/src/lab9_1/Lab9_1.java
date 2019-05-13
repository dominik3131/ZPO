package lab9_1;

public class Lab9_1 {

    
    public static void main(String[] args) {
        String[] strings = {"aaaa", "bb", "ccccccccccccc", "dddddd"};
        Lock lock=new Lock();
        Thread[] threads=new Thread[strings.length];
        for(int i=0;i<strings.length;i++){
            Thread t=new Thread(new Printer(lock,i,strings[i],strings.length));
            threads[i]=t;
        }
        lock.setThreads(threads);
        for(Thread t:threads){
            t.start();
        }
    }
    
}
