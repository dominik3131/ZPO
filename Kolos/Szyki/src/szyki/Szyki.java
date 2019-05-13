package szyki;

import java.util.ArrayList;
import java.util.List;

public class Szyki {

    List<BoardMap> boardMaps;
    List<BoardLong> boardLongs;

    public Szyki() {
        boardMaps = new ArrayList<>();
        boardLongs = new ArrayList<>();

        for (int i = 1; i < 7; i++) {
            String file = "partia" + Integer.toString(i) + ".txt";
            boardMaps.add(new BoardMap(file, Integer.toString(i)));
            boardLongs.add(new BoardLong(file, Integer.toString(i)));
        }
        getPairsMap();
        getPairsLong();
    }

    public void getPairsMap() {
        List<BoardMap> temp = new ArrayList<>();
        while (!boardMaps.isEmpty()) {
            temp.add(boardMaps.remove(0));
            int i = 0;
            while (i < boardMaps.size()) {
                if (temp.get(0).equals(boardMaps.get(i))) {
                    temp.add(boardMaps.remove(i));
                } else {
                    i++;
                }
            }
            if (temp.size() > 1) {
                System.out.print("[");
                for (BoardMap b : temp) {
                    System.out.print(b.name + " ");
                }
                System.out.print("]");
            }

            temp.clear();

        }
        System.out.println();
    }

    public void getPairsLong() {
        List<BoardLong> temp = new ArrayList<>();
        while (!boardLongs.isEmpty()) {
            temp.add(boardLongs.remove(0));
            int i = 0;
            while (i < boardLongs.size()) {
                if (temp.get(0).equals(boardLongs.get(i))) {
                    temp.add(boardLongs.remove(i));
                } else {
                    i++;
                }
            }
            if (temp.size() > 1) {
                System.out.print("[");
                for (BoardLong b : temp) {
                    System.out.print(b.name + " ");
                }
                System.out.print("]");
            }

            temp.clear();

        }
        System.out.println();
    }
}
