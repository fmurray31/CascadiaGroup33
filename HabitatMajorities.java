import java.util.ArrayList;
import java.util.List;

public class HabitatMajorities {
    Player player;
    Score score = new Score();

    public int findHabitatMajority (Player p) {
        this.player = p;

        List<ScoreCards> tempSc = new ArrayList<>();
        score.scorePlayer(player, tempSc);
        player.resetScore();
        int maxTiles = 0;
        
        for (int i=0; i<player.getMaxMap(); i++) {
            for (int j=0; j<player.getMaxMap(); j++) {
                if (!player.getPlayerMap()[i][j].getNorthWest().equals("blank")) {
                    int tempCount = 0;
                    int[][] tempCoords = new int[player.getMaxMap()][player.getMaxMap()];

                    String direction;
                    String terrain;
                    direction = matchingSides(i, j, tempCoords);
                    while (!direction.equals("none")) {
                        terrain = adjacentSide(i, j, direction);

                        tempCount = recursiveHabCount(i, j, new int[player.getMaxMap()][player.getMaxMap()], terrain);

                        if (tempCount > maxTiles) {
                            maxTiles = tempCount;
                        }

                        direction = matchingSides(i, j, tempCoords);
                    }


                }
            }
        }
        return maxTiles;
    }

    // takes a string representing a side of a hex tile and the coordinates of a tile, then returns the neighbouring tile side
    private String adjacentSide (int i, int j, String side) {
        switch (side) {
            case "w": return player.getPlayerMap()[i][j-1].getEast(); 
            case "e": return player.getPlayerMap()[i][j+1].getWest(); 
            
            case "nw": if (i%2 == 0) {
                return player.getPlayerMap()[i-1][j].getSouthEast();
            } else return player.getPlayerMap()[i-1][j-1].getSouthEast();
            
            case "ne": if (i%2 == 0) {
                return player.getPlayerMap()[i-1][j+1].getSouthWest();
            } else return player.getPlayerMap()[i-1][j].getSouthWest();
            
            case "sw": if (i%2 == 0) {
                return player.getPlayerMap()[i+1][j].getNorthEast();
            } else return player.getPlayerMap()[i+1][j-1].getNorthEast();
            
            case "se": if (i%2 == 0) {
                return player.getPlayerMap()[i+1][j+1].getNorthWest();
            } else return player.getPlayerMap()[i+1][j].getNorthWest();
            
            
            default: throw new IllegalArgumentException("Invalid side passed to adjacentSide: " + side);
        }
    }

    private String matchingSides (int i, int j, int[][] counted) {
        int[] adj;
        adj = score.directionToLocation("w", i, j);
        if (player.getPlayerMap()[adj[0]][adj[1]].getEast().equals(player.getPlayerMap()[i][j].getWest()) && counted[adj[0]][adj[1]] == 0) {
            counted[adj[0]][adj[1]] = 1;
            return "w";
        }

        adj = score.directionToLocation("nw", i, j);
        if (player.getPlayerMap()[adj[0]][adj[1]].getSouthEast().equals(player.getPlayerMap()[i][j].getNorthWest()) && counted[adj[0]][adj[1]] == 0) {
            counted[adj[0]][adj[1]] = 1;
            return "nw";
        }

        adj = score.directionToLocation("ne", i, j);
        if (player.getPlayerMap()[adj[0]][adj[1]].getSouthWest().equals(player.getPlayerMap()[i][j].getNorthEast()) && counted[adj[0]][adj[1]] == 0) {
            counted[adj[0]][adj[1]] = 1;
            return "ne";
        }

        adj = score.directionToLocation("e", i, j);
        if (player.getPlayerMap()[adj[0]][adj[1]].getWest().equals(player.getPlayerMap()[i][j].getEast()) && counted[adj[0]][adj[1]] == 0) {
            counted[adj[0]][adj[1]] = 1;
            return "e";
        }

        adj = score.directionToLocation("se", i, j);
        if (player.getPlayerMap()[adj[0]][adj[1]].getNorthWest().equals(player.getPlayerMap()[i][j].getSouthEast()) && counted[adj[0]][adj[1]] == 0) {
            counted[adj[0]][adj[1]] = 1;
            return "se";
        }

        adj = score.directionToLocation("sw", i, j);
        if (player.getPlayerMap()[adj[0]][adj[1]].getNorthEast().equals(player.getPlayerMap()[i][j].getSouthWest()) && counted[adj[0]][adj[1]] == 0) {
            counted[adj[0]][adj[1]] = 1;
            return "sw";
        }

        return "none";
    }

    // takes two ints representing the current tile, a 2d int array used to track already counted tiles, and a string representing a terrain type.
    // Recursively searches through connected habitats, and returns the number of contiguous terrains found.
    private int recursiveHabCount (int i, int j, int[][]counted, String terrain) {
        String matchedSide = matchingSides(i, j, counted);
        int[] nextCoord;
        int output = 0;

        while (!matchedSide.equals("none")) {
            if (adjacentSide(i, j, matchedSide).equals(terrain)) {
                nextCoord = score.directionToLocation(matchedSide, i, j);
                output += 1 + recursiveHabCount(nextCoord[0], nextCoord[1], counted, terrain);
            }
            matchedSide = matchingSides(i, j, counted);
        }

        return output;
    }
}
