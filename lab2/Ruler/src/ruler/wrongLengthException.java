/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruler;

/**
 *
 * @author Dominik
 */

public class wrongLengthException extends Exception{
    String alert;
    wrongLengthException(String message){
        alert=message;
    }
}
