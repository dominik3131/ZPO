/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package race;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author 209443
 */
public class Race {

    private final static Logger logger = Logger.getLogger(Race.class.getName());
     private static FileHandler logFile = null;
    int numberOfCyclists = 15,numberOfCyclistsStarted=0;
    CyclistGenerator generator;
    Label[] labels = new Label[4];
    Cyclist first, second, third;
    Cyclist lastCyclist;
    Comparator<Cyclist> comparator = new CyclistComparator();
    PriorityQueue<Cyclist> queue = new PriorityQueue<>(comparator);

    public Race(Stage primaryStage) throws IOException {

        GridPane grid = new GridPane();
        for (int i = 0; i < 4; i++) {
            labels[i] = new Label();
            grid.addRow(i, labels[i]);
        }
        generator = new CyclistGenerator(40, 300, "http://szgrabowski.kis.p.lodz.pl/zpo18/nazwiska.txt");

        StackPane root = new StackPane();
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        initLogs();
        initLabels();
        startRace();

    }

    public void startRace() {
        logger.log(Level.INFO, "Race started");
        Timeline raceTimeline = new Timeline(new KeyFrame(Duration.millis(2400), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                runNextCyclist();
                numberOfCyclistsStarted++;
              
            }
        }));
        raceTimeline.setCycleCount(numberOfCyclists);
        raceTimeline.play();
    }

    private void runNextCyclist() {
        Cyclist nextCyclist = generator.nextCyclist();
        logger.log(Level.INFO, nextCyclist.toString()+" started");
        Timeline cyclistTimeline = new Timeline(new KeyFrame(Duration.millis(nextCyclist.raceTime * 1000 / 25), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                lastCyclist = nextCyclist;
                queue.offer(lastCyclist);
                updateLabels();
                logger.log(Level.INFO, lastCyclist.toString()+" ended race");
                if(numberOfCyclistsStarted==15) logger.log(Level.INFO, "race has ended");
            }
        }));
        cyclistTimeline.setCycleCount(1);
        cyclistTimeline.play();
    }

    private void updateLabels() {
        labels[0].setText("On meta: " + lastCyclist);
        if (!queue.isEmpty()) {
            first = queue.poll();
            labels[1].setText("1." + first.toString());
        }
        if (!queue.isEmpty()) {
            second = queue.poll();
            labels[2].setText("2." + second.toString());
        }
        if (!queue.isEmpty()) {
            third = queue.poll();
            labels[3].setText("3." + third.toString());
        }
        if (third != null) {
            queue.add(third);
        }
        if (second != null) {
            queue.add(second);
        }
        queue.add(first);

    }

    public static void initLogs() {
        try {
            logFile = new FileHandler("loggerExample.log", false);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        logFile.setFormatter(new SimpleFormatter());
        l.addHandler(logFile);
        l.setLevel(Level.CONFIG);
    }
    private void initLabels(){
        labels[0].setText("On Meta");
        labels[1].setText("1. ------");
        labels[2].setText("2. ------");
        labels[3].setText("3. ------");
    }

}
