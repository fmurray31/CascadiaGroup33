import java.util.ArrayList;
import java.util.Scanner;

public class SetupInput {
    // Takes the three habitat tiles from a starter habitat and assigns them to a player's map
    public void addStarterHabitats(ArrayList<HabitatTiles> habAL, Player player) {
        player.addHabitatToMap(habAL.get(0), 25, 25);
        player.addHabitatToMap(habAL.get(1), 26, 24);
        player.addHabitatToMap(habAL.get(2), 26, 25);
    }

    // Takes user input for the number of players, with error handling
    public int numPlayer() {
        int numUsers = 0;
        Scanner in = new Scanner(System.in);

        // Takes user input for number of players with error checking, only allows integer inputs
        System.out.println("Enter the number of players, between 2 and 4:");
        while (numUsers == 0) {
            try {
                int num = Integer.parseInt(in.nextLine());

                if (num > 4) {
                    System.out.println("Number of players cannot be greater than 4");
                } else if (num < 2) {
                    System.out.println("Number of players cannot be less than 2");
                } else {
                    numUsers = num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Value entered must be in integer form, i.e. 2, 3, 4");
            }
        }
        System.out.println("Number of users is: " + numUsers);
        return numUsers;
    }

    // Takes usernames from users, only invalid entries are blank names
    public Player[] userNameRequest(int numUsers) {
        String input = "";

        Player[] players = new Player[numUsers];
        Scanner in = new Scanner(System.in);
        for (int i=0; i<numUsers; i++) {
            System.out.println("Enter a username for player " + (i+1));
            input = in.nextLine();
            if (input.equals("")){
                System.out.println("Username may not be blank");
                i--;
            } else {
                players[i] = new Player(input); //passMap);
                System.out.println("Player " + (i+1) + " is named " + input);
            }
        }
        System.out.println("\n\n");
        return players;
    }

    //Printing and Displaying map of users, with user prompts
    public void userPrompts(Player[] playerArray) {
        Scanner in = new Scanner(System.in);
        int i = 0;

        while (i < playerArray.length) {
            try {
                System.out.println("\n\n-----------------------------------------------------------\n");
                System.out.println("Player " + playerArray[i].getUserName() + "'s" + " Habitat:\n\n");

                //Put code to display player's habitat here

                
                playerArray[i].printMap(playerArray[i]);

                if (i == playerArray.length - 1) {
                    i++;
                    break;
                }

                System.out.println("\n\n[1] View next player's map");
                System.out.println("[2] Exit program\n");

                //User prompt
                int num = Integer.parseInt(in.nextLine());

                //error handling
                if (num < 1) {
                    System.out.println("Choice must be either 1 or 2");
                } else if (num > 2) {
                    System.out.println("Choice must be either 1 or 2");
                }

                if (num == 1) {
                    i++;
                } else if (num == 2) {
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Value entered must be either integer 1 or 2");
            }
        }
    }
}