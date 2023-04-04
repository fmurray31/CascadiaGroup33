/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import java.util.List;

public class Score {
    private Player player;
    private List<ScoreCards> scoreCards;
    // takes a player and a list of scorecards, and increases the player's score depending on which conditions they meet and the scorecards chosen
    public void scorePlayer (Player p, List<ScoreCards> sc) {
        player = p;
        scoreCards = sc;
        // adds points for nature token count
        p.addScore(p.getNatureTokens());

        // goes through the list of scorecards, calling the relevant method for each card
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

                case "HawkA": hawkAScore(); break;
                case "HawkB": hawkBScore(); break;
                case "HawkC": hawkCScore(); break;

                case "SalmonA": salmonGeneric('a'); break;
                case "SalmonB": salmonGeneric('b'); break;
                case "SalmonC": salmonGeneric('c'); break;

                default: throw new IllegalArgumentException("Invalid score card passed to scorePlayer: " + scoreCards.get(i).getCardTitle());
            }
        }
    }


    // bear scoring
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
                        if (outputCoordinates[0] != -1 && recursiveSearch("Bear", coordinates, i, j)+1 < 3) {
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
                        if (numAdjacent == 3) {
                            lineCount++;
                        }
                    }
                }
            }
        }
        player.addScore(lineCount*10);
    }

    private void bearCScore() {
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];
        boolean single = false;
        boolean pair = false;
        boolean triple = false;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j = 0; j < player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Bear") && coordinates[i][j] == 0) {
                    coordinates[i][j] = 1;
                    int bearGroup = 1 + recursiveSearch("Bear", coordinates, i, j);

                    switch (bearGroup) {
                        case 1: player.addScore(2); single=true; break;
                        case 2: player.addScore(5); pair = true; break;
                        case 3: player.addScore(8); triple = true; break;

                        default: break;
                    }
                }
            }
        }
        if (single && pair && triple) player.addScore(3);
    }

    // Fox scoring
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
    }

    // Elk scoring
    private void elkAScore() {
        int[] output;
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j = 0; j < player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Elk") && coordinates[i][j] == 0) {
                    coordinates[i][j] = 1;
                    output = adjacentAnimal("Elk", coordinates, i, j);
                    String direction;
                    int temp;
                    int maxCount = 1;
                    String maxDirection = "";

                    while (output[0] != -1) {
                        direction = adjacentToDirection(i, j, output);
                        coordinates[output[0]][output[1]] = 1;

                        temp = 1;

                        if (direction.equals("w") || direction.equals("e")) {
                            temp += searchLineCount("Elk", "w", coordinates, output[0], output[1]) + 1;
                            temp += searchLineCount("Elk", "e", coordinates, output[0], output[1]);
                        }

                        if (direction.equals("nw") || direction.equals("se")) {
                            temp += searchLineCount("Elk", "nw", coordinates, output[0], output[1]) + 1;
                            temp += searchLineCount("Elk", "se", coordinates, output[0], output[1]);
                        }

                        if (direction.equals("sw") || direction.equals("ne")) {
                            temp += searchLineCount("Elk", "sw", coordinates, output[0], output[1]) + 1;
                            temp += searchLineCount("Elk", "ne", coordinates, output[0], output[1]);
                        }


                        if (temp > maxCount) {
                            maxCount = temp;
                            maxDirection = direction;
                        }

                        output = adjacentAnimal("Elk", coordinates, i, j);
                    }

                    if (maxDirection.equals("w") || maxDirection.equals("e")) {
                        elkAHelper("w", coordinates, i, j);
                        elkAHelper("e", coordinates, i, j);
                    }

                    if (maxDirection.equals("nw") || maxDirection.equals("se")) {
                        elkAHelper("nw", coordinates, i, j);
                        elkAHelper("se", coordinates, i, j);
                    }

                    if (maxDirection.equals("sw") || maxDirection.equals("ne")) {
                        elkAHelper("sw", coordinates, i, j);
                        elkAHelper("ne", coordinates, i, j);
                    }

                    //maxCount++;
                    switch (maxCount) {
                        case 0: break;
                        case 1: player.addScore(2); break;
                        case 2: player.addScore(5); break;
                        case 3: player.addScore(9); break;
                        default: player.addScore(13); break;
                    }
                }
            }
        }
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
    }

    private void elkCScore() {
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];

        for (int i = 0; i < player.getMaxMap(); i++) {
            for (int j = 0; j < player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Elk") && coordinates[i][j] == 0) {
                    int[] tempCoords;
                    int[] tempCoords2;
                    coordinates[i][j] = 1;
                    int adjacentCount = adjacentCounter("Elk", i, j);

                    if (adjacentCount == 0) {
                        player.addScore(2);
                    }
                    if (adjacentCount == 1) {
                        tempCoords = adjacentAnimal("Elk", coordinates, i, j);
                        coordinates[tempCoords[0]][tempCoords[1]] = 1;
                        player.addScore(5);
                    }
                    if (adjacentCount == 2) {
                        tempCoords = adjacentAnimal("Elk", coordinates, i, j);
                        tempCoords2 = adjacentAnimal("Elk", coordinates, tempCoords[0], tempCoords[1]);
                        coordinates[i][j] = 1;
                        if (recursiveSearch("Elk", coordinates, i, j) == 2) {
                            player.addScore(9);
                        } else if (adjacentCounter("Elk", tempCoords[0], tempCoords[1]) == 3 && adjacentCounter("Elk", tempCoords2[0], tempCoords2[1]) == 3) {
                            player.addScore(13);
                            coordinates[tempCoords[0]][tempCoords[1]] = 1;
                            coordinates[tempCoords2[0]][tempCoords2[1]] = 1;
                        }
                    }
                    if (adjacentCount == 3) {
                        int [][] temp = new int[player.getMaxMap()][player.getMaxMap()];
                        temp[i][j] = 1;
                        if (recursiveSearch("Elk", temp, i, j) == 4) {
                            player.addScore(13);
                        }
                    }
                }
            }
        }
    }

    // Hawk scoring
    private void hawkAScore() {
        int[] outputCoordinates;
        int[][] placeholder = new int[player.getMaxMap()][player.getMaxMap()];
        int soloHawks = 0;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j< player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Hawk")) {
                    outputCoordinates = adjacentAnimal("Hawk", placeholder, i, j);

                    if (outputCoordinates[0] == -1) {
                        soloHawks++;
                    }
                }
            }
        }

        switch (soloHawks) {
            case 0: break;
            case 1: player.addScore(2); break;
            case 2: player.addScore(5); break;
            case 3: player.addScore(8); break;
            case 4: player.addScore(11); break;
            case 5: player.addScore(14); break;
            case 6: player.addScore(18); break;
            case 7: player.addScore(22); break;

            default: player.addScore(26); break;
        }
    }

    private void hawkBScore() {
        int[] outputCoordinates;
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];
        int numPairs = 0;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j< player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Hawk")) {
                    coordinates[i][j] = 1;
                    boolean pairFound = false;
                    int[] pairLocation = {0,0};

                    outputCoordinates = searchLine("Hawk", "w", 0, i, j);
                    if (outputCoordinates[0] != -1 && coordinates[outputCoordinates[0]][outputCoordinates[1]] == 0) {
                        pairFound = true;
                        pairLocation = outputCoordinates;
                    }

                    outputCoordinates = searchLine("Hawk", "nw", 0, i, j);
                    if (outputCoordinates[0] != -1 && coordinates[outputCoordinates[0]][outputCoordinates[1]] == 0 && !pairFound) {
                        pairFound = true;
                        pairLocation = outputCoordinates;
                    }

                    outputCoordinates = searchLine("Hawk", "ne", 0, i, j);
                    if (outputCoordinates[0] != -1 && coordinates[outputCoordinates[0]][outputCoordinates[1]] == 0 && !pairFound) {
                        pairFound = true;
                        pairLocation = outputCoordinates;
                    }

                    outputCoordinates = searchLine("Hawk", "e", 0, i, j);
                    if (outputCoordinates[0] != -1 && coordinates[outputCoordinates[0]][outputCoordinates[1]] == 0 && !pairFound) {
                        pairFound = true;
                        pairLocation = outputCoordinates;
                    }

                    outputCoordinates = searchLine("Hawk", "se", 0, i, j);
                    if (outputCoordinates[0] != -1 && coordinates[outputCoordinates[0]][outputCoordinates[1]] == 0 && !pairFound) {
                        pairFound = true;
                        pairLocation = outputCoordinates;
                    }

                    outputCoordinates = searchLine("Hawk", "sw", 0, i, j);
                    if (outputCoordinates[0] != -1 && coordinates[outputCoordinates[0]][outputCoordinates[1]] == 0 && !pairFound) {
                        pairFound = true;
                        pairLocation = outputCoordinates;
                    }

                    if (pairFound) {
                        coordinates[pairLocation[0]][pairLocation[1]] = 1;
                        numPairs++;
                    }
                }
            }
        }

        switch (numPairs) {
            case 0: break;
            case 1: player.addScore(2); break;
            case 2: player.addScore(5); break;
            case 3: player.addScore(9); break;
            case 4: player.addScore(12); break;
            case 5: player.addScore(16); break;
            case 6: player.addScore(20); break;
            case 7: player.addScore(24); break;

            default: player.addScore(28); break;
        }
    }

    private void hawkCScore() {
        int[] outputCoordinates;
        int numPairs = 0;

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j< player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Hawk")) {
                    boolean pairFound = false;

                    outputCoordinates = searchLine("Hawk", "w", 0, i, j);
                    if (outputCoordinates[0] != -1) {
                        pairFound = true;
                    }

                    outputCoordinates = searchLine("Hawk", "nw", 0, i, j);
                    if (outputCoordinates[0] != -1 && !pairFound) {
                        pairFound = true;
                    }

                    outputCoordinates = searchLine("Hawk", "ne", 0, i, j);
                    if (outputCoordinates[0] != -1 && !pairFound) {
                        pairFound = true;
                    }

                    outputCoordinates = searchLine("Hawk", "e", 0, i, j);
                    if (outputCoordinates[0] != -1 && !pairFound) {
                        pairFound = true;
                    }

                    outputCoordinates = searchLine("Hawk", "se", 0, i, j);
                    if (outputCoordinates[0] != -1 && !pairFound) {
                        pairFound = true;
                    }

                    outputCoordinates = searchLine("Hawk", "sw", 0, i, j);
                    if (outputCoordinates[0] != -1 && !pairFound) {
                        pairFound = true;
                    }

                    if (pairFound) {
                        numPairs++;
                    }
                }
            }
        }

        numPairs = numPairs/2;
        player.addScore(numPairs*3);
    }

    // this method performs the operations common to all of the salmon scoring cards, then passes the result to methods which perform the card specific scoring
    private void salmonGeneric (char c) {
        int[] outputCoordinates;
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];

        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j<player.getMaxMap(); j++) {
                if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals("Salmon")) {
                    if (coordinates[i][j] == 0) {
                        coordinates[i][j] = 1;
                        int numSalmon = 1;

                        if (adjacentCounter("Salmon", i, j) == 1) {
                            numSalmon += recursiveSearch("Salmon", coordinates, i, j);
                        }

                        if (adjacentCounter("Salmon", i, j) == 2) {
                            outputCoordinates = adjacentAnimal("Salmon", coordinates, i, j);
                            numSalmon += recursiveSearch("Salmon", coordinates, outputCoordinates[0], outputCoordinates[1]);

                            outputCoordinates = adjacentAnimal("Salmon", coordinates, i, j);
                            numSalmon += recursiveSearch("Salmon", coordinates, outputCoordinates[0], outputCoordinates[1]);
                        }

                        switch (c){
                            case 'a': salmonAScore(numSalmon); break;
                            case 'b': salmonBScore(numSalmon); break;
                            case 'c': salmonCScore(numSalmon); break;
                        }
                    }

                }
            }
        }
    }
    // salmon scoring, taking the output from salmonGeneric
    private void salmonAScore(int num) {
        switch (num) {
            case 0: break;
            case 1: player.addScore(2); break;
            case 2: player.addScore(4); break;
            case 3: player.addScore(7); break;
            case 4: player.addScore(11); break;
            case 5: player.addScore(15); break;
            case 6: player.addScore(20); break;

            default: player.addScore(26); break;
        }
    }

    private void salmonBScore(int num) {
        switch (num) {
            case 0: break;
            case 1: player.addScore(2); break;
            case 2: player.addScore(4); break;
            case 3: player.addScore(8); break;

            default: player.addScore(12); break;
        }
    }

    private void salmonCScore(int num) {
        switch (num) {
            case 0: break;
            case 1: player.addScore(2); break;
            case 2: player.addScore(4); break;
            case 3: player.addScore(9); break;
            case 4: player.addScore(11); break;

            default: player.addScore(17); break;
        }
    }

    // counts the number of a set animal adjacent to a given position
    private int adjacentCounter(String animal, int i, int j) {
        int[][] coordinates = new int[player.getMaxMap()][player.getMaxMap()];
        int[] outputCoordinates = adjacentAnimal(animal, coordinates, i, j);
        int adjacent = 0;

        while (outputCoordinates[0] != -1) {
            adjacent++;
            coordinates[outputCoordinates[0]][outputCoordinates[1]] = 1;
            outputCoordinates = adjacentAnimal(animal, coordinates, i, j);
        }
        return adjacent;
    }

    // recursively counts each specified animal adjacent to a given animal
    private int recursiveSearch(String animal, int[][] coordinates, int i, int j) {
        int[] outputCoordinates = adjacentAnimal(animal, coordinates, i, j);
        int output = 0;

        if (coordinates[i][j] != 0) {

        }

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
    public int[] adjacentAnimal(String animal, int[][] coordinates, int i, int j) {
        int[] output = new int[2];

        // tiles to the left and right are always at the same coordinates, whether x is even or not
        if (player.getPlayerMap()[i][j - 1].isOccupied() && player.getPlayerMap()[i][j - 1].getCreature1().equals(animal)) {
            if (coordinates[i][j-1] == 0) {
                output[0] = i;
                output[1] = j-1;
                return output;
            }
        }
        if (player.getPlayerMap()[i][j + 1].isOccupied() && player.getPlayerMap()[i][j + 1].getCreature1().equals(animal)) {
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

    // this method takes the coordinates of two adjacent locations, and returns the compass direction of the second location respective to the starting location
    public String adjacentToDirection (int i, int j, int[] adjacent) {
        if (i==adjacent[0] && j-1==adjacent[1]) {
            return "w";
        } else if (i==adjacent[0] && j+1==adjacent[1]) {
            return "e";
        }

        if (i%2 == 0){
            if (i-1 == adjacent[0]) {
                if (j == adjacent[1]) return "nw";
                if (j+1 == adjacent[1]) return "ne";
            } else if (i+1 == adjacent[0]) {
                if (j == adjacent[1]) return "sw";
                if (j+1 == adjacent[1]) return "se";
            }
        } else {
            if (i-1 == adjacent[0]) {
                if (j-1 == adjacent[1]) return "nw";
                if (j == adjacent[1]) return "ne";
            } else if (i+1 == adjacent[0]) {
                if (j-1 == adjacent[1]) return "sw";
                if (j == adjacent[1]) return "se";
            }
        }

        throw new IllegalArgumentException("Error with arguments passed to adjacentToDirection. i: " + i + " j: " + j + " adjacent: " + adjacent);

    }

    // this method recursively searches for a copy of a given animal in a given direction, and returns the coordinates of the found animal, or -1, -1 if not found
    private int[] searchLine (String animal, String direction, int count, int i, int j) {
        if (i==0 || j==0 || i == player.getMaxMap() || j == player.getMaxMap()) return new int[]{-1, -1};

        if (player.getPlayerMap()[i][j].isOccupied() && player.getPlayerMap()[i][j].getCreature1().equals(animal) && count>1) {
            return new int[]{i, j};
        }

        count++;
        switch (direction) {
            case "w": return searchLine(animal, direction, count, i, j-1);
            case "e": return searchLine(animal, direction, count, i, j+1);

            case "nw": if (i%2 == 0) {
                return searchLine(animal, direction, count, i-1, j);
            } else {
                return searchLine(animal, direction, count, i-1, j-1);
            }
            case "ne":if (i%2 == 0) {
                return searchLine(animal, direction, count, i-1, j+1);
            } else {
                return searchLine(animal, direction, count, i-1, j);
            }

            case "sw": if (i%2 == 0) {
                return searchLine(animal, direction, count, i+1, j-1);
            } else {
                return searchLine(animal, direction, count, i+1, j);
            }

            case "se": if (i%2 == 0) {
                return searchLine(animal, direction, count, i+1, j+1);
            } else {
                return searchLine(animal, direction, count, i+1, j);
            }

            default: throw new IllegalArgumentException("Invalid argument passed to direction in searchLine: " + direction);
        }
    }

    // recursively counts the number of a given animal in a straight line
    private int searchLineCount (String animal, String direction, int[][]coordinates, int i, int j) {
        int[] current = directionToLocation(direction, i, j);

        if (player.getPlayerMap()[current[0]][current[1]].isOccupied() && player.getPlayerMap()[current[0]][current[1]].getCreature1().equals(animal) && coordinates[current[0]][current[1]] == 0) {
            return 1 + searchLineCount(animal, direction, coordinates, current[0], current[1]);
        }

        return 0;
    }

    // method which takes in a string representing a compass direction and two ints representing a location on the player map, and returns two ints representing
    // the next location in that direction
    // public so it can be used to aid in test construction in ScoreTest
    public int[] directionToLocation (String direction, int i, int j) {
        if (i==0 || j==0 || i == player.getMaxMap() || j == player.getMaxMap()) return new int[]{-1, -1};
        switch (direction) {
            case "w": return new int[]{i, j-1};
            case "e": return new int[]{i, j+1};

            case "nw": if (i%2 == 0) {
                return  new int[]{i-1,j};
            } else return new int[]{i-1, j-1};
            case "ne": if (i%2 == 0) {
                return new int[]{i-1, j+1};
            } else return new int[]{i-1, j};

            case "sw": if (i%2 == 0) {
                return new int[]{i+1, j};
            } else return new int[]{i+1, j-1};
            case "se": if (i%2 == 0) {
                return new int[]{i+1, j+1};
            } else return new int[]{i+1, j};

            default: throw new IllegalArgumentException("Invalid direction passed to directionToLocation: " + direction);
        }
    }

    // method which marks chosen tiles from elkAScore as already counted
    private void elkAHelper (String direction, int[][] coordinates, int i, int j) {
        int[] next = directionToLocation(direction, i, j);

        while (player.getPlayerMap()[next[0]][next[1]].isOccupied() && player.getPlayerMap()[next[0]][next[1]].getCreature1().equals("Elk")) {
            coordinates[next[0]][next[1]] = 1;
            next = directionToLocation(direction, next[0], next[1]);
        }
    }
}
