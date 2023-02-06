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

    public void displayCentralTiles() {
        for (int i=0; i<4; i++) {
            System.out.println(centralHabitats.get(i));
            System.out.println(centralAnimals.get(i));
        }
    }
}
