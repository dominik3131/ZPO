package com.mycompany.bazamaven;

import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Dominik
 */
public class Client {

    Scanner input;
    Database db;

    public static void main(String[] args) {
        Client client = new Client();
    }

    public Client() {
        input = new Scanner(System.in);
        db = new Database();
        showMenu();
    }

    public void showMenu() {
        System.out.println("-- Lista opcji --");
        System.out.println(
                "  1) Dodaj pracownika\n"
                + "  2) Pokaż posortowane po konkretnym polu\n"
                + "  3) Pokaż średnie zarobki \n"
                + "  4) Wyczyść tabelę \n"
                + "  5) Usuń tabelę \n"
                + "  6) Stwórz populację tabeli \n"
                + "  7) Stwórz tabelę \n"
                + "  8) Wyjdź\n "
        );

        int selection = input.nextInt();
        input.nextLine();
        switch (selection) {
            case 1:
                addEmployeeToTable();

                break;
            case 2:
                showSortedTable();
                break;
            case 3:
                showAvarageSalary();
                break;
            case 4:
                db.truncateTable();
                break;
            case 5:
                db.dropTable();
                break;
            case 6:
                db.populateTable();
                break;
            case 7:
                db.createTable();
                break;
            case 8:
                db.closeConnection();
                return;
            default:
                System.out.println("niepoprawny wybór");
                break;
        }
        showMenu();
    }

    private void addEmployeeToTable() {
        String name;
        String surname;
        String country;
        Integer salary;
        System.out.println("Wprowadź imię");
        name = input.nextLine();
        System.out.println("Wprowadź nazwisko");
        surname = input.nextLine();
        System.out.println("Wprowadź kraj");
        country = input.nextLine();
        System.out.println("Wprowadź pensję");
        salary = input.nextInt();
        input.nextLine();

        db.addRow(name, surname, country, salary);
        
    }

    private void showSortedTable() {
        System.out.println("-- Lista pól --");
        System.out.println(
                "  1) imię\n"
                + "  2) nazwisko\n"
                + "  3) kraj \n"
                + "  4) pensja\n "
        );

        int selection = input.nextInt();
        input.nextLine();
        String option;
        switch (selection) {
            case 1:
                option = "imie";
                break;
            case 2:
                option = "nazwisko";
                break;
            case 3:
                option = "kraj";
                break;
            case 4:
                option = "placa";
                break;
            default:
                System.out.println("niepoprawny wybór, wybierz ponownie");
                showSortedTable();
                return;
        }
        db.orderBy(option);
    }

    private void showAvarageSalary() {
        db.showAverageSalary();
    }
}
