/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package race;

/**
 *
 * @author 209443
 */
public class Cyclist {
    public double raceTime;
    public String lastName;
    public Cyclist(double time, String name){
        raceTime = time;
        lastName = name;
    }

    Cyclist() {
        
    }
    public String toString(){
        return lastName+" "+String.valueOf(raceTime);
    }
}
