import java.util.*;

public class Bot {
    Score score = new Score();
    HabitatMajorities habitatMajorities = new HabitatMajorities();
    Tiles tiles;
    Player bot;
    Random rand = new Random();

    // Arraylists with class scope
    // arraylist for all possible tile coordinates that neighbour already placed tiles
    private ArrayList<int[]> possibleTileLocations;

    // Arraylists containing every "placeable" animal and the matching coordinates of their locations respectively
    // Designed so that possibleAnimals.get(x) matches the location possibleAnimalLocations.get(x)
    private ArrayList<String> possibleAnimals;
    private ArrayList<int[]> possibleAnimalLocations;

    // arraylist for the names of the central animal tiles
    private ArrayList<String> centralAnimalAL;

    // arraylists for the possible animals that can be placed on the central habitat tiles, and an arraylist of the matching coordinates
    ArrayList<String> centralHabitatAnimals;
    ArrayList<Integer> centralHabitatAnimalsCoordinates;

    // class scope variables to hold the chosen animal and habitat tiles respectively
    private AnimalTiles selectedAnimal;
    private HabitatTiles selectedHabitat;

    // static trackers for number of placed animals
    private static int placedHawkCount = 0;
    private static int placedBearPairCount = 0;

    public void botTurn (Player bot, Tiles tiles) {
        this.tiles = tiles;
        this.bot = bot;
        this.score.setPlayer(bot);
        habitatMajorities.player = bot;

        System.out.println("Bots' turn!");

        // sets up tiles on the first loop
        if (tiles.centralAnimals == null) {
            tiles.setupTiles();
            tiles.setupCentralTiles();
        }

        botCull();

        // Initialising class scope arraylists
        possibleAnimals = new ArrayList<>();
        possibleAnimalLocations = new ArrayList<>();

        possibleTileLocations = new ArrayList<>();

        centralAnimalAL = new ArrayList<>();

        centralHabitatAnimals = new ArrayList<>();
        centralHabitatAnimalsCoordinates = new ArrayList<>();

        // methods to fill arraylists
        botCentralAnimalsFetcher();
        botCentralHabitatAnimalFetcher();
        botLocationFetcher();


        // bot logic
        botTileChoice();


        System.out.println("Bots' map:");
        bot.printMap();
        System.out.println("");

        tiles.drawCentralTiles();
    }

    // method to handle choice between different habitat and animal tiles from the central pool, returns an integer representing the choice of animal, and calls
    // the tile placement method. Currently just chooses the first tile pair
    private void botTileChoice () {
        boolean placed = false;

        // nature token implementation
        if (bot.getNatureTokens() > 0) {
            botNatureToken();
        }

        // attempts to place each of the animal types in the below order, if it fails then a method to handle a default case is needed
        if (centralAnimalAL.contains("Hawk")) {
            placed = botTileHelper("Hawk");
        }
        if (centralAnimalAL.contains("Bear") && !placed) {
            placed = botTileHelper("Bear");
        }
        if (centralAnimalAL.contains("Elk") && !placed) {
            placed = botTileHelper("Elk");
        }
        if (centralAnimalAL.contains("Salmon") && !placed) {
            placed = botTileHelper("Salmon");
        }
        if (centralAnimalAL.contains("Fox") && !placed) {
            placed = botTileHelper("Fox");
        }
        // if an animal tile cannot be placed
        if (!placed) botDefaultAnimalPlacement();

        botHabitatPlacement();

        // habitat tile removed from central tiles here
        tiles.centralHabitats.remove(selectedHabitat);
        tiles.centralAnimals.remove(selectedAnimal);
    }

    // returns true if an animal tile is successfully placed, otherwise returns false
    private boolean botTileHelper (String name) {
        int index;
        boolean output = false;

        if (possibleAnimals.contains(name.toLowerCase())) {
            index = centralAnimalAL.indexOf(name);
            selectedAnimal = tiles.centralAnimals.get(index);
            selectedHabitat = tiles.centralHabitats.get(index);

            switch (name) {
                case "Hawk": output = botHawk(); break;
                case "Bear": output = botBear(); break;
                case "Elk": output = botElk(); break;
                case "Salmon": output = botSalmon(); break;
                case "Fox": output = botFox(); break;
            }
        }
        return output;
    }

