import java.util.Scanner;

public class Cascadia {

    public static void main(String[] args) {

        SetupInput setupInput = new SetupInput();
        Score score = new Score();
        Tiles tiles = new Tiles();
        Player player;

        System.out.println("\t\t -------- Welcome to Cascadia --------\n\n\n");

        // testing UI
        HabitatTiles testHab = new HabitatTiles("mountain", "mountain", "mountain",
                "forest", "forest", "forest",
                "elk", "salmon", "fox");

        //Setup the number of players
        int numUsers = setupInput.numPlayer();
        Player[] playerArray;

        //Set a username for each player
        playerArray = setupInput.userNameRequest(numUsers);

        score.setOrder(playerArray);
        score.printOrder(playerArray);

        System.out.println("\n\n-----------------------------------------------------------\n\n");

        tiles.setupTiles();

        tiles.drawCentralTiles();
        tiles.displayCentralTiles();

        setupInput.userPrompts(playerArray, numUsers, testHab);

            /*
            // initialise all new habitats in map to "blank" habitats, add functionality to print blank habitats in terrainToAscii and animalToAscii
            playerArray[0].addHabitatToMap(testHab, 1, 1);
            System.out.println(playerArray[0].printSingleTile(playerArray[0], 0, 0));
            playerArray[0].printMap(playerArray[0]);
             */
    }
}

