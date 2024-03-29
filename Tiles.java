/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import java.util.ArrayList;

public class Tiles {
    HabitatTiles habitatTiles = new HabitatTiles("","","","","","","","","");
    AnimalTiles animalTiles = new AnimalTiles("");

    ArrayList<HabitatTiles> centralHabitats;
    ArrayList<AnimalTiles> centralAnimals;


    // sets up arraylists of both animal and habitat tiles
    public void setupTiles() {
        animalTiles.animalSetup();
        habitatTiles.habitatSetup();
    }

    public ArrayList<AnimalTiles> getAnimalTiles() {
        return animalTiles.getAnimalAL();
    }

    // removes up to four habitats and animals from their respective arraylists and adds them to separate arraylists representing the central tiles
    public void setupCentralTiles() {
        centralHabitats = new ArrayList<>();
        int i = 0;
        while (centralHabitats.size() < 4){
            centralHabitats.add(habitatTiles.getHabitatArray().get(i));
            habitatTiles.getHabitatArray().remove(i);
            i++;
        }

        centralAnimals = new ArrayList<>();
        i=0;
        while (centralAnimals.size() < 4){
            centralAnimals.add(animalTiles.getAnimalAL().get(i));
            animalTiles.getAnimalAL().remove(i);
            i++;
        }
    }

    // draws more habitat and animal tiles until both arraylists are full
    public void drawCentralTiles() {
        int i = 0;
        while (centralHabitats.size() < 4){
            centralHabitats.add(habitatTiles.getHabitatArray().get(i));
            habitatTiles.getHabitatArray().remove(i);
            i++;
        }

        i=0;
        while (centralAnimals.size() < 4){
            centralAnimals.add(animalTiles.getAnimalAL().get(i));
            animalTiles.getAnimalAL().remove(i);
            i++;
        }
    }

    // Takes an arraylist of animal tiles and returns an array of two objects, the most common tile in the list and its count
    public Object[] optionalCull (ArrayList<AnimalTiles> al) {
        int maxCount = 0;
        int tempCount;
        AnimalTiles max = al.get(0);
        AnimalTiles temp;

       for (int i=0; i<al.size(); i++) {
           temp = al.get(i);
           tempCount = 0;

           for (int j=i; j<al.size(); j++) {
               if (al.get(j).equals(temp)) {
                   tempCount++;
               }
           }

           if (tempCount>maxCount) {
               max = temp;
               maxCount = tempCount;
           }
       }

        Object output[] = new Object[2];
        output[0] = max;
        output[1] = maxCount;
        return output;
    }

    // removes an animal tile from the central tiles, adds it to the total pool and shuffles the pool before drawing a new one
    public void redrawAnimals (AnimalTiles animal) {
        for (int i=0; i<4; i++) {
            if (centralAnimals.get(i).equals(animal)) {
                animalTiles.animalAL.add(centralAnimals.get(i));
                animalTiles.shuffleAnimals();
                centralAnimals.remove(i);
                centralAnimals.add(i, animalTiles.animalAL.get(animalTiles.animalAL.size()-1));
                animalTiles.getAnimalAL().remove(getAnimalTiles().size()-1);
            }
        }
    }

    // displays the central four habitat and animal tiles
    public void displayCentralTiles() {
        boolean first = true;
        System.out.println("\nShared Tiles:\n");
        System.out.println("[1]\t\t\t\t\t\t\t\t [2]\t\t\t\t\t\t\t  [3]\t\t\t\t\t\t\t   [4]");

        for (int j=0; j<=4; j++) {
            for (int i=0; i<4; i++) {
                switch (j) {
                    case 0:
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getNorthWest()));
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getNorthWest()));
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getNorthEast()));
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getNorthEast())+ " ");
                        break;

                    case 1:
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getWest()));
                        System.out.print(habitatTiles.animalToAscii(centralHabitats.get(i).getCreature1()));
                        System.out.print(habitatTiles.animalToAscii(centralHabitats.get(i).getCreature2()));
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getEast())+ " ");
                        break;

                    case 2:
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getWest()));
                        System.out.print(habitatTiles.animalToAscii(centralHabitats.get(i).getCreature3()));
                        System.out.print("        ");
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getEast()) + " ");
                        break;

                    case 3:
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getSouthWest()));
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getSouthWest()));
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getSouthEast()));
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getSouthEast())+ " ");
                        break;
                }
                if (j==4 && first) {
                    System.out.println("");
                    first = false;
                }
                if (j==4) {
                    System.out.print(habitatTiles.animalToAscii(centralAnimals.get(i).toString()));
                    System.out.print("\t\t\t\t\t\t   ");
                }
            }
            System.out.println("");
        }
    }

    // returns true if a habitat has a spot for an animal
    public boolean suitableForAnimal(HabitatTiles habTile, AnimalTiles aniTile) {
        return habTile.getCreature1().equals(aniTile.toString().toLowerCase()) || habTile.getCreature2().equals(aniTile.toString().toLowerCase()) || habTile.getCreature3().equals(aniTile.toString().toLowerCase());
    }

    // places an animal on a habitat
    public void placeAnimal(HabitatTiles habTile, AnimalTiles aniTile) {
        habTile.setCreature1(aniTile.toString());
        habTile.setCreature2("");
        habTile.setCreature3("");
        habTile.setOccupied();
    }

    // checks if there are any blank spots on the map for a certain animal
    public boolean freeAnimalSpace(Player player, AnimalTiles animal) {
        HabitatTiles tempHab;
        for (int i=0; i< player.getMaxMap(); i++) {
            for (int j=0; j< player.getMaxMap(); j++) {
                tempHab = player.getPlayerMap()[i][j];
                if (!tempHab.isOccupied() && suitableForAnimal(tempHab, animal)) {
                    return true;
                }
            }
        }
        return false;
    }
}
