import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HabitatTiles {
    private String northWest, northEast, west, east, southWest, southEast;

    private String creature1, creature2, creature3;
    ArrayList<HabitatTiles> habitatArray;

    public void habitatSetup() {
        habitatArray = new ArrayList<>();
        habitatArray = generateHabitats();
        shuffleHabitats(habitatArray);
    }

    public ArrayList<HabitatTiles> getHabitatArray() {
        return habitatArray;
    }

    // Class that stores the six "sides" of each habitat and up to three creatures
    public HabitatTiles(String northWest, String northEast, String west, String east, String southWest, String southEast, String creature1, String creature2, String creature3) {
        this.northWest = northWest;
        this.northEast = northEast;
        this.west = west;
        this.east = east;
        this.southWest = southWest;
        this.southEast = southEast;
        this.creature1 = creature1;
        this.creature2 = creature2;
        this.creature3 = creature3;
    }

    // Method that converts each tile type into a coloured ascii representation of the tile
    public String terrainToAscii (String input) {
        switch (input) {
            case "forest":
                return "\u001b[42m" + "        " + "\u001b[0m";

            case "wetland":
                return "\u001b[102m" + "        " + "\u001b[0m";

            case "river":
                return "\u001b[44m" + "        " + "\u001b[0m";

            case "mountain":
                return "\u001b[100m" + "        " + "\u001b[0m";

            case "prairie":
                return "\u001b[43m" + "        " + "\u001b[0m";

            default: throw new IllegalArgumentException("Invalid terrain passed to terrainToAscii: " + input);
        }
    }

    // Method that converts each animal type into a coloured representation of the animal tile
    public String animalToString (String input) {
        input = input.toLowerCase();
        switch (input) {
            case "hawk":
                return "\u001b[34m" + "Hawk    " + "\u001b[0m";

            case "bear":
                return "\u001b[33m" + "Bear    " + "\u001b[0m";

            case "elk":
                return "\u001b[30m" + "Elk     " + "\u001b[0m";

            case "salmon":
                return "\u001b[95m" + "Salmon  " + "\u001b[0m";

            case "fox":
                return "\u001b[31m" + "Fox     " + "\u001b[0m";

            case "":
                return "        ";

            default: throw new IllegalArgumentException("Invalid animal passed to animalToAscii: " + input);
        }
    }

    // Method to rotate a habitat tile clockwise
    public void rotateTile(int rotations) {
        for (int i=0; i<rotations; i++) {
            String temp = this.northWest;
            this.northWest = this.northEast;
            this.northEast = this.east;
            this.east = this.southEast;
            this.southEast = this.southWest;
            this.southWest = this.west;
            this.west = temp;
        }
    }

    private ArrayList<HabitatTiles> generateHabitats() {
        ArrayList<HabitatTiles> output = new ArrayList<>();
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                output.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", generateHabitatHelper(j), "", ""));
                output.add(new HabitatTiles("wetland", "wetland", "wetland", "wetland", "wetland", "wetland", generateHabitatHelper(j), "", ""));
                output.add(new HabitatTiles("river", "river", "river", "river", "river", "river", generateHabitatHelper(j), "", ""));
                output.add(new HabitatTiles("mountain", "mountain", "mountain", "mountain", "mountain", "mountain", generateHabitatHelper(j), "", ""));
                output.add(new HabitatTiles("prairie", "prairie", "prairie", "prairie", "prairie", "prairie", generateHabitatHelper(j), "", ""));
            }
        }
        //output = randomiseHabitats(output);
        return output;
    }

    // helper function to turn an int into an animal string
    private String generateHabitatHelper(int num) {
        switch (num+1) {
            case 1: return "hawk";
            case 2: return "bear";
            case 3: return "elk";
            case 4: return "salmon";
            case 5: return "fox";

            default: throw new IllegalArgumentException("Invalid int passed to generateHabitatHelper: " + num);
        }
    }

    private void shuffleHabitats(ArrayList<HabitatTiles> al) {
        Collections.shuffle(al, new Random());
    }

    @Override
    public String toString() {
        return terrainToAscii(northWest) + terrainToAscii(northWest) + terrainToAscii(northEast) + terrainToAscii(northEast) + "\n" +
                terrainToAscii(west) + animalToString(creature1) + animalToString(creature2) + terrainToAscii(east) + "\n" +
                terrainToAscii(west) + animalToString(creature3) + "        " + terrainToAscii(east) + "\n" +
                terrainToAscii(southEast) + terrainToAscii(southEast) + terrainToAscii(southWest) + terrainToAscii(southWest) + "\n";
    }

    public String getNorthWest() {
        return northWest;
    }

    public String getNorthEast() {
        return northEast;
    }

    public String getWest() {
        return west;
    }

    public String getEast() {
        return east;
    }

    public String getSouthWest() {
        return southWest;
    }

    public String getSouthEast() {
        return southEast;
    }

    public String getCreature1() {
        return creature1;
    }

    public String getCreature2() {
        return creature2;
    }

    public String getCreature3() {
        return creature3;
    }
}
