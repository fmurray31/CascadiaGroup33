/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HabitatMajoritiesTest {
    HabitatMajorities habitatMajorities = new HabitatMajorities();

    @Test
    public void testFindHabitatMajority() {
        Player player = new Player("testPlayer");

        player.addHabitatToMap(new HabitatTiles("forest", "forest", "forest", "mountain", "mountain", "mountain", "", "", ""), 15, 15);
        player.addHabitatToMap(new HabitatTiles("mountain", "mountain", "mountain", "forest", "forest", "forest", "", "", ""), 15, 14);

        assertEquals(2, habitatMajorities.findHabitatMajority(player));

        player.addHabitatToMap(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "", "", ""), 16, 14);
        player.addHabitatToMap(new HabitatTiles("forest", "forest", "forest", "mountain", "mountain", "mountain", "", "", ""), 17, 15);

        assertEquals(4, habitatMajorities.findHabitatMajority(player));
    }

    @Test
    public void testFindHabitatMajorityAdvanced() {
        Player player = new Player("testPlayer");

        player.addHabitatToMap(new HabitatTiles("forest", "forest", "forest", "mountain", "mountain", "mountain", "", "", ""), 15, 15);
        player.addHabitatToMap(new HabitatTiles("mountain", "mountain", "mountain", "forest", "forest", "forest", "", "", ""), 15, 14);
        player.addHabitatToMap(new HabitatTiles("mountain", "mountain", "mountain", "mountain", "mountain", "mountain", "", "", ""), 15, 16);

        assertEquals(2, habitatMajorities.findHabitatMajority(player));

        player.addHabitatToMap(new HabitatTiles("mountain", "mountain", "mountain", "forest", "forest", "forest", "", "", ""), 15, 17);

        assertEquals(3, habitatMajorities.findHabitatMajority(player));

        player.addHabitatToMap(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "", "", ""), 16, 14);
        player.addHabitatToMap(new HabitatTiles("forest", "forest", "forest", "mountain", "mountain", "mountain", "", "", ""), 17, 15);

        assertEquals(4, habitatMajorities.findHabitatMajority(player));

        player.addHabitatToMap(new HabitatTiles("river", "river", "river", "river", "river", "river", "", "", ""), 19, 15);
        player.addHabitatToMap(new HabitatTiles("river", "river", "river", "river", "river", "river", "", "", ""), 19, 16);
        player.addHabitatToMap(new HabitatTiles("river", "river", "river", "river", "river", "river", "", "", ""), 19, 17);
        player.addHabitatToMap(new HabitatTiles("river", "river", "river", "river", "river", "river", "", "", ""), 19, 18);
        player.addHabitatToMap(new HabitatTiles("river", "river", "river", "river", "river", "river", "", "", ""), 19, 14);
        player.addHabitatToMap(new HabitatTiles("river", "river", "river", "river", "river", "river", "", "", ""), 19, 13);
        player.addHabitatToMap(new HabitatTiles("river", "river", "river", "river", "river", "river", "", "", ""), 18, 15);

        assertEquals(7, habitatMajorities.findHabitatMajority(player));
    }
}
