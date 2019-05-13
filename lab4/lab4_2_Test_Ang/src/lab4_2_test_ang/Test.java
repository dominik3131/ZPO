/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4_2_test_ang;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Dominik
 */
public class Test {
    double testResult=0;
    Random rand = new Random();
    Questions questions;
    int questionsAnsweared=0;
    StackPane root;
    Text resultText;
    Text timeText;
    Long startTime;
    Long endTime;
    public Test(Stage primaryStage) {
        try {
            questions = new Questions();
        } catch (FileNotFoundException e) {
            System.out.println("no file");
        }
        startTime=System.nanoTime();
        nextQuestionDialog();
        endTime=System.nanoTime();
        resultText = new Text();
        timeText=new Text();
        root = new StackPane();
        root.getChildren().add(resultText);
        root.getChildren().add(timeText);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        showResults();
    }

    private void nextQuestionDialog() {
        SingleQuestion tempQuestion = getNextQuestion();
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Question");
        dialog.setHeaderText(tempQuestion.getQuestionName());
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
           testResult+= tempQuestion.checkAnswear(result.get());
           questions.removeQuestionFromPoll(tempQuestion);
           
           if(++questionsAnsweared<5)nextQuestionDialog();
          
           
        }
    }

    private SingleQuestion getNextQuestion() {

        return questions.getQuestion(rand.nextInt(questions.getNumberOfQuestionsLeft()));
    }
    private void showResults(){
       
        resultText.setText("Your result is "+testResult+" points\n"+"Your time is "+((endTime-startTime)/10000000)/100.0+" seconds");
        //timeText.setText();
    }
}