    // handles the logic for bot nature token use
    private void botNatureToken() {
        boolean shuffled = false;
        StringBuilder output = new StringBuilder("Bot is shuffling ");

        if (placedHawkCount >= 8) {
            tiles.redrawAnimals(new AnimalTiles("Hawk"));
            output.append("all Hawk tiles, ");
            shuffled = true;
        }

        if (placedBearPairCount >= 4) {
            tiles.redrawAnimals(new AnimalTiles("Bear"));
            output.append("all Bear tiles, ");
            shuffled = true;
        }

        if(shuffled) {
            output.deleteCharAt(output.length());
            System.out.println(output);
            bot.removeNatureToken();
        }

        if (shuffled) {
            bot.removeNatureToken();
        }
    }

    // attempts to place a tile optimally in the below order, and if it fails places it randomly
    private void botHabitatPlacement () {
        ArrayList<String> possibleAnimals = habitatContains(selectedHabitat);

        boolean placed = false;

        // places bear tiles next to other bear tiles
        // TODO: 09/04/2023 could be expanded with improved functionality, currently only using very basic logic
        if (possibleAnimals.contains("bear")) {
            for (int[] tileCoord : possibleTileLocations) {
                if (adjacentCheck(tileCoord, "bear")) {
                    botHabitatRotation(tileCoord[0], tileCoord[1]);
                    bot.addHabitatToMap(selectedHabitat, tileCoord[0], tileCoord[1]);
                    placed = true;
                    break;
                }
            }
        }

        if (possibleAnimals.contains("Hawk") && !placed) {
            for (int[] tileCoord : possibleTileLocations) {
                if (!adjacentCheck(tileCoord, "hawk")) {
                    botHabitatRotation(tileCoord[0], tileCoord[1]);
                    bot.addHabitatToMap(selectedHabitat, tileCoord[0], tileCoord[1]);
                    placed = true;
                    break;
                }
            }
        }

        // TODO: Handle cases where there is an elk above or below the elkline
        if (possibleAnimals.contains("Elk") && !placed) {
            for (int[] tileCoord : possibleTileLocations) {
                if (adjacentCheck(tileCoord, "elk")) {
                    int [] elkLine = score.directionToLocation("w",tileCoord[0], tileCoord[1]);
                    //check if elkline coords has an empty space
                    if (bot.getPlayerMap()[elkLine[0]][elkLine[1]] == null) {
                        botHabitatRotation(tileCoord[0], tileCoord[1]);
                        bot.addHabitatToMap(selectedHabitat, tileCoord[0], tileCoord[1]);
                        placed = true;
                        break;
                    } else {
                        elkLine = score.directionToLocation("e",tileCoord[0], tileCoord[1]);
                        if (bot.getPlayerMap()[elkLine[0]][elkLine[1]] == null) {
                            botHabitatRotation(tileCoord[0], tileCoord[1]);
                            bot.addHabitatToMap(selectedHabitat, tileCoord[0], tileCoord[1]);
                            placed = true;
                            break;
                        }
                    }
                }
            }
        }

        if (possibleAnimals.contains("Salmon") && !placed) {
            botDefaultHabPlacement();
        }

        if (possibleAnimals.contains("Fox") && !placed) {
            for (int[] tileCoord : possibleTileLocations) {
                if (adjacentCheck(tileCoord, "fox")) {
                    botHabitatRotation(tileCoord[0], tileCoord[1]);
                    bot.addHabitatToMap(selectedHabitat, tileCoord[0], tileCoord[1]);
                    placed = true;
                    break;
                }
            }
        }

        // if tile still hasn't been placed, place it randomly
        if (!placed) botDefaultHabPlacement();
    }

    // places a habitat tile in a way that maximises habitat corridors
    private void botHabitatRotation (int i, int j) {
        String[] sides = new String[]{"w", "nw", "ne", "e", "se", "sw"};

        for (String side : sides) {
            if (bot.getPlayerMap()[i][j].getWest().equals(habitatMajorities.adjacentSide(i, j, side))) {
                habitatRotationHelper("w", side);
            } else if (bot.getPlayerMap()[i][j].getEast().equals(habitatMajorities.adjacentSide(i, j, side))) {
                habitatRotationHelper("e", side);
            }
        }
    }

