import java.util.*;

public class Bot {
    Score score = new Score();
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

    // class scope variables to hold the chosen animal and habitat tiles respectively
    private AnimalTiles selectedAnimal;
    private HabitatTiles selectedHabitat;

    public void botTurn (Player bot, Tiles tiles) {
        this.tiles = tiles;
        this.bot = bot;
        this.score.setPlayer(bot);

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

        ArrayList<String> centralHabitatAnimals = new ArrayList<>();
        ArrayList<Integer> centralHabitatAnimalsCoordinates = new ArrayList<>();

        // methods to fill arraylists
        botCentralAnimalsFetcher(centralAnimalAL);
        botCentralHabitatAnimalFetcher(centralHabitatAnimals, centralHabitatAnimalsCoordinates);
        botLocationFetcher(possibleAnimals, possibleAnimalLocations, possibleTileLocations);


        // bot logic
        //botAnimalPlacement(botTileChoice());
        botTileChoice();



        System.out.println("Bots' map:");
        bot.printMap(bot);
        System.out.println("");

        tiles.drawCentralTiles();
    }

    // method to handle choice between different habitat and animal tiles from the central pool, returns an integer representing the choice of animal, and calls
    // the tile placement method. Currently just chooses the first tile pair
    private void botTileChoice () {
        int tileChoice = 0;
        int animalChoice = 0;

        String[] aniOrder = {"Hawk", "Bear", "Elk", "Salmon", "Fox"};
        for (int i = 0; i < aniOrder.length; i++) {
            for (int j = 0; j < tiles.centralAnimals.size(); j++) {
                if (tiles.centralAnimals.get(j).toString().equals(aniOrder[i])) {
                    tileChoice = j;
                    animalChoice = j;
                    i += 5;
                    break;
                }
            }
        }

        selectedHabitat = tiles.centralHabitats.get(tileChoice);
        selectedAnimal = tiles.centralAnimals.get(animalChoice);

        boolean placed = false;

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
                case "Hawk": output = botHawk();
                case "Bear": output = botBear();
                case "Elk": output = botElk();
                case "Salmon": output = botSalmon();
                case "Fox": output = botFox();
            }
        }
        return output;
    }

    // attempts to place a tile optimally in the below order, and if it fails places it randomly
    private void botHabitatPlacement () {
        ArrayList<String> possibleAnimals = habitatContains(selectedHabitat);

        boolean placed = false;

        if (possibleAnimals.contains("Bear")) {
            for (int[] tileCoord : possibleTileLocations) {
                if (adjacentCheck(tileCoord, "Bear")) {
                    bot.addHabitatToMap(selectedHabitat, tileCoord[0], tileCoord[1]);
                }
            }
        }
        if (possibleAnimals.contains("Hawk") && !placed) {

        }
        if (possibleAnimals.contains("Elk") && !placed) {

        }
        if (possibleAnimals.contains("Salmon") && !placed) {

        }
        if (possibleAnimals.contains("Fox") && !placed) {

        }

        // if tile still hasn't been placed, place it randomly
        if (!placed) botDefaultHabPlacement();
    }

    // helper method which calls one of the animal specific placement methods
    // deprecated
//    private void botAnimalPlacement (int index) {
//        switch (selectedAnimal.toString()) {
//            case "Bear": botBear(); break;
//            case "Fox": botFox(); break;
//            case "Elk": botElk(); break;
//            case "Hawk": botHawk(); break;
//            case "Salmon": botSalmon(); break;
//
//            default: throw new IllegalArgumentException("Invalid animal passed in botAnimalPlacement: " + selectedAnimal);
//        }
//    }

    // methods which handle the placement of each of the animal types, in a valid and point scoring manner
    private boolean botBear () {
        System.out.println("bot bear placed");
        return true;
    }

    private boolean botFox () {
        System.out.println("bot fox placed");
        return true;
    }

    private boolean botElk () {
        System.out.println("bot elk placed");
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
                return  true;
            }

            hawkIndex = getNext(possibleAnimals, hawkIndex);
        } while (hawkIndex != -1);
        return false;
    }

    private boolean botSalmon () {
        System.out.println("bot salmon placed");
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
    private void botLocationFetcher (ArrayList<String> possibleAnimals, ArrayList<int[]> possibleAnimalLocations, ArrayList<int[]> possibleHabitatLocations) {
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
                        possibleHabitatLocations.add(new int[]{i, j});
                    }
            }
        }
    }

    // fills the central animal arraylist with the names of the central animals
    private void botCentralAnimalsFetcher (ArrayList<String> centralAnimalAL) {
        for (int i=0; i<tiles.centralAnimals.size(); i++) {
            centralAnimalAL.add(i, tiles.centralAnimals.get(i).toString());
        }
    }

    private void botCentralHabitatAnimalFetcher (ArrayList<String> centralHabitatAnimals, ArrayList<Integer> centralHabitatAnimalsCoordinates) {
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

        bot.addHabitatToMap(selectedHabitat, tempCoords[0], tempCoords[1]);
        possibleTileLocations.remove(index);
    }

    // places an animal tile in any valid place, throws an error if there is none. For use when a scoring place cannot be found
    private void botDefaultAnimalPlacement () {
        if (possibleAnimals.contains(selectedAnimal.toString())) {
            int[] coords = possibleAnimalLocations.get(possibleAnimals.indexOf(selectedAnimal.toString()));
            tiles.placeAnimal(bot.getPlayerMap()[coords[0]][coords[1]], selectedAnimal);
        } else throw new IllegalArgumentException("animal tile: " + selectedAnimal + " could not be placed by the bot");
    }

}
