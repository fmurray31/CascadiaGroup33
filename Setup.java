/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Setup {
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
        System.out.println("Enter the number of players, between 2 and 4.\nEnter 5 to play versus a bot:");
        while (numUsers == 0) {
            try {
                int num = Integer.parseInt(in.nextLine());

                if (num > 5) {
                    System.out.println("Number of players cannot be greater than 4");
                } else if (num < 2) {
                    System.out.println("Number of players cannot be less than 2");
                } else if (num == 5) {
                    numUsers = 1;
                    System.out.println("Bot game chosen");
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
    public ArrayList<Player> userNameRequest(int numUsers) {
        String input = "";
        ArrayList<Player> players = new ArrayList<>();

        if (numUsers == 1) {
            players.add(new Player("Bot"));
        }

        Scanner in = new Scanner(System.in);
        for (int i=0; i<numUsers; i++) {
            System.out.println("Enter a username for player " + (i+1));
            input = in.nextLine();
            if (input.equals("")){
                System.out.println("Username may not be blank");
                i--;
            } else if (input.equalsIgnoreCase("bot")) {
                System.out.println("Protected name used, please try a different name");
                i--;
            } else {
                players.add(new Player(input));
                System.out.println("Player " + (i+1) + " is named " + input);
            }
        }
        System.out.println("\n\n");

        return players;
    }

    // Randomises the order of players
    public void setOrder(ArrayList<Player> players) {
        System.out.println("Randomising order of players:");

        Collections.shuffle(players);
    }

    // Prints the player array and displays their order
    public void printOrder(ArrayList<Player> players) {
        for (int i=0; i< players.size(); i++) {
            System.out.println("Player " + (i+1) + " is " + players.get(i).getUserName());
        }
    }
}