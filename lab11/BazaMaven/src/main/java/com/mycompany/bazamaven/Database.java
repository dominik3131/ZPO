package com.mycompany.bazamaven;

/**
 *
 * @author Dominik
 */
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    Connection con;
    Statement stmt;
    String host;
    String userName;
    String userPass;

    public Database() {
        host = "jdbc:mysql://localhost/zpo?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        userName = "root";
        userPass = "";
        connectToDatabase();
        createStatement();
        createTable();
        
    }

    public Database(String dbHost, String dbUser, String dbPass) {
        host = dbHost;
        userName = dbUser;
        userPass = dbPass;
        connectToDatabase();
        createStatement();
        createTable();
    }

    public static void main(String[] args) {
       
    }

    public void createStatement() {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dropTable() {
        try {
            String tableDropStatement = "DROP TABLE pracownicy";
            stmt.executeUpdate(tableDropStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createTable() {
        try {
            String tableCreateStatement = "CREATE TABLE IF NOT EXISTS pracownicy("
                    + "id INTEGER AUTO_INCREMENT,"
                    + "imie VARCHAR(30),"
                    + "nazwisko VARCHAR(30),"
                    + "kraj VARCHAR(4),"
                    + "placa INT,"
                    + "PRIMARY KEY( id )"
                    + ")";
            stmt.executeUpdate(tableCreateStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void orderBy(String condition) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM pracownicy ORDER BY " + condition );
          
            while (rs.next()) {
                String s = rs.getString("imie")+" "+rs.getString("nazwisko")+" "+rs.getString("kraj")+" "+rs.getString("placa");
                System.out.println(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showAverageSalary() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT kraj,AVG(placa) as avgSal FROM pracownicy GROUP BY kraj ");
            
            while (rs.next()) {
                
                
                System.out.println(rs.getString("kraj")+" "+rs.getString("avgSal"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRow(String name, String surname, String country, String salary) throws IllegalArgumentException {
        if (!StringUtils.isNumeric(salary)) {
            throw new IllegalArgumentException("salary needs to be unsigned integer");
        }
        try {
            String tableInsertStatement = "INSERT INTO pracownicy (imie, nazwisko, kraj, placa) VALUES(";
            String values = "'" + name + "',"
                    + "'" + surname + "',"
                    + "'" + country + "',"
                    + salary
                    + ")";
            stmt.executeUpdate(tableInsertStatement + values);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRow(String name, String surname, String country, Integer salary) {
        addRow(name, surname, country, salary.toString());
    }

    public void populateTable() {
        try {
            URL url = new URL("http://szgrabowski.kis.p.lodz.pl/zpo18/dane.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                String[] temp = inputLine.split(" ");
                addRow(temp[0], temp[1], temp[2], temp[3]);
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void truncateTable() {
        try {
            String tableTruncateStatement = "TRUNCATE TABLE pracownicy";
            stmt.executeUpdate(tableTruncateStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(host, userName, userPass);
            System.out.println("Połączono");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Nie znaleziono sterowników");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
