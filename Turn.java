import java.util.Objects;
import java.util.Scanner;

public class Turn {
    private AnimalTiles animalTiles;
    Tiles tiles = new Tiles();

    // this class takes a player class as input, and contains the code and driver for a single player's turn
    public void turnLoop(Player player){
        boolean endTurn = false;


        Scanner in = new Scanner(System.in);
        int input = 0;

        while (!endTurn) {
            System.out.println("Current player: " + player.getUserName());
            System.out.println("\n" + player.getUserName() + "'s map: \n");

            player.printMap(player);
            System.out.println("\n\n-----------------------------------------------------------\n");

            boolean cullFinish = false;
            while (!cullFinish) {
                if (tiles.centralAnimals == null) {
                    tiles.setupTiles();
                    tiles.drawCentralTiles();
                }

                tiles.displayCentralTiles();

                Object maxTiles[];

                maxTiles = tiles.optionalCull(tiles.getAnimalTiles());
                if ((Integer)maxTiles[1]  == 4) {
                    System.out.println("All animal tiles the same, initiating automatic cull");
                    tiles.redrawAnimals((AnimalTiles) maxTiles[0]);
                }

                if ((Integer)maxTiles[1]  == 3) {
                    while (input != 2)
                    System.out.println("Would you like to redraw all " + maxTiles[0].toString() + "?");
                    System.out.println("Enter 1 for yes, 2 for no: ");
                    try {
                        input = Integer.parseInt(in.nextLine());

                        if (input == 1) {
                            tiles.redrawAnimals((AnimalTiles) maxTiles[0]);
                            input = 2;
                        }
                        if (input != 1 && input != 2) {
                            System.out.println("Value entered was neither 1 nor 2");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Value entered must be an integer, without spaces or punctuation");
                    }
                }
                cullFinish = true;
            }

            // taking player input for central tile choice and nature token use, with error handling
            boolean choice = false;
            int centralChoice = 0;
            while (!choice) {
                System.out.println("Enter 1, 2, 3 or 4 to choose a habitat tile and animal tile combination from the central pool. Enter 5 to use a nature token.");

                try {
                    input = Integer.parseInt(in.nextLine());

                    switch (input){
                        case 1:
                            System.out.println("choice 1");
                            centralChoice = 1;
                            choice = true;
                            break;

                        case 2:
                            System.out.println("choice 2");
                            centralChoice = 2;
                            choice = true;
                            break;

                        case 3:
                            System.out.println("choice 3");
                            centralChoice = 3;
                            choice = true;
                            break;

                        case 4:
                            System.out.println("choice 4");
                            centralChoice = 4;
                            choice = true;
                            break;

                        case 5:
                            System.out.println("nature token");
                            choice = true;
                            break;

                        default:
                            System.out.println("Invalid input. Input may only be 1, 2, 3, 4 or 5");
                            break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Value entered must be an integer, without spaces or punctuation");
                }
            }
            choice = false;

            while (!choice) {
                int row = 0;
                int column = 0;
                player.printRows(player);
                System.out.println("Enter the number of the row where you want to place your habitat tile:");

                try {
                    input = Integer.parseInt(in.nextLine());
                    row = input;
                } catch (NumberFormatException e) {
                    System.out.println("Value entered must be an integer, without spaces or punctuation");
                }

                player.printSingleRow(player, input);
                System.out.println("Enter the number of the column where you want to place your habitat tile");

                try {
                    input = Integer.parseInt(in.nextLine());
                    column = input;
                } catch (NumberFormatException e) {
                    System.out.println("Value entered must be an integer, without spaces or punctuation");
                }

                row = player.rowConversion(player, row);
                column = player.columnConversion(player, column);

                if (!player.habitatTiles.isBlankHabitat(player, row, column)) {
                    System.out.println("Can only add habitat tiles to a blank spot in your map");
                }  else if (player.habitatTiles.isIsolated(player, row, column)) {
                    System.out.println("Newly placed tiles must be adjacent to current map");
                } else {
                    player.addHabitatToMap(tiles.centralHabitats.get(centralChoice), row, column);
                    player.printMap(player);
                    choice = true;
                }
            }
            endTurn = true;
        }
    }
}