    private void habitatRotationHelper (String current, String opposite) {
        int rotations = 0;
        if (current.equals("w")) {
            switch (opposite) {
                case "w": rotations = 3; break;
                case "nw": rotations = 4; break;
                case "ne": rotations = 5; break;
                case "e": rotations = 0; break;
                case "se": rotations = 1; break;
                case "sw": rotations = 2; break;
            }
        } else {
            switch (opposite) {
                case "w": rotations = 0; break;
                case "nw": rotations = 1; break;
                case "ne": rotations = 2; break;
                case "e": rotations = 3; break;
                case "se": rotations = 4; break;
                case "sw": rotations = 5; break;
            }
        }
        selectedHabitat.rotateTile(rotations);
    }

    // methods which handle the placement of each of the animal types, in a valid and point scoring manner. Each returns true if it successfully places the tile, false otherwise
    private boolean botBear () {
        int[][] tempArray = new int[bot.getMaxMap()][bot.getMaxMap()];
        int bearIndex = possibleAnimals.indexOf("bear");
        int[] bearCoords;

        do {
            bearCoords = possibleAnimalLocations.get(bearIndex);
            int[] tempCoords = score.adjacentAnimal("Bear", tempArray, bearCoords[0], bearCoords[1]);

            // finds already placed bear
            if (tempCoords[0] != -1) {
                // check if already placed bear is part of a pair or not
                if (score.adjacentAnimal("Bear", tempArray, tempCoords[0], tempCoords[1])[0] == -1) {
                    // bear placement

                    // a pair has been placed, so pairCount++
                    placedBearPairCount++;
                    return true;
                }
            }

            bearIndex = getNext(possibleAnimals, bearIndex);
        } while (bearIndex != -1);

        // need second case then for if place beside already placed bear

        return false;
    }

    private boolean botFox () {


        System.out.println("Bot placed a Fox");
        return true;
    }

    private boolean botElk () {
        int[][] tempArray = new int[bot.getMaxMap()][bot.getMaxMap()];
        int elkIndex = possibleAnimals.indexOf("elk");
        int[] elkCoords;

        int[] output;
        int[][] coordinates = new int[bot.getMaxMap()][bot.getMaxMap()];

        for (int i=0; i< bot.getMaxMap(); i++) {
            for (int j = 0; j < bot.getMaxMap(); j++) {
                if (bot.getPlayerMap()[i][j].isOccupied() && bot.getPlayerMap()[i][j].getCreature1().equals("Elk") && coordinates[i][j] == 0) {
                    coordinates[i][j] = 1;
                    output = score.adjacentAnimal("Elk", coordinates, i, j);
                    String direction;
                    int temp;
                    int maxCount = 1;
                    String maxDirection = "";

                    while (output[0] != -1) {
                        direction = score.adjacentToDirection(i, j, output);
                        coordinates[output[0]][output[1]] = 1;

                        temp = 1;

                        if (direction.equals("w") || direction.equals("e")) {
                            temp += score.searchLineCount("Elk", "w", coordinates, output[0], output[1]) + 1;
                            temp += score.searchLineCount("Elk", "e", coordinates, output[0], output[1]);
                        }

                        if (direction.equals("nw") || direction.equals("se")) {
                            temp += score.searchLineCount("Elk", "nw", coordinates, output[0], output[1]) + 1;
                            temp += score.searchLineCount("Elk", "se", coordinates, output[0], output[1]);
                        }

                        if (direction.equals("sw") || direction.equals("ne")) {
                            temp += score.searchLineCount("Elk", "sw", coordinates, output[0], output[1]) + 1;
                            temp += score.searchLineCount("Elk", "ne", coordinates, output[0], output[1]);
                        }


                        if (temp > maxCount) {
                            maxCount = temp;
                            maxDirection = direction;
                        }

                        output = score.adjacentAnimal("Elk", coordinates, i, j);
                    }

                    if (maxDirection.equals("w") || maxDirection.equals("e")) {
                        score.elkAHelper("w", coordinates, i, j);
                        score.elkAHelper("e", coordinates, i, j);
                    }

                    if (maxDirection.equals("nw") || maxDirection.equals("se")) {
                        score.elkAHelper("nw", coordinates, i, j);
                        score.elkAHelper("se", coordinates, i, j);
                    }

                    if (maxDirection.equals("sw") || maxDirection.equals("ne")) {
                        score.elkAHelper("sw", coordinates, i, j);
                        score.elkAHelper("ne", coordinates, i, j);
                    }
                }
            }
        }

        System.out.println("Bot placed an Elk");
        return true;
    }

