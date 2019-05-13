package lab9_2;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import javafx.util.Pair;


public class FirstNumber extends RecursiveAction {
    CopyOnWriteArrayList<Integer> list;
    private int start;
    private int end;
    protected static int threshold = 1000;

    public FirstNumber(CopyOnWriteArrayList<Integer> list, int start, int end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }
    
    public static void main(String[] args) throws Exception {
        CopyOnWriteArrayList<Integer> list= new CopyOnWriteArrayList<Integer>();
        FirstNumber fn = new FirstNumber(list, 0, 100000);

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();
        pool.invoke(fn);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Image blur took " + (endTime - startTime) + 
                " milliseconds.");
        System.out.println("found " + list.size());
        
        Collections.sort(list);
        System.out.println(getPairs(list));
        
    }
    protected static List<Pair<Integer,Integer>> getPairs(CopyOnWriteArrayList<Integer> list){
        Collections.reverse(list);
        List<Pair<Integer,Integer>> temp=new ArrayList<>();
        
        for(int i=0;i<list.size()-1;i++)
        {
            if(list.get(i)==list.get(i+1)+2) {
                temp.add(new Pair<>(list.get(i),list.get(i+1)));
            }
            if(temp.size()==5) break;
        }
        
        return temp;
    }
    
    protected void computeDirectly() {
        for (int index = start; index <= end; index++) {
            if(isFirst(index)) list.add(index);
        }
    }

    @Override
    protected void compute() {
        if (end-start < threshold) {
            computeDirectly();
            return;
        }

        int split = (end-start) / 2;
        
        invokeAll(new FirstNumber(list, start, start+split),
                new FirstNumber(list, start + split+1, end));
    }
    
    
    private boolean isFirst(long x) {
        if (x == 1) {
            return false;
        } else if (x == 2 || x == 3) {
            return true;
        } else {

            if (x % 2 == 0) {
                return false;
            } else {
                for (int i = 3; i <= Math.sqrt(x); i = i + 2) {
                    if (x % i == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    
}
