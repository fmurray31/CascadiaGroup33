/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */
public class Player {
    private String userName;
    private int score, natureTokens;
    private HabitatTiles[][] playerMap;
    // Integer value which stores the maximum map size
    private final int maxMap = 55;
    // "blank" habitat tile used to fill a player's initial map
    HabitatTiles habitatTiles = new HabitatTiles("blank","blank","blank","blank","blank","blank","","","");

    // constructor that creates a player class using a name, and initialising a map of blank habitats and a score of 0
    public Player(String userName) {
        this.userName = userName;
        score = 0;
        this.playerMap = new HabitatTiles[maxMap][maxMap];
        // Initialising blank habitats
        for (int i=0; i<maxMap; i++) {
            for (int j=0; j<maxMap; j++) {
                playerMap[i][j] = new HabitatTiles("blank","blank","blank","blank","blank","blank","","","");
            }
        }
        natureTokens = 0;
    }

    // methods used to get and manipulate nature token counts
    public int getNatureTokens() {
        return natureTokens;
    }
    public void addNatureToken() {
        this.natureTokens++;
    }
    public void removeNatureToken() {
        this.natureTokens--;
    }

    public int getMaxMap() {
        return maxMap;
    }
    public HabitatTiles[][] getPlayerMap() {
        return playerMap;
    }

    // method which prints a single habitat tile from a player's map, used for testing
    public String printSingleTile(Player player, int x, int y) {
        return player.playerMap[x][y].toString();
    }

    public String getUserName() {
        return userName;
    }
    public int getScore() {
        return score;
    }

    // method to add score, ensures score can never be deprecated when accessed
    public void addScore(int add) {
        this.score += add;
    }

    @Override
    public String toString() {
        return userName + ", score: " + score;
    }

    // Overwrites a tile in a player's map with a new habitat
    public void addHabitatToMap(HabitatTiles habitatTile, int row, int column) {
        this.playerMap[row][column] = habitatTile;
    }

    // sets a player's score to 0
    public void resetScore() {
        this.score = 0;
    }

