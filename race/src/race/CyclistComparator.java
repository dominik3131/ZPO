/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package race;

/**
 *
 * @author Dominik
 */
import java.util.Comparator;

public class CyclistComparator implements Comparator<Cyclist> {

    @Override
    public int compare(Cyclist x, Cyclist y)
    {
        
        if (x.raceTime < y.raceTime)
        {
            return -1;
        }
        if (x.raceTime > y.raceTime)
        {
            return 1;
        }
        return 0;
    }
}
