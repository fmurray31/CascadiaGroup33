import java.nio.file.StandardWatchEventKinds;
import java.util.Random;

public class Score {
    public void printPlayerArray(Player[] playerArray) {
        for (Player i : playerArray) {
            System.out.println(i);
        }
    }

    // Randomises the order of players
    public void setOrder(Player[] playerArray) {
        Random rand = new Random();
        int tempInt;

        System.out.println("Randomising order of players:");

        for (int i=0; i<playerArray.length; i++) {
            tempInt = rand.nextInt(playerArray.length);
            Player temp = playerArray[i];
            playerArray[i] = playerArray[tempInt];
            playerArray[tempInt] = temp;
        }
    }

    // Prints the player array and displays their order
    public void printOrder(Player[] playerArray) {
        for (int i=0; i< playerArray.length; i++) {
            System.out.println("Player " + (i+1) + " is " + playerArray[i].getUserName());
        }
    }
}
