/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szyki;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import static szyki.Board.letters;

/**
 *
 * @author Dominik
 */
public class BoardLong implements Board {

    Long blue;
    Long black;
    public String name;

    public BoardLong(String file, String n) {
        initializeBoard();
        loadBoard(file);
        name = n;
    }

    @Override
    public void makeMove(String s, boolean isBlue) {
        
        int offset;
        
        if (isBlue) {
            offset=letters.indexOf(s.charAt(0))+(Integer.valueOf(s.substring(1, 2))-1)*8;
            blue=blue&(~(1<<offset));
            offset=letters.indexOf(s.charAt(2))+(Integer.valueOf(s.substring(3, 4))-1)*8;
            blue+=1<<offset;
        } else {
            offset=letters.indexOf(s.charAt(0))+(Integer.valueOf(s.substring(1, 2))-1)*8;
            black=black&(~(1<<offset));
            offset=letters.indexOf(s.charAt(2))+(Integer.valueOf(s.substring(3, 4))-1)*8;
            black+=1<<offset;
        }
      

    }

    @Override
    public void loadBoard(String file) {
        Path p = FileSystems.getDefault().getPath(file);
        try {
            List<String> moves = Files.readAllLines(p, StandardCharsets.UTF_8);
            for (String move : moves) {
                String tmp[] = move.split("\t");
                makeMove(tmp[0], true);
                makeMove(tmp[1], false);
            }
        } catch (IOException ex) {
            Logger.getLogger(BoardMap.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initializeBoard() {
        blue = 0L << 63;
        black = blue;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 0 || i == 7) && (j != 0 && j != 7)) {
                    blue += 1 << (8 * i + j);

                } else if ((j == 0 || j == 7) && (i != 0 && i != 7)) {
                    black += 1 << (8 * i + j);
                }
            }
        }
    }

   

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.blue);
        hash = 67 * hash + Objects.hashCode(this.black);
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
        final BoardLong other = (BoardLong) obj;
        if (!Objects.equals(this.blue, other.blue)) {
            return false;
        }
        if (!Objects.equals(this.black, other.black)) {
            return false;
        }
        return true;
    }

}
