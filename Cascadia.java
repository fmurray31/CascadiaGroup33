import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Cascadia {
    public static void main(String[] args) {

        StarterHabitat starterHabitat = new StarterHabitat();
        SetupInput setupInput = new SetupInput();
        Score score = new Score();
        Tiles tiles = new Tiles();
        Player player;
        Turn turn = new Turn();

        // generating new instance of starting habitats, adding them to a central arraylist, then shuffling that arraylist
        ArrayList<ArrayList> starterHabitatPool = new ArrayList<>();
        starterHabitatPool.add(starterHabitat.StarterHabitat1);
        starterHabitatPool.add(starterHabitat.StarterHabitat2);
        starterHabitatPool.add(starterHabitat.StarterHabitat3);
        starterHabitatPool.add(starterHabitat.StarterHabitat4);
        starterHabitatPool.add(starterHabitat.StarterHabitat5);

        Collections.shuffle(starterHabitatPool);


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

        for (int i=0; i<numUsers; i++) {
            setupInput.addStarterHabitats(starterHabitatPool.get(i), playerArray[i]);
        }

        //tiles.setupTiles();


        // used for testing map printing function
//        playerArray[0].addHabitatToMap(testHab, 5, 5);
//        playerArray[0].addHabitatToMap(testHab, 4, 4);
//        playerArray[0].addHabitatToMap(testHab, 5, 4);
//        playerArray[0].addHabitatToMap(testHab, 4, 5);
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

