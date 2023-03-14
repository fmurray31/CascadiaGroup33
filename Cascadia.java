import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cascadia {
    public static void main(String[] args) {

        StarterHabitat starterHabitat = new StarterHabitat();
        Setup setup = new Setup();
        Score score = new Score();
        ScoreCards scoreCards = new ScoreCards("blank", "blank");
        Tiles tiles = new Tiles();
        Player player;
        Turn turn = new Turn();

        // stores randomised score cards in a list
        List<ScoreCards> chosenScoreCards = scoreCards.generateScore();


        // generating new instance of starting habitats, adding them to a central arraylist, then shuffling that arraylist
        ArrayList<ArrayList> starterHabitatPool = new ArrayList<>();
        starterHabitatPool.add(starterHabitat.StarterHabitat1);
        starterHabitatPool.add(starterHabitat.StarterHabitat2);
        starterHabitatPool.add(starterHabitat.StarterHabitat3);
        starterHabitatPool.add(starterHabitat.StarterHabitat4);
        starterHabitatPool.add(starterHabitat.StarterHabitat5);

        Collections.shuffle(starterHabitatPool);


        System.out.println("\t\t -------- Welcome to Cascadia --------\n\n");


        // taking the number of players
        int numUsers = setup.numPlayer();
        Player[] playerArray;

        // Set a username for each player
        playerArray = setup.userNameRequest(numUsers);

        // Randomising then printing the order of players
        setup.setOrder(playerArray);
        setup.printOrder(playerArray);

        for (int i=0; i<numUsers; i++) {
            setup.addStarterHabitats(starterHabitatPool.get(i), playerArray[i]);
        }

        int turnCount = 0;
        int remainingTurns = numUsers*20+3;

        while (remainingTurns>0) {
            System.out.println("Remaining turns: " + remainingTurns);
            remainingTurns--;
            turn.turnLoop(playerArray[turnCount % playerArray.length], playerArray.length);
            turnCount++;
        }

        System.out.println("Game Ends!");
        System.out.println("scoring goes here");

        for (int i=0; i< playerArray.length; i++) {
            score.scorePlayer(playerArray[i], chosenScoreCards);
            System.out.println("Player " + playerArray[i].getUserName() + "'s score:");
            System.out.println(playerArray[i].getScore());
        }
    }
}

