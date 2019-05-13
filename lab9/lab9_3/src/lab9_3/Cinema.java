package lab9_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Cinema {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Cinema cinema = new Cinema();
        int howManyGoing=cinema.howManyPeopleGoing();
        if(howManyGoing<5) System.out.println("Przepraszamy filmu nie będzie");
        else{
            System.out.println("Film się rozpoczął");
            Thread.sleep(2000);
            int howManyStaying=cinema.howManyViewersStay(howManyGoing);
            if(howManyStaying<5) System.out.println("Filmu nie będzie, a Szanowni Państwo to są frejerzy i kasy nie oddamy");
            else{
                Thread.sleep(2000);
                System.out.println("Koniec Filmu");
            }
        }
    }

    public int howManyPeopleGoing() throws InterruptedException, ExecutionException {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            personList.add(new Person());
        }
        Executor decideProcess = Executors.newFixedThreadPool(100);
        List<Future<Boolean>> decisionList = ((ExecutorService) decideProcess).invokeAll(personList);
        int amount = 0;
        for (Future<Boolean> decision : decisionList) {
            if (decision.get()) {
                amount++;
            }
        }
        return amount;
    }

    public int howManyViewersStay(int viewersAmount) throws InterruptedException, ExecutionException {
        List<Viewer> viewerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            viewerList.add(new Viewer());
        }
        Executor decideProcess = Executors.newFixedThreadPool(100);
        List<Future<Boolean>> decisionList = ((ExecutorService) decideProcess).invokeAll(viewerList);
        int amount = 0;
        for (Future<Boolean> decision : decisionList) {
            if (decision.get()) {
                amount++;
            }
        }
        return amount;
    }

}
