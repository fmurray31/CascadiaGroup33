import java.util.Scanner;

public class Turn {
    private AnimalTiles animalTiles;
    Tiles tiles = new Tiles();

    // this class takes a player class as input, and contains the code and driver for a single player's turn
    public void turnLoop(Player player, int playerCount){
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

            // handles the "cull" part of the turn
            boolean cullFinish = false;
            while (!cullFinish) {
                if (tiles.centralAnimals == null) {
                    ;
                    tiles.setupTiles(playerCount);
                    tiles.setupCentralTiles();
                }

                tiles.displayCentralTiles();

                Object maxTiles[];

                maxTiles = tiles.optionalCull(tiles.getAnimalTiles());
                if ((Integer)maxTiles[1]  == 4) {
                    System.out.println("All animal tiles the same, initiating automatic cull");
                    tiles.redrawAnimals((AnimalTiles) maxTiles[0]);
                }

                if ((Integer)maxTiles[1]  == 3) {
                    input = 0;
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
                    System.out.println("Would you like to rotate this tile? Enter 1 for yes, 2 for no. Enter 3 to cancel tile placement");
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
                        }else if (input == 3) {
                            System.out.println("Cancelled tile placement");
                            cancelled = true;
                            break;
                        }else {
                            System.out.println("Value entered was not 1, 2 or 3");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Value entered must be an integer, without spaces or punctuation");
                    }
                }

                if (cancelled) break;
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

                System.out.println("Choose a habitat to place " + tempAnimal + "\n");

                player.printRows(player);
                System.out.println("Enter the number of the row where you want to place your animal tile:");

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
                    System.out.println("Enter the number of the column where you want to place " + tempAnimal);
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
}
