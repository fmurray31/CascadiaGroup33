import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    @Test
    public void botHawkTest() {
        Bot botClass = new Bot();
        Score score = new Score();
        Tiles tiles = new Tiles();
        Player bot = new Player("Bot");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("river", "river", "river", "river", "river", "river", "hawk", "fox", "elk"));
        }

        AnimalTiles testHawk = new AnimalTiles("Hawk");
        AnimalTiles testBear = new AnimalTiles("Bear");

        scoreCards.add(new ScoreCards("HawkA", "desc"));

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                bot.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        // catches and discards all system outputs, used to prevent unnecessary information from printing to console
        PrintStream tempOut = new PrintStream(OutputStream.nullOutputStream());
        System.setOut(tempOut);

        tiles.setupTiles();
        tiles.setupCentralTiles();
        tiles.centralAnimals.set(0, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(0, bot.getScore());

        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(2, bot.getScore());

        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(5, bot.getScore());

        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(8, bot.getScore());

        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(11, bot.getScore());

        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(14, bot.getScore());

        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(18, bot.getScore());

        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(22, bot.getScore());

        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(26, bot.getScore());

        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testBear);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(26, bot.getScore());
    }
}