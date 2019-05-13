package lab10_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    Student student;
    Scanner scanner;
    Socket socket;
    ObjectOutputStream output;
    ObjectInputStream input;

    public Client(String serverName, int socketNumber) throws IOException {
        System.out.println("Client");
        socket = new Socket(serverName, socketNumber);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.flush();
        input = new ObjectInputStream(socket.getInputStream());
        scanner = new Scanner(System.in);
    }

    public static void main(String argv[]) throws Exception {
        Client client = new Client("localhost", 6000);
        client.startSession();

    }

    public void startSession() throws IOException, ClassNotFoundException {
        System.out.println("Wpisz index: ");
        int index = scanner.nextInt();
        output.writeInt(index);
        output.flush();
        System.out.println("test");
        Object tempStudent = input.readObject();
        if (tempStudent == null) {
            System.out.println("Serwer nie ma informacji o danym studencie");
            socket.close();
        } else if (tempStudent.getClass() != Student.class) {
            System.out.println("Serwer odesłał nieprawidłowe dane");
        } else {
            student = (Student) tempStudent;
            System.out.println("Dane studenta:");
            System.out.println(student);

            addMark();
        }
    }

    private void addMark() throws IOException {
        while (true) {
            System.out.println("Czy dopisać ocenę? T/N: ");
            String answer = scanner.next();
            if (answer.toLowerCase().equals("t")) {
                int mark = readMarkToAdd();
                student.addMark(mark);
                System.out.println(mark);
                System.out.println("Dodono ocenę, dane studenta po aktualizacji");
                System.out.println(student);
                System.out.println("Przesyłam dane na serwer");
                output.writeObject(student);
                output.flush();
                System.out.println("Przesłano");
                break;
            } else if (answer.toLowerCase().equals("n")) {
                output.writeObject(null);
                output.flush();
                break;
            } else {
                System.out.println("nie ma takiej odpowiedzi, spróbuj ponownie");
            }
        }
        System.out.println("Koniec sesji");
        socket.close();
        output.close();
    }

    private int readMarkToAdd() {
        int mark;
        while (true) {
            System.out.println("Wprowadz ocene");
            mark = scanner.nextInt();
            if (mark < 7 && mark > 0) {
                return mark;
            } else {
                System.out.println("wprowadzono złą ocenę");
            }
        }
    }
}
