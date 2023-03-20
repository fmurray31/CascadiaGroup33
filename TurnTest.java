import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TurnTest {
    Turn turn = new Turn();

    @Test
    public void testAutomaticCull() {
        Tiles tiles = new Tiles();

        Player player = new Player("testPlayer");

        ArrayList<AnimalTiles> tempAnimals = new ArrayList<>();
        for (int i=0; i<4; i++) {
            tempAnimals.add(new AnimalTiles("Hawk"));
        }
        ArrayList<HabitatTiles> tempHabs = new ArrayList<>();
        for (int i=0; i<4;i++) {
            tempHabs.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "hawk", "", ""));
        }
        turn.tiles.setupTiles(2);
        turn.tiles.centralAnimals = tempAnimals;
        turn.tiles.centralHabitats = tempHabs;

        //turn.turnLoop(player, 2);
    }
}