/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4_2_test_ang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static lab4_2_test_ang.Levenstein.LevQWERTY;

/**
 *
 * @author Dominik
 */
public class SingleQuestion {

    String question;
    List<String> answearList = new ArrayList<String>();
    String[] a;

    public SingleQuestion(String q) {
        question = q;
    }
    public SingleQuestion(String q,List<String> answears) {
        question = q;
        addAnswears(answears);
    }

    public void addAnswears(List<String> answears) {

        for (int i = 0; i < answears.size(); i++) {
           
            answearList.add(answears.get(i).toLowerCase());
        }

    }

    public double checkAnswear(String answear) {
        String lowerCaseAnswear=answear.toLowerCase();
        if (answearList.contains(lowerCaseAnswear)) {
            return 1;
        } else {
            for (int i = 0; i < answearList.size(); i++) {
                if (LevQWERTY(lowerCaseAnswear, answearList.get(i)) == 1) {
                    return 0.5;
                }
            }
            return 0;
        }
    }
    public String toString(){
        String str;
        str=question;
        for(int i=0;i<answearList.size();i++){
            str+=" "+answearList.get(i);
        }
        return str;
    }
    public String getQuestionName(){
        return question;
    }
}