    private boolean botHawk () {
        int[][] tempArray = new int[bot.getMaxMap()][bot.getMaxMap()];
        int hawkIndex = possibleAnimals.indexOf("hawk");
        int[] hawkCoords;

        do {
            hawkCoords = possibleAnimalLocations.get(hawkIndex);

            if (score.adjacentAnimal("Hawk", tempArray, hawkCoords[0], hawkCoords[1])[0] == -1) {
                tiles.placeAnimal(bot.getPlayerMap()[hawkCoords[0]][hawkCoords[1]], selectedAnimal);
                placedHawkCount++;
                System.out.println("Bot placed a Hawk");
                return true;
            }

            hawkIndex = getNext(possibleAnimals, hawkIndex);
        } while (hawkIndex != -1);
        return false;
    }

    private boolean botSalmon () {

        System.out.println("Bot placed a Salmon");
        return true;
    }

    // takes an arraylist and an index, returns the index of the next instance of the given item, or -1 if none found
    private int getNext (ArrayList arrayList, int index) {
        for (int i=index+1; i<arrayList.size(); i++) {
            if (arrayList.get(i).equals(arrayList.get(index))) {
                return i;
            }
        }
        return -1;
    }

    // returns an arraylist containing all possible animals on an empty habitat, else returns an arraylist containing an empty string
    private ArrayList<String> habitatContains (HabitatTiles habitat) {
        ArrayList<String> animals = new ArrayList<>();

        if (!habitat.isOccupied()) {
            animals.add(habitat.getCreature1());
            if (!habitat.getCreature2().equals("")) {
                animals.add(habitat.getCreature2());
                if (!habitat.getCreature3().equals("")) {
                    animals.add(habitat.getCreature3());
                }
            }
        } else {
            animals.add("");
        }

        return animals;
    }


    // checks each adjacent tile to a location to see if any of them have a given animal as a possible placement
    private boolean adjacentCheck (int[] coordinates, String animal) {
        ArrayList<String> animals;

        // west
        animals = habitatContains(bot.getPlayerMap()[coordinates[0]][coordinates[1]-1]);
        if (animals.contains(animal)) return true;

        // east
        animals = habitatContains(bot.getPlayerMap()[coordinates[0]+1][coordinates[1]+1]);
        if (animals.contains(animal)) return true;

        if (coordinates[0]%2 == 0) {
            // north west
            animals = habitatContains(bot.getPlayerMap()[coordinates[0]-1][coordinates[1]]);
            if (animals.contains(animal)) return true;

            // north east
            animals = habitatContains(bot.getPlayerMap()[coordinates[0]-1][coordinates[1]+1]);
            if (animals.contains(animal)) return true;

            // south west
            animals = habitatContains(bot.getPlayerMap()[coordinates[0]+1][coordinates[1]]);
            if (animals.contains(animal)) return true;

            // south east
            animals = habitatContains(bot.getPlayerMap()[coordinates[0]+1][coordinates[1]+1]);
            if (animals.contains(animal)) return true;
        }

        if (coordinates[0]%2 != 0) {
            // north west
            animals = habitatContains(bot.getPlayerMap()[coordinates[0]-1][coordinates[1]-1]);
            if (animals.contains(animal)) return true;

            // north east
            animals = habitatContains(bot.getPlayerMap()[coordinates[0]-1][coordinates[1]]);
            if (animals.contains(animal)) return true;

            // south west
            animals = habitatContains(bot.getPlayerMap()[coordinates[0]+1][coordinates[1]-1]);
            if (animals.contains(animal)) return true;

            // south east
            animals = habitatContains(bot.getPlayerMap()[coordinates[0]+1][coordinates[1]]);
            if (animals.contains(animal)) return true;
        }
        return false;
    }

