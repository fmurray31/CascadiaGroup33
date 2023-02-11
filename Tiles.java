import javax.swing.*;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Random;

public class Tiles {
    HabitatTiles habitatTiles = new HabitatTiles("","","","","","","","","");
    AnimalTiles animalTiles = new AnimalTiles("");

    ArrayList<HabitatTiles> centralHabitats;
    ArrayList<AnimalTiles> centralAnimals;


    public void setupTiles() {
        animalTiles.animalSetup();
        habitatTiles.habitatSetup();
    }

    public ArrayList<HabitatTiles> getHabitatTiles() {
        return habitatTiles.getHabitatArray();
    }

    public ArrayList<AnimalTiles> getAnimalTiles() {
        return animalTiles.getAnimalAL();
    }

    // removes up to four habitats and animals from their respective arraylists and adds them to separate arraylists representing the central tiles
    public void drawCentralTiles() {
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

    // displays the central four habitat and animal tiles
    public void displayCentralTiles() {
        boolean first = true;
        System.out.println("\nShared Tiles:");
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
                        System.out.print(habitatTiles.terrainToAscii(centralHabitats.get(i).getWest()) + " ");
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
}
