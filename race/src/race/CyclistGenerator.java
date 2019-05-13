/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package race;

import java.util.Set;
import java.util.Random;
import java.util.HashSet;
import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Comparator;
import java.util.PriorityQueue;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author 209443
 */
public class CyclistGenerator {

    final Random randomGenerator;
    double std;
    double mean;
    LinkedList<String> nameList;
    Set<String> cyclistsNames = new HashSet<>();
    
    
    public CyclistGenerator(double std, double mean, String lastNameAddress) throws MalformedURLException, IOException {
        this.std = std;
        this.mean = mean;
       
        randomGenerator = new Random();
        nameList = new LinkedList<>();
        getNames(lastNameAddress);
    }

    
    public Cyclist nextCyclist() {
        String name;
        Double raceTime;
        while (true) {
            int index = randomGenerator.nextInt(nameList.size());
            if (cyclistsNames.add(nameList.get(index))) {
                name = nameList.get(index);
                break;
            }
        }
        raceTime = raceTime();
        if (raceTime < 250.0) {
            raceTime = 250.0;
        } else if (raceTime > 370.0) {
            raceTime = 370.0;
        }
        return new Cyclist(raceTime, name);
    }

    private double raceTime() {
        return randomGenerator.nextGaussian() * std + mean;
    }

    private void getNames(String lastNameAddress) throws MalformedURLException, IOException {
        URL url = new URL(lastNameAddress);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                nameList.add(inputLine);
            }
        }
    }

}
