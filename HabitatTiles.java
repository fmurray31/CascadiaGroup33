/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HabitatTiles {
    private String northWest, northEast, west, east, southWest, southEast;
    private boolean occupied;

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

    // object that stores the six "sides" of each habitat and up to three creatures, as well as a boolean which tracks whether a tile is "occupied",
    // which is initialised to false
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
        this.occupied = false;
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

            case "blank":
                return "        ";

            default: throw new IllegalArgumentException("Invalid terrain passed to terrainToAscii: " + input);
        }
    }

    // Method that converts each animal type into a coloured representation of the animal tile
    public String animalToAscii (String input) {
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

    // Method to rotate a habitat tile 60 degrees clockwise
    public void rotateTile(int rotations) {
        for (int i=0; i<rotations; i++) {
            String temp = this.northWest;
            this.northWest = this.west;
            this.west = this.southWest;
            this.southWest = this.southEast;
            this.southEast = this.east;
            this.east = this.northEast;
            this.northEast = temp;
        }
    }

    // creates an arraylist of habitats
    private ArrayList<HabitatTiles> generateHabitats() {
        ArrayList<HabitatTiles> output = new ArrayList<>();

        //5 Habitat
        for (int i = 1; i <= 5; i++) {
            for (int j = i; j <= 5; j++) {
                output.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", generateHabitatHelper(j), "", ""));
                output.add(new HabitatTiles("wetland", "wetland", "wetland", "wetland", "wetland", "wetland", generateHabitatHelper(j), "", ""));
                output.add(new HabitatTiles("river", "river", "river", "river", "river", "river", generateHabitatHelper(j), "", ""));
                output.add(new HabitatTiles("mountain", "mountain", "mountain", "mountain", "mountain", "mountain", generateHabitatHelper(j), "", ""));
                output.add(new HabitatTiles("prairie", "prairie", "prairie", "prairie", "prairie", "prairie", generateHabitatHelper(j), "", ""));
            }
        }

        //Combination tiles with 2 animals
        for (int i = 1; i <= 5; i++) {
            for(int j = i + 1; j <= 5; j++) {
                output.add(new HabitatTiles("forest", "forest", "wetland", "forest", "wetland", "wetland", generateHabitatHelper(i), generateHabitatHelper(j), ""));
                output.add(new HabitatTiles("forest", "forest", "river", "forest", "river", "river", generateHabitatHelper(i), generateHabitatHelper(j), ""));
                output.add(new HabitatTiles("forest", "forest", "mountain", "forest", "mountain", "mountain", generateHabitatHelper(i), generateHabitatHelper(j), ""));
                output.add(new HabitatTiles("forest", "forest", "prairie", "forest", "prairie", "prairie", generateHabitatHelper(i), generateHabitatHelper(j), ""));
                output.add(new HabitatTiles("river", "river", "wetland", "river", "wetland", "wetland", generateHabitatHelper(i), generateHabitatHelper(j), ""));
                output.add(new HabitatTiles("river", "river", "mountain", "river", "mountain", "mountain", generateHabitatHelper(i), generateHabitatHelper(j), ""));
                output.add(new HabitatTiles("river", "river", "prairie", "river", "prairie", "prairie", generateHabitatHelper(i), generateHabitatHelper(j), ""));
                output.add(new HabitatTiles("wetland", "wetland", "mountain", "wetland", "mountain", "mountain", generateHabitatHelper(i), generateHabitatHelper(j), ""));
                output.add(new HabitatTiles("wetland", "wetland", "prairie", "wetland", "prairie", "prairie", generateHabitatHelper(i), generateHabitatHelper(j), ""));
                output.add(new HabitatTiles("mountain", "mountain", "prairie", "mountain", "prairie", "prairie", generateHabitatHelper(i), generateHabitatHelper(j), ""));
            }
        }

        //Combination tiles with 3 animals
        for (int i = 1; i <= 5; i++) {
            for(int j = i + 1; j <= 5; j++) {
                for(int k = j + 1; k <= 5; k++) {
                    output.add(new HabitatTiles("forest", "forest", "wetland", "forest", "wetland", "wetland", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                    output.add(new HabitatTiles("forest", "forest", "river", "forest", "river", "river", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                    output.add(new HabitatTiles("forest", "forest", "mountain", "forest", "mountain", "mountain", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                    output.add(new HabitatTiles("forest", "forest", "prairie", "forest", "prairie", "prairie", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                    output.add(new HabitatTiles("river", "river", "wetland", "river", "wetland", "wetland", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                    output.add(new HabitatTiles("river", "river", "mountain", "river", "mountain", "mountain", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                    output.add(new HabitatTiles("river", "river", "prairie", "river", "prairie", "prairie", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                    output.add(new HabitatTiles("wetland", "wetland", "mountain", "wetland", "mountain", "mountain", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                    output.add(new HabitatTiles("wetland", "wetland", "prairie", "wetland", "prairie", "prairie", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                    output.add(new HabitatTiles("mountain", "mountain", "prairie", "mountain", "prairie", "prairie", generateHabitatHelper(i), generateHabitatHelper(j), generateHabitatHelper(k)));
                }
            }
        }
        
        return output;
    }

    // helper function to turn an int into an animal string, populates habitats with animals
    private String generateHabitatHelper(int num) {
        switch (num) {
            case 1: return "hawk";
            case 2: return "bear";
            case 3: return "elk";
            case 4: return "salmon";
            case 5: return "fox";

            default: throw new IllegalArgumentException("Invalid int passed to generateHabitatHelper: " + num);
        }
    }

    // uses collections to randomise the order of the arraylist
    private void shuffleHabitats(ArrayList<HabitatTiles> al) {
        Collections.shuffle(al, new Random());
    }

    // toString method to print a single habitat tile with correct formatting and ascii representation
    @Override
    public String toString() {
        return terrainToAscii(northWest) + terrainToAscii(northWest) + terrainToAscii(northEast) + terrainToAscii(northEast) + "\n" +
                terrainToAscii(west) + animalToAscii(creature1) + animalToAscii(creature2) + terrainToAscii(east) + "\n" +
                terrainToAscii(west) + animalToAscii(creature3) + "        " + terrainToAscii(east) + "\n" +
                terrainToAscii(southWest) + terrainToAscii(southWest) + terrainToAscii(southEast) + terrainToAscii(southEast) + "\n";
    }

    // getters for all elements in a habitat tile
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


    public void setCreature1(String creature1) {
        this.creature1 = creature1;
    }
    public void setCreature2(String creature2) {
        this.creature2 = creature2;
    }
    public void setCreature3(String creature3) {
        this.creature3 = creature3;
    }

    // used to switch a tile to occupied
    public void setOccupied() {
        this.occupied = true;
    }

    // returns true if the passed habitat coordinate is blank
    public boolean isBlankHabitat(Player player, int row, int column) {
        return (player.getPlayerMap()[row][column].getNorthWest().equals("blank"));
    }

    // returns true if all 6 tiles surrounding the given tile coordinates are blank
    public boolean isIsolated(Player player, int row, int column) {
        boolean output;
        output = isBlankHabitat(player, row, column+1) && isBlankHabitat(player, row, column-1);
        output = output && isBlankHabitat(player, row+1, column) && isBlankHabitat(player, row-1, column);

        if (row%2 == 0) {
            output = output && isBlankHabitat(player, row+1, column+1) && isBlankHabitat(player, row-1, column+1);
        } else {
            output = output && isBlankHabitat(player, row+1, column-1) && isBlankHabitat(player, row-1, column-1);
        }
        return output;
    }

    // returns occupied status of a habitat tile
    public boolean isOccupied () {
        return this.occupied;
    }

    // return true if a habitat tile is a keystone (all the same terrain)
    public boolean isKeystone () {
        return this.getNorthWest().equals(this.getNorthEast()) && this.getEast().equals(this.getWest()) && this.getSouthWest().equals(this.getSouthEast());
    }

    // method which adds a white background to a string, used to display occupied habitat tiles
    public String occupiedBackground () {
        if (this.isOccupied()) {
            return "\u001b[47m";
        } else {
            return "";
        }
    }
}
