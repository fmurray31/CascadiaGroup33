import java.util.Scanner;

public class Cascadia {

    public static void main(String[] args) {

        SetupInput setupInput = new SetupInput();
        Score score = new Score();
        Tiles tiles = new Tiles();
        Player player;
        Scanner in = new Scanner(System.in);
        int inPlay;

        System.out.println("\t\t -------- Welcome to Cascadia --------\n\n\n");

        // testing UI
        HabitatTiles testHab = new HabitatTiles("mountain", "mountain", "mountain",
                "forest", "forest", "forest",
                "elk", "salmon", "fox");

        int numUsers = setupInput.numPlayer();
        Player[] playerArray;

        playerArray = setupInput.userNameRequest(numUsers);

        score.setOrder(playerArray);
        score.printOrder(playerArray);

        System.out.println("\n\n-----------------------------------------------------------\n\n");

        tiles.setupTiles();

        tiles.drawCentralTiles();
        tiles.displayCentralTiles();

        for (int i = 1; i < numUsers; i++) {

        }


        //Do While loop, prompts the user if they would like to play
        do {
            System.out.println("\nPlayer " + playerArray[i].getUserName()+ "'s" + " Habitat:\n");
            playerArray[i].printMap(playerArray[i]);
            System.out.println("\n\n[1] View next player's map");
            System.out.println("[2] Exit program");
            inPlay = in.nextInt();
        } while (inPlay != 1 && inPlay != 2);

        //If the user would like to play....
        if (inPlay == 1) {
            int i = 0;
            while (i < numUsers) {
                playerArray[i].addHabitatToMap(testHab, 1, 1);
                playerArray[i].printMap(playerArray[i]);

                System.out.println("[1] View next player's map");
                System.out.println("[2] Exit program");
                inPlay = in.nextInt();

                if (inPlay != 1 && inPlay != 2) {
                    System.out.println("Invalid choice!");
                    System.out.println("[1] View next player's map");
                    System.out.println("[2] Exit program");
                    inPlay = in.nextInt();
                } else if (inPlay == 1) {
                    i++;
                } else if (inPlay == 2) {
                    System.exit(0);
                }
            }
            /*
            // initialise all new habitats in map to "blank" habitats, add functionality to print blank habitats in terrainToAscii and animalToAscii
            playerArray[0].addHabitatToMap(testHab, 1, 1);
            System.out.println(playerArray[0].printSingleTile(playerArray[0], 0, 0));
            playerArray[0].printMap(playerArray[0]);
             */

        //Incomplete, supposed to loop through the list of players and their maps 1 at a time, prompts user if they would like ot continue
        int i = 0;
        while (i < numUsers) {
            playerArray[i].addHabitatToMap(testHab, 1, 1);
            playerArray[i].printMap(playerArray[i]);

            System.out.println("[1] View next player's map");
            System.out.println("[2] Exit program");
            inPlay = in.nextInt();

            if (inPlay != 1 && inPlay != 2) {
                System.out.println("Invalid choice!");
                System.out.println("[1] View next player's map");
                System.out.println("[2] Exit program");
                inPlay = in.nextInt();
            } else if (inPlay == 1) {
                i++;
            } else if (inPlay == 2) {
                System.exit(0);
            } else if (inPlay == 2) {
                System.exit(0);
            }
        }

        //initialise all new habitats in map to "blank" habitats, add functionality to print blank habitats in terrainToAscii and animalToAscii
        //playerArray[0].addHabitatToMap(testHab, 1, 1);
        //System.out.println(playerArray[0].printSingleTile(playerArray[0], 0, 0));
        //playerArray[0].printMap(playerArray[0]);
    }
}

