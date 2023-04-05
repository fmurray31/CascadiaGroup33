/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import java.util.Scanner;

public class Turn {
    Tiles tiles;

    // this class takes a player class as input, and contains the code and driver for a single player's turn
    // this method takes a player class as input, and contains the code and driver for a single player's turn
    public void turnLoop(Player player, int playerCount, Tiles tiles){
        this.tiles = tiles;
        boolean endTurn = false;
        boolean cancelled = false;
        // int to track which tile is chosen
        int centralHabChoice = -1;
        // int to track which animal is chosen
        int centralAnimalChoice = -1;


        Scanner in = new Scanner(System.in);

        while (!endTurn) {
            int input = 0;
            System.out.println("Current player: " + player.getUserName());
            System.out.println(player.getUserName() + "'s nature token count is: " + player.getNatureTokens());
            System.out.println("\n" + player.getUserName() + "'s map: \n");

            player.printMap(player);
            System.out.println("\n\n-----------------------------------------------------------\n");

            // sets up tiles on the first loop
            if (tiles.centralAnimals == null) {
                tiles.setupTiles(playerCount);
                tiles.setupCentralTiles();
            }

            cull();

            // taking player input for central tile choice and nature token use, with error handling
            boolean choice = false;
            while (!choice) {
                System.out.println("Enter [1], [2], [3] or [4] to choose a habitat tile and animal tile combination from the central pool. Enter [5] to use a nature token.");
                try {
                    input = Integer.parseInt(in.nextLine());

                    switch (input){
                        case 1:
                            System.out.println("choice 1");
                            centralHabChoice = 0;
                            centralAnimalChoice = 0;
                            choice = true;
                            break;

                        case 2:
                            System.out.println("choice 2");
                            centralHabChoice = 1;
                            centralAnimalChoice = 1;
                            choice = true;
                            break;

                        case 3:
                            System.out.println("choice 3");
                            centralHabChoice = 2;
                            centralAnimalChoice = 2;
                            choice = true;
                            break;

                        case 4:
                            System.out.println("choice 4");
                            centralHabChoice = 3;
                            centralAnimalChoice = 3;
                            choice = true;
                            break;

                        case 5:
                            // nature token usage
                            System.out.println("You have " + player.getNatureTokens() + " nature tokens.");
                            int input2 = 0;
                            boolean choice2 = true;

                            if (player.getNatureTokens() <= 0) {
                                System.out.println("You don't have any nature tokens!");
                            } else {
                                player.removeNatureToken();
                                System.out.println("\nYou may use the nature token in the following ways:");
                                System.out.println("\n[1] Take any combination of one tile and one token of your choice from the selection." +
                                        "\n[2] Wipe any number of wildlife tokens of your choice.");

                                while (choice2) {
                                    input2 = Integer.parseInt(in.nextLine());

                                    switch (input2) {
                                        case 1:
                                            System.out.println("\nPlease select from tiles [1], [2], [3], [4]");
                                            centralHabChoice = numChoose() - 1;

                                            System.out.println("\nPlease select from animals [1], [2], [3], [4]");
                                            centralAnimalChoice = numChoose() - 1;

                                            choice = true;
                                            choice2 = false;
                                            break;

                                        case 2:
                                            System.out.println("\nPlease select how many wildlife tokens you would like to get rid of:");
                                            removeAni();
                                            choice2 = false;
                                            break;

                                        default:
                                            System.out.println("Please enter only enter integer [1] or [2]");
                                            break;
                                    }
                                }
                            }
                            break;

                        default:
                            System.out.println("Invalid input. Input may only be 1, 2, 3, 4 or 5");
                            break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Value entered must be an integer, without spaces or punctuation");
                }

                // checking that a chosen animal can be placed on a player's map
                if (centralAnimalChoice + centralHabChoice > 0) {
                    if (!tiles.freeAnimalSpace(player, tiles.centralAnimals.get(centralAnimalChoice))  && !tiles.suitableForAnimal(tiles.centralHabitats.get(centralHabChoice), tiles.centralAnimals.get(centralAnimalChoice))) {
                        System.out.println("There are no empty spaces on the board to place this animal, please choose another tile");
                        choice = false;
                    }
                }
            }

            // loop for placing habitat tiles
            choice = false;
            while (!choice && !cancelled) {
                boolean mistake = false;
                int habRow = 0;
                int habColumn = 0;
                player.printMap(player);

                // Loop for rotating a tile before placing
                boolean rotateDone = false;
                while (!rotateDone){
                    System.out.println("This is your current tile: \n" + tiles.centralHabitats.get(centralHabChoice).toString());
                    System.out.println("Would you like to rotate this tile? Enter 1 for yes, 2 for no.");
                    try {
                        input = Integer.parseInt(in.nextLine());

                        if (input == 1) {
                            System.out.println("Enter the number of rotations you want to apply. Each rotation is 60 degrees clockwise, or one side of the hex tile");
                            input = Integer.parseInt(in.nextLine());
                            if (input < 1) {
                                System.out.println("Must rotate at least one time");
                            } else {
                                tiles.centralHabitats.get(centralHabChoice).rotateTile(input);
                            }
                        } else if (input == 2) {
                            rotateDone = true;
                        } else {
                            System.out.println("Value entered was not 1, 2 or 3");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Value entered must be an integer, without spaces or punctuation");
                    }
                }

                player.printRows(player);
                System.out.println("Enter the number of the row where you want to place your habitat tile:");

                try {
                    input = Integer.parseInt(in.nextLine());
                    habRow = player.rowConversion(player, input);
                    if (habRow > player.getMaxMap()-5 || habRow < 0) {
                        System.out.println("Attempted to input an out of bounds location, please try again");
                        mistake = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Value entered must be an integer, without spaces or punctuation");
                    mistake = true;
                }

                if (!mistake) {
                    player.printSingleRow(player, habRow);
                    System.out.println("Enter the number of the column where you want to place your habitat tile");

                    try {
                        input = Integer.parseInt(in.nextLine());
                        habColumn = player.columnConversion(player, input);
                        if (habColumn > player.getMaxMap()-5 || habRow < 0) {
                            System.out.println("Attempted to input an out of bounds location, please try again");
                            mistake = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Value entered must be an integer, without spaces or punctuation");
                        mistake = true;
                    }

                    if (!mistake) {
                        if (!player.habitatTiles.isBlankHabitat(player, habRow, habColumn)) {
                            System.out.println("Can only add habitat tiles to a blank spot in your map");
                        }  else if (player.habitatTiles.isIsolated(player, habRow, habColumn)) {
                            System.out.println("Newly placed tiles must be adjacent to current map");
                        } else {
                            player.addHabitatToMap(tiles.centralHabitats.get(centralHabChoice), habRow, habColumn);
                            player.printMap(player);
                            choice = true;
                        }
                    }

                }

            }

            // loop for placing animal tiles
            choice = false;
            while (!choice && !cancelled) {
                boolean mistake = false;
                int aniRow = 0;
                int aniColumn = 0;
                String tempAnimal = player.habitatTiles.animalToAscii(tiles.centralAnimals.get(centralAnimalChoice).toString());

                player.printRows(player);
                System.out.println("Enter the number of the row where you want to place your animal tile: " + tempAnimal);

                try {
                    input = Integer.parseInt(in.nextLine());
                    aniRow = player.rowConversion(player, input);
                    if (aniRow > player.getMaxMap()-5 || aniRow < 0) {
                        System.out.println("Attempted to input an out of bounds location, please try again");
                        mistake = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Value entered must be an integer, without spaces or punctuation");
                    mistake = true;
                }

                if (!mistake) {
                    player.printSingleRow(player, aniRow);
                    System.out.println("Enter the number of the column where you want to place your animal tile: " + tempAnimal);
                    try {
                        input = Integer.parseInt(in.nextLine());
                        aniColumn = player.columnConversion(player, input);
                        if (aniColumn > player.getMaxMap()-5 || aniColumn < 0) {
                            System.out.println("Attempted to input an out of bounds location, please try again");
                            mistake = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Value entered must be an integer, without spaces or punctuation");
                        mistake = true;
                    }

                    if (!mistake) {
                        HabitatTiles tempHab = player.getPlayerMap()[aniRow][aniColumn];

                        if (player.habitatTiles.isBlankHabitat(player, aniRow, aniColumn)) {
                            System.out.println("Can only add animal tiles to an existing habitat");
                            mistake = true;
                        } else if (tempHab.isOccupied()) {
                            System.out.println("This tile is already occupied by another animal");
                            mistake = true;
                        } else if (!tiles.suitableForAnimal(tempHab, tiles.centralAnimals.get(centralAnimalChoice))) {
                            System.out.println("This tile is not suitable for " + tempAnimal);
                            mistake = true;
                        } else {
                            tiles.placeAnimal(tempHab, tiles.centralAnimals.get(centralAnimalChoice));
                            if (tempHab.isKeystone()) {
                                player.addNatureToken();
                                System.out.println("Nature token added!");
                            }
                        }
                    }
                }
                if (!mistake) {
                    player.printMap(player);

                    choice = true;
                }
            }
            endTurn = true;
        }
        tiles.centralHabitats.remove(centralHabChoice);
        tiles.centralAnimals.remove(centralAnimalChoice);
        tiles.drawCentralTiles();
    }

    // method which handles the "cull" mechanics
    public void cull() {
        boolean cullFinish = false;

        while (!cullFinish) {
            Scanner in = new Scanner(System.in);
            int input = 0;

            tiles.displayCentralTiles();

            Object[] maxTiles = tiles.optionalCull(tiles.centralAnimals);

            while ((Integer)maxTiles[1] == 4) {
                cullHelper(maxTiles);
                maxTiles = tiles.optionalCull(tiles.centralAnimals);
            }

            if ((Integer)maxTiles[1] == 3) {
                while (input != 2) {
                    input = 0;
                    System.out.println("Would you like to redraw all " + maxTiles[0].toString() + "?");
                    System.out.println("Enter 1 for yes, 2 for no: ");

                    try {
                        input = Integer.parseInt(in.nextLine());

                        if (input == 1) {
                            tiles.redrawAnimals((AnimalTiles) maxTiles[0]);
                            tiles.displayCentralTiles();
                            input = 2;
                        }
                        if (input != 2) {
                            System.out.println("Value entered was neither 1 nor 2");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Value entered must be an integer, without spaces or punctuation");
                    }
                }
            }

            if ((Integer)maxTiles[1] != 4) {
                cullFinish = true;
            }
        }
    }

    // helper method to enable testing without infinite loop
    public void cullHelper(Object[] maxTiles) {
        System.out.println("All animal tiles the same, initiating automatic cull");
        tiles.redrawAnimals((AnimalTiles) maxTiles[0]);
    }

    public int numChoose () {
        int input = 0;
        Scanner in = new Scanner(System.in);

        while (input <= 0 || input > 4) {
            try {
                System.out.println("Invalid input, please enter 1, 2, 3 or 4.");
                input = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please select an integer [1], [2], [3] or [4].");
            }
        }

        return input;
    }

    public void removeAni () {
        int input = numChoose();
        System.out.println("Please select the animal [1], [2], [3] or [4] you would like to get rid of:");

        for (int i = 0; i < input; i++) {
            input = numChoose();
            tiles.animalTiles.animalAL.add(tiles.centralAnimals.get(input));
            tiles.centralAnimals.remove(input);
            System.out.println("You got rid of " + "[" + input + "]");
        }
        tiles.drawCentralTiles();
        tiles.displayCentralTiles();
    }
}