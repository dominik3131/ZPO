package lab9_1;

public class Printer implements Runnable {

    private int turn;
    private Lock lock;
    private String toPrint;
    private int index;
    private int lastPrinter;

    public Printer(Lock lock, int flag, String toPrint,int lastPrinter) {
        this.index = 0;
        this.lock = lock;
        this.turn = flag;
        this.toPrint = toPrint;
        this.lastPrinter=lastPrinter;
    }

    @Override
    public void run() {
        try {

            synchronized (lock) {
                for(int i=0;i<toPrint.length();i++){
                    
                    while (lock.flag != turn) {
                        lock.wait();
                    }
                    System.out.print(toPrint.charAt(i));
                    
                    lock.next();
                    lock.notifyAll();
                }
            }

        } catch (Exception e) {
            return;
        }
    }

}
