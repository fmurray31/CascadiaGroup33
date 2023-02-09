public class Player {
    private String userName;
    private int score;
    private HabitatTiles[][] playerMap;
    private int maxMap = 50;
    HabitatTiles habitatTiles;

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

    public HabitatTiles[][] getPlayerMap() {
        return playerMap;
    }

    public void addHabitatToMap(HabitatTiles habitatTile, int x, int y) {
        this.playerMap[x][y] = habitatTile;
    }

    public void printMap (Player player) {
        // rows
        for (int i=0; i<maxMap; i++) {
            // "lines" of each tile
            for (int j=0; j<4; j++) {
                // columns
                for (int k=0; k<maxMap; k++) {
                    switch (j) {
                        case 0:
                            if (player.playerMap[i][k] != null) {
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthWest()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthWest()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthEast()));
                                System.out.print(habitatTiles.terrainToAscii(player.playerMap[i][k].getNorthEast()));
                                break;
                            } else {
                                System.out.print("\t\t\t\t\t\t\t\t ");
                            }

                        default: break;
                    }
                }
                System.out.println("");
            }
        }
    }
}
