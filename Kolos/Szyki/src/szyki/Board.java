/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szyki;

import java.util.List;
public interface Board {
    String letters="abcdefgh";
    public void makeMove(String s,boolean isBlue);
    public boolean equals(Object o);
    public void loadBoard(String file);
    public void initializeBoard();
      
}
