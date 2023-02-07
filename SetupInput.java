import java.util.Scanner;

public class SetupInput {
    public int numPlayer() {
        int numUsers = 0;
        Scanner in = new Scanner(System.in);

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

    public Player[] userNameRequest(int numUsers) {
        String input = "";

        //Could pass this variable but would need to initialize before passing
        //May need to determine size of map beforehand?
        int[][] passMap = new int[50][50];

        Player[] players = new Player[numUsers];
        Scanner in = new Scanner(System.in);
        for (int i=0; i<numUsers; i++) {
            System.out.println("Enter a username for player " + (i+1));
            input = in.nextLine();
            if (input.equals("")){
                System.out.println("Username may not be blank");
                i--;
            } else {
                players[i] = new Player(input, passMap); //passMap);
                System.out.println("Player " + (i+1) + " is named " + input);
            }
        }
        System.out.println("\n\n");
        return players;
    }
}
