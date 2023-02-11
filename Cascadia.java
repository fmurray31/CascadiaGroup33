import java.util.Scanner;

public class Cascadia {

    public static void main(String[] args) {

        SetupInput setupInput = new SetupInput();
        Score score = new Score();
        Tiles tiles = new Tiles();
        Player player;

        System.out.println("\t\t -------- Welcome to Cascadia --------\n\n");


        // habitat tile for testing UI
        HabitatTiles testHab = new HabitatTiles("mountain", "mountain", "mountain",
                "forest", "forest", "forest",
                "elk", "salmon", "fox");

        // taking the number of players
        int numUsers = setupInput.numPlayer();
        Player[] playerArray;

        // Set a username for each player
        playerArray = setupInput.userNameRequest(numUsers);

        // Randomising then printing the order of players
        score.setOrder(playerArray);
        score.printOrder(playerArray);

        tiles.setupTiles();

        // Showing the central 4 tiles
        tiles.drawCentralTiles();
        tiles.displayCentralTiles();

        setupInput.userPrompts(playerArray, numUsers, testHab);

        // used for testing map printing function
//        playerArray[0].addHabitatToMap(testHab, 1, 1);
//        playerArray[0].addHabitatToMap(testHab, 0, 0);
//        playerArray[0].addHabitatToMap(testHab, 1, 0);
//        playerArray[0].addHabitatToMap(testHab, 0, 1);
//        playerArray[0].printMap(playerArray[0]);
    }
}

