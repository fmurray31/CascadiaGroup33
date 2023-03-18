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
        // TODO: 18/03/2023 needs fixing
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        AnimalTiles testElk = new AnimalTiles("Elk");
        scoreCards.add(new ScoreCards("ElkA", "desc"));

        // places tiles in the player map
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        tiles.placeAnimal(player.getPlayerMap()[15][15], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][15], testElk);
        score.scorePlayer(player, scoreCards);
//        assertEquals(5, player.getScore());
//
//        printNeighbouringCoordinates(14, 15);
    }

    @Test
    public void testElkB() {
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        AnimalTiles testElk = new AnimalTiles("Elk");
        scoreCards.add(new ScoreCards("ElkB", "desc"));

        // places tiles in the player map
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        tiles.placeAnimal(player.getPlayerMap()[15][15], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[15][16], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(4, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[15][17], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(7, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][16], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(10, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][17], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(14, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][18], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(18, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[13][16], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(23, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[13][15], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(28, player.getScore());
    }

    @Test
    public void testElkC() {
        // TODO: 18/03/2023 needs fixing
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        AnimalTiles testElk = new AnimalTiles("Elk");
        scoreCards.add(new ScoreCards("ElkC", "desc"));

        // places tiles in the player map
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        tiles.placeAnimal(player.getPlayerMap()[15][15], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[15][16], testElk);
        score.scorePlayer(player, scoreCards);
        assertEquals(5, player.getScore());

//        player.resetScore();
//        tiles.placeAnimal(player.getPlayerMap()[14][16], testElk);
//        score.scorePlayer(player, scoreCards);
//        assertEquals(9, player.getScore());
    }

    @Test
    public void testHawkA() {
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "hawk"));
        }

        AnimalTiles testHawk = new AnimalTiles("Hawk");
        AnimalTiles testBear = new AnimalTiles("Bear");
        scoreCards.add(new ScoreCards("HawkA", "desc"));

        // places tiles in the player map
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        tiles.placeAnimal(player.getPlayerMap()[15][15], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[15][16], testBear);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[15][14], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[10][10], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[10][12], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(5, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[10][14], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(8, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[10][16], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(11, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[10][18], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(14, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[10][20], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(18, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[18][10], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(22, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[18][12], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(26, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[18][14], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(26, player.getScore());
    }

    @Test
    public void testHawkB() {
        // TODO: 18/03/2023 double check 
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "hawk"));
        }

        AnimalTiles testHawk = new AnimalTiles("Hawk");
        AnimalTiles testBear = new AnimalTiles("Bear");
        scoreCards.add(new ScoreCards("HawkB", "desc"));

        // places tiles in the player map
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        tiles.placeAnimal(player.getPlayerMap()[12][12], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[12][11], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[12][14], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][15], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(5, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][13], testBear);
        tiles.placeAnimal(player.getPlayerMap()[14][13], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(9, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[14][11], testHawk);
        tiles.placeAnimal(player.getPlayerMap()[16][11], testHawk);
        tiles.placeAnimal(player.getPlayerMap()[16][13], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(12, player.getScore());
    }

    @Test
    public void testHawkC() {
        // TODO: 18/03/2023 not working correctly
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "hawk"));
        }

        AnimalTiles testHawk = new AnimalTiles("Hawk");
        AnimalTiles testBear = new AnimalTiles("Bear");
        scoreCards.add(new ScoreCards("HawkC", "desc"));

        // places tiles in the player map
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        tiles.placeAnimal(player.getPlayerMap()[12][12], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[12][11], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(0, player.getScore());

        tiles.placeAnimal(player.getPlayerMap()[12][14], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(3, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[12][16], testHawk);
        score.scorePlayer(player, scoreCards);
        assertEquals(6, player.getScore());
    }

    @Test
    public void testSalmonA() {
        // TODO: 18/03/2023 not working
        Player player = new Player("testPlayer");
        List<ScoreCards> scoreCards = new ArrayList<>();

        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "salmon", "fox", "hawk"));
        }

        AnimalTiles testSalmon = new AnimalTiles("Salmon");
        scoreCards.add(new ScoreCards("SalmonA", "desc"));

        // places tiles in the player map
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                player.addHabitatToMap(habStack.pop(), i+10, j+10);
            }
        }

        tiles.placeAnimal(player.getPlayerMap()[12][12], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(2, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[12][13], testSalmon);
        score.scorePlayer(player, scoreCards);
        assertEquals(4, player.getScore());

        player.resetScore();
        tiles.placeAnimal(player.getPlayerMap()[12][14], testSalmon);
        player.printMap(player);
        score.scorePlayer(player, scoreCards);
        assertEquals(11, player.getScore());

        printNeighbouringCoordinates(12, 14);
    }

    @Test
    public void testSalmonB(){

    }

    @Test
    public void testSalmonC(){

    }





    // helper method to print all the neighbours of a given coordinate pair, helpful for constructing test cases
    private void printNeighbouringCoordinates (int a, int b) {
        System.out.println("w:  " + Arrays.toString(score.directionToLocation("w", a, b)));
        System.out.println("nw: " + Arrays.toString(score.directionToLocation("nw", a, b)));
        System.out.println("ne: " + Arrays.toString(score.directionToLocation("ne", a, b)));
        System.out.println("e:  " + Arrays.toString(score.directionToLocation("e", a, b)));
        System.out.println("se: " + Arrays.toString(score.directionToLocation("se", a, b)));
        System.out.println("sw: " + Arrays.toString(score.directionToLocation("sw", a, b)));
    }
}