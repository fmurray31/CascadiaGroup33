import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

        ArrayList<HabitatTiles> habArray = new ArrayList<>();
        Stack<HabitatTiles> habStack = new Stack<>();
        for (int i=0; i<100; i++) {
            habStack.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk"));
        }

        //HabitatTiles testHab = new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "bear", "fox", "elk");

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
    }
}