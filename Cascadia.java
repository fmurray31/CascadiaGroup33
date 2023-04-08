/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import org.junit.rules.Stopwatch;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Cascadia {
    public static void main(String[] args) {
        StarterHabitat starterHabitat = new StarterHabitat();
        Setup setup = new Setup();
        Tiles tiles = new Tiles();
        Score score = new Score();
        ScoreCards scoreCards = new ScoreCards("blank", "blank");
        HabitatMajorities habitatMajorities = new HabitatMajorities();
        Turn turn = new Turn();
        Bot bot = new Bot();
        Stopwatch stopwatch = new Stopwatch();

        boolean botGame = false;

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

        // Set a username for each player
        ArrayList<Player> playerAL = setup.userNameRequest(numUsers);

        if (playerAL.size() == 2 && numUsers == 1) {
            numUsers = 2;
            botGame = true;
        }

        // Randomising then printing the order of players
        setup.setOrder(playerAL);
        setup.printOrder(playerAL);

        for (int i=0; i<numUsers; i++) {
            setup.addStarterHabitats(starterHabitatPool.get(i), playerAL.get(i));
        }

        // List to store score cards, cards randomised if not a bot game
        List<ScoreCards> chosenScoreCards;
        if (botGame) {
            chosenScoreCards = scoreCards.generateBotScore();
        } else {
            chosenScoreCards = scoreCards.generateScore();
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
            int currentPlayerIndex = turnCount % playerAL.size();
            if (playerAL.get(currentPlayerIndex).getUserName().equals("Bot")) {
                bot.botTurn(playerAL.get(currentPlayerIndex), tiles);
            } else {
                turn.turnLoop(playerAL.get(currentPlayerIndex), tiles);
            }

            score.scorePlayer(playerAL.get(currentPlayerIndex), chosenScoreCards);
            System.out.println(playerAL.get(currentPlayerIndex).getUserName() + "'s current score is: " + playerAL.get(currentPlayerIndex).getScore());
            System.out.println("\n\n");

            turnCount++;
        }

        System.out.println("Game Ends!");

        // calculating and scoring the largest habitat corridor(s)
        int maxHabs = 0;
        int secondMaxHabs = 0;
        Player maxHabPlayer = playerAL.get(0);
        Player secondMaxHabPlayer = playerAL.get(0);

        for (Player currentPlayer : playerAL) {
            // adds points for nature token count
            currentPlayer.addScore(currentPlayer.getNatureTokens());

            int habCount = habitatMajorities.findHabitatMajority(currentPlayer);
            currentPlayer.addScore(habCount);
            if (habCount >= maxHabs) {
                secondMaxHabPlayer = maxHabPlayer;
                secondMaxHabs = maxHabs;

                maxHabs = habCount;
                maxHabPlayer = currentPlayer;
            }
        }

        switch (playerAL.size()) {
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

        for (Player currentPlayer : playerAL) {
            System.out.println("Player " + currentPlayer.getUserName() + "'s map:");
            currentPlayer.printMap(currentPlayer);
            score.scorePlayer(currentPlayer, chosenScoreCards);
            System.out.print("Player " + currentPlayer.getUserName() + "'s score: ");
            System.out.println(currentPlayer.getScore());
        }

        System.out.println("\n\n-----------------------------------------------------------\n\n");
        
        winnerDisplay(playerAL);
    }

    private static void winnerDisplay (ArrayList<Player> playerAL) {
        //Set the winner as the first person in the player array
        int winScore = 0;
        int winToken = 0;
        String winPlayer = "";
        //loops through list of players
        for (int i = 0; i < playerAL.size(); i++) {
            //compares the score of a player to another and updates winner if a larger score is found
            if(playerAL.get(i).getScore() > winScore) {
                winScore = playerAL.get(i).getScore();
                winPlayer = playerAL.get(i).getUserName();
                winToken = playerAL.get(i).getNatureTokens();
            } else if (playerAL.get(i).getScore() == winScore) {
                //in the case of a draw it compares the nature tokens
                if (playerAL.get(i).getNatureTokens() > winToken) {
                    winPlayer = playerAL.get(i).getUserName();
                    winToken = playerAL.get(i).getNatureTokens();
                } else if (playerAL.get(i).getNatureTokens() == winToken) {
                    winPlayer += " + " + playerAL.get(i).getUserName();
                }
            }
        }
        System.out.println("The winner is " + winPlayer + " with a score of " + winScore + "!");
    }
}