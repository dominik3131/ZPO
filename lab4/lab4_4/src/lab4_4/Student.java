package lab4_4;

import java.util.Date;


public class Student {
    public String imie, nazwisko;
    public Date dataUrodzenia;
    public int wzrost;

    public Student(String x, String y, Date z, int u)
    {
        imie=x;
        nazwisko=y;
        dataUrodzenia=z;
        wzrost= u;
    }

    @Override
    public String toString() {
        return this.imie + " " + this.nazwisko + " " + this.dataUrodzenia.toString() + " " + this.wzrost;
    }




}
