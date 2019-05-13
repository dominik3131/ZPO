package lab7_2;

import java.io.*;
import java.net.*;

public class TCPServerFirst {

    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String answerSentence;
        ServerSocket welcomeSocket = new ServerSocket(6821);
        String[] stringNumbers;
        long from;
        long to;
        long n;
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            
            
            stringNumbers = clientSentence.split(" ");  //podzial na pojedyncze cyfry
            
            //zamiana na long
            from = Long.valueOf(stringNumbers[0]);      
            to = Long.valueOf(stringNumbers[1]);
            
            //tworzenie dwoch zakresow
            FirstCounter nc1 = new FirstCounter(from, to/4*3);  
            FirstCounter nc2 = new FirstCounter(to/4*3+1, to);
           
            //tworzenie watkow
            Thread t1 = new Thread(nc1);
            Thread t2 = new Thread(nc2);
            Long startTime=System.nanoTime();
            //uruchamianie watkow
            t1.start();
            t2.start();
            
            //oczekiwanie na ich zakonczenie
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Long endTime=System.nanoTime();
            endTime=(endTime-startTime)/1000000000;
            n = nc1.getN()+nc2.getN();
            
            
            answerSentence = "count of natural numbers in range from "+from+" to "+to+" is "+n+ "  |  time of calculation is "+endTime+'\n';
            outToClient.writeBytes(answerSentence);
        }
    }

}
