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

        for (int i=0; i<sc.size(); i++) {
            switch (scoreCards.get(i).getCardTitle()) {
                case "BearA": bearAScore(); break;
                case "BearB": bearBScore(); break;
                case "BearC": bearCScore(); break;

                case "FoxA": foxAScore(); break;
                case "FoxB": foxBScore(); break;
                case "FoxC": foxCScore(); break;

                case "ElkA": elkAScore(); break;
                case "ElkB": elkBScore(); break;
                case "ElkC": elkCScore(); break;

                default: throw new IllegalArgumentException("Invalid score card passed to scorePlayer: " + scoreCards.get(i).getCardTitle());
            }
        }
    }



    private void bearAScore () {
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];
        int[] outputCoordinates;
        int pairCount = 0;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j<player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Bear")) {
                    if (coordinates[i][j] == 0) {
                        coordinates[i][j] = 1;

                        outputCoordinates = adjacentAnimal("Bear", coordinates, i, j);
                        if (outputCoordinates[0] != -1) {
                            pairCount++;
                            coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
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
        //temp
        System.out.println("after bearA: " + player.getUserName() + " has score:" + player.getScore());
    }
    private void bearBScore () {
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];
        int[] outputCoordinates;
        int lineCount = 0;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j<player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Bear")) {
                    if (coordinates[i][j] == 0) {
                        coordinates[i][j] = 1;
                        int numAdjacent = 1;

                        outputCoordinates = adjacentAnimal("Bear", coordinates, i, j);

                        while (outputCoordinates[0] != -1) {
                            coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
                            numAdjacent++;
                            outputCoordinates = adjacentAnimal("Bear", coordinates, outputCoordinates[0], outputCoordinates[1]);
                        }
                        if (numAdjacent >= 3) {
                            lineCount++;
                        }
                    }
                }
            }
        }
        player.addScore(lineCount*10);
        //temp
        System.out.println("after bearB: " + player.getUserName() + " has score:" + player.getScore());
    }
    private void bearCScore() {
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];
        boolean single = false;
        boolean pair = false;
        boolean triple = false;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j = 0; j < player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Bear")) {
                    coordinates[i][j] = 1;
                    int bearGroup = 1 + recursiveSearch("Bear", coordinates, i, j);

                    switch (bearGroup) {
                        case 1: player.addScore(2); single=true; break;
                        case 2: player.addScore(5); pair = true; break;
                        case 3: player.addScore(7); triple = true; break;

                        default: break;
                    }
                }
            }
        }
        if (single && pair && triple) player.addScore(3);
        //temp
        System.out.println("after bearC: " + player.getUserName() + " has score:" + player.getScore());
    }

    private void foxAScore() {
        int[] outputCoordinates;
        int[][] placeHolder = new int[player.getMaxMap()][player.getMaxMap()];

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j< player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Fox")) {
                    int uniqueCount = 0;

                    outputCoordinates = adjacentAnimal("Bear", placeHolder, i, j);
                    if (outputCoordinates[0] != -1) {
                        uniqueCount++;
                    }

                    outputCoordinates = adjacentAnimal("Fox", placeHolder, i, j);
                    if (outputCoordinates[0] != -1) {
                        uniqueCount++;
                    }

                    outputCoordinates = adjacentAnimal("Elk", placeHolder, i, j);
                    if (outputCoordinates[0] != -1) {
                        uniqueCount++;
                    }

                    outputCoordinates = adjacentAnimal("Hawk", placeHolder, i, j);
                    if (outputCoordinates[0] != -1) {
                        uniqueCount++;
                    }

                    outputCoordinates = adjacentAnimal("Salmon", placeHolder, i, j);
                    if (outputCoordinates[0] != -1) {
                        uniqueCount++;
                    }

                    player.addScore(uniqueCount);
                }
            }
        }
        //temp
        System.out.println("after foxA: " + player.getUserName() + " has score:" + player.getScore());
    }

    private void foxBScore() {
        int[] outputCoordinates;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j< player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Fox")) {

                    int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];

                    int numAnimal = 0;
                    int pairs = 0;
                    outputCoordinates = adjacentAnimal("Bear", coordinates, i, j);
                    while (outputCoordinates[0] != -1) {
                        coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
                        numAnimal++;
                        outputCoordinates = adjacentAnimal("Bear", coordinates, i, j);
                    }
                    if (numAnimal > 1) {
                        pairs++;
                    }
                    numAnimal = 0;

                    outputCoordinates = adjacentAnimal("Elk", coordinates, i, j);
                    while (outputCoordinates[0] != -1) {
                        coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
                        numAnimal++;
                        outputCoordinates = adjacentAnimal("Elk", coordinates, i, j);
                    }
                    if (numAnimal > 1) {
                        pairs++;
                    }
                    numAnimal = 0;

                    outputCoordinates = adjacentAnimal("Hawk", coordinates, i, j);
                    while (outputCoordinates[0] != -1) {
                        coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
                        numAnimal++;
                        outputCoordinates = adjacentAnimal("Hawk", coordinates, i, j);
                    }
                    if (numAnimal > 1) {
                        pairs++;
                    }
                    numAnimal = 0;

                    outputCoordinates = adjacentAnimal("Salmon", coordinates, i, j);
                    while (outputCoordinates[0] != -1) {
                        coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
                        numAnimal++;
                        outputCoordinates = adjacentAnimal("Salmon", coordinates, i, j);
                    }
                    if (numAnimal > 1) {
                        pairs++;
                    }

                    if (pairs > 0) {
                        player.addScore(1 + 2 * pairs);
                    }
                }
            }
        }
        //temp
        System.out.println("after foxB: " + player.getUserName() + " has score:" + player.getScore());
    }

    private void foxCScore() {
        int[] outputCoordinates;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j< player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Fox")) {

                    int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];

                    int numBears = 0;
                    outputCoordinates = adjacentAnimal("Bear", coordinates, i, j);
                    while (outputCoordinates[0] != -1) {
                        coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
                        numBears++;
                        outputCoordinates = adjacentAnimal("Bear", coordinates, i, j);
                    }

                    int numElk = 0;
                    outputCoordinates = adjacentAnimal("Elk", coordinates, i, j);
                    while (outputCoordinates[0] != -1) {
                        coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
                        numElk++;
                        outputCoordinates = adjacentAnimal("Elk", coordinates, i, j);
                    }

                    int numHawks = 0;
                    outputCoordinates = adjacentAnimal("Hawk", coordinates, i, j);
                    while (outputCoordinates[0] != -1) {
                        coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
                        numHawks++;
                        outputCoordinates = adjacentAnimal("Hawk", coordinates, i, j);
                    }

                    int numSalmon = 0;
                    outputCoordinates = adjacentAnimal("Salmon", coordinates, i, j);
                    while (outputCoordinates[0] != -1) {
                        coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
                        numSalmon++;
                        outputCoordinates = adjacentAnimal("Salmon", coordinates, i, j);
                    }

                    int max = Math.max(numBears, numElk);
                    max = Math.max(max, numHawks);
                    max = Math.max(max, numSalmon);

                    player.addScore(max);
                }
            }
        }
        //temp
        System.out.println("after foxC: " + player.getUserName() + " has score:" + player.getScore());
    }

    private void elkAScore() {
//        int[] outputCoordinates;
//        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];
//
//        for (int i=0; i< player.getMaxMap(); i++) {
//            for (int j = 0; j < player.getMaxMap(); j++) {
//                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Elk")) {
//                    boolean nwse = false;
//                    boolean nesw = false;
//                    boolean we = false;
//
//                    outputCoordinates = adjacentAnimal("Elk", coordinates, i, j);
//                    if (outputCoordinates[0] != -1) {
//                        // horizontal line of elk
//                        if (outputCoordinates[0] == i) we = true;
//
//                        if (i%2 == 0 && )
//
//                    }
//                }
//            }
//        }
    }

    private void elkBScore() {
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j = 0; j < player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Elk") && coordinates[i][j] == 0) {
                    coordinates[i][j] = 1;
                    int elkGroup = 1 + recursiveSearch("Elk", coordinates, i, j);

                    switch (elkGroup) {
                        case 1: player.addScore(2); break;
                        case 2: player.addScore(4); break;
                        case 3: player.addScore(7); break;
                        case 4: player.addScore(10); break;
                        case 5: player.addScore(14); break;
                        case 6: player.addScore(18); break;
                        case 7: player.addScore(23); break;

                        default: player.addScore(28); break;
                    }
                }
            }
        }
        //temp
        System.out.println("after elkB: " + player.getUserName() + " has score:" + player.getScore());
    }

    private void elkCScore() {

    }

    private int recursiveSearch(String animal, int[][] coordinates, int i, int j) {
        int[] outputCoordinates = adjacentAnimal(animal, coordinates, i, j);
        int output = 0;

        while (outputCoordinates[0] != -1) {
            coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
            output += 1 + recursiveSearch(animal, coordinates, outputCoordinates[0], outputCoordinates[1]);
            outputCoordinates = adjacentAnimal(animal, coordinates, i, j);
        }
        return output;
    }

    // this method takes the name of an animal, a 2d array of coordinates and two integers representing a current location as an argument. It checks each adjacent tile to see
    // if it contains the provided animal type and has not already been marked as counted, and if it is successful it returns the coordinates of that copy. Otherwise,
    // it returns [-1, -1]
    private int[] adjacentAnimal(String animal, int[][] coordinates, int i, int j) {
        int[] output = new int[2];

        // tiles to the left and right are always at the same coordinates, whether x is even or not
        if (player.getPlayerMap()[i][j - 1].isOccupied() && player.getPlayerMap()[i][j - 1].getCreature1().equals(animal)) {
            if (coordinates[i][j-1] == 0) {
                output[0] = i;
                output[1] = j-1;
                return output;
            }
        } else if (player.getPlayerMap()[i][j + 1].isOccupied() && player.getPlayerMap()[i][j + 1].getCreature1().equals(animal)) {
            if (coordinates[i][j+1] == 0) {
                output[0] = i;
                output[1] = j+1;
                return output;
            }
        }

        // checking adjacent tiles to an even x coordinate
        if (i % 2 == 0) {
            // tiles below x
            if (player.getPlayerMap()[i - 1][j].isOccupied() && player.getPlayerMap()[i - 1][j].getCreature1().equals(animal)) {
                if (coordinates[i-1][j] == 0) {
                    output[0] = i-1;
                    output[1] = j;
                    return output;
                }
            } if (player.getPlayerMap()[i - 1][j + 1].isOccupied() && player.getPlayerMap()[i - 1][j + 1].getCreature1().equals(animal)) {
                if (coordinates[i-1][j+1] == 0) {
                    output[0] = i-1;
                    output[1] = j+1;
                    return output;
                }
            }
            // tiles above x
            if (player.getPlayerMap()[i + 1][j].isOccupied() && player.getPlayerMap()[i+1][j].getCreature1().equals(animal)) {
                if (coordinates[i+1][j] == 0) {
                    output[0] = i+1;
                    output[1] = j;
                    return output;
                }
            } if (player.getPlayerMap()[i + 1][j+1].isOccupied() && player.getPlayerMap()[i+1][j+1].getCreature1().equals(animal)) {
                if (coordinates[i+1][j+1] == 0) {
                    output[0] = i+1;
                    output[1] = j+1;
                    return output;
                }
            }
        }

        // checking adjacent tiles to an odd x coordinate
        else {
            // checking tiles below x
            if (player.getPlayerMap()[i - 1][j-1].isOccupied() && player.getPlayerMap()[i - 1][j-1].getCreature1().equals(animal)) {
                if (coordinates[i-1][j-1] == 0) {
                    output[0] = i-1;
                    output[1] = j-1;
                    return output;
                }
            } if (player.getPlayerMap()[i - 1][j].isOccupied() && player.getPlayerMap()[i - 1][j].getCreature1().equals(animal)) {
                if (coordinates[i-1][j] == 0) {
                    output[0] = i-1;
                    output[1] = j;
                    return output;
                }
            }

            // tiles above x
            if (player.getPlayerMap()[i + 1][j-1].isOccupied() && player.getPlayerMap()[i + 1][j-1].getCreature1().equals(animal)) {
                if (coordinates[i+1][j-1] == 0) {
                    output[0] = i+1;
                    output[1] = j-1;
                    return output;
                }
            } if (player.getPlayerMap()[i + 1][j].isOccupied() && player.getPlayerMap()[i + 1][j].getCreature1().equals(animal)) {
                if (coordinates[i+1][j] == 0) {
                    output[0] = i+1;
                    output[1] = j;
                    return output;
                }
            }
        }
        output[0] = -1;
        output[1] = -1;

        return output;
    }
}
