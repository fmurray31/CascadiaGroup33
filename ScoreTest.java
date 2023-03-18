import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    Score score = new Score();
    Tiles tiles = new Tiles();

    @Test
    public void testBearA() {
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        AnimalTiles testBear = new AnimalTiles("Bear");
        scoreCards.add(new ScoreCards("BearA", "desc"));

        // places tiles in the player map
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[15][15], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[16][15], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(4, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][15], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[12][12], testBear);
        tiles.placeAnimal(player.getPlayerMap()[12][13], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(4, player.getScore());
    }

    @Test
    public void testBearB() {
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        AnimalTiles testBear = new AnimalTiles("Bear");
        scoreCards.add(new ScoreCards("BearB", "desc"));

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[15][15], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[15][16], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[16][15], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(10, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[16][16], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());
    }

    @Test
    public void testBearC() {
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        AnimalTiles testBear = new AnimalTiles("Bear");
        scoreCards.add(new ScoreCards("BearC", "desc"));

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[15][15], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[18][15], testBear);
        tiles.placeAnimal(player.getPlayerMap()[18][16], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(7, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[11][10], testBear);
        tiles.placeAnimal(player.getPlayerMap()[11][11], testBear);
        tiles.placeAnimal(player.getPlayerMap()[11][12], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(18, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[11][13], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(7, player.getScore());
    }

    @Test
    public void testFoxA() {
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        AnimalTiles testFox = new AnimalTiles("Fox");
        AnimalTiles testBear = new AnimalTiles("Bear");
        AnimalTiles testElk = new AnimalTiles("Elk");
        AnimalTiles testHawk = new AnimalTiles("Hawk");
        AnimalTiles testSalmon = new AnimalTiles("Salmon");

        scoreCards.add(new ScoreCards("FoxA", "desc"));

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[15][15], testFox);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[15][16], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(1, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[15][14], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(1, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][14], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][15], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(3, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[16][15], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(4, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[16][14], testFox);
        score.scorePlayer(player, scoreCards);
        assertEquals(8, player.getScore());
    }

    @Test
    public void foxTestB() {
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        AnimalTiles testFox = new AnimalTiles("Fox");
        AnimalTiles testBear = new AnimalTiles("Bear");
        AnimalTiles testElk = new AnimalTiles("Elk");
        AnimalTiles testHawk = new AnimalTiles("Hawk");
        AnimalTiles testSalmon = new AnimalTiles("Salmon");

        scoreCards.add(new ScoreCards("FoxB", "desc"));

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        tiles.placeAnimal(player.getPlayerMap()[11][11], testFox);
        tiles.placeAnimal(player.getPlayerMap()[11][12], testFox);
        tiles.placeAnimal(player.getPlayerMap()[12][11], testFox);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[15][15], testFox);
        tiles.placeAnimal(player.getPlayerMap()[15][16], testBear);
        tiles.placeAnimal(player.getPlayerMap()[15][14], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(3, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][14], testElk);
        tiles.placeAnimal(player.getPlayerMap()[14][15], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(5, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[16][15], testHawk);
        tiles.placeAnimal(player.getPlayerMap()[16][14], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(7, player.getScore());


        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[18][17], testFox);
        tiles.placeAnimal(player.getPlayerMap()[18][18], testSalmon);
        tiles.placeAnimal(player.getPlayerMap()[18][16], testSalmon);
        tiles.placeAnimal(player.getPlayerMap()[17][18], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(10, player.getScore());
    }

    @Test
    public void testFoxC() {
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        AnimalTiles testFox = new AnimalTiles("Fox");
        AnimalTiles testBear = new AnimalTiles("Bear");
        AnimalTiles testElk = new AnimalTiles("Elk");
        AnimalTiles testHawk = new AnimalTiles("Hawk");
        AnimalTiles testSalmon = new AnimalTiles("Salmon");

        scoreCards.add(new ScoreCards("FoxC", "desc"));

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        tiles.placeAnimal(player.getPlayerMap()[15][15], testFox);
        tiles.placeAnimal(player.getPlayerMap()[15][14], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(1, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[15][16], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][14], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(3, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][15], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(4, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[16][14], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(5, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[16][15], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(6, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[11][11], testFox);
        tiles.placeAnimal(player.getPlayerMap()[11][10], testFox);
        tiles.placeAnimal(player.getPlayerMap()[11][12], testFox);
        score.scorePlayer(player, scoreCards);
        assertEquals(6, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[18][18], testFox);
        tiles.placeAnimal(player.getPlayerMap()[18][17], testBear);
        tiles.placeAnimal(player.getPlayerMap()[18][19], testElk);
        tiles.placeAnimal(player.getPlayerMap()[19][19], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(8, player.getScore());
    }

    @Test
    public void elkTestA() {

    }





    // helper method to print all the neighbours of a given coordinate pair, helpful for constructing test cases
    private void printNeighbouringCoordinates (int a, int b) {
        System.out.println(Arrays.toString(score.directionToLocation("w", a, b)));
        System.out.println(Arrays.toString(score.directionToLocation("nw", a, b)));
        System.out.println(Arrays.toString(score.directionToLocation("ne", a, b)));
        System.out.println(Arrays.toString(score.directionToLocation("e", a, b)));
        System.out.println(Arrays.toString(score.directionToLocation("se", a, b)));
        System.out.println(Arrays.toString(score.directionToLocation("sw", a, b)));
    }
}