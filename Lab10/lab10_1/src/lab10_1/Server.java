package lab10_1;

import java.io.*;
import java.net.*;

/**
 *
 * @author Dominik
 */
public class Server {

    Student[] studentArray = new Student[10];
    int index;
    ObjectOutputStream output;
    ObjectInputStream input;
    ServerSocket serverSocket;
    Student student;
    

    public static void main(String argv[]) throws Exception {
        Server server = new Server();
        server.startServer();

    }

    public Server() throws IOException {
        System.out.println("Serwer");
        studentArray = new Student[10];
        for (int i = 0; i < 10; i++) {
            studentArray[i] = new Student(Integer.toString(i), Integer.toString(i), i);
        }
        serverSocket = new ServerSocket(6000);
    }

    public void startServer() throws ClassNotFoundException, IOException {
        while (true) {
            System.out.println("\nOczekuję na połączenie");
            Socket socket = serverSocket.accept();
            System.out.println("Connected");
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
            index = input.readInt();
            System.out.println("Otrzymano: " + index);
            student=findStudent(index);
            System.out.println("Wysyłam dane studenta");
            output.writeObject(student);
            output.flush();
            
            System.out.println("Czekam na odpowiedź");
            Object tempStudent;
            try{
               tempStudent = input.readObject();
            }
            catch(EOFException e){
                tempStudent=null;
            }
            
            
            if (tempStudent == null) {
                System.out.println("Klient nie odesłał studenta, zamykam połączenie");
            } else if (tempStudent.getClass() != Student.class) {
                System.out.println("Klient odesłał nieprawidłowe dane, zamykam połączenie");
            } else {
                student = (Student) tempStudent;
                System.out.println("Dane studenta po aktualizacji:");
                System.out.println(student);
                System.out.println("Aktualizuje dane w tablicy");
                updateStudentMarks();
                System.out.println("Zamykam połączenie");
                

                
            }

        }
    }

    private Student findStudent(int index) {
        Student temp = new Student(index);
        for (Student s : studentArray) {
            if (s.equals(temp)) {
                return s;
            }
        }
        return null;

    }

    private void updateStudentMarks() {
        for (int i = 0; i < studentArray.length; i++) {
            if(studentArray[i].equals(student)){
                studentArray[i]=student;
            }
                
        }
    }

}
