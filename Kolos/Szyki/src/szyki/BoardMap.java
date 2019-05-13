/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szyki;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardMap implements Board {

    private HashMap<String, String> board;
    public String name;
    public BoardMap(String file,String n){
        initializeBoard();
        loadBoard(file);
        name=n;
    }
    @Override
    public void makeMove(String s,boolean isBlue) {
        board.put(s.substring(0, 2), "X");
        String player;
        if(isBlue) player = "N";
        else player="C";
        board.put(s.substring(2, 4), player);

    }

    @Override
    public void loadBoard(String file) {
        Path p = FileSystems.getDefault().getPath(file);
        try {
            List<String> moves = Files.readAllLines(p, StandardCharsets.UTF_8);
            for (String move : moves) {
               String tmp[]=move.split("\t");
               makeMove(tmp[0],true);
               makeMove(tmp[1],false);
            }
        } catch (IOException ex) {
            Logger.getLogger(BoardMap.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initializeBoard() {
        board = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 0 || i == 7) && (j != 0 && j != 7)) {
                    board.put(letters.charAt(j) + Integer.toString(i + 1), "N");
                } else if ((j == 0 || j == 7) && (i != 0 && i != 7)) {
                    board.put(letters.charAt(j) + Integer.toString(i + 1), "C");
                } else {
                    board.put(letters.charAt(j) + Integer.toString(i + 1), "X");
                }
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.board);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardMap other = (BoardMap) obj;
        if (!Objects.equals(this.board, other.board)) {
            return false;
        }
        return true;
    }
    

}
