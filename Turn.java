import java.util.Scanner;

public class Turn {
    private Tiles tiles;

    // this class takes a player class as input, and contains the code and driver for a single player's turn
    public void turn(Player player){
        boolean endTurn = false;

        Scanner in = new Scanner(System.in);
        int input;

        while (!endTurn) {
            System.out.println("Current player: " + player);
            System.out.println("");

            player.printMap(player);
            System.out.println("\n\n-----------------------------------------------------------\n");

            System.out.println("Central tiles:");
            tiles.displayCentralTiles();

            // taking player input for central tile choice and nature token use, with error handling
            boolean choice = false;
            while (!choice) {
                System.out.println("Enter 1, 2, 3 or 4 to choose a habitat tile and animal tile combination from the central pool. Enter 5 to use a nature token.");

                try {
                    input = Integer.parseInt(in.nextLine());

                    switch (input){
                        case 1:
                            System.out.println("choice 1");
                            choice = true;
                            break;

                        case 2:
                            System.out.println("choice 2");
                            choice = true;
                            break;

                        case 3:
                            System.out.println("choice 3");
                            choice = true;
                            break;

                        case 4:
                            System.out.println("choice 4");
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
        }
    }
}
