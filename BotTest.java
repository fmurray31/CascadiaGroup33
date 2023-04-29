import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    @Test
    public void botRotationTest() {
        Bot botClass = new Bot();
        Tiles tiles = new Tiles();
        Player bot = new Player("Bot");

        HabitatTiles hab1 = new HabitatTiles("river", "river", "river", "forest", "forest", "forest","hawk", "fox", "elk");
        HabitatTiles hab2 = new HabitatTiles("river", "river", "river", "mountain", "mountain", "mountain","hawk", "fox", "elk");
        HabitatTiles hab3 = new HabitatTiles("forest", "forest", "forest", "mountain", "mountain", "mountain","hawk", "bear", "elk");

        HabitatTiles hab4 = new HabitatTiles("forest", "forest", "forest", "mountain", "mountain", "mountain","hawk", "bear", "elk");

        AnimalTiles testHawk = new AnimalTiles("Hawk");
        AnimalTiles testBear = new AnimalTiles("Bear");

        bot.addHabitatToMap(hab1, 15, 15);
        bot.addHabitatToMap(hab2, 16, 15);
        bot.addHabitatToMap(hab3, 16, 14);

        // catches and discards all system outputs, used to prevent unnecessary information from printing to console
//        PrintStream tempOut = new PrintStream(OutputStream.nullOutputStream());
//        System.setOut(tempOut);

        tiles.setupTiles();
        tiles.setupCentralTiles();
        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testHawk);
        tiles.centralAnimals.set(2, testBear);
        tiles.centralAnimals.set(3, testBear);
        tiles.centralHabitats.set(0, hab4);
        botClass.botTurn(bot, tiles);

        // tests that the tile has successfully been rotated
        assertEquals("forest", bot.getPlayerMap()[15][14].getEast());
    }
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
        AnimalTiles testElk = new AnimalTiles("Elk");

        scoreCards.add(new ScoreCards("HawkA", "desc"));

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                bot.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        PrintStream tempOut = new PrintStream(OutputStream.nullOutputStream());
        System.setOut(tempOut);

        tiles.setupTiles();
        tiles.setupCentralTiles();
        tiles.centralAnimals.set(0, testBear);
        tiles.centralAnimals.set(1, testBear);
        tiles.centralAnimals.set(2, testElk);
        tiles.centralAnimals.set(3, testElk);
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

    @Test
    public void botSalmon() {
        Bot botClass = new Bot();
        Score score = new Score();
        Tiles tiles = new Tiles();
        Player bot = new Player("Bot");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("river", "river", "river", "forest", "forest", "forest", "hawk", "salmon", "fox"));
        }

        AnimalTiles testHawk = new AnimalTiles("Hawk");
        AnimalTiles testSalmon = new AnimalTiles("Salmon");
        AnimalTiles testFox = new AnimalTiles("Fox");

        scoreCards.add(new ScoreCards("SalmonA", "desc"));

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                bot.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        PrintStream tempOut = new PrintStream(OutputStream.nullOutputStream());
        System.setOut(tempOut);

        tiles.setupTiles();
        tiles.setupCentralTiles();

        setCentralHabs(tiles, "bear");
        tiles.centralAnimals.set(0, testSalmon);
        tiles.centralAnimals.set(1, testSalmon);
        tiles.centralAnimals.set(2, testFox);
        tiles.centralAnimals.set(3, testFox);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(2, bot.getScore());

        setCentralHabs(tiles, "bear");
        tiles.centralAnimals.set(0, testHawk);
        tiles.centralAnimals.set(1, testSalmon);
        tiles.centralAnimals.set(2, testFox);
        tiles.centralAnimals.set(3, testFox);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(2, bot.getScore());

        setCentralHabs(tiles, "bear");
        tiles.centralAnimals.set(0, testSalmon);
        tiles.centralAnimals.set(1, testSalmon);
        tiles.centralAnimals.set(2, testFox);
        tiles.centralAnimals.set(3, testFox);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(4, bot.getScore());

        setCentralHabs(tiles, "bear");
        tiles.centralAnimals.set(0, testSalmon);
        tiles.centralAnimals.set(1, testSalmon);
        tiles.centralAnimals.set(2, testFox);
        tiles.centralAnimals.set(3, testFox);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(7, bot.getScore());

        setCentralHabs(tiles, "bear");
        tiles.centralAnimals.set(0, testSalmon);
        tiles.centralAnimals.set(1, testSalmon);
        tiles.centralAnimals.set(2, testFox);
        tiles.centralAnimals.set(3, testFox);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(11, bot.getScore());

        setCentralHabs(tiles, "bear");
        tiles.centralAnimals.set(0, testSalmon);
        tiles.centralAnimals.set(1, testSalmon);
        tiles.centralAnimals.set(2, testFox);
        tiles.centralAnimals.set(3, testFox);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(15, bot.getScore());

        setCentralHabs(tiles, "bear");
        tiles.centralAnimals.set(0, testSalmon);
        tiles.centralAnimals.set(1, testSalmon);
        tiles.centralAnimals.set(2, testFox);
        tiles.centralAnimals.set(3, testFox);
        botClass.botTurn(bot, tiles);
        score.scorePlayer(bot, scoreCards);
        assertEquals(20, bot.getScore());
    }

    // helper method used to set all central tiles to a given animal, used to prevent unexpected outcomes when testing
    private void setCentralHabs (Tiles tiles, String animal) {
        for (int i=0; i<4; i++) {
            tiles.centralHabitats.set(i, new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", animal, "", ""));
        }
    }
}
