/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

class TurnTest {
    // used to catch output for testing
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    Turn turn = new Turn();

    @Test
    public void testAutomaticCull() {
        turn.tiles = new Tiles();
        ArrayList<AnimalTiles> tempAnimals = new ArrayList<>();
        for (int i=0; i<4; i++) {
            tempAnimals.add(new AnimalTiles("Hawk"));
        }

        ArrayList<HabitatTiles> tempHabs = new ArrayList<>();
        for (int i=0; i<4;i++) {
            tempHabs.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "hawk", "", ""));
        }

        turn.tiles.setupTiles();
        turn.tiles.centralAnimals = tempAnimals;
        turn.tiles.centralHabitats = tempHabs;

        Object[] output = turn.tiles.optionalCull(turn.tiles.centralAnimals);

        AnimalTiles checkHawk = new AnimalTiles("Hawk");
        assertEquals(output[0], checkHawk);
        assertEquals(output[1], 4);

        turn.cullHelper(output);
        assertEquals("All animal tiles the same, initiating automatic cull", outputStreamCaptor.toString().trim());
    }

    @Test
    public void optionalCull() {
        turn.tiles = new Tiles();
        ArrayList<AnimalTiles> tempAnimals = new ArrayList<>();
        for (int i=0; i<3; i++) {
            tempAnimals.add(new AnimalTiles("Hawk"));
        }
        tempAnimals.add(new AnimalTiles("Elk"));

        ArrayList<HabitatTiles> tempHabs = new ArrayList<>();
        for (int i=0; i<4;i++) {
            tempHabs.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "hawk", "", ""));
        }

        turn.tiles.setupTiles();
        turn.tiles.centralAnimals = tempAnimals;
        turn.tiles.centralHabitats = tempHabs;

        Object[] output = turn.tiles.optionalCull(turn.tiles.centralAnimals);

        AnimalTiles checkHawk = new AnimalTiles("Hawk");
        assertEquals(output[0], checkHawk);
        assertEquals(output[1], 3);
    }
}