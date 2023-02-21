import java.util.Scanner;

public class Cascadia {
    public static void main(String[] args) {

        SetupInput setupInput = new SetupInput();
        Score score = new Score();
        Tiles tiles = new Tiles();
        Player player;
        Turn turn = new Turn();

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

        //tiles.setupTiles();

        // Showing the central 4 tiles
        //tiles.drawCentralTiles();
        //tiles.displayCentralTiles();

        // used for testing map printing function
        playerArray[0].addHabitatToMap(testHab, 5, 5);
        playerArray[0].addHabitatToMap(testHab, 4, 4);
        playerArray[0].addHabitatToMap(testHab, 5, 4);
        playerArray[0].addHabitatToMap(testHab, 4, 5);
        //playerArray[0].printRows(playerArray[0]);
//        System.out.println("testing print single tile");
//        System.out.println(playerArray[0].printSingleTile(playerArray[0],5, 5));;

        //setupInput.userPrompts(playerArray);

        boolean gameEnd = false;
        int turnCount = 0;
        while (!gameEnd) {
            turn.turnLoop(playerArray[turnCount % playerArray.length]);
            turnCount++;
        }
    }
}

