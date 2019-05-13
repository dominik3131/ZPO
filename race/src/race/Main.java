/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package race;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author 209443
 */
public class Main extends Application {

    
    Race race;
    @Override
    public void start(Stage primaryStage) throws IOException {

        race=new Race(primaryStage);
        primaryStage.show();
        
        

    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        launch(args);

    }

    
}
