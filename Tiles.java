import java.util.ArrayList;
import java.util.Random;

public class Tiles {
    public void setupTiles() {
        generateAnimals();
        shuffleAnimals();

    }
    ArrayList<AnimalTiles> animalAL = new ArrayList<>();

    public ArrayList<AnimalTiles> getAnimalAL() {
        return animalAL;
    }

    private void generateAnimals() {
        for (int i=0; i<20; i++) {
            getAnimalAL().add(new AnimalTiles("Hawk"));
            getAnimalAL().add(new AnimalTiles("Bear"));
            getAnimalAL().add(new AnimalTiles("Elk"));
            getAnimalAL().add(new AnimalTiles("Salmon"));
            getAnimalAL().add(new AnimalTiles("Fox"));
        }
    }

    private void shuffleAnimals() {
        Random rand = new Random();
        int tempInt;

        for (int i=0; i<80; i++) {
            tempInt = rand.nextInt(80);
            AnimalTiles temp = getAnimalAL().get(i);
            getAnimalAL().get(i).setAnimal(getAnimalAL().get(tempInt).getAnimal());
            getAnimalAL().get(tempInt).setAnimal(temp.getAnimal());
        }
    }

}