    // prints a players map of habitat tiles, with indents on even rows to represent six sided tiles.
    public void printMap () {
        Player player = this;
        boolean first;

        boolean hasReachedLine = false;
        // rows
        for (int i=0; i<maxMap; i++) {
            // "lines" of each tile
            for (int j=0; j<4; j++) {
                // boolean to check if a tile is the first in an even row to be printed
                first = true;
                // columns
                for (int k=0; k<maxMap; k++) {
                    if (rowCheck(player, i) && columnCheck(player, k)) {
                        hasReachedLine = true;
                    switch (j) {
                        case 0:
                            // indents each line by one "side" on even rows
                            if (i % 2==0 && first) {
                                System.out.print("                ");
                                first = false;
                            }

                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthWest()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthWest()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthEast()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthEast()));
                            break;

                        case 1:
                            if (i % 2==0 && first) {
                                System.out.print("                ");
                                first = false;
                            }

                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getWest()));
                            System.out.print(new StringBuilder().append(player.playerMap[i][k].occupiedBackground()).append(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature1())));
                            System.out.print(player.playerMap[i][k].occupiedBackground() + habitatTiles.animalToAscii(player.playerMap[i][k].getCreature2()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getEast()));
                            break;

                        case 2:
                            if (i % 2==0 && first) {
                                System.out.print("                ");
                                first = false;
                            }

                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getWest()));
                            System.out.print(player.playerMap[i][k].occupiedBackground() + habitatTiles.animalToAscii(player.playerMap[i][k].getCreature3()));
                            System.out.print(player.playerMap[i][k].occupiedBackground() + "        ");
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getEast()));
                            break;

                        case 3:
                            if (i % 2==0 && first) {
                                System.out.print("                ");
                                first = false;
                            }

                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getSouthWest()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getSouthWest()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getSouthEast()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getSouthEast()));
                            break;

                        default:
                            break;
                        }
                    }
                }
                if (futureRowCheck(player, i) && hasReachedLine){
                    System.out.println("");
                }
            }
        }
    }

    // checks a row to see if it contains any non-blank habitats, returns true if there are any non-blank, returns false if everything in a row is blank
    private boolean rowCheck (Player player, int row) {
        for (int i=0; i<maxMap; i++) {
            if (!player.playerMap[row][i].getNorthWest().equals("blank")){
                return true;
            }
        }
        return false;
    }

    // checks a column to see if it contains any non-blank habitats, returns true if there are any non-blank, returns false if everything in a column is blank
    private boolean columnCheck (Player player, int column) {
        for (int i=0; i<maxMap; i++) {
            if (!player.playerMap[i][column].getNorthWest().equals("blank")){
                return true;
            }
        }
        return false;
    }
    // checks if future rows are all empty
    private boolean futureRowCheck(Player player, int row) {
        for (int i=row; i<maxMap; i++) {
            if (rowCheck(player, i)) {
                return true;
            }
        }
        return false;
    }

    // prints a player's map with a number beside each row, used to take player input on row selection
    public void printRows (Player player) {
        boolean first;

        boolean hasReachedLine = false;
        int row = 1;
        // rows
        for (int i=1; i<maxMap-1; i++) {
            // "lines" of each tile
            for (int j=0; j<4; j++) {
                // boolean to check if a tile is the first in a row to be printed
                first = true;
                // columns
                for (int k=0; k<maxMap; k++) {
                    if ((columnCheck(player, k)) && (((rowCheck(player, i)) || (rowCheck(player, i-1)) || (rowCheck(player, i+1))))) {
                        hasReachedLine = true;
                        switch (j) {
                            case 0:
                                if (first) {
                                    System.out.print("   | ");
                                }

                                // indents each line by one "side" on even rows
                                if (i % 2==0 && first) {
                                    System.out.print("                ");
                                }
                                first = false;

                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthWest()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthWest()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthEast()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthEast()));
                                break;

                            case 1:
                                // code to display row numbers, with support for integers > 9
                                if (first) {
                                    if (row >= 10) {
                                        System.out.print(row + " | ");
                                    } else {
                                        System.out.print(row + "  | ");
                                    }
                                    row++;
                                }

                                if (i % 2==0 && first) {
                                    System.out.print("                ");
                                }
                                first = false;

                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getWest()));
                                System.out.print(new StringBuilder().append(player.playerMap[i][k].occupiedBackground()).append(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature1())).toString());
                                System.out.print(player.playerMap[i][k].occupiedBackground() + habitatTiles.animalToAscii(player.playerMap[i][k].getCreature2()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getEast()));
                                break;

                            case 2:
                                if (first) {
                                    System.out.print("   | ");
                                }

                                if (i % 2==0 && first) {
                                    System.out.print("                ");
                                }
                                first = false;

                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getWest()));
                                System.out.print(player.playerMap[i][k].occupiedBackground() + habitatTiles.animalToAscii(player.playerMap[i][k].getCreature3()));
                                System.out.print(player.playerMap[i][k].occupiedBackground() + "        ");
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getEast()));
                                break;

                            case 3:
                                if (first) {
                                    System.out.print("   | ");
                                }

                                if (i % 2==0 && first) {
                                    System.out.print("                ");
                                }
                                first = false;

                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getSouthWest()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getSouthWest()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getSouthEast()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getSouthEast()));
                                break;

                            default:
                                break;
                        }
                    }
                }
                if ((hasReachedLine) && (futureRowCheck(player, i) || futureRowCheck(player, i-1))){
                    System.out.println("");
                }
            }
        }
    }

    // prints a single row, specified by player input, with a number under each column
    public void printSingleRow (Player player, int row) {
        int column = 1;

        for (int i=0; i<=4; i++) {
            for (int j=1; j<maxMap-1; j++) {
                if (columnCheck(player, j) || columnCheck(player, j-1) || columnCheck(player, j+1))
                switch (i){
                    case 0:
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getNorthWest()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getNorthWest()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getNorthEast()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getNorthEast()));
                        break;
                        
                    case 1:
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getWest()));
                        System.out.print(player.playerMap[row][j].occupiedBackground() + habitatTiles.animalToAscii(player.playerMap[row][j].getCreature1()));
                        System.out.print(player.playerMap[row][j].occupiedBackground() + habitatTiles.animalToAscii(player.playerMap[row][j].getCreature2()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getEast()));
                        break;
                        
                    case 2:
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getWest()));
                        System.out.print(player.playerMap[row][j].occupiedBackground() + habitatTiles.animalToAscii(player.playerMap[row][j].getCreature3()));
                        System.out.print(player.playerMap[row][j].occupiedBackground() + "        ");
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getEast()));
                        break;
                        
                    case 3:
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getSouthWest()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getSouthWest()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getSouthEast()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getSouthEast()));
                        break;

                    case 4:
                        // prints the number beneath each column, with support for double digit numbers being differently sized
                        if (column < 10) {
                            System.out.print("        " + column + "       " + "        " + "        ");
                        } else {
                            System.out.print("        " + column + "      " + "        " + "        ");
                        }
                        column++;
                }
            }
            System.out.println("");
        }
    }

    // Takes the row number provided by a user, and converts it to the correct tile coordinate
    public int rowConversion(Player player, int row) {
        int rowFind = 0;

        while (!rowCheck(player, rowFind)) {
            rowFind++;
        }
        if (rowFind == maxMap) System.out.println("No valid rows found by rowConversion for input row:" + row);

        return row + rowFind - 2;
    }

    // Takes the column number provided by a user, and converts it to the correct tile coordinate
    public int columnConversion(Player player, int column) {
        int columnFind = 0;

        while (!columnCheck(player, columnFind)) {
            columnFind++;
        }
        if (columnFind == maxMap) System.out.println("No valid columns found by rowConversion for input column:" + column);

        return column + columnFind - 2;
    }
}
