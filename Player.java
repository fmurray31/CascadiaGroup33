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
        // rows
        for (int i=0; i<maxMap; i++) {
            // "lines" of each tile
            for (int j=0; j<4; j++) {
                // columns
                for (int k=0; k<maxMap; k++) {
                    if (rowCheck(player, i) || columnCheck(player, k)) {
                    switch (j) {
                        case 0:
                            // indents each line by one "side" on even rows
                            if (i % 2 == 0) System.out.println("        ");
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthWest()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthWest()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthEast()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthEast()));
                            break;

                        case 1:
                            if (i % 2 == 0) System.out.println("        ");
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getWest()));
                            System.out.print(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature1()));
                            System.out.print(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature2()));
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getEast()));
                            break;

                        case 2:
                            if (i % 2 == 0) System.out.println("        ");
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getWest()));
                            System.out.print(habitatTiles.animalToAscii(player.playerMap[i][k].getCreature3()));
                            System.out.print("        ");
                            System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getEast()));
                            break;

                        case 3:
                            if (i % 2 == 0) System.out.println("        ");
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
                System.out.println("");
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
}
