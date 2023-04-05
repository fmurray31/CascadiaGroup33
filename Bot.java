import java.util.ArrayList;
import java.util.Random;

public class Bot {
    Score score = new Score();
    Tiles tiles;
    Player bot;
    Random rand = new Random();


    public void botTurn (Player bot, int playerCount, Tiles tiles) {
        this.tiles = tiles;
        this.bot = bot;
        System.out.println("Bots' turn!");

        // sets up tiles on the first loop
        if (tiles.centralAnimals == null) {
            tiles.setupTiles(playerCount);
            tiles.setupCentralTiles();
        }

        botCull();

        // Arraylists containing every "placeable" animal and the matching coordinates of their locations respectively
        // Designed so that possibleAnimals.get(x) matches the location possibleAnimalLocations.get(x)
        ArrayList<String> possibleAnimals = new ArrayList<>();
        ArrayList<int[]> possibleAnimalLocations = new ArrayList<>();

        ArrayList<String> centralAnimalAL = new ArrayList<>();
        ArrayList<String> centralHabitatAnimals = new ArrayList<>();
        ArrayList<Integer> centralHabitatAnimalsCoordinates = new ArrayList<>();

        ArrayList<int[]> possibleTileLocations = new ArrayList<>();

        botCentralAnimalsFetcher(centralAnimalAL);
        botCentralHabitatAnimalFetcher(centralHabitatAnimals, centralHabitatAnimalsCoordinates);
        botLocationFetcher(possibleAnimals, possibleAnimalLocations, possibleTileLocations);

        botHabitatPlacement(tiles.centralHabitats.get(0), possibleTileLocations);
        tiles.centralHabitats.remove(0);

        System.out.println("Bots' new score is: " + "score goes here");
        System.out.println("Bots' map:");
        bot.printMap(bot);
        System.out.println("");

        tiles.drawCentralTiles();
    }

    // handles bot habitat placement logic and priority
    // TODO: 04/04/2023 places a tile in a random location for testing purposes, to be refined later
    private void botHabitatPlacement (HabitatTiles habitatTile, ArrayList<int[]> possibleTileLocations) {
        ArrayList<String> habAnimals = habitatContains(habitatTile);
        int priority = -1;

        int index = rand.nextInt(possibleTileLocations.size());
        int[] tempCoords = possibleTileLocations.get(index);

        bot.addHabitatToMap(habitatTile, tempCoords[0], tempCoords[1]);
        possibleTileLocations.remove(index);
    }

    private int getNext (ArrayList arrayList, int index) {
        for (int i=index; i<arrayList.size(); i++) {
            if (arrayList.get(i).equals(arrayList.get(index))) {
                return i;
            }
        }
        return -1;
    }

    private void botHawkPlacement () {

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
                    if (!bot.getPlayerMap()[i][j].isBlankHabitat(bot, i, j)) {
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
                    } else if (!bot.getPlayerMap()[i][j].isIsolated(bot, i, j)) {
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
