/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6_2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Lab6_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //a
       LocalDate WW2Start = LocalDate.parse("1939-09-01");
       LocalDate WW2End   = LocalDate.parse("1945-05-08");
       long daysOfWW2 = ChronoUnit.DAYS.between(WW2Start , WW2End)+1; //+1 beacuse start date is included but end is not
       System.out.println("WW2 was " + daysOfWW2 + " days long");
       
       //b
       
       LocalDate day68 = LocalDate.parse("2016-01-01").plus(67,ChronoUnit.DAYS);
       System.out.println("68 day of 2016 is " + day68);
       
       //c
       LocalDate dateOfMyBirth = LocalDate.parse("1996-01-12");
       int leapYears=0;
       
       //sprawdzamy w roku urodzenia
       if(dateOfMyBirth.isLeapYear()){
           if(dateOfMyBirth.getMonthValue()<=2){
               leapYears++;
           }
       }
       dateOfMyBirth=dateOfMyBirth.plus(1,ChronoUnit.YEARS);
       
       //przez wszystkie kolejne lata
       while(dateOfMyBirth.getYear()<LocalDate.now().getYear()){
           if(dateOfMyBirth.isLeapYear()){
               leapYears++;
           }
           dateOfMyBirth=dateOfMyBirth.plus(1,ChronoUnit.YEARS);
       }
       
       // i w roku biezacym
       if(dateOfMyBirth.isLeapYear()){
           if(LocalDate.now().getMonthValue()>2){
               leapYears++;
           }
       }
       
       System.out.println("Number of 29 Februarys I have lived in is " + leapYears);
    }
    
}
