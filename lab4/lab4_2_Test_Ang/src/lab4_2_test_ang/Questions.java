/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4_2_test_ang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author Dominik
 */
public class Questions {

    private final List<SingleQuestion> questionsList;
    Gson gson;

    public Questions() throws FileNotFoundException {
        gson = new Gson();
        File file = new File("PolEngTest.json");
        Scanner sc = new Scanner(file);
        String jsonFile = new String();
        while (sc.hasNextLine()) {
            jsonFile += sc.nextLine();
        }
        sc.close();

        //String arrayFromString = "[{\"question\":\"krzyczeć\",\"answearList\":[\"scream\",\"shout\",\"yell\",\"cry\"]},{\"question\":\"biegać\",\"answearList\":[\"run\",\"race\",\"play\"]},{\"question\":\"pływać\",\"answearList\":[\"swim\",\"float\",\"sail\"]},{\"question\":\"skakać\",\"answearList\":[\"jump\",\"leap\",\"bounce\"]},{\"question\":\"rzucać\",\"answearList\":[\"throw\",\"toss\"]},{\"question\":\"mówić\",\"answearList\":[\"speak\",\"tell\",\"say\"]},{\"question\":\"spać\",\"answearList\":[\"sleep\",\"slumber\"]},{\"question\":\"ciąć\",\"answearList\":[\"cut\",\"slash\",\"chop\"]},{\"question\":\"kopać\",\"answearList\":[\"kick\",\"dig\"]},{\"question\":\"jeść\",\"answearList\":[\"eat\",\"food\"]}]";
        Type listType = new TypeToken<ArrayList<SingleQuestion>>() {}.getType();
        questionsList = new Gson().fromJson(jsonFile, listType);
        //System.out.print(questionsList.get(0).toString());
    }
    public void removeQuestionFromPoll(SingleQuestion q){
        questionsList.remove(q);
    }
    public SingleQuestion getQuestion(int index){
        if(index<0||index>=questionsList.size()){
            throw new IllegalArgumentException();
        }
        return questionsList.get(index);
    }
   
    public int getNumberOfQuestionsLeft(){
        return questionsList.size();
    }
}
