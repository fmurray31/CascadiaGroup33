public class HabitatTiles {
    private String northWest;
    private String northEast;
    private String west;
    private String east;
    private String southWest;
    private String southEast;

    private String creature1;
    private String creature2;
    private String creature3;

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

    private String terrainToAscii (String input) {
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

            default: throw new IllegalArgumentException("Invalid terrain passed to terrainToAscii" + input);
        }
    }

    private String animalToString (String input) {
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

    @Override
    public String toString() {
        return terrainToAscii(northWest) + terrainToAscii(northWest) + terrainToAscii(northEast) + terrainToAscii(northEast) + "\n" +
                terrainToAscii(west) + animalToString(creature1) + animalToString(creature2) + terrainToAscii(east) + "\n" +
                terrainToAscii(west) + animalToString(creature3) + "        " + terrainToAscii(east) + "\n" +
                terrainToAscii(southEast) + terrainToAscii(southEast) + terrainToAscii(southWest) + terrainToAscii(southWest);

    }
}
