import java.util.*;

public class Bot {
    Score score = new Score();
    Tiles tiles;
    Player bot;
    Random rand = new Random();

    // Arraylists with class scope
    private ArrayList<int[]> possibleTileLocations;

    // Arraylists containing every "placeable" animal and the matching coordinates of their locations respectively
    // Designed so that possibleAnimals.get(x) matches the location possibleAnimalLocations.get(x)
    private ArrayList<String> possibleAnimals;
    private ArrayList<int[]> possibleAnimalLocations;

    private AnimalTiles selectedAnimal;
    private HabitatTiles selectedHabitat;

    public void botTurn (Player bot, int playerCount, Tiles tiles) {
        this.tiles = tiles;
        this.bot = bot;
        this.score.setPlayer(bot);

        System.out.println("Bots' turn!");

        // sets up tiles on the first loop
        if (tiles.centralAnimals == null) {
            tiles.setupTiles(playerCount);
            tiles.setupCentralTiles();
        }

        botCull();

        // Initialising class scope arraylists
        possibleAnimals = new ArrayList<>();
        possibleAnimalLocations = new ArrayList<>();

        possibleTileLocations = new ArrayList<>();

        ArrayList<String> centralAnimalAL = new ArrayList<>();
        ArrayList<String> centralHabitatAnimals = new ArrayList<>();
        ArrayList<Integer> centralHabitatAnimalsCoordinates = new ArrayList<>();

        // methods to fill arraylists
        botCentralAnimalsFetcher(centralAnimalAL);
        botCentralHabitatAnimalFetcher(centralHabitatAnimals, centralHabitatAnimalsCoordinates);
        botLocationFetcher(possibleAnimals, possibleAnimalLocations, possibleTileLocations);


        // bot logic
        botAnimalPlacement(botTileChoice());


        System.out.println("Bots' map:");
        bot.printMap(bot);
        System.out.println("");

        tiles.drawCentralTiles();
    }

    // method to handle choice between different habitat and animal tiles from the central pool, returns an integer representing the choice of animal, and calls
    // the tile placement method. Currently just chooses the first tile pair
    private int botTileChoice () {
        int tileChoice = 0;
        int animalChoice = 0;

        // TODO: 05/04/2023 tile choice logic goes here

//        List<String> animals = new ArrayList<>(Arrays.asList("cat", "dog", "lion", "tiger", "monkey"));
//        Collections.shuffle(animals); // shuffle the list to make it random
//        String[] prioritizedAnimals = {"cat", "dog", "lion", "tiger", "monkey"}; // define the prioritized animals in order
//
//        for (String animal : prioritizedAnimals) {
//            if (animals.contains(animal)) {
//                int index = animals.indexOf(animal);
//                System.out.printf("%s found at index %d\n", animal, index);
//                break; // exit the loop as soon as the animal is found
//            }
//        }
        //    ArrayList<AnimalTiles> centralAnimals;

        int i = 0;
        int j = 0;
        String[] aniOrder = {"Hawk", "Bear", "Elk", "Salmon", "Fox"};
        while (!tiles.centralAnimals.get(i).toString().equals(aniOrder[j])) {
            for (j = 0; j < aniOrder.length; j++) {
                if (tiles.centralAnimals.get(i).toString().equals(aniOrder[j])) {
                    tileChoice = i;
                    animalChoice = i;
                    break;
                }
            }
            i++;
        }

        }

        for (int i = 0; i < tiles.centralAnimals.size(); i++) {
            for (int j = 0; j < tiles.centralAnimals.size(); j++) {

            }
        }

        for (int i = 0; i < tiles.centralAnimals.size(); i++) {
            if (tiles.centralAnimals.get(i).toString().equals("Hawk")) {
                tileChoice = i;
                animalChoice = i;
                break;
            } else if (tiles.centralAnimals.get(i).toString().equals("")) {
                tileChoice = i;
                animalChoice = i;
            }
        }


        selectedHabitat = tiles.centralHabitats.get(tileChoice);
        selectedAnimal = tiles.centralAnimals.get(animalChoice);

        botHabitatPlacement();

        // habitat tile removed from central tiles here
        tiles.centralHabitats.remove(tileChoice);

        return animalChoice;
    }

    // handles bot habitat placement logic and priority
    // TODO: 04/04/2023 places a tile in a random location for testing purposes, to be refined later
    private void botHabitatPlacement () {
        int index = rand.nextInt(possibleTileLocations.size());
        int[] tempCoords = possibleTileLocations.get(index);

        bot.addHabitatToMap(selectedHabitat, tempCoords[0], tempCoords[1]);
        possibleTileLocations.remove(index);
    }

    // helper method which calls one of the animal specific placement methods
    private void botAnimalPlacement (int index) {
        switch (selectedAnimal.toString()) {
            case "Bear": botBear(); break;
            case "Fox": botFox(); break;
            case "Elk": botElk(); break;
            case "Hawk": botHawk(); break;
            case "Salmon": botSalmon(); break;

            default: throw new IllegalArgumentException("Invalid animal passed in botAnimalPlacement: " + selectedAnimal);
        }
    }

    private void botBear () {
        System.out.println("bot bear placed");
    }

    private void botFox () {
        System.out.println("bot fox placed");
    }

    private void botElk () {
        System.out.println("bot elk placed");
    }

    private void botHawk () {
        int[][] tempArray = new int[bot.getMaxMap()][bot.getMaxMap()];
        int hawkIndex = possibleAnimals.indexOf("hawk");
        int[] hawkCoords;

        do {
            hawkCoords = possibleAnimalLocations.get(hawkIndex);

            if (score.adjacentAnimal("Hawk", tempArray, hawkCoords[0], hawkCoords[1])[0] == -1) {
                tiles.placeAnimal(bot.getPlayerMap()[hawkCoords[0]][hawkCoords[1]], selectedAnimal);
                break;
            }

            hawkIndex = getNext(possibleAnimals, hawkIndex);
        } while (hawkIndex != -1);
    }

    private void botSalmon () {
        System.out.println("bot salmon placed");
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
}
