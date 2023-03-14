import java.nio.file.StandardWatchEventKinds;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Score {
    private Player player;
    private List<ScoreCards> scoreCards;
    public void scorePlayer (Player p, List<ScoreCards> sc) {
        player = p;
        scoreCards = sc;

        // TODO: 14/03/2023 temporarily set to only loop once, for testing
        for (int i=0; i<1; i++) {
            switch (scoreCards.get(i).getCardTitle()) {
                case "BearA": bearAScore(); break;
                case "BearB": bearBScore(); break;
                case "BearC": bearCScore(); break;

                case "FoxA": break;
            }
        }
    }



    private void bearAScore () {
        int coordinates[][] = new int[player.getMaxMap()][player.getMaxMap()];
        int pairCount = 0;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j<player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Bear")) {
                    if (coordinates[i][j] == 0) {
                        if (adjacentAnimal("Bear", coordinates, i, j)) {
                            pairCount++;
                        }
                    }
                }
            }
        }

        switch (pairCount){
            case 0: break;
            case 1: player.addScore(4); break;
            case 2: player.addScore(11); break;
            case 3: player.addScore(19); break;

            default: player.addScore(27); break;
        }
    }
    private void bearBScore () {}
    private  void bearCScore() {}

    // method which takes the string form of an animal, two arraylists representing x and y coordinates, and two ints i and j, which represent the current coordinates to be checked
    // the method returns a boolean which represents whether a tile adjacent to the provided coordinates contains an animal matching the provided string
    // the method also updates the provided arraylists with the coordinates of the found animal
    private boolean adjacentAnimal(String animal, int[][] coordinates, int i, int j) {

        // tiles to the left and right are always at the same coordinates, whether x is even or not
        if (player.getPlayerMap()[i][j - 1].isOccupied() && player.getPlayerMap()[i][j - 1].getCreature1().equals(animal)) {
            coordinates[i][j-1] = 1;
            return true;
        } else if (player.getPlayerMap()[i][j + 1].isOccupied() && player.getPlayerMap()[i][j + 1].getCreature1().equals(animal)) {
            coordinates[i][j+1] = 1;
            return true;
        }

        // checking adjacent tiles to an even x coordinate
        if (i % 2 == 0) {
            // tiles below x
            if (player.getPlayerMap()[i - 1][j].isOccupied() && player.getPlayerMap()[i - 1][j].getCreature1().equals(animal)) {
                coordinates[i-1][j] = 1;
                return true;
            } if (player.getPlayerMap()[i - 1][j + 1].isOccupied() && player.getPlayerMap()[i - 1][j + 1].getCreature1().equals(animal)) {
                coordinates[i-1][j+1] = 1;
                return true;
            }
            // tiles above x
            if (player.getPlayerMap()[i + 1][j].isOccupied() && player.getPlayerMap()[i+1][j].getCreature1().equals(animal)) {
                coordinates[i+1][j] = 1;
                return true;
            } if (player.getPlayerMap()[i + 1][j+1].isOccupied() && player.getPlayerMap()[i+1][j+1].getCreature1().equals(animal)) {
                coordinates[i+1][j+1] = 1;
                return true;
            }
        }

        // checking adjacent tiles to an odd x coordinate
        else {
            // checking tiles below x
            if (player.getPlayerMap()[i - 1][j-1].isOccupied() && player.getPlayerMap()[i - 1][j-1].getCreature1().equals(animal)) {
                coordinates[i-1][j-1] = 1;
                return true;
            } if (player.getPlayerMap()[i - 1][j].isOccupied() && player.getPlayerMap()[i - 1][j].getCreature1().equals(animal)) {
                coordinates[i-1][j] = 1;
                return true;
            }

            // tiles above x
            if (player.getPlayerMap()[i + 1][j-1].isOccupied() && player.getPlayerMap()[i + 1][j-1].getCreature1().equals(animal)) {
                coordinates[i+1][j-1] = 1;
                return true;
            } if (player.getPlayerMap()[i + 1][j].isOccupied() && player.getPlayerMap()[i + 1][j].getCreature1().equals(animal)) {
                coordinates[i+1][j] = 1;
                return true;
            }
        }
        return false;
    }
}
