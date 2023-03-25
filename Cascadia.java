/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cascadia {
    public static void main(String[] args) {
        StarterHabitat starterHabitat = new StarterHabitat();
        Setup setup = new Setup();
        Score score = new Score();
        ScoreCards scoreCards = new ScoreCards("blank", "blank");
        HabitatMajorities habitatMajorities = new HabitatMajorities();
        Turn turn = new Turn();

        // stores randomised score cards in a list
        List<ScoreCards> chosenScoreCards = scoreCards.generateScore();

        // generating new instance of starting habitats, adding them to a central arraylist, then shuffling that arraylist
        ArrayList<ArrayList<HabitatTiles>> starterHabitatPool = new ArrayList<>();
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


        // printing the chosen 5 scorecards, with formatting
        System.out.println("\n\nWildlife Scoring Cards for this game:");
        scoreCards.displayScoreCards(chosenScoreCards);
        System.out.println("\n\n-----------------------------------------------------------\n");

        // turn count functionality
        int turnCount = 0;
        int remainingTurns = numUsers*20+3;

        // main game loop
        while (remainingTurns>0) {
            System.out.println("Remaining turns: " + remainingTurns);
            remainingTurns--;
            turn.turnLoop(playerArray[turnCount % playerArray.length], playerArray.length);
            turnCount++;
        }

        System.out.println("Game Ends!");

        // calculating and scoring the largest habitat corridor(s)
        int maxHabs = 0;
        int secondMaxHabs = 0;
        Player maxHabPlayer = playerArray[0];
        Player secondMaxHabPlayer = playerArray[0];

        for (Player currentPlayer : playerArray) {
            int habCount = habitatMajorities.findHabitatMajority(currentPlayer);
            currentPlayer.addScore(habCount);
            if (habCount >= maxHabs) {
                secondMaxHabPlayer = maxHabPlayer;
                secondMaxHabs = maxHabs;

                maxHabs = habCount;
                maxHabPlayer = currentPlayer;
            }
        }

        switch (playerArray.length) {
            case 4:
            case 3:
                if (maxHabs == secondMaxHabs) {
                maxHabPlayer.addScore(2);
                secondMaxHabPlayer.addScore(2);
            }  else {
                maxHabPlayer.addScore(3);
                secondMaxHabPlayer.addScore(1);
            }   break;

            case 2: if (maxHabs == secondMaxHabs) {
                maxHabPlayer.addScore(1);
                secondMaxHabPlayer.addScore(1);
            }  else {
                maxHabPlayer.addScore(2);
            }   break;
        }

        for (Player currentPlayer : playerArray) {
            System.out.println("Player " + currentPlayer.getUserName() + "'s map:");
            currentPlayer.printMap(currentPlayer);
            score.scorePlayer(currentPlayer, chosenScoreCards);
            System.out.print("Player " + currentPlayer.getUserName() + "'s score: ");
            System.out.println(currentPlayer.getScore());
        }

        System.out.println("\n\n-----------------------------------------------------------\n\n");

        winnerDisplay(playerArray);
    }

    private static void winnerDisplay (Player[] playerArray) {
        //Set the winner as the first person in the player array
        int winScore = 0;
        int winToken = 0;
        String winPlayer = "";
        //loops through list of players
        for (int i = 0; i < playerArray.length; i++) {
            //compares the score of a player to another and updates winner if a larger score is found
            if(playerArray[i].getScore() > winScore) {
                winScore = playerArray[i].getScore();
                winPlayer = playerArray[i].getUserName();
                winToken = playerArray[i].getNatureTokens();
            } else if (playerArray[i].getScore() == winScore) {
                if (playerArray[i].getNatureTokens() > winToken) {
                    winPlayer = playerArray[i].getUserName();
                    winToken = playerArray[i].getNatureTokens();
                } else if (playerArray[i].getNatureTokens() == winToken) {
                    winPlayer += " + " + playerArray[i].getUserName();
                }
            }
        }

        System.out.println("The winner is " + winPlayer + " with a score of " + winScore + "!");
    }
}