    // Handles automatic and optional culling, similar to cull() in Turn.java
    private void botCull() {
        Object[] maxTiles = tiles.optionalCull(tiles.centralAnimals);

        while ((Integer)maxTiles[1] == 4) {
            botCullHelper(maxTiles);
            maxTiles = tiles.optionalCull(tiles.centralAnimals);
        }

        if ((Integer)maxTiles[1] == 3) {
            System.out.println("Bot has redrawn all " + maxTiles[0].toString() + " tokens");
            tiles.redrawAnimals((AnimalTiles) maxTiles[0]);
        }
    }

    // helper method for botCull
    public void botCullHelper(Object[] maxTiles) {
        System.out.println("All animal tiles the same, initiating automatic cull");
        tiles.redrawAnimals((AnimalTiles) maxTiles[0]);
    }

    // Searches through the bots' map, finding every possible animal which can be placed (animal on unoccupied habitat) and adds
    // those animals to the arraylist possibleAnimals, and adds the coordinates of that animal to the same "array" location in the
    // arraylist possibleAnimalLocations
    private void botLocationFetcher () {
        for (int i=1; i<bot.getMaxMap()-1; i++) {
            for (int j=1; j<bot.getMaxMap()-1; j++) {
                if (!bot.getPlayerMap()[i][j].isBlankHabitat(bot, i, j) && !bot.getPlayerMap()[i][j].isOccupied()) {
                    possibleAnimals.add(bot.getPlayerMap()[i][j].getCreature1());
                    possibleAnimalLocations.add(new int[]{i, j});

                    if (!bot.getPlayerMap()[i][j].getCreature2().equals("")) {
                        possibleAnimals.add(bot.getPlayerMap()[i][j].getCreature2());
                        possibleAnimalLocations.add(new int[]{i, j});

                        if (!bot.getPlayerMap()[i][j].getCreature3().equals("")) {
                            possibleAnimals.add(bot.getPlayerMap()[i][j].getCreature3());
                            possibleAnimalLocations.add(new int[]{i, j});
                        }
                    }
                } else if (!bot.getPlayerMap()[i][j].isIsolated(bot, i, j) && !bot.getPlayerMap()[i][j].isOccupied()) {
                    possibleTileLocations.add(new int[]{i, j});
                }
            }
        }
    }

    // fills the central animal arraylist with the names of the central animals
    private void botCentralAnimalsFetcher () {
        for (int i=0; i<tiles.centralAnimals.size(); i++) {
            centralAnimalAL.add(i, tiles.centralAnimals.get(i).toString());
        }
    }

    // fills the arraylist for potential animals in the central habitat tiles
    private void botCentralHabitatAnimalFetcher () {
        for (int i=0; i<tiles.centralHabitats.size(); i++) {
            centralHabitatAnimals.add(tiles.centralHabitats.get(i).getCreature1());
            centralHabitatAnimalsCoordinates.add(i);

            if (!tiles.centralHabitats.get(i).getCreature2().equals("")) {
                centralHabitatAnimals.add(tiles.centralHabitats.get(i).getCreature2());
                centralHabitatAnimalsCoordinates.add(i);

                if (!tiles.centralHabitats.get(i).getCreature3().equals("")) {
                    centralHabitatAnimals.add(tiles.centralHabitats.get(i).getCreature3());
                    centralHabitatAnimalsCoordinates.add(i);
                }
            }
        }
    }

    // method which places a habitat tile at a random valid location, to be used when a better placement cannot be found
    private void botDefaultHabPlacement () {
        int index = rand.nextInt(possibleTileLocations.size());
        int[] tempCoords = possibleTileLocations.get(index);

        botHabitatRotation(tempCoords[0], tempCoords[1]);
        bot.addHabitatToMap(selectedHabitat, tempCoords[0], tempCoords[1]);
    }

    // places an animal tile in any valid place, throws an error if there is none. For use when a scoring place cannot be found
    private void botDefaultAnimalPlacement () {
        for (int i=0; i<4; i++) {
            selectedAnimal = tiles.centralAnimals.get(i);
            selectedHabitat = tiles.centralHabitats.get(i);
            if (possibleAnimals.contains(selectedAnimal.toString())) {
                int[] coords = possibleAnimalLocations.get(possibleAnimals.indexOf(selectedAnimal.toString()));
                tiles.placeAnimal(bot.getPlayerMap()[coords[0]][coords[1]], selectedAnimal);
                return;
            }
        }
        throw new IllegalArgumentException("no animal could be placed by the bot");
    }

}
