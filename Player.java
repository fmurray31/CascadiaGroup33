public class Player {
    private String userName;
    private int score;
    private HabitatTiles[][] playerMap;
    // Integer value which stores the maximum map size
    private int maxMap = 50;
    HabitatTiles habitatTiles = new HabitatTiles("blank","blank","blank","blank","blank","blank","","","");

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return userName + ", score: " + score;
    }

    // Overwrites a tile in a player's map with a new habitat
    public void addHabitatToMap(HabitatTiles habitatTile, int x, int y) {
        this.playerMap[x][y] = habitatTile;
    }

    // prints a players map of habitat tiles (WIP)
    public void printMap (Player player) {
        boolean first;
        // rows
        for (int i=0; i<maxMap; i++) {
            // "lines" of each tile
            for (int j=0; j<4; j++) {
                // boolean to check if a tile is the first in an even row to be printed
                first = true;
                // columns
                for (int k=0; k<maxMap; k++) {
                    if (rowCheck(player, i) && columnCheck(player, k)) {
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
                            System.out.print(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature1()));
                            System.out.print(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature2()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getEast()));
                            break;

                        case 2:
                            if (i % 2==0 && first) {
                                System.out.print("                ");
                                first = false;
                            }

                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getWest()));
                            System.out.print(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature3()));
                            System.out.print("        ");
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
                if (futureRowCheck(player, i)){
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

    // prints a player's map with a number beside each row for player input
    // TODO: 20/02/2023 add extra blank row on the top and bottom 
    public void printRows (Player player) {
        boolean first;
        int row = 1;
        // rows
        for (int i=0; i<maxMap; i++) {
            // "lines" of each tile
            for (int j=0; j<4; j++) {
                // boolean to check if a tile is the first in a row to be printed
                first = true;
                // columns
                for (int k=0; k<maxMap; k++) {
                    if (rowCheck(player, i) && columnCheck(player, k)) {
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
                                System.out.print(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature1()));
                                System.out.print(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature2()));
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
                                System.out.print(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature3()));
                                System.out.print("        ");
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
                if (futureRowCheck(player, i)){
                    System.out.println("");
                }
            }
        }
    }

    // TODO: 20/02/2023 broken, also needs to account for one extra column on each side 
    public void printSingleRow (Player player, int row) {
        int rowFind = 0;
        int column = 0;

        while (!rowCheck(player, rowFind)) {
            rowFind++;
        }
        if (rowFind == maxMap) System.out.println("no valid rows to print");
        
        row += rowFind;

        // print row + rowfind row
        for (int i=0; i<=4; i++) {
            for (int j=0; j<maxMap; j++) {
                switch (i){
                    case 0:
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getNorthWest()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getNorthWest()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getNorthEast()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getNorthEast()));
                        break;
                        
                    case 1:
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getWest()));
                        System.out.print(habitatTiles.animalToAscii(player.playerMap[row][j].getCreature1()));
                        System.out.print(habitatTiles.animalToAscii(player.playerMap[row][j].getCreature2()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getEast()));
                        break;
                        
                    case 2:
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getWest()));
                        System.out.print(habitatTiles.animalToAscii(player.playerMap[row][j].getCreature3()));
                        System.out.print("        ");
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getEast()));
                        break;
                        
                    case 3:
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getSouthWest()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getSouthWest()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getSouthEast()));
                        System.out.print(habitatTiles.terrainToAscii(player.playerMap[row][j].getSouthEast()));
                        break;

                    case 4:
                        if (column < 10) {
                            System.out.print("        " + column + "       " + "        " + "        ");
                        } else {
                            System.out.print("        " + column + "      " + "        " + "        ");
                        }
                        column++;
                }
            }
        }

        System.out.println("");
    }
}
